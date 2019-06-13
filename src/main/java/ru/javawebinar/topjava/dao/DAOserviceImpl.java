package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface DAOserviceImpl {

    List<MealTo> getAll();

    void deleteMeal(int id);

    void replaceMeal(int id, Meal meal);

    void addMeal(Meal meal);
}
