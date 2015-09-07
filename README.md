Introduce
=====
This project is a O2O system for take-away. it contain two roles, customer and administrator.<br/>
It use the bootstrap as CSS framework. and use the jquery as JS library.</br>
It use the ssh framework to build the backend service, and make web page by JSP.<br/>
It use the mysql to store the table, and use the jedis as cache to speed up the web site.<br/>

Directory
=====
WEB-INF - backend java files<br/>
bin - shell script<br/>
res - configuration and resource files<br/>

Run
=====
You can access the root directory of this system in web browser, it will show the main page of this system.<br/>

Usage
====
1. after upload the sources, compile the project by eclipse.<br/>
2. install and deploy the nignx and tomcat in your server, config the nginx to transfer all requests to the tomcat, meanwhile install and deploy the mysql and redis.<br/>
3. create a directory "ibooking" in the tomcat/webapps, then copy all files into "ibooking" directory.<br/>
4. import the database backup files ibooking_mysql_db.sql into mysql at root directory.<br/>
5. you can access the web service by browser, play a customer or administrator role.<br/>

Design
=====
Please refer to my blog(Chinese):<br/>
http://blog.csdn.net/weiganyi/article/details/48261189<br/>
