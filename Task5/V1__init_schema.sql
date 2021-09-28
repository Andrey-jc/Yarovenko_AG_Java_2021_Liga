create table teacher(
                        id integer primary key,
                        lastname varchar(255),
                        firstname varchar(255),
                        surname varchar(255),
                        age integer not null,
                        sex varchar(5),
                        lesson_id integer,
                        school_id integer
);

create table student(
                        id integer  primary key,
                        lastname varchar(255),
                        firstname varchar(255),
                        surname varchar(255),
                        age integer  not null,
                        sex varchar(5),
                        lesson_id integer,
                        school_id integer
);

create table school(
                       id integer primary key,
                       name varchar(255),
                       address varchar (255)
);

create table lesson(
                       id integer primary key,
                       name varchar(255),
                       teacher_id integer,
                       student_id integer
);

alter table teacher
    add foreign key (lesson_id) references lesson(id),
    add foreign key (school_id) references school(id);

alter table student
    add foreign key (lesson_id) references lesson(id),
    add foreign key (school_id) references school(id);


alter table lesson
    add foreign key (teacher_id) references teacher (id),
    add foreign key (student_id) references student (id);




