CREATE TABLE IF NOT EXISTS USERS
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    login VARCHAR(20) NOT NULL,
    password  VARCHAR(100) NOT NULL
);