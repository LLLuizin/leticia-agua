package com.luizin.lembreteagua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
 public void onReceive(Context c, Intent i) {
  if (new ReminderStateStore(c).areRemindersEnabled()) {
   ReminderScheduler.scheduleAll(c);
  }
 }
}
