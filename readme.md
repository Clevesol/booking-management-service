
# Deployment Guide

Please follow below instructions to deploy the application


## Prerequisite


| Parameter | Type     | Download Link                |
| :-------- | :------- | :------------------------- |
| Apache Tomcat | `Application Server` | **Required**. https://tomcat.apache.org/download-90.cgi |
| JRE | `Java Runtime Environtment` | **Required**. https://openjdk.org/ |
| Maven | `Build Tool` | **Required**. https://maven.apache.org/download.cgi |
| Git | `Source Control` | **Optional**. https://git-scm.com/install/ |




## Deployment Steps


- 1] Building the source code

- 2] Deploying the application
  - a) Deploying on a standalone apache tomcat application server
  - b) Deploying on a docker container image


## 1]Buidling the source code

To build the source code, Either unzip the provied source code zip file or clone from below git repository

```bash
  git clone https://github.com/Clevesol/booking-management-service.git
```

Change directory to the source code root

```bash
  cd ./booking-service
```

Build the source code with Maven, Running below command inside the source code folder

```bash
 mvn clean install
```



## b) Deploying on a docker container image

Locate the runner.sh file in the source code directory. Provide execution permissions to script file with appropriate privilaged user
```bash
  chmod +x runner.sh
```

Run the runner.sh file with build app archive (booking-service.war)

```bash
  ./runner.sh /home/.../booking-service/target/booking-service.war
```
**Please note that both runner.sh and Dockefile should be in the same directory inorder to this application deployment on docker container**
## 2]Deploying the application

## a) Deploying in a standalone application server

After a succesful build from previous step, Find the built .war file from the source directory. **../booking-service/target/booking-service.war**

Locate the webapps folder of your apache tomcat installation. Found by a echo command

```bash
  echo $CATALINA_HOME
```

Copy the built .war file into the webapps folder, Additionally rename the **booking-service.war** file to **ROOT.war**

```bash
  mv ./booking-service.war ./ROOT.war
```

Start the application server. Run the start batch file depending on the OS of your system

**[Windows]**
```bash
  $CATALINA_HOME/bin/startup.bat
```
**[Mac]**
```bash
  $CATALINA_HOME/bin/startup.bat
```
**[Linux]**
```bash
  $CATALINA_HOME/bin/catalina.sh 
```


## Authors

- [@handeebanSelvadurai](https://github.com/Clevesol/)

