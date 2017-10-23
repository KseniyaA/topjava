package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        System.out.println("TODO return filtered list with correctly exceeded field");
        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, List<UserMeal>> collect = mealList.stream()
                .collect(
                        Collectors.groupingBy(p ->
                                LocalDate.of(
                                        p.getDateTime().getYear(),
                                        p.getDateTime().getMonth(),
                                        p.getDateTime().getDayOfMonth())
                        ))
                ;
        for (Map.Entry<LocalDate, List<UserMeal>> entry: collect.entrySet()) {
            List<UserMeal> value = entry.getValue();
            int sum = value.stream()
                    .mapToInt(s -> s.getCalories())
                    .sum();
            if (sum > caloriesPerDay) {
                value.stream()
                        .filter(s->s.getDateTime().toLocalTime()
                                .isAfter(startTime)
                                && s.getDateTime().toLocalTime()
                                .isBefore(endTime))
                        .forEach(s->result.add(
                                new UserMealWithExceed(s.getDateTime(), s.getDescription(), s.getCalories(), true)
                        ));
            }
            System.out.println(Arrays.asList(result));

        }
        return result;
    }
}
