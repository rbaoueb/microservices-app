FROM eclipse-temurin:17
WORKDIR /app
COPY target/customer-service*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]