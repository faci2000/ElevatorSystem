
# RUN yum update -y && yum install -y xorg libgl1-mesa-glx
# RUN apk --no-cache add ca-certificates wget && wget --quiet --output-document=/etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub && wget https://github.com/sgerrand/alpine-pkg-java-openjfx/releases/download/8.151.12-r0/java-openjfx-8.151.12-r0.apk && apk add --no-cache java-openjfx-8.151.12-r0.apk
# RUN apk add gtk+3.0
# RUN apk add  libswt-gtk-3-java
# FROM ubuntu:18.04
# COPY  out/artifacts/ElevatorSystem_jar tmp/
# COPY out/libs/ tmp/
# RUN apt-get update && apt-get install -y --no-install-recommends xvfb openjfx && rm -rf /var/lib/apt/lists/*
# WORKDIR /tmp/

# FROM openjdk:15

# ENTRYPOINT [ "java", "--module-path", "javafx-sdk-16/lib", "--add-modules", "javafx.controls,javafx.fxml,javafx.graphics", "-jar", "ElevatorSystem.jar", "-Dprism.verbose=true"]


FROM openjdk:8-jdk
RUN apt-get update && apt-get install -y --no-install-recommends xvfb openjfx && rm -rf /var/lib/apt/lists/*
COPY  out/artifacts/ElevatorSystem tmp/
WORKDIR /tmp
ENTRYPOINT [ "java", "-jar", "ElevatorSystem.jar"]