CREATE TABLE `user_track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `path_name` varchar(255) DEFAULT NULL,
  `referrer_host` varchar(255) DEFAULT NULL,
  `referrer_path` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `tracking_id` varchar(100) DEFAULT NULL,
  `new_visitor` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `retention_period` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tracking_id` varchar(100) DEFAULT NULL,
  `retention_period` int(10) DEFAULT NULL,
  `valid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `re_visit` int(10) DEFAULT NULL,
  `new_visit` int(10) DEFAULT NULL,
  `retention_average_period` int(10) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;