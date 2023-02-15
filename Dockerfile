FROM ibm-semeru-runtimes:open-17-jre-jammy

ADD target/svc.jar /opt/svc.jar

CMD ["java", "-jar", "/opt/svc.jar"]