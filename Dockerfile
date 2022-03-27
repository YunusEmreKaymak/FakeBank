FROM openjdk:17

EXPOSE 8080

WORKDIR /FakeBank

ADD target/FakeBank.jar FakeBank.jar

ENTRYPOINT ["java", "-jar", "FakeBank.jar"]


