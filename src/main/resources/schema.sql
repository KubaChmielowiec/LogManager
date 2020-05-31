DROP TABLE IF EXISTS Log;

CREATE TABLE Log (
    id INT IDENTITY PRIMARY KEY,
    date TIMESTAMP,
    level VARCHAR,
    logging_class VARCHAR,
    content VARCHAR
);