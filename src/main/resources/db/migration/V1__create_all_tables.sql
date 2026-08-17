CREATE TABLE college (
                         id INT NOT NULL,
                         name VARCHAR(255),
                         PRIMARY KEY (id)
);

CREATE TABLE student (
                         id INT NOT NULL,
                         name VARCHAR(255),
                         age INT NOT NULL,
                         college_id INT,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_student_college
                             FOREIGN KEY (college_id)
                                 REFERENCES college(id)
);

CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       role VARCHAR(255),
                       PRIMARY KEY (id)
);