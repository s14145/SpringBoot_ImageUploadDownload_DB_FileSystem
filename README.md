![Generic badge](https://img.shields.io/badge/completion-90%25-green)
![GitHub repo size](https://img.shields.io/github/repo-size/s14145/SpringBoot_ImageUploadDownload_DB_FileSystem)
![GitHub last commit](https://img.shields.io/github/last-commit/s14145/SpringBoot_ImageUploadDownload_DB_FileSystem)
![Snyk Vulnerabilities for GitHub Repo](https://img.shields.io/snyk/vulnerabilities/github/s14145/SpringBoot_ImageUploadDownload_DB_FileSystem)


# Spring Boot Project for Image Upload Download to DB and File System

This application starts on port 6065.

The technologies used in this application are:

1. Spring Boot Web
2. Spring Boot Data JPA
3. Spring Boot Devtools
4. Lombok
5. Hibernate
6. H2 Database
7. Spring Boot Actuator
8. Transaction Management
9. Docker
10. Docker Hub
11. Github Actions for automating developer workflows (secrets used to login to docker hub are stored in this code repository "Settings" tab)


H2 Database URI: http://localhost:6065/h2-console

The REST Endpoints exposed by this application are:
1. POST Upload Image To DB : http://localhost:6065/api/v1/images/uploadToDB
2. GET Download Image From DB : http://localhost:6065/api/v1/images/downloadFromDB/{fileName}
3. POST Upload Image To File System : http://localhost:6065/api/v1/images/uploadToFileSystem
4. GET Download Image From File System : http://localhost:6065/api/v1/images/downloadFromFileSystem/{fileName}
5. DELETE Image From DB : http://localhost:6065/api/v1/images/deleteFromDB/{fileName}
6. DELETE Image From File System : http://localhost:6065/api/v1/images/deleteFromFileSystem/{fileName}

Spring Boot Actuator Endpoints:
1. Health : http://localhost:6065/actuator/health
2. Metrics : http://localhost:6065/actuator/metrics
3. Env : http://localhost:6065/actuator/env
4. Beans : http://localhost:6065/actuator/beans
5. Threaddump : http://localhost:6065/actuator/threaddump
6. Heapdump : http://localhost:6065/actuator/headdump 

