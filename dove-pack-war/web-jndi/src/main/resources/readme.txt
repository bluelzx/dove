how to set up jndi datasource in tomcat6

a. conf/context.xml

b. add a resource node to root node:
  	<Resource name="dove" auth="Container" type="javax.sql.DataSource"
               maxActive="100" maxIdle="30" maxWait="10000"
               username="sample_dev" password="sample_dev" driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/wcsb"/>
	<Resource name="sinovatech-sample2" auth="Container" type="javax.sql.DataSource"
               maxActive="100" maxIdle="30" maxWait="10000"
               username="sample_test" password="sample_test" driverClassName="oracle.jdbc.driver.OracleDriver"
               url="jdbc:oracle:thin:@192.168.2.16:1521:ecom"/>

BTW. Do not forget to change your dialect in hibernate.properties
