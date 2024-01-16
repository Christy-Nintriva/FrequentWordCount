--liquibase formatted sql
--changeset Christy:create_user_table
CREATE TABLE IF NOT EXISTS public.epassi_user(
    id bigint PRIMARY KEY,
    email VARCHAR(100),
    firstName VARCHAR(45),
    lastname VARCHAR(45),
    password_hash VARCHAR(250)
    );
--changeset Francis:create_normal_user
INSERT INTO epassi_user values (1,'admin@mail.com','admin','admin','$2a$10$DaYrrxAqaqLp5AoISNc7xehg/zFiBaiKWhh9WOHhbTBf.QM4Gp.eO');
