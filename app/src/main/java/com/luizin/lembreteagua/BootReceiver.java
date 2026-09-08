package com.luizin.lembreteagua; import android.content.*; public class BootReceiver extends BroadcastReceiver { public void onReceive(Context c,Intent i){ReminderScheduler.scheduleAll(c);} }
