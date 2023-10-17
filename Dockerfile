FROM gradle:latest as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle assemble

FROM openjdk:11.0.16-jre-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/kotlin-api-0.0.1.jar .
CMD ["java", "-jar", "kotlin-api-0.0.1.jar"]

