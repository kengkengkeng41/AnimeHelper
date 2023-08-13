use Anime;

create table anime (
    number smallint primary key AUTO_INCREMENT COMMENT '番剧唯一编号',
    name varchar(128) unique COMMENT '番剧名称',
    j_name varchar(128) COMMENT '番剧日文名称',
    s_day char(1) COMMENT '星期几更新',
    ym char(6) COMMENT '首播年月',
    p1 varchar(128) COMMENT '番剧主视觉图',
    p2 varchar(128) COMMENT '番剧视觉图',
    brief varchar(888) COMMENT '番剧简介',
    insert_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    insert_user bigint(20) NOT NULL DEFAULT '10000' COMMENT '创建者id',
    update_time timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    update_user bigint(20) NOT NULL DEFAULT '10000' COMMENT '修改者id'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000;

create table links (
    link_name varchar(128) primary key COMMENT '链接名称',
    link varchar(888) COMMENT '磁力链接',
    belong smallint COMMENT '所属番剧',
    subtitle varchar(32) COMMENT '字幕组',
    insert_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    insert_user bigint(20) NOT NULL DEFAULT '10000' COMMENT '创建者id',
    foreign key(belong) references anime(number)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table visitor (
    user_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键ID',
    nick_name varchar(50) NOT NULL default 'user114514' COMMENT '用户昵称',
    login_name varchar(32) NOT NULL default '' COMMENT '登录用户名',
    password_md5 varchar(60) NOT NULL default '' COMMENT 'MD5加密后的密码',
    avatar varchar(128) not null default 'http://localhost:8081/avatar/default.png' COMMENT '用户头像',
    role char(1)  not null default '0' COMMENT '用户身份，0:访客，1:管理',
    insert_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    update_time timestamp ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (user_id) using btree
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=100000;

CREATE TABLE user_token (
    user_id bigint(20) NOT NULL primary key COMMENT '用户主键id',
    token varchar(32) NOT NULL UNIQUE COMMENT 'token值(32位字符串)',
    insert_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    expire_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE carousel (
    carousel_id tinyint NOT NULL primary key AUTO_INCREMENT COMMENT '首页轮播图主键id',
    carousel_url varchar(128) NOT NULL DEFAULT '' COMMENT '轮播图',
    redirect_url varchar(128) NOT NULL DEFAULT '' COMMENT '跳转地址',
    is_deleted char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
    insert_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    insert_user bigint(20) NOT NULL DEFAULT '10000' COMMENT '创建者id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;