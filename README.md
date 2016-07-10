
##介绍
* dove是一个微信业务服务应用

### 核心功能
* 通用的DAO、Service、Controller抽象，从CRUD中解放
* ……

### 技术选型

#### 管理
* gradle依赖和项目管理
* git版本控制

#### 后端
* IoC容器 spring
* web框架 springmvc
* servlet 3.0(需要支持servlet3的servlet容器，如tomcat7)

#### 数据库
 * 目前只支持mysql，建议mysql5.5及以上

####
 * 本脚手架会选型技术的最新版本

###支持的浏览器
 * chrome
 * firefox（目前使用firefox 19.0.2测试）
 * opera 12
 * ie7及以上（建议ie9以上，获取更好的体验）
 * 其他浏览器暂时未测试


##如何运行

####1、到es/web/pom.xml修改数据库配置：
*  默认修改：profiles/profile/development下的
*  connection.admin.url
*  connection.username
*  connection.password

####2、到项目的根下
* cd bin
* install.bat 安装jar包到本地仓库（jdk6即可）
* create-db.bat 创建数据库（mysql需要5.5及以上 编码为utf-8）
* refresh-db.bat 创建schema和初始化data
* jetty.bat 启动web应用 默认端口9080 可以到/web/pom.xml下修改（servlet 2.5即可）

####3、注意
* 消息加解密：
1、提供接收和推送给公众平台消息的加解密接口(UTF8编码的字符串).
<ol>
	<li>第三方回复加密消息给公众平台</li>
	<li>第三方收到公众平台发送的消息，验证消息的安全性，并对消息进行解密。</li>
</ol>
说明：异常java.security.InvalidKeyException:illegal Key Size的解决方案
<ol>
	<li>在官方网站下载JCE无限制权限策略文件（JDK7的下载地址：
 	http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html</li>
 	<li>下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt</li>
  	<li>如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件</li>
 	<li>如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件</li>
</ol>

2、/doc/说明/jce6、jce7存放JCE无限制权限策略文件，将local_policy.jar、US_export_policy.jar替换/JRE/lib/security
目录下的原来文件。

* 测试接入信息：
企业号接入地址：http://域名/dove/cp/callback
订阅、服务号接入地址：http://域名/dove/mp/callback
