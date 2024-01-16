insert into `users` (`user_id`, `email`,`name`,`profile_image`)
values( 1, 'sed@yahoo.edu', '썸네일', 'https://guardian.co.uk/one');
insert into `users` (`user_id`, `email`,`name`,`profile_image`)
values( 2, 'abc@yahoo.edu', '썸네일2', 'https://guardian.co.uk/one');

insert into nail_shop (nail_shop_id, map_lat, map_lng, employee_num, monthly_nail_maximum_price, monthly_nail_minimum_price,business_hour, detail_images, location, monthly_nail_instagram_link, nail_shop_name, naver_map_link, reservation_table, street_address, title_image)
values(1,'41.4019163136','172.669129728',9,14428, 28026, '1:30 PM', 'http://facebook.com/sub?client=g', '서울시 중구','http://cnn.com/fr?q=test','썸네일네일샵','https://pinterest.com/en-ca?client=g','ligula. Nullam enim.', '328-2245 Tincidunt. Ave','http://zoom.us?g=1');

insert into user_nail_shop (user_id, nail_shop_id)
values(1, 1);

insert into hashtag (hashtag_id, hashtag_name)
values(1, '심플한');
insert into hashtag (hashtag_id, hashtag_name)
values(2, '화려한');

insert into nail_shop_hashtag (hashtag_id, nail_shop_id)
values(1, 1);
insert into nail_shop_hashtag (hashtag_id, nail_shop_id)
values(2, 1);

insert into recent_search(recent_search_id, date_time, user_id, station)
values(1, '2018-10-08 16:38:00.000000',1, '배방');
insert into recent_search(recent_search_id, date_time, user_id, station)
values(2, '2018-10-09 16:38:00.000000',1, '외대앞');

