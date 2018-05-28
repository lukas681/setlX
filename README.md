# SetlX Sound Plugin 

This student research project adds sound and music functionality into setlX. This is done by integrating the open source java framework jFugue.

## Getting Started

```
addPattern("Test","C D E F G A B C6");
addPattern("Test2", "V0 Cq Dq Eq Fq V1 Rq Fq Gq Aq");
showMusic();
play("Test");
play("Test2");
```

### Prerequisites

You will need *java 8* (at least jre) in order to run the interpreter. I also recommend intelliJ for development, because of its good support for maven scripts.
You will also need maven (3+) in order to build setlX and a connection to maven central. (Check your proxy settings)

### Installing

First open the directory in IntelliJ. Make sure, that the directories java and test are marked as source/test-root. 

Then execute the interpreter/pom.xml with mvn install
```
$ mvn install
```
Now you can start the interpreter
```
$ ./setlX
```
and use the new functions. for example, try
```
addPattern("Test","C D E F G A B C");
play("Test");
```
Thats it! If you can not hear anything, check your volume settings.

## Running the tests

There are two types of tests in setlX. The first ones are pure jUnit 4 tests, the other ones setlx specific testfiles, that call functions and expect special return values.

The tests are automatically executed when distributing the software, but of course you can manually call jUnit within intellij (or your preferred development environment. vim of course!) 

## Deployment

There is a deployment script
```
./createDistribution 
```
or, if you do not have the time for the test cases:
```
-/createDistribution noTests
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JFugue](https://jfugue.org/) - Framework for sounnd plugin 

## Authors

* **Lukas Retschmeier** - *Initial work*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
