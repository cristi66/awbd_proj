DROP TABLE IF EXISTS Users_Progress;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Enrollments;
DROP TABLE IF EXISTS Assessments;
DROP TABLE IF EXISTS Lessons;
DROP TABLE IF EXISTS Ratings;
DROP TABLE IF EXISTS Transactions;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS Users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       level INT DEFAULT 0,
                       currency INT DEFAULT 0,
                       created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Courses (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         total_progress INT NOT NULL,
                         level_required INT DEFAULT 0,
                         cost INT DEFAULT 0,
                         type ENUM('SELECT','INSERT','UPDATE','DELETE')
);


CREATE TABLE Users_Progress (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               course_id BIGINT NOT NULL,
                               progress_percentage INT NOT NULL,
                               last_accessed TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (user_id) REFERENCES users(id),
                               FOREIGN KEY (course_id) REFERENCES courses(id)
);


CREATE TABLE Lessons (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         course_id BIGINT NOT NULL,
                         lesson_number INT NOT NULL,
                         FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE Assessments (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             title VARCHAR(255) NOT NULL,
                             description TEXT NOT NULL,
                             questions VARCHAR(255) NOT NULL,
                             answers VARCHAR(255) NOT NULL,
                             course_id BIGINT,
                             FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE Enrollments (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            course_id BIGINT NOT NULL,
                            enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE Comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          content TEXT NOT NULL,
                          timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          user_id BIGINT NOT NULL,
                          course_id BIGINT NOT NULL,
                          lesson_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (course_id) REFERENCES courses(id),
                          FOREIGN KEY (lesson_id) REFERENCES lessons(id)
);

CREATE TABLE Transactions (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          amount INT NOT NULL,
                          user_id BIGINT NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          type ENUM('SPENT','EARNED'),
                          created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE Ratings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          rating INT NOT NULL,
                          created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          user_id BIGINT NOT NULL,
                          course_id BIGINT NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(id),
                          FOREIGN KEY (course_id) REFERENCES courses(id)
);