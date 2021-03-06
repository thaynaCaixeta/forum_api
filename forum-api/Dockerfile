FROM adoptopenjdk/openjdk15:alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} forum-api.jar
ENTRYPOINT ["java", "-jar", "/forum-api.jar"]