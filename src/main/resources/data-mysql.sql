-- INSERT INTO Users (username, email, password, created_on) VALUES
--                                                               ('John Doe', 'john.doe@example.com', 'password123', '2024-06-04'),
--                                                               ('Jane Smith', 'jane.smith@example.com', 'securepass', '2024-06-04'),
--                                                               ('Alice Johnson', 'alice.johnson@example.com', 'mypassword', '2024-06-04');


INSERT INTO Courses (title, description, total_progress, type) VALUES
                                                                   ('Java Programming', 'Learn Java from basics to advanced', 100, 'SELECT'),
                                                                   ('Web Development', 'Learn to build websites using HTML, CSS, and JavaScript', 100, 'DELETE');


INSERT INTO Lessons (title, content, course_id, lesson_number) VALUES
                                                                   ('Introduction to Java', 'This lesson covers the basics of Java.', 1,1),
                                                                   ('Java Variables', 'This lesson covers Java variables and data types.', 1,2),
                                                                   ('Introduction to HTML', 'This lesson covers the basics of HTML.', 2,1),
                                                                   ('CSS Fundamentals', 'This lesson covers the basics of CSS.', 2,2);


INSERT INTO Assessments (title, description, questions, answers, course_id) VALUES
                                                                                ('Java Basics Quiz', 'A quiz covering the basics of Java.', 'testQuestions1', 'testAnswers1', 1),
                                                                                ('HTML and CSS Assignment', 'An assignment to create a basic webpage.', 'testQuestions2', 'testAnswers2', 2);


INSERT INTO Enrollments (user_id, course_id, enrollment_date) VALUES
                                                                  (1, 1, '2024-06-01'),
                                                                  (2, 1, '2024-06-02'),
                                                                  (1, 2, '2024-06-03'),
                                                                  (3, 2, '2024-06-04');


INSERT INTO Users_Progress (user_id, course_id, progress_percentage, last_accessed) VALUES
                                                                                        (1, 1, 50, '2024-06-20'),
                                                                                        (2, 1, 30, '2024-06-18'),
                                                                                        (1, 2, 70, '2024-06-21'),
                                                                                        (3, 2, 90, '2024-06-22');


INSERT INTO Comments (content, timestamp, user_id, course_id, lesson_id) VALUES
                                                                             ('Great introduction to Java!', '2024-06-15', 1, 1, 1),
                                                                             ('Very helpful lesson on CSS.', '2024-06-16', 2, 2, 4),
                                                                             ('Looking forward to more content!', '2024-06-17', 1, 1, NULL),
                                                                             ('This quiz was challenging but fun.', '2024-06-18', 3, 1, NULL);


INSERT INTO Transactions (user_id, amount, type, description, created_on) VALUES (1, 50, 'SPENT', 'Enrolled in Java Basics', '2023-01-01 12:00:00'), (2, 100, 'EARNED', 'Finished Advanced Java', '2023-02-01 12:00:00');


INSERT INTO ratings (user_id, course_id, rating, created_on) VALUES
                                                                 (1, 1, 5, '2023-01-01 12:00:00'),
                                                                 (2, 2, 4, '2023-02-01 12:00:00');