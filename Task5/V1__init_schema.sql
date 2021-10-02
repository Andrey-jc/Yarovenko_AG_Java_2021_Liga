create table TEACHER
(
    id         integer primary key,
    patronymic varchar(255),
    name       varchar(255),
    surname    varchar(255),
    age        integer not null,
    sex        varchar(5),
    school_id  integer
);

create table STUDENT
(
    id         integer primary key,
    patronymic varchar(255),
    name       varchar(255),
    surname    varchar(255),
    age        integer not null,
    sex        varchar(5),
    school_id  integer
);

create table SCHOOL
(
    id      integer primary key,
    name    varchar(255),
    address varchar(255)
);

create table LESSON
(
    id   integer primary key,
    name varchar(255)
);

create table STUDENT_AND_LESSON
(
    student_id integer NOT NULL,
    lesson_id  integer NOT NULL,
    foreign key (lesson_id) references LESSON (id),
    foreign key (student_id) references STUDENT (id),
    primary key (student_id, lesson_id)
);

create table TEACHER_AND_LESSON
(
    teacher_id integer NOT NULL,
    lesson_id  integer NOT NULL,
    foreign key (teacher_id) references TEACHER (id),
    foreign key (lesson_id) references LESSON (id),
    primary key (teacher_id, lesson_id)
);

alter table TEACHER
    add foreign key (school_id) references SCHOOL (id);

alter table STUDENT
    add foreign key (school_id) references SCHOOL (id);