package ru.philosophyit.internship.javabase.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CalendarBuilder {

    private static final String DAY_WEEK = "пн вт ср чт пт сб вс";

    private static final int CURRENT_MONTH;
    private static LocalDateTime presentDay;


    static {

        CURRENT_MONTH = LocalDateTime.now().getMonthValue();
        // first day month
        int currentDay = LocalDateTime.now().getDayOfMonth();
        presentDay = LocalDateTime.now().minusDays(currentDay - 1);
    }

    // начало след месяца при необходимости
    public void nextMonthDays() {
        if ((presentDay.getDayOfWeek().getValue()) != 1 ) {
            int restDayWeek =presentDay.getDayOfWeek().getValue() - 1;
            for (int i = 0; restDayWeek < 7; i++) {
                System.out.print(" " + presentDay.plusDays(i).getDayOfMonth() + " ");
                restDayWeek++;
            }
        }
    }

    // вывод дней с предыдущего месяца
    public void daysOfLastMonth() {
        if (presentDay.getDayOfWeek().getValue() != 1) {
            int dayOfTheWeek = presentDay.getDayOfWeek().getValue();
            LocalDateTime restDayMonth = presentDay.minusDays(dayOfTheWeek);
            for (int i = 1; i < dayOfTheWeek; i++) {
                System.out.print(restDayMonth.plusDays(i).getDayOfMonth() + " ");
            }
        }
    }

    //    основной вывод дней месяца текущего
    public void displayDaysCurrentMonth() {
        while (CURRENT_MONTH == presentDay.getMonth().getValue()) {
            int week = presentDay.getDayOfWeek().getValue();
            String formatText;
            if (presentDay.getDayOfMonth() < 10) {
                formatText = " %s ";
            } else {
                formatText = "%s ";
            }
            System.out.printf(formatText, presentDay.getDayOfMonth());

            if (week == 7) {
                System.out.println();
            }
            presentDay = presentDay.plusDays(1L);
        }
    }

    public void run() {

        System.out.println(Month.valueOf(LocalDate.now().getMonth().name()).getTitle());

        System.out.println(DAY_WEEK);

        daysOfLastMonth();

        displayDaysCurrentMonth();

        nextMonthDays();

    }


}
