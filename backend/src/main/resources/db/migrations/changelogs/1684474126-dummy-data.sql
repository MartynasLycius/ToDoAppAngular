-- liquibase formatted sql

-- changeset eastnetic:1684474126-1
insert into users (email, first_name, last_name, hashed_password, created_at, updated_at)
values ('john@gmail.com', 'john', 'doe', '$2a$10$vPGtb5HoAi6DCTuRV827IeDW7LT4nJ4ILSrcXSJvzhOv0.jfK.eN6', current_date,
        current_date),
       ('mark@gmail.com', 'mark', 'rifle', '$2a$10$vPGtb5HoAi6DCTuRV827IeDW7LT4nJ4ILSrcXSJvzhOv0.jfK.eN6', current_date,
        current_date),
       ('alice@gmail.com', 'alice', 'becker', '$2a$10$vPGtb5HoAi6DCTuRV827IeDW7LT4nJ4ILSrcXSJvzhOv0.jfK.eN6',
        current_date,
        current_date);

-- changeset eastnetic:1684474126-2
insert into todos (created_at, updated_at, "name", description, due_date, user_id, status)
values (current_date,
        current_date,
        'complete the frontend for todo app',
        '',
        current_date,
        (select id from users where email = 'john@gmail.com'),
        101),
       (current_date,
        current_date,
        'complete the backend for todo app',
        '',
        current_date + interval '1 day',
        (select id from users where email = 'john@gmail.com'),
        101),
       (current_date,
        current_date,
        'complete the database seed for todo app',
        '',
        current_date + interval '2 day',
        (select id from users where email = 'john@gmail.com'),
        101),

       (current_date,
        current_date,
        'complete the frontend for city app',
        '',
        current_date,
        (select id from users where email = 'alice@gmail.com'),
        101),
       (current_date,
        current_date,
        'complete the backend for city app',
        '',
        current_date + interval '1 day',
        (select id from users where email = 'alice@gmail.com'),
        101),
       (current_date,
        current_date,
        'complete the database seed for city app',
        '',
        current_date + interval '2 day',
        (select id from users where email = 'alice@gmail.com'),
        101),

       (current_date,
        current_date,
        'complete the frontend for new app',
        '',
        current_date,
        (select id from users where email = 'mark@gmail.com'),
        101),
       (current_date,
        current_date,
        'complete the backend for new app',
        '',
        current_date + interval '1 day',
        (select id from users where email = 'mark@gmail.com'),
        101),
       (current_date,
        current_date,
        'complete the database seed for new app',
        '',
        current_date + interval '2 day',
        (select id from users where email = 'mark@gmail.com'),
        101);
