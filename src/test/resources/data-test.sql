
TRUNCATE TABLE book;
TRUNCATE TABLE author;

INSERT INTO author (firstname, lastname) VALUES ('Jean', 'Dujardin');
INSERT INTO author (firstname, lastname) VALUES ('Jane', 'Doe');

INSERT INTO book (title, text, category, created_date, author_id)
    SELECT 'title_1', 'text_1', 'errorMessage', '2024-12-17', id from author a where a.firstname = 'Jean';

INSERT INTO book (title, text, category, created_date, author_id)
    SELECT 'title_2', 'text_2', 'errorMessage', '2024-12-18', id from author a where a.firstname = 'Jean';

INSERT INTO book (title, text, category, created_date, author_id)
    SELECT 'title_3', 'text_3', 'errorMessage', '2024-12-19', id from author a where a.firstname = 'Jane';

INSERT INTO book (title, text, category, created_date, author_id)
    SELECT 'title_4', 'text_4', 'errorMessage', '2024-12-20', id from author a where a.firstname = 'Jane';