-- auto-generated definition
create table todo_items
(
    id          bigserial
        primary key,
    created_at  timestamp(6) not null,
    created_by  varchar(255),
    deleted     boolean      not null,
    updated_at  timestamp(6),
    updated_by  varchar(255),
    uuid        uuid         not null
        constraint uk_6e3cfbbdtql6rergu6unddhlx
            unique,
    date        timestamp(6) not null,
    description text,
    name        varchar(255) not null,
    priority    varchar(255),
    status      varchar(255) not null,
    tags        varchar(255)
);

alter table todo_items
    owner to postgres;

-- New Migration
