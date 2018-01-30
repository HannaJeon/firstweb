INSERT INTO user (user_id, password, name, email) VALUES ('Hanna', '1234', '전한나', 'jks7psj@gmail.com');
INSERT INTO user (user_id, password, name, email) VALUES ('Yejun', '1111', '박예준', 'smartpark@gamil.com');

INSERT INTO question (writer_id, title, contents, create_date) VALUES (2, '나는바보다', '나는 바보 멍청이다.', CURRENT_TIMESTAMP());
INSERT INTO question (writer_id, title, contents, create_date) VALUES (1, '나는천재다', '나는 천재이다.', CURRENT_TIMESTAMP());