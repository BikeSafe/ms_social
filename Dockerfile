FROM openjdk:11.0.10
EXPOSE 4006
WORKDIR /app
COPY target/ms_social-0.0.2.jar .
ENTRYPOINT [ "java", "-jar", "ms_social-0.0.2.jar" ]