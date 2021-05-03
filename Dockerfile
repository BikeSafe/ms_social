FROM openjdk:11.0.10
EXPOSE 8084
WORKDIR /app
COPY target/ms_social-0.0.1.jar .
ENTRYPOINT [ "java", "-jar", "ms_social-0.0.1.jar" ]