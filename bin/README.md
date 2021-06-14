
# HWA - Swimr

Swimr is a web app where you can find places to swim. 

Client side is a web page layout (HTML, CSS, JavaScript), backend is developed in Java using Spring framework and the data is persisted in a MySQL database on a GCP instance.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


## Prerequisites


* #### [Operating system](https://github.com/Thealinivan/hwa-swimr#Operating-system-Windows-10)
* #### [Programming language](https://github.com/Thealinivan/hwa-swimr#Programming-language-Java-JDK)
* #### [Spring framework](https://github.com/Thealinivan/hwa-swimr#Spring-framework)
* #### [Build tool](https://github.com/Thealinivan/hwa-swimr#Build-tool-Maven)
* #### [Data storage environment](https://github.com/Thealinivan/hwa-swimr#Data-storage-MySQL-WorkBench)
* #### [Integrates Development Environment](https://github.com/Thealinivan/hwa-swimr#Integrated-Development-Environment-Eclipse-IDE)
	* ##### [Integrates Development Environment](https://github.com/Thealinivan/hwa-swimr#Integrated-Development-Environment-Eclipse-IDE)
	* ##### [Integrates Development Environment](https://github.com/Thealinivan/hwa-swimr#Integrated-Development-Visual-Studio-Code)
* #### [Version control](https://github.com/Thealinivan/hwa-swimr#Version-control-Git-Bash)



## Runing the project

* #### [Running on the local environment](https://github.com/Thealinivan/hwa-swimr#Running-the-project-in-development)
* #### [Testing](https://github.com/Thealinivan/hwa-swimr#Testing)
	* ##### [Unit testing](https://github.com/Thealinivan/thealinivan-assessment#Unit-Tests-JUnit)
	* ##### [Integration testing](https://github.com/Thealinivan/hwa-swimr#Integration-tests-Mockito)
	* ##### [Code quality testing](https://github.com/Thealinivan/hwa-swimr#Static-analysis-tests-SonarQube)
* #### [Deployment](https://github.com/Thealinivan/hwa-swimr#Deployment-Maven)



## Installing

A step by step series of examples that tell you how to get a development env running

### Operating-system-Windows-10
This software has been developed, tested and built on Windows 10 Operating System.
To avoid compatibility issues, is advised that development environment as well as deployment to be done on Windows 10 OS.

### Programming-language-Java-JDK
- Latest version and instalation steps here https://adoptopenjdk.net/
- Instalation type: UI
- Further setup: no
After instalation run the following command in the command line interface(CLI) to test instalation succes by checking current version of Java
```
java --version
```

### Spring-framework

Spring framework can be installed as an add-on tool to Eclipse IDE through their market place
Additional add-ons can be installed to enhace the development efficiency and speed (see bellow picture)

///Add screen shot of Eclipse Marketplace installed plugins

### Build-tool-Maven
- Latest version and instalation steps here https://maven.apache.org/download.cgi
*make sure you download the binary zip archive
- Instalation type: UI
- Further setup: create local variable
After instalation run the following command in the command line interface(CLI) to test instalation succes by checking current version of Maven
```
maven -version
```
A guide guide on how to setup environment variable fro Maven here https://maven.apache.org/configure.html

In terms of data storage current project is using a MySQL database as a cloud service from Google (GCP). Because the setup is similar to a local environment, instalation guidance for both options will be provided.

### Data-storage-MySQL-WorkBench

- Latest version and instalation steps here https://dev.mysql.com/downloads/mysql/8.0.html 
- Instalation type: UI
- Requires: Setting up a local variable after instalation

After instalation open command line interface(CLI) and navigate to your mysql root folder (the local route might be different on your computer) 
```
cd C:\Program Files\MySQL\MySQL Server 8.0\bin

```
Then run the following command in the command line interface(CLI) to test instalation succes by checking current version of MySQL
```
mysql --version
```
A guide on how to setup your local variable from CLI here https://dev.mysql.com/doc/refman/8.0/en/using-system-variables.html
If you prefer setting up you local variable from the Windows UI, this might help https://dev.mysql.com/doc/mysql-windows-excerpt/5.7/en/mysql-installation-windows-path.html#:~:text=On%20the%20Windows%20desktop%2C%20right,System%20Variable%20dialogue%20should%20appear.

Extend database to cloud(optional): MySQL cloud instance
- Start here Google Cloud Platform (GCP) https://cloud.google.com/sql/
- Instalation type: setting up cloud environment
- Requires:
	requires setting up a Google account
	requires setting up a MySQL cloud instance


- Will be installed from the MySQL package
- Instalation type: UI
- Further setup: Connect to a MySQL instance (local or cloud) and requires MySQL instance URL, user and password
A guide on how to connect to you MySQL instance in using MySQL Workbench here https://dev.mysql.com/doc/workbench/en/wb-getting-started-tutorial-create-connection.html

