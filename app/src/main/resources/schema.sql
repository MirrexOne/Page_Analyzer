DROP TABLE IF EXISTS urls;

CREATE TABLE urls
(
    id         SERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
)