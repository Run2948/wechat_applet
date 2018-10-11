# 微信小程序 开源

#### 项目介绍
微信小程序 开源  不像很多人一样代码放上面有很多坑，本程序是借鉴码云其他开源项目。把所有坑都已经躺过了，可以直接使用。后台代码还在开发整理中。如果有需要联系qq：191888810

## 技术选型
* 1 后端使用技术
    * 1.1 springframework4.3.7.RELEASE
    * 1.2 mybatis3.4.1
    * 1.3 shiro1.3.2
    * 1.4 servlet3.1.0
    * 1.5 druid1.0.28
    * 1.6 slf4j1.7.19
    * 1.7 fastjson1.2.30
    * 1.8 poi3.15
    * 1.9 velocity1.7
    * 1.10 quartz2.2.3
    * 1.11 mysql5.1.39
    * 1.12 swagger2.4
    * 1.13 j2cache2.3.22-release
        
* 2 前端使用技术
    * 2.1 Vue2.5.1
    * 2.2 iview
    * 2.3 layer3.0.3
    * 2.4 jquery2.2.4
    * 2.5 bootstrap3.3.7
    * 2.6 jqgrid5.1.1
    * 2.7 ztree3.5.26
    * 2.8 froala_editor1.2.2

## 项目结构
~~~
platform-wechat-mall
|--platform-admin 后台管理
|--platform-api 微信小程序商城api接口
|--platform-common 公共模块
|--platform-framework 系统WEB合并
|--platform-gen 代码生成
|--platform-schedule 定时任务
|--platform-shop 商城后台管理
|--wx-mall 微信小程序商城
|--platform-vue 微信公众号商城（待开发）
~~~

## 自动代码生成结构
~~~
AutoCode
├─menu.sql                      创建菜单的sql
│ 
└─main    
     ├─java                     生成的java代码
     │    └─com
     │       └─platform
     │            ├─controller
     │            ├─dao
     │            ├─entity
     │            └─service
     │                └─impl
     └─webapp                   生成的页面文件
         ├─js   
         │  └─shop   
         └─WEB-INF 
             └─page  
                └─shop  

~~~
## 实现功能

* 一：会员管理
    * a 会员管理
    * b 会员等级
    * c 收货地址管理
    * d 会员优惠劵
    * e 会员收藏
    * f 会员足迹
    * g 搜索历史
    * h 购物车

* 二：商城配置
    * a 区域配置
    * b 商品属性种类
    * c 品牌制造商
    * d 商品规格
    * e 订单管理
    * f 商品类型
    * g 渠道管理
    * h 商品问答
    * i 反馈
    * j 关键词

* 三：商品编辑
    * a 所有商品
    * b 用户评论
    * c 产品设置
    * d 商品规格
    * e 商品回收站

* 四：推广管理
    * a 广告列表
    * b 广告位置
    * c 优惠劵管理
    * d 专题管理
    * e 专题分类

* 五：订单管理
    * a 所有订单管理

* 六：系统管理
    * a 管理员列表
    * b 角色管理
    * c 菜单管理
    * d SQL监控
    * e 定时任务
    * f 参数管理
    * g 代码生成器
    * h 系统日志
    * i 文件上传
    * j 通用字典表