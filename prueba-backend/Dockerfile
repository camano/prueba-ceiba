FROM openjdk:21-jdk-slim
COPY "./target/prueba-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9001
ENTRYPOINT ["java","-jar","app.jar" ]