package ru.philosophyit.internship.javabase.time;

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

    public void getMonth() {
        switch (CURRENT_MONTH) {
            case 1 -> System.out.println(Month.JANUARY.getTitle());
            case 2 -> System.out.println(Month.FEBRUARY.getTitle());
            case 3 -> System.out.println(Month.MARCH.getTitle());
            case 4 -> System.out.println(Month.APRIL.getTitle());
            case 5 -> System.out.println(Month.MAY.getTitle());
            case 6 -> System.out.println(Month.JUNE.getTitle());
            case 7 -> System.out.println(Month.JULY.getTitle());
            case 8 -> System.out.println(Month.AUGUST.getTitle());
            case 9 -> System.out.println(Month.SEPTEMBER.getTitle());
            case 10 -> System.out.println(Month.OCTOBER.getTitle());
            case 11 -> System.out.println(Month.NOVEMBER.getTitle());
            case 12 -> System.out.println(Month.DECEMBER.getTitle());
        }
    }

    // начало след месяца при необходимости
    public void nextMonthDays() {
        int restDayWeek = 7 - (presentDay.getDayOfWeek().getValue());
        for (int i = 0; i < restDayWeek; i++) {
            System.out.print(" " + presentDay.plusDays(i).getDayOfMonth() + " ");
        }
    }

    // вывод дней с предыдущего месяца
    public void daysOfLastMonth() {
        if (presentDay.getDayOfWeek().getValue() != 1) {
            int dayOfTheWeek = presentDay.getDayOfWeek().getValue();
            LocalDateTime restDayMonth = presentDay.minusDays(dayOfTheWeek);
            for (int i = 0; i < dayOfTheWeek; i++) {
                System.out.print(restDayMonth.plusDays(i).getDayOfMonth() + " ");
            }
        }
    }

    //    основной вывод дней месяца текущего
    public void displayDaysCurrentMonth() {
        while (CURRENT_MONTH == presentDay.getMonth().getValue()) {
            int week = presentDay.getDayOfWeek().getValue();
            // if day week
            if (week == 7) {
                System.out.println();
            }
            if (presentDay.getDayOfMonth() < 10) {
                System.out.print(" " + presentDay.getDayOfMonth() + " ");
            } else {
                System.out.print(presentDay.getDayOfMonth() + " ");
            }
            presentDay = presentDay.plusDays(1L);
        }
    }

    public void run() {

        getMonth();

        System.out.println(DAY_WEEK);

        daysOfLastMonth();

        displayDaysCurrentMonth();

        nextMonthDays();

    }


}
