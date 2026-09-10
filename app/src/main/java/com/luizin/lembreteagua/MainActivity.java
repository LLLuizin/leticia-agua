package com.luizin.lembreteagua;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
 private TextView status;
 private TextView greeting;
 private Button activate;
 private ReminderStateStore stateStore;
 private boolean active;

 public void onCreate(Bundle b) {
  super.onCreate(b);
  setContentView(R.layout.activity_main);
  stateStore = new ReminderStateStore(this);
  status = findViewById(R.id.status);
  greeting = findViewById(R.id.greeting);
  activate = findViewById(R.id.btnActivate);
  Button disable = findViewById(R.id.btnDisable);
  TextView settings = findViewById(R.id.settingsLink);
  createChannel();

  activate.setOnClickListener(v -> {
   requestNotifications();
   if (requestExactIfNeeded()) {
    return;
   }
   ReminderScheduler.scheduleAll(this);
   active = ReminderScheduler.hasAnyScheduled(this);
   stateStore.setRemindersEnabled(active);
   update();
  });

  disable.setOnClickListener(v -> {
   ReminderScheduler.cancelAll(this);
   stateStore.setRemindersEnabled(false);
   active = false;
   update();
  });

  settings.setOnClickListener(v -> requestExactIfNeeded());
 }

 protected void onResume() {
  super.onResume();
  greeting.setText(getString(R.string.greeting_format, getString(GreetingHelper.periodGreetingRes()), getString(R.string.user_name)));
  active = stateStore.areRemindersEnabled() && ReminderScheduler.hasAnyScheduled(this);
  update();
 }

 private void update() {
  if (active) {
   status.setText(getString(R.string.status_active_format, ReminderScheduler.scheduleWindowLabel()));
   status.setTextColor(Color.rgb(20, 104, 170));
   activate.setText(R.string.activate_button_active);
  } else {
   status.setText(getString(R.string.status_inactive_format, ReminderScheduler.reminderCount()));
   status.setTextColor(Color.rgb(109, 140, 166));
   activate.setText(R.string.activate_button_inactive);
  }
 }

 private void requestNotifications() {
  if (Build.VERSION.SDK_INT >= 33
      && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
   requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 20);
  }
 }

 private boolean requestExactIfNeeded() {
  if (Build.VERSION.SDK_INT >= 31) {
   AlarmManager a = (AlarmManager) getSystemService(ALARM_SERVICE);
   if (a != null && !a.canScheduleExactAlarms()) {
    try {
     startActivity(new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, Uri.parse("package:" + getPackageName())));
     return true;
    } catch (Exception ignored) {
    }
   }
  }
  return false;
 }

 private void createChannel() {
  if (Build.VERSION.SDK_INT >= 26) {
   NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
   if (manager == null) {
    return;
   }
   NotificationChannel c = new NotificationChannel(AlarmReceiver.CHANNEL_ID, "Lembretes de água", NotificationManager.IMPORTANCE_HIGH);
   c.setDescription("Lembretes de hidratação");
   c.enableVibration(true);
   manager.createNotificationChannel(c);
  }
 }
}
