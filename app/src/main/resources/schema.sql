DROP TABLE IF EXISTS urls;

CREATE TABLE urls
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMP
)