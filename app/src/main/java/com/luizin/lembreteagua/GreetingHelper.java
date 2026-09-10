package com.luizin.lembreteagua;

import java.util.Calendar;

public final class GreetingHelper {
 private GreetingHelper() {}

 public static int periodGreetingRes() {
  int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
  if (hour < 12) {
   return R.string.period_morning;
  }
  if (hour < 18) {
   return R.string.period_afternoon;
  }
  return R.string.period_night;
 }
}
