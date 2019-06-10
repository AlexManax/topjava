package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        System.out.println(request.getContextPath());
        try {
            System.out.println(request.getPathInfo().replace("/", ""));

            if (!request.getPathInfo().replace("/", "").equals(""))
                MealsUtil.mealList.remove(Integer.parseInt(request.getPathInfo().replace("/", "")));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        System.out.println(MealsUtil.getList());
        request.setAttribute("mealList", MealsUtil.getList());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }


}
