-- ROLES
INSERT INTO rol (id, name) VALUES (1, 'TEACHER');
INSERT INTO rol (id, name) VALUES (2, 'STUDENT');

-- COURSES
INSERT INTO course (id, description, name, year) VALUES (1, 'Curso de Matemáticas', 'Matemáticas 101', 2023);
INSERT INTO course (id, description, name, year) VALUES (2, 'Curso de Historia', 'Historia Antigua', 2023);
INSERT INTO course (id, description, name, year) VALUES (3, 'Curso de Ciencias de la Computación', 'Introducción a la Programación', 2023);

-- SECTIONS
INSERT INTO section (id, name) VALUES (1, 'Primero A');
INSERT INTO section (id, name) VALUES (2, 'Primero B');
INSERT INTO section (id, name) VALUES (3, 'Primero C');

-- USER
INSERT INTO user (id, address, dni, phone_number, names, last_names, email, password, role_id) VALUES (1, 'jr honestidad 3454', '85859695', '933344857', 'Juan', 'Pérez', 'juan_profesor@example.com', 'string', 1);
INSERT INTO user (id, address, dni, phone_number, names, last_names, email, password, role_id) VALUES (2, 'jr unidad 2346', '84959485', '844477584', 'María', 'Gómez', 'maria_estudiante@example.com', 'string', 2);
INSERT INTO user (id, address, dni, phone_number, names, last_names, email, password, role_id) VALUES (3, 'jr perseverancia 2676', '45789023', '948574935', 'Ana', 'López', 'ana_estudiante@example.com', 'string', 2);
INSERT INTO user (id, address, dni, phone_number, names, last_names, email, password, role_id) VALUES (4, 'jr tolerancia 9380', '34899023', '300044393', 'Marta', 'Alpina', 'marta_profesor@example.com', 'string', 1);

-- STUDENTS
INSERT INTO student (id, section_id, user_id) VALUES (1, 1, 2);
INSERT INTO student (id, section_id, user_id) VALUES (2, 2, 3);

-- TEACHERS
INSERT INTO teacher (id, user_id) VALUES (1, 1);
INSERT INTO teacher (id, user_id) VALUES (2, 4);

-- COURSE_SECTION
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (1, 1, 1, 1);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (2, 2, 2, 2);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (3, 3, 3, 2);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (4, 1, 1, 1);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (5, 2, 2, 2);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (6, 3, 3, 1);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (7, 1, 1, 2);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (8, 2, 2, 2);
INSERT INTO course_section (id, course_id, section_id, teacher_id) VALUES (9, 3, 3, 1);

-- LESSONS
INSERT INTO lesson (id, course_id) VALUES (1, 1);
INSERT INTO lesson (id, course_id) VALUES (2, 1);
INSERT INTO lesson (id, course_id) VALUES (3, 2);


-- DETECTOR
INSERT INTO detector (id, detection_date, final_state, initial_state, middle_state, lesson_id, student_id) VALUES (1, '2023-10-11 08:00:00', 'Desconcentrado', 'Concentrado', 'Concentrado', 1, 1);
INSERT INTO detector (id, detection_date, final_state, initial_state, middle_state, lesson_id, student_id) VALUES (2, '2023-10-12 09:15:00', 'Concentrado','Desconcentrado',  'Desconcentrado', 2, 2);
INSERT INTO detector (id, detection_date, final_state, initial_state, middle_state, lesson_id, student_id) VALUES (3, '2023-10-13 10:30:00', 'Desconcentrado', 'Desconcentrado', 'Concentrado', 3, 1);





