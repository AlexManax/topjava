package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAOservice implements DAOserviceImpl{
    private static List<Meal> mealToList = null;

    public List<MealTo> getAll() {
        return MealsUtil.getAllWithExcess(initList());
    }

    public void deleteMeal(int id) {
        initList();
        mealToList.remove(id);
    }

    public void replaceMeal(int id, Meal meal){
        initList().remove(id);
        initList().add(id, meal);
    }

    public void addMeal(Meal meal){
        initList().add(meal);
    }

    public List<Meal> initList() {

        if (mealToList == null) {
            mealToList = Collections.synchronizedList(new ArrayList<Meal>());
        mealToList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500));
        mealToList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000));
        mealToList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500));
        mealToList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000));
        mealToList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500));
        mealToList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510)
        );}
        return mealToList;
    }
}