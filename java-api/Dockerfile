FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app
COPY target/quarkus-app/lib /app/lib/
COPY ../java-api/target/quarkus-app/*.jar /app/
COPY target/quarkus-app/app /app/app/
COPY target/quarkus-app/quarkus /app/quarkus/
EXPOSE 8080
CMD ["java", "-jar", "quarkus-run.jar"]
