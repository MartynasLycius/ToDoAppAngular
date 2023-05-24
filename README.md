# ToDoApp

## Task description
You need to make a Todo app with these requirements:
1. Page for listing all the todo items
2. Todo item add and edit forms (separate pages)
3. Todo item consists of (date, item name and description)
4. Pages must interact between each other logically. (You can go from list to new or edit and when saving go back to list)

All the other specific requirements are up to you

## Technologies used in this application
* AngularJS for frontend
* JavaEE for backend (Maven project)
* MySQL database

## JavaEE Application (Backend)
### Environment Setup
* Download JDK 1.8 or higher and configure
* Download Eclipse IDE for JavaEE Developers (https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)
* Download Wildfly 18.0.0.Final (.zip) (https://download.jboss.org/wildfly/18.0.0.Final/wildfly-18.0.0.Final.zip). Chage the port to 8089
* Add datasouce named 'TestDS' in the standalone.xml 
```
<datasource jndi-name="java:jboss/datasources/TestDS" pool-name="TestDS">
    <connection-url>jdbc:mysql://localhost:3306/todo</connection-url>
    <driver>mysql</driver>
    <security>
        <user-name>root</user-name>
        <password></password>
    </security>
</datasource>
```
* Add Wildfly server to Eclipse
* Download MySQL and install (ignore if already available). Create a database named 'todo'.
* Clone the repo and open the 'todo-service' in Eclipse
* Configure datasource in persistance.xml
```
<persistence-unit name="TestDS" transaction-type="JTA">
    <jta-data-source>java:jboss/datasources/TestDS</jta-data-source>
    <properties>
    	<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect"/>
	    <property name="hibernate.hbm2ddl.auto" value="create"/>
	    <property name="hibernate.show_sql" value="true"/>
	    <property name="javax.persistence.sql-load-script-source" value="META-INF/sql/data.sql"/>
    </properties>
</persistence-unit>
```
* Select project root > Run As > Run on server
* Test the resouce endpoint in postman:
* For All Items (GET Method)
```
http://localhost:8089/todo-service/resources/items
```

## AngularJS Web Application (Frontend)
### Environment Setup
* Download Node.js 14.x (https://nodejs.org/en/blog/release/v14.17.3)
* Download VSCode and install
* Open 'todo-web' form the previously cloned repository
* Run the project with the following commands:
```
npm install
```
```
gulp
```

