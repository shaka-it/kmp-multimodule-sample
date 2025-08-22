# ---------- build stage ----------
FROM gradle:8.7.0-jdk21 AS build
WORKDIR /app
COPY .. .
RUN gradle :server:shadowJar --no-daemon

# ---------- runtime stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
ENV PORT=8080
EXPOSE 8080
COPY --from=build /app/server/build/libs/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]