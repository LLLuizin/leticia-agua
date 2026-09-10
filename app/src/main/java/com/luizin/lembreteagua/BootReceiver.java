package com.luizin.lembreteagua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
 public void onReceive(Context c, Intent i) {
  String action = i != null ? i.getAction() : null;
  boolean isExpectedAction = Intent.ACTION_BOOT_COMPLETED.equals(action)
      || Intent.ACTION_MY_PACKAGE_REPLACED.equals(action);
  if (isExpectedAction && new ReminderStateStore(c).areRemindersEnabled()) {
   ReminderScheduler.scheduleAll(c);
  }
 }
}
