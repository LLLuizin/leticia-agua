package com.luizin.lembreteagua;

import java.util.Calendar;

public final class GreetingHelper {
 private GreetingHelper() {}

 public static String periodGreeting() {
  int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
  if (hour < 12) {
   return "Bom dia";
  }
  if (hour < 18) {
   return "Boa tarde";
  }
  return "Boa noite";
 }
}
