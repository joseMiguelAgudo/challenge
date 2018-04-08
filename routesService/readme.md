<h1>Routes Service</h1>
<h2>Building routesService</h2>
routesService has been developed using maven framework, building it is quite simple by invoking:
mvnw clean package
it will result into a .jar file under target directory
<h2>Running routesService</h2>
After having packaged the jar file, you just need to run it either from Eclipse file or from command line:
java -jar routes-service-VERSION.jar
<h2>Using routesService</h2>
After having deployed the application, it will be reachable from the browser, you just need to enter:
http://localhost:8080/routesService/v2/api-docs
and it will display the application swagger
<h2>Frameworks used</h2>
<p>Maven: build and dependency management tool</p>
<p>SpringBoot: WebService framework, used for writing the service schema, annotations like @SpringBootApplication, @RestController are provided by this framework to abstract the developer from the tedious tasks of writing the entire web service from scratch</p>
<p>SpringFox: framework used to expose the service swagger, thanks to it, developer does not have to care about documenting the API, by default all the components of the application are scanned and documented</p>
<h2>Pending TODO'S</h2>
<h3>Docker</h3>
Connectivity has not been achieved, a known issue with win10 made it impossible to reach running containers, nevertheless, dockerfile and image has been created, they can be rebuild by typing:
<p>mvnw package dockerfile:build</p>
Image has been already created and made available at:
<a href="https://hub.docker.com/r/joseam/routes-service/">ROUTES SERVICE IMAGE<a>
<h3>Security</h3>
Using @Secure annotation allows you to secure the access to your API, by means of JWT tokens and implementing an own header checker will allow you to control the access to your application
