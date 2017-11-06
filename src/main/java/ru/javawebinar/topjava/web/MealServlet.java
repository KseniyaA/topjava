package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final List<MealWithExceed> mealList = Arrays.asList(
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 10, 0, 0), "Завтрак", 500, true),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 13, 0, 0), "Обед", 500, false),
            new MealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 19, 0, 0), "Ужин", 700, true)
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        request.setAttribute("mealList", mealList);
        //response.sendRedirect("meals.jsp"); // в этом случае теряются атрибуты
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
