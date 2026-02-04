# Simulation Exploration Experience (SEE) Baseplate

This repository contains a template designed to ease getting started with developing HLA federates in the [Simulation Exploration Experience (SEE)](https://www.simulationexplorationexperience.org) program.
It is intended to be used with the [SEE HLA Starter Kit Framework](https://www.github.com/SMASH-Lab/SEE-HLA-Starter-Kit) (SKF) and includes
a basic federate, math and geometry utility libraries, as well as implementations of the objects and interactions defined
in the SpaceFOM standard. It even includes classes for encoding/decoding SpaceFOM data types.

## Project Structure

The project uses [Apache Maven](https://maven.apache.org/) as its build system and is organized as follows:


```
.
├── src/main/java/
│   └── org.see.baseplate/
│       ├── encoding/
│       │   └── ...
│       ├── models/
│       │   ├── objects/
│       │   │   └── ...
│       │   └── interactions/
│       │       └── ...
│       ├── types/
│       │   └── ...
│       └── ExampleFederate.java
├── src/main/resources/
│   ├── foms/
│   ├── example_federate.conf
│   └── logback.xml
└── src/test/
    └── ...
```


The `org.see.baseplate` is the project root.
Subfolders are structured with the following intent:
* `encoding`: Coder classes that decode and encode HLA data types to and from their native Java equivalents.
* `models`: Java class definitions of HLA object and interaction classes mentioned in the SpaceFOM. It includes just about everything defined in v1.0 of the standard.
* `types`: Encapsulations of the data types defined in the SpaceFOM.

Every SKF simulation requires a configuration file (`*.conf` or `*.properties`) to start. An example is present in the `resources` directory.
This **has to be changed** to match your test environment. If you plan to use any FOM module extensions to the SpaceFOM, place them in the `foms` directory and uncomment the `fomDirectory` parameter in the configuration. (*N.B. The FOMs can be placed anywhere, this is just how it has been set up by default*.)

The `resources` directory is where supplementary files used by your federate will go,
such as the `logback.xml` file which can be used to interactively log data to the console.

If you want to add unit testing to your federate, the `src/test/java` directory would be the place to put all your test cases. 

### Dependencies

1. [Apache Commons](https://commons.apache.org/): The modules in the `commons-math` library provide solid, well-tested mathematical data structures. They are used here to implement the data types like `SpaceTimeCoordinateState` and `AttitudeQuaternion` described in the SpaceFOM standard.  
2. [SLF4J](https://www.slf4j.org/): Logging facade with support for multiple logging backends. `logback-classic` is already configured for this project, but you have the option of swapping it out for another backend.

> [!NOTE]
> This project structure can be modified to suit your requirements. The primary goal is to have it serve as a good starting point for developing HLA federates in SEE.

## Installation

First, clone the repository to your machine. Most dependencies should be resolved as soon as you load it into your IDE of choice (tested on Eclipse and IntelliJ).
The Pitch pRTI libraries, however, are not publicly hosted so you have two options:

1. Link the pRTI HLA 4 JAR libraries (`prti1516-hla4.jar`, `prticore.jar`, and `booster1516.jar`) from the RTI installation directory (typically under  `C:\Program Files\Pitch pRTI 6\libs` on Windows) to the project in your IDE. If you decide to pick this option, **comment out or entirely remove the pRTI dependency declarations** in `pom.xml` to prevent classpath conflicts. 
2. Generate the dependencies as third-party JARs
in your local Maven repository. See below for instructions.

The above also applies to the SKF dependency. Download a release version from [here](https://www.github.com/SMASH-Lab/SEE-HLA-Starter-Kit/releases) and either link it to your project manually or generate it as a third-party JAR in your local Maven repository. 

### Generating Dependencies for Local Maven Repository

A third-party JAR library for Maven can be generated as follows:

* Navigate to the root of your cloned version of this repository. Make sure that `pom.xml` is in the same directory level or this won't work (can be verified  by quickly running `dir` on Windows or `ls` on PowerShell/macOS/Linux).
* There are three main dependencies we want: `prti1516-hla4.jar`, `prticore.jar`, and `booster1516.jar`. Run the following commands to generate the dependencies (**remember to enter the path to the JAR files for `-Dfile` and set `-Dversion` to the pRTI version you have installed**):

```shell
$ mvn install:install-file -Dfile="<PATH-TO-JAR-FILE>" -DgroupId="se.pitch" -DartifactId="prti1516-hla4" -Dversion="6.1.0" -Dpackaging=jar
$ mvn install:install-file -Dfile="<PATH-TO-JAR-FILE>" -DgroupId="se.pitch" -DartifactId="prticore" -Dversion="6.1.0" -Dpackaging=jar
$ mvn install:install-file -Dfile="<PATH-TO-JAR-FILE>" -DgroupId="se.pitch" -DartifactId="booster1516" -Dversion="6.1.0" -Dpackaging=jar
```

* If you want to use the SEE HLA Starter Kit as a Maven dependency as well, then grab the latest release from GitHub and run the following command as before (**remember to set the correct pathname and version number**):

```shell
$ mvn install:install-file -Dfile="<PATH-TO-JAR-FILE>" -DgroupId="org.see" -DartifactId="skf" -Dversion="2.0.3" -Dpackaging=jar
```

This process is adapted from the official Maven documentation [here](https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html).

## Updating

When fetching a new version of Baseplate, there is a chance it relies on a different version of the framework. In this case, you will have to repeat the process of linking the SKF JAR library.
The same applies when upgrading to a new version of the Pitch pRTI: the pRTI JAR libraries have to be re-linked.

If Maven is being used to manage the SKF and pRTI dependencies, make sure to run `mvn clean` and `mvn install` operations to clear out old build data before doing so.
Likewise, the pRTI dependencies have to be rebuilt and re-linked to the project.
