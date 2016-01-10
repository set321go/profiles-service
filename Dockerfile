FROM develar/java:latest

ADD build/installShadow/profiles-service/lib/profiles-service-all.jar profiles-service/lib/profiles-service-all.jar

EXPOSE 8080

CMD ["-jar", "profiles-service/lib/profiles-service-all.jar"]