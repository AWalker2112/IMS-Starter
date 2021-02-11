Coverage: 80.1%
# IMS Starter Project

The Inventory Management System (IMS) allows a user to create customers, items and orders. These are saved to a MySQL database through a CRUDController. This project has used a variety of different technologies to achieve it including JIRA, GitHub, JAVA, Maven, Junit and SonarQube.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The application as a minimum needs JAVA installed on the machine, this application was written in JAVA 14.0.2. Choose the correct installer for your operating system from the link below. 

```
https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html 
```
For running the application, the best option would be to use GitBash, GitBash for windows can be downloaded from the link below. 

```
https://gitforwindows.org/
```
Alternatively, you may run the program through the command line interface. 

For editing the application, the eclipse IDE is recommended which can be downloaded from the link below. 
```
https://www.eclipse.org/
```

### Installing

1. Start by choosing a folder you wish to save all the files of the application. 

2. Open Git Bash there.

3. Clone the repository to your local machine.

```
git clone https://github.com/AWalker2112/IMS-Starter.git
```
Now you are able to run the application or edit it in an IDE.

### Running the application

1. Choose the file directory the application and git repository are saved in. 

2. Open Git Bash there. 

```
java -jar ims-0.0.1-jar-with-dependencies.jar
```

The application should now be running, you may choose some options for the application, for example choosing:  

```
CUSTOMER
CREATE 
John 
Smith
```
This will create a customer with the name John Smith. 

There are additional features like READ and DELETE. 

### Editing the application

For editing the application  

1. Open Eclipse 

2. Open File -> Import 

3. Select "Existing Projects into Workspace" from the Selection Wizard 

4. Select Next to get the Import Wizzard. Browse to find the location of the Project 

5. Make sure the Project you want is checked, then hit Finish. 

## Running the tests

To run automated tests in eclipse 

1. Find the folder src/test/java 

2. Run Coverage as -> JUnit Test 

### Unit Tests 

The unit tests test each individual method in the application at the lowest possible level. 

Open the src/test/java folder 

Navigate to the JUnit test you wish to look at. 

Open the file and you will see each individual method test 

```
com.qa.ims 

controllers 

CustomerControllerTest.java 
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Alexander Walker** - *Continued Work* - [Alex Walker](https://github.com/AWalker2112)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

*QA Consulting for offering the training and expertise 

*Special thanks to Pawel Stypulkowski, Savannah Vaithilingam, Aswene Sivaraj and Jordan Harrison. 
