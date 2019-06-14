package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DAOservice;
import ru.javawebinar.topjava.dao.DAOserviceImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private DAOserviceImpl daOservice;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (daOservice==null) daOservice = new DAOservice();
        log.debug("redirect to meals");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy HH'h'mm");

        String action = request.getParameter("action");
        if (action == null) {
            System.out.println(action);
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId")) - 1;
            daOservice.deleteMeal(mealId);
        } else if (action.equalsIgnoreCase("edit")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            System.out.println("mealId : " + mealId);
            System.out.println("edit");
        } else if (action.equalsIgnoreCase("save")) {
            System.out.println("save");
            daOservice.replaceMeal(Integer.parseInt(request.getParameter("mealId"))-1,
                    new Meal(LocalDateTime.parse(request.getParameter("date"),formatter),
                            request.getParameter("description"), Integer.parseInt(request.getParameter("calories"))
                    ));


        } else if (action.equalsIgnoreCase("add")) {
            daOservice.addMeal(new Meal(LocalDateTime.parse(request.getParameter("date"),formatter),
                    request.getParameter("description"), Integer.parseInt(request.getParameter("calories"))));
        } else {
            System.out.println("Something else");
        }


//        System.out.println(MealsUtil.getList());
        request.setAttribute("mealList", daOservice.getAll());
        request.getRequestDispatcher("/meals.jsp").

                forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealList", daOservice.getAll());

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