Create schema named <ims> in your MySQL database as you will need a chema to connect to from the IDE.
	
### Integrated-Development-Environment-Eclipse-IDE
	
- Latest version and instalation steps here https://www.eclipse.org/downloads/packages/installer
- Instalation type: UI

### Integrated-Development-Environment-Visual-Studio-Code
Latest version and instalation steps here https://code.visualstudio.com/download
Instalation type: UI
	
### Version-control-Git-Bash
- Latest version and instalation steps here https://git-scm.com/downloads
- Instalation type: UI


### Running-the-project-in-development

Open an instance of Git Bash and navigate to your projects folder
```
cd <your folder path>
```
Next by runing the following command, you will clone your repository to your local environment using the repository link https://github.com/Thealinivan/hwa-swimr.git
```
git clone https://github.com/Thealinivan/hwa-swimr.git
```
Next by runnning the following command you will navigate to the cloned project folder
```
cd hwa-swimr
```
As you may see, you are on <main>/<master> branch of the project and every contribution shoud be done by following feature-branch model. This is done by branching <dev> branch, followed by branching any feature branches from <dev> branch.
	

You can now open Eclipse IDE/Visual Studio Code and import the project folder. By running the program a Spring will launch a server to your local host
You can access your landing page at
```
localhost:8080
```
or
```
localhost:8080/index.html
```

///Screenshot with index.html
///Screenshot with update pop-up


``` 

### Testing

Testing coverage in excess of 80% is aimed as a total for the project to match the industry standard (see documentation folder for reports).

### Unit-tests-JUnit-Mockito 

As part of the testing process, each of the object, model and controller classes are pairing with a test class in which all methods of the given class are tested using JUnit.
If you have installed Juni as a prerequisite, you can simlply run the project or a single class as a Coverage -> JUnit Test. The individual, per method, tests will asses whether the output of a given method is matching a hard-coded pre-defined result in relation with the input of that method. This is to ensure all methods are fit for purpose within the program.
Unit testing has been attepted on every method of the Controller and Service classes targeting the boiler plate code for the current project. Further testing is encouraged.

```
JUnit tests can be runned from the Eclipse IDE
```

### Integration-Tests

Domain, Controllers and Service classes have been tested as part of the integration testing targeting the boiler plate code for the current project. Further testing is encouraged.
Main focus of the intergation testing was on the API functionality.
See repo documentation foro further reference and test reports.

```
Intergration tests can be runned from Eclipse IDE
```

### Static-analysis-tests-SonarQube

Further testing was done through Sonar Qube which provided a further overview over the project such as testing coverage bugs and code smells (see documentation folder for reports).
All bugs and most of the code smells have been removed after Sonar Qube test.
This is available at: https://docs.sonarqube.org/latest/setup/get-started-2-minutes/
Once downloaded extraction from archive is required and after you placed the folder with its contents in:
```
C:\Program Files (x86)\
```

simply run the folowing file (bin will contain different directories for other operating systems as well):
```
C:\Program Files (x86)\sonarqube-8.9.0.43852\bin\windows-x86-64\StartSonar
```

After addingthe required dependencies in pom.xml java file in your project open a CLI terminal in your respository source folder and type the following command:
```
mvn sonar sonar
```
The above command will run the tests and provide a full report at port 9000 in your local host (provided host is available to be used by Sonar Qube)

```
Following first Sonar Qube test, whle refactoring, 1 bug and 18 code smells have been removed (see documentation).
```

## Deployment-Maven


Provided you have succesfully installed Maven and all your Junit unit tests are succesful access your source folder (repository folder) from any CLI interface and run the following command:
```
mvn clean package
```
The above command will clean your project, run the test, and provided they are successfull, it will build 2 jar files (with and without the dependencies) in your source folder inside the directory name target

To start the server through the comand line interface open the terminal and navigate to the source folder (or open a CLI terminal form inside the source folder) and run the following command:
```
java -jar fat.jar
```

You can now access your landing page at
```
localhost:8080
```
or
```
localhost:8080/index.html
```
This will run the server the same way it is deployed in your IDE in the console, the difference being that you do not need an IDE anymore.

///Screenshot with index.html
///Screenshot with update pop-up



More documentation over the Website Mockups, Database ERD, Java classes UML Diagrams, test reports and risk assesement management see documentation folder in the source directory.




HWA - Swimr




## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Alin Ivan** - *Initial work* [thealinivan](https://github.com/thealinivan)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* #Oracle documentation, a formal way to approach a problem
* #StackOverFlow, an amazing community of developers
* #Team Birch for their inspiration and support

