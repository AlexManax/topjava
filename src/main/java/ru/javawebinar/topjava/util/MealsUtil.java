package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class MealsUtil {

    public static List<Meal> mealList = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510)
    );

    public static void main(String[] args) {



//        getFilteredWithExcess(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
//        System.out.println(getList());
    }

    public static List<MealTo> getList() {
        return getFilteredWithExcess(mealList, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
    }

    public static List<MealTo> getFilteredWithExcess(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Collection<List<Meal>> list = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate)).values();

        return list.stream().flatMap(dayMeals -> {
            boolean excess = dayMeals.stream().mapToInt(Meal::getCalories).sum() > caloriesPerDay;
            return dayMeals.stream().filter(meal ->
                    TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                    .map(meal -> createWithExcess(meal, excess));
        }).collect(toList());
    }

    private static MealTo createWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}