DROP TABLE  IF EXISTS `api_vendor`;
CREATE TABLE `api_vendor` (
                              `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                              `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '供应商名称',
                              `remark` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '供应商描述',
                              `app_key` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Api App Key',
                              `secret_key` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Api Secret Key',
                              `json_config` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Api Json配置',
                              `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                              `create_by` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                              `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(),
                              `modified_by` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                              `deleted_flag` tinyint(1) unsigned NOT NULL DEFAULT '1',
                              `version` int(5) unsigned NOT NULL DEFAULT 1,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='供应商表';

DROP TABLE  IF EXISTS `template`;
CREATE TABLE `template` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板编号',
                            `template` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '模板内容',
                            `audit_reason` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核原因',
                            `audit_status` tinyint(1) unsigned DEFAULT '0' COMMENT '审核状态(0:审核中 1：已通过 2：未通过)',
                            `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                            `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(),
                            `modified_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `deleted_flag` tinyint(1) unsigned NOT NULL DEFAULT '1',
                            `version` int(5) unsigned NOT NULL DEFAULT 1,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='模板表';

DROP TABLE  IF EXISTS `message`;
CREATE TABLE `message` (
                           `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                           `template_id` bigint(20)  NOT NULL COMMENT '关联模板',
                           `target` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发送目标',
                           `json_data` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消息数据（JSON）',
                           `gmt_create` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `gmt_modified` datetime(6) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
                           `modified_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `deleted_flag` tinyint(1) unsigned NOT NULL DEFAULT '1',
                           `version` int(5) unsigned NOT NULL DEFAULT 1,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';
