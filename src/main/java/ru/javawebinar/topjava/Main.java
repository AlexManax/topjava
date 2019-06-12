package ru.javawebinar.topjava;

import ru.javawebinar.topjava.dao.DAOservice;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        DAOservice daOservice = new DAOservice();
        System.out.println(daOservice.getAll());
        daOservice.deleteMeal(1);
        daOservice.deleteMeal(1);
        daOservice.deleteMeal(1);
        System.out.println("deleted");
        System.out.println(daOservice.getAll());
        System.out.println();
        System.out.println();
        daOservice.deleteMeal(1);
        System.out.println("deleted");
        System.out.println(daOservice.getAll());
    }
}
