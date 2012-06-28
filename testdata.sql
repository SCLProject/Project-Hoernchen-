insert into parson_t (user_id, name, grade)
       values("b09201", "tarou", 1);
insert into parson_t (user_id, name, grade)
       values("b09204", "jirou", 2);
insert into parson_t (user_id, name, grade)
       values("b09209", "sabrou", 3);

insert into seat_t (seat_id, user_id)
       values(1, "b09209");
insert into seat_t (seat_id, user_id)
       values(2, "b09201");
insert into seat_t (seat_id, user_id)
       values(3, "b09204");
insert into seat_t (seat_id) values(3);
insert into seat_t (seat_id) values(4);

insert into felica_t (felica_id, user_id)
       values (11111111, "b09201");
insert into felica_t (felica_id, user_id)
       values (22222222, "b09204");
insert into felica_t (felica_id, user_id)
       values (33333333, "b09209");

insert into log_t (seat_id, state)
       values (2, 0);
insert into log_t (seat_id, state)
       values (1, 2);
insert into log_t (seat_id, state)
       values (3, 4);
insert into log_t (seat_id, state)
       values (2, 2);
insert into log_t (seat_id, state)
       values (1, 0);
