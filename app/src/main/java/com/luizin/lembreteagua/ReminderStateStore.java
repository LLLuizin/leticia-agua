package com.luizin.lembreteagua;

import android.content.Context;
import android.content.SharedPreferences;

public final class ReminderStateStore {
 private static final String FILE_NAME = "water_reminder_state";
 private static final String KEY_REMINDERS_ENABLED = "reminders_enabled";

 private final SharedPreferences preferences;

 public ReminderStateStore(Context context) {
  this.preferences = context.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
 }

 public void setRemindersEnabled(boolean enabled) {
  preferences.edit().putBoolean(KEY_REMINDERS_ENABLED, enabled).apply();
 }

 public boolean areRemindersEnabled() {
  return preferences.getBoolean(KEY_REMINDERS_ENABLED, false);
 }
}
