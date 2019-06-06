package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000)
        );
        System.out.println(
                getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)
        );
        System.out.println(
                getFilteredWithExceededOptional2v1(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)
        );
        System.out.println(
                getFilteredWithExceededOptional2v2(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)
        );
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> groupedMealList = new HashMap<>();
        if (mealList == null || mealList.size() == 0) return null;
        for (UserMeal userMealElement : mealList) {
            groupedMealList.merge(userMealElement.getDateTime().toLocalDate(), userMealElement.getCalories(), Integer::sum);
        }
        List<UserMealWithExceed> mealListWithExceeded = new ArrayList<>();
        for (UserMeal userMealElement : mealList) {
            if (TimeUtil.isBetween(userMealElement.getDateTime().toLocalTime(), startTime, endTime))
                mealListWithExceeded.add(convertUMtoUMWE(userMealElement, groupedMealList.get(userMealElement.getDateTime().toLocalDate()) > caloriesPerDay));
        }
        return mealListWithExceeded;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededOptional2v1(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> mealListWithExceeded = new ArrayList<>();
        List<UserMeal> tempMealList = new ArrayList<>();
        Map<LocalDate, Integer> groupedMealList = new HashMap<>();
        int counter=0, listSize=mealList.size();
        for (UserMeal userMealElement : mealList) {
            groupedMealList.merge(userMealElement.getDateTime().toLocalDate(), userMealElement.getCalories(), Integer::sum);
            if (TimeUtil.isBetween(userMealElement.getDateTime().toLocalTime(), startTime, endTime)) {
                tempMealList.add(userMealElement);
            }
            if (++counter==listSize)
                for (UserMeal userMeal:tempMealList) {
                    mealListWithExceeded.add(convertUMtoUMWE(userMeal, groupedMealList.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
                }
        }
        return mealListWithExceeded;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededOptional2v2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dateSumCaloriesMap = mealList.stream()                                                  //Creates map with dates and summ of calories per the day.
                .collect(Collectors.groupingBy(x -> x.getDateTime().toLocalDate(), (Collectors.summingInt(UserMeal::getCalories))
                ));

        return mealList.stream()                                                                                        //Creates UMWE from UserMeal using reference to dateSumCaloriesMap
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(x -> convertUMtoUMWE(x,dateSumCaloriesMap.get(x.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }


    public static UserMealWithExceed convertUMtoUMWE(UserMeal meal, boolean exceed) {
        return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }


}
