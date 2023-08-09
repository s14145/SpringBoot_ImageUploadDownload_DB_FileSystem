FROM openjdk:20
LABEL Name="SpringBoot Image Upload Download To DB FileSystem"
EXPOSE 6065:6065
ADD target/SpringBoot_ImageUploadDownload_DB_FileSystem-0.0.1-SNAPSHOT.jar SpringBoot_ImageUploadDownload_DB_FileSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","SpringBoot_ImageUploadDownload_DB_FileSystem-0.0.1-SNAPSHOT.jar"]