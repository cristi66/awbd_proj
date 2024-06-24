DROP TABLE IF EXISTS Users_Progress;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Enrollments;
DROP TABLE IF EXISTS Assessments;
DROP TABLE IF EXISTS Lessons;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_on DATE,
                       level int
);


CREATE TABLE Courses (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         description TEXT,
                         total_progress INT,
                         type VARCHAR(6)
);


CREATE TABLE Users_Progress (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT,
                                course_id BIGINT,
                                progress_percentage INT,
                                last_accessed DATE,
                                FOREIGN KEY (user_id) REFERENCES Users(id),
                                FOREIGN KEY (course_id) REFERENCES Courses(id)
);


CREATE TABLE Lessons (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content TEXT,
                         course_id BIGINT,
                         lesson_number INT,
                         FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE Assessments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             title VARCHAR(255) NOT NULL,
                             description TEXT,
                             due_date DATE,
                             course_id BIGINT,
                             FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE Enrollments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_id BIGINT,
                             course_id BIGINT,
                             enrollment_date DATE,
                             FOREIGN KEY (user_id) REFERENCES Users(id),
                             FOREIGN KEY (course_id) REFERENCES Courses(id)
);

CREATE TABLE Comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          content TEXT,
                          timestamp DATE,
                          user_id BIGINT,
                          course_id BIGINT,
                          lesson_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES Users(id),
                          FOREIGN KEY (course_id) REFERENCES Courses(id),
                          FOREIGN KEY (lesson_id) REFERENCES Lessons(id)
);