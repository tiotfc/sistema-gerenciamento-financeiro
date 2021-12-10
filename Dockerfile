FROM openjdk

WORKDIR /app

COPY /target/sistema-gerenciamento-financeiro-0.0.1-SNAPSHOT.jar /app/sistema-gerenciamento-financeiro-0.0.1.jar

ENTRYPOINT ["java","-jar","/app/sistema-gerenciamento-financeiro-0.0.1.jar"]

EXPOSE 8080