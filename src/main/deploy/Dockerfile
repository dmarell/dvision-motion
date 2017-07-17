FROM java:8-jre
MAINTAINER daniel@marell.se
COPY dvision-motion-@project.version@.jar /dvision-motion.jar
RUN apt-get update && apt-get install -y libgtk2.0-0
EXPOSE 8080
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "dvision-motion.jar" ]
