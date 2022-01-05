CREATE SEQUENCE user_sequence start 1 increment 1;

CREATE TABLE "user"(
    id       SERIAL,
    app_user_role VARCHAR,
    username VARCHAR NOT NULL UNIQUE,
    email    VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    logged boolean,
    enabled boolean,
    PRIMARY KEY (id)
);
