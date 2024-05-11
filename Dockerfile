FROM openjdk:17-oracle
RUN mkdir data-shared
COPY target/ms-new.jar ms-new.jar
EXPOSE 8023
ENTRYPOINT ["java","-jar","/ms-new.jar"]