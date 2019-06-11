CREATE DATABASE IF NOT EXISTS mealsDB
    COLLATE utf8_general_ci;

USE mealsDB;

DROP TABLE IF EXISTS meals;

create table if not exists mealsDB.meals
(
    id          bigint auto_increment
        primary key,
    date        datetime    null,
    description varchar(50) null,
    calories    int(9)      null,
    excess      bit         null
);

insert into mealsDB.meals (date, description, calories, excess)
values ('2015-05-29 10:00:00', 'Breakfast', 500, false)
     , ('2015-05-29 13:00:00', 'Lunch', 1000, false)
     , ('2015-05-29 20:00:00', 'Dinner', 500, false)
     , ('2015-05-30 10:00:00', 'Breakfast', 500, true)
     , ('2015-05-30 13:00:00', 'Lunch', 1000, true)
     , ('2015-05-30 20:00:00', 'Dinner', 510, true)
     , ('2015-05-31 10:00:00', 'Breakfast', 500, false)
     , ('2015-05-31 13:00:00', 'Lunch', 1000, false)
     , ('2015-05-31 20:00:00', 'Dinner', 500, false)
;
