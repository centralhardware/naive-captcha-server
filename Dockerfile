FROM maven:3.6.3-openjdk-11 as maven

COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package

FROM openjdk:11

WORKDIR /znatokiBot

COPY --from=maven target/test2-1.0-SNAPSHOT.jar .

CMD ["java", "-jar", "test2-1.0-SNAPSHOT.jar" ]