DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config`  (
                                   `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                                   `config_name` varchar(50) NOT NULL COMMENT '配置名称',
                                   `config_key` varchar(50) NOT NULL COMMENT '配置项',
                                   `config_value` varchar(500) NULL COMMENT '配置值',
                                   `config_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型',
                                   `gmt_create`   datetime            NOT NULL           DEFAULT CURRENT_TIMESTAMP(),
                                   `create_by`    varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `gmt_modified` datetime                               DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(),
                                   `modified_by`  varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                   `deleted_flag` tinyint(1) unsigned NOT NULL           DEFAULT '1',
                                   `version`      int(5) unsigned     NOT NULL           DEFAULT 1,
                                   PRIMARY KEY (`id`)
) COMMENT = '系统配置表';