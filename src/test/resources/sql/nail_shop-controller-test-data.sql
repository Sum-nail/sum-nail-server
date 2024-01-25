insert into nail_shop (nail_shop_id, business_hour, detail_images, location, nail_shop_name)
values (1, '1:30 PM', 'http://facebook.com/sub?client=g', '서울시 강남구', '귀여운 서울역 네일샵');
insert into nail_shop (nail_shop_id, business_hour, detail_images, location, nail_shop_name)
values (2, '2:30 PM', 'http://facebook.com/sub?client=g', '서울시 양천구', '쿨한 강남역 네일샵');

insert into station (station_id, line, station_name)
values (1, '01호선, 04호선', '서울역');
insert into station (station_id, line, station_name)
values (2, '02호선', '강남역');

insert into hashtag (hashtag_id, hashtag_name)
values (1, '귀여운');
insert into hashtag (hashtag_id, hashtag_name)
values (2, '쿨한');
insert into hashtag (hashtag_id, hashtag_name)
values (3, '멋진');
insert into hashtag (hashtag_id, hashtag_name)
values (4, '따뜻한');

insert into nail_shop_hashtag (nail_shop_hashtag_id, nail_shop_id, hashtag_id)
values (1, 1, 1);
insert into nail_shop_hashtag (nail_shop_hashtag_id, nail_shop_id, hashtag_id)
values (2, 2, 2);
insert into nail_shop_station (nail_shop_station_id, nail_shop_id, station_id)
values (1, 2, 2);
insert into nail_shop_station (nail_shop_station_id, nail_shop_id, station_id)
values (2, 1, 1);