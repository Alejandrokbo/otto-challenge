FROM openjdk:17

WORKDIR /app
COPY build/libs/petshop-1.0.jar /app/petshop-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "petshop-1.0.jar"]