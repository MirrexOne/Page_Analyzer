DROP TABLE IF EXISTS urls CASCADE;
DROP TABLE IF EXISTS url_checks CASCADE;

CREATE TABLE urls
(
    id         SERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(255)       NOT NULL UNIQUE,
    created_at TIMESTAMP          NOT NULL
);

CREATE TABLE url_checks
(
    id SERIAL PRIMARY KEY NOT NULL,
    url_id BIGINT NOT NULL,
    CONSTRAINT fk_url_checks_id
        FOREIGN KEY (url_id)
            REFERENCES urls(id),
    status_code INT,
    title VARCHAR(255),
    h1 VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP NOT NULL
)