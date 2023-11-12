begin transaction;

drop table if exists users;
drop table if exists tweets;
drop table if exists followers;
drop table if exists notifications;
drop table if exists comments;

CREATE TABLE "users" (
	email text primary key,
	user_name text not null,
	password text not null
);

CREATE TABLE "followers" (
    id integer primary key autoincrement,
    email text not null,
    follower text not null
);


create table "comments" (
	id text primary key,
	text text,
	email text,
	tweet_id integer,
	creation_time date,
	foreign key(email) references users(email),
	foreign key(tweet_id) references tweets(id)
);

create table "tweets" (
	id text primary key,
	email text not null,
	creation_time date,
	content text,
	foreign key(email) references users(email)
);

create table "notifications" (
    id text primary key,
    tweet_id text not null,
    from_user_email text not null,
    to_user_email text not null,
    creation_time date
);

insert into users (email, user_name, password) values ("trung@gmail.com", "Trung Ngo", "1111");
insert into users (email, user_name, password) values ("mike@gmail.com", "Mike", "1112");
insert into users (email, user_name, password) values ("sam@gmail.com", "Sam", "1113");
insert into users (email, user_name, password) values ("huffson@gmail.com", "Huffson", "1114");


insert into tweets (id,email,creation_time,content) values ("t4531sdf", "trung@gmail.com", datetime("now"), "Hey @mike");
insert into tweets (id,email,creation_time,content)values ("twr6432d", "mike@gmail.com", datetime("now"), "Hey @trung!");

insert into comments (id, tweet_id, email, creation_time, text) values ("casdf46","t4531sdf", "mike", datetime("now"), "Comment 1");
insert into comments (id, tweet_id, email, creation_time, text) values ("casdf45","twr6432d", "mike", datetime("now"), "Comment 2");

insert into followers(email, follower) values ("trung@gmail.com", "mike@gmail.com");
insert into followers(email, follower) values ("sam@gmail.com", "trung@gmail.com");
insert into followers(email, follower) values ("mike@gmail.com", "huffson@gmail.com");

insert into notifications(id, tweet_id, from_user_email, to_user_email, creation_time) values 
("n46555", "t4531sdf", "trung@gmail.com", "mike@gmail.com", datetime("now"));
insert into notifications(id, tweet_id, from_user_email, to_user_email, creation_time) values 
("n46556", "twr6432d", "mike@gmail.com", "huffson@gmail.com", datetime("now"));


commit;
