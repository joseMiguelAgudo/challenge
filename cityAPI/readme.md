<h1>CITY API</h1>
<h2>Building cityAPI</h2>
CityAPI has been developed using maven framework, building it is quite simple by invoking:
mvnw clean package
it will result into a .jar file under target directory
<h2>Running cityAPI</h2>
After having packaged the jar file, you just need to run it either from Eclipse file or from command line:
java -jar city-api-VERSION.jar
<h2>Using cityAPI</h2>
After having deployed the application, it will be reachible from the browser, you just need to enter:
http://localhost:8090/cityAPI/v2/api-docs
and it will display the application swagger
<h2>Frameworks used</h2>
<p>Maven: build and dependency management tool</p>
<p>SpringBoot: WebService framework, used for writting the service schema, annotations like @SpringBootApplication, @RestController are provided by this framework to abstract the developer from the tedious tasks of writting the entire web service from scratch</p>
<p>SpringFox: framework used to expose the service swagger, thanks to it, developer does not have to care about documenting the API, by default all the components of the application are scanned and documented</p>
<p>H2 Database: in memory database, it represents the data storage part of the service, thanks to its integration with Stringboot, only has to be declared at application.properties.  Even a initial dataload has been included by just filling data.sql file</p>
<p>JPA: Represents de data access layer of the service, by default typical database operation comes implemented by just creating the repository.  On top of that, custom queries can be written in an intuitive way</p>
<h2>Pending TODO'S</h2>
<h3>Docker</h3>
Connectivity has not been achieved, a known issue with win10 made it imposible to reach running containers, nevertheless, dockerfile and image has been created, they can be rebuild by typing:
<p>mvnw package dockerfile:build</p>
Image has been already created and made available at:
<a href="https://hub.docker.com/r/joseam/city-api/">CITY API IMAGE<a>
<h3>Security</h3>
Using @Secure annotation allows you to secure the access to your API, by means of JWT tokens and implementing an own header checker will allow you to control the access to your application
