CREATE TABLE `config_properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key1` varchar(50) COLLATE utf8_bin NOT NULL,
  `value1` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `application` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile` varchar(50) COLLATE utf8_bin NOT NULL,
  `label` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
