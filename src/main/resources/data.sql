create table if not exists users
(
    id       int auto_increment primary key,
    name     varchar(45),
    password varchar(45)
);

-- insert into users(name, password)
-- values ('John Doe', 'fs09v0x'),
--        ('Jane Smith', 'sdf89s8');

-- insert into moderators(EMAIL, PASSWORD)
-- values ('JohnDoe@gmail.com', 'lkj342ljl;l3'),
--        ('JaneSmith@gmail.com', 'sdlfkj4lkj;fdg0');
