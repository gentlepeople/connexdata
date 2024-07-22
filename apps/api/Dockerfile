FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew build --no-daemon || return 0

# ARG JAR_FILE=build/libs/*.jar

# COPY ${JAR_FILE} app.jar

EXPOSE 8080

CMD ["./gradlew", "bootRun"]


# ENTRYPOINT ["java","-jar","app.jar"]