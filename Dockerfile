FROM maven:3.9.0-eclipse-temurin-11
RUN mkdir /photon
WORKDIR /photon
COPY pom.xml /photon
COPY cucumber-junit-examples/src /photon/cucumber-junit-examples/src/
COPY cucumber-junit-examples/pom.xml /photon/cucumber-junit-examples
COPY cucumber-testng-examples/src /photon/cucumber-testng-examples/src/
COPY cucumber-testng-examples/pom.xml /photon/cucumber-testng-examples
COPY junit4-examples/src /photon/junit4-examples/src/
COPY junit4-examples/pom.xml /photon/junit4-examples
COPY junit5-examples/src /photon/junit5-examples/src/
COPY junit5-examples/pom.xml /photon/junit5-examples
COPY testng-xml/src /photon/testng-xml/src/
COPY testng-xml/pom.xml /photon/testng-xml
CMD mvn test -pl testng-xml -DtestngXmlFile=myDemoTests.xml -X



