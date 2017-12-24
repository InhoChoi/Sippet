CREATE TABLE `user_track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `path_name` varchar(255) DEFAULT NULL,
  `referrer` varchar(255) DEFAULT NULL,
  `tracking_id` varchar(1000) DEFAULT NULL,
  `new_visitor` tinyint(1) DEFAULT 0,
  `created_at`datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;s