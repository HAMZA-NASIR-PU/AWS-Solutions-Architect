## HOW TO RUN CODE OF JAVA ?


- Create a new Maven Project

```sh
mvn -B archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```

- Clean and compile the maven project

```sh
mvn clean compile
```

- Run the maven project

```sh
BUCKET_NAME=my-java-example-bucket-1 mvn exec:java -Dexec.mainClass="com.mycompany.app.App"
```