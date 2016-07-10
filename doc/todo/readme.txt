开发说明


-----------------------------------
一、使用Git、Maven工程


二、开发、测试的菜单地址：http://127.0.0.1:8080/dove-web/


三、dove-dev-env供开发人员使用
作用：
1）、提供开发使用的数据源
2）、备份本地数据库的表记录（发布应用和运行单元测试）
3）、将数据初始化到本地数据库表中（发布应用和运行单元测试）

备份数据的操作步骤：
1）、打开该应用下register-context.xml文件下的
<bean class="com.gustz.sample.dev.data.BackupDataBean">... 的配置后运行单元测试或发布应用。
2）、把该应用/target/datas/下模块生成的xml文件内容，复制到/src/main/resources/data/对应的xml文件里。
3）、提交SVN/GIT


初始化数据的操作步骤：
1）、打开该应用下register-context.xml文件下的
<bean class="com.gustz.sample.dev.data.UseDataBean">... 的配置后发布应用。
2）、发布应用和运行单元测试后即可
3）、注释已打开的配置项

四、.classpath、.project、.setting文件不要提交SVN



