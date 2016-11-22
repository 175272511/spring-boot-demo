DROP TABLE IF EXISTS `t_auth_user`;

/*
普通用户表 t_auth_user
*/
CREATE TABLE `t_auth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`userType` INT(4) NOT NULL DEFAULT 1 COMMENT '用户类型 1:普通用户 2:管理员',
  `roleId` bigint(20) NOT NULL COMMENT '角色id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `sex` int(4) DEFAULT NULL COMMENT '性别',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) COMMENT '电话',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `address` varchar(64) DEFAULT NULL COMMENT '常用地址',
  `remark` varchar(1024) DEFAULT NULL COMMENT '个性签名',
  `userImg` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `status` int(4) DEFAULT 1 COMMENT '用户状态',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_auth_user_username` (`username`),
  UNIQUE KEY `idx_t_auth_user_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='普通用户表';

INSERT INTO t_auth_user(`id`,`userType`,`roleId`,`username`,`password`,`sex`,`nickname`,`phone`,`email`,`address`,`remark`,`userImg`,`status`,`createTime`,`updateTime`) VALUES 
('1','1','2','测试2','123456','1','测试2','13666666667','test@admin.com','地址2','','','1',now(),now()),
('2','2','1','测试1','123456','1','测试1','13666666666','admin@admin.com','地址1','','','1',now(),now());


/*
角色表 t_auth_role
*/
DROP TABLE IF EXISTS `t_auth_role`;

CREATE TABLE `t_auth_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleName` varchar(32) DEFAULT NULL COMMENT '角色名',
  `roleSign` varchar(128) DEFAULT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  `desc` varchar(256) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 COMMENT='角色表';

INSERT INTO t_auth_role ( roleName, roleSign, `desc`, createTime ) VALUES ('普通用户','user','普通用户', now()),('管理员','admin','管理员', now());

/*
用户角色表 t_auth_user_role
*/
DROP TABLE IF EXISTS `t_auth_user_role`;

CREATE TABLE `t_auth_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_auth_user_role_userid_roleid` (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';

INSERT INTO t_auth_user_role (userId,roleId,createTime) VALUES ('1','1',now()),('2','1',now()),('2','2',now());

/*
权限表 t_auth_permission
*/
DROP TABLE IF EXISTS `t_auth_permission`;

CREATE TABLE `t_auth_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permissionName` varchar(32) NOT NULL COMMENT '权限名',
  `permissionSign` varchar(128) NOT NULL COMMENT '权限标识,程序中判断使用,如"user:create"',
  `href` varchar(128) DEFAULT NULL COMMENT '链接',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述,UI界面显示使用',
  `status` bigint(4) NOT NULL DEFAULT '1' COMMENT '状态 0:禁用 1:启用',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_t_permission_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

INSERT INTO t_auth_permission VALUES ('1','普通用户权限1','user:test1','','普通用户权限1','1',now()),
('2','普通用户权限2','user:test2','','普通用户权限2','1',now()),
('3','管理员权限1','admin:test1','','管理员权限1','1',now()),
('4','管理员权限2','admin:test2','','管理员权限2','1',now()),
('5','管理员权限3','admin:test3','','管理员权限3','1',now());

/*
角色权限 t_auth_role_permission
*/
DROP TABLE IF EXISTS `t_auth_role_permission`;

CREATE TABLE `t_auth_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permissionId` bigint(20) DEFAULT NULL COMMENT '权限id',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_t_role_permission_roleId` (`roleId`),
  KEY `idx_t_role_permission_permissionId` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与权限关联表';

INSERT INTO t_auth_role_permission VALUES ('1','1','1',now()),
('2','1','2',now()),
('3','2','1',now()),
('4','2','2',now()),
('5','2','3',now()),
('6','2','4',now()),
('7','2','5',now());
