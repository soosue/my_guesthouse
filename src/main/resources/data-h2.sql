insert into member (id, created_date, email, member_img_name, member_img_path, member_img_size, member_info, member_level, name, password, phone_number, point) values (1, now(), 'korong@email.com', null, null, 0, null, 'MEMBER', '코롱', 'korong2', '010-1234-5678', 0);

insert into guest_house (id, name, host_id) values (1, '행복한 하우스', 1);
insert into room (id, name, guests_count, guest_house_id) values (1, '봄날의 햇살룸', 5, 1);
insert into day_of_room (id, date, remain_count, room_id) values (1, '2022-09-20', 5, 1);
insert into day_of_room (id, date, remain_count, room_id) values (2, '2022-09-21', 5, 1);
insert into day_of_room (id, date, remain_count, room_id) values (3, '2022-09-22', 5, 1);
insert into day_of_room (id, date, remain_count, room_id) values (4, '2022-09-23', 5, 1);
insert into day_of_room (id, date, remain_count, room_id) values (5, '2022-09-24', 5, 1);
