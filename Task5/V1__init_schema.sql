create table teacher
(
    id         integer primary key,
    patronymic varchar(255),
    firstname  varchar(255),
    surname    varchar(255),
    age        integer not null,
    sex        varchar(5),
    school_id  integer
);

create table student
(
    id         integer primary key,
    patronymic varchar(255),
    firstname  varchar(255),
    surname    varchar(255),
    age        integer not null,
    sex        varchar(5),
    school_id  integer
);

create table school
(
    id      integer primary key,
    name    varchar(255),
    address varchar(255)
);

create table lesson
(
    id   integer primary key,
    name varchar(255)
);

create table studentAndLesson
(
    id         integer primary key,
    student_id integer,
    lesson_id  integer,
    foreign key (lesson_id) references lesson (id),
    foreign key (student_id) references student (id)
);

create table teacherAndLesson
(
    id         integer primary key,
    teacher_id integer,
    lesson_id  integer,
    foreign key (teacher_id) references teacher (id),
    foreign key (lesson_id) references lesson (id)
);

alter table teacher
    add foreign key (school_id) references school (id);

alter table student
    add foreign key (school_id) references school (id);
