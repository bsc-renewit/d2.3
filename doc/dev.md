# Developer/Deployer Manual

## How to compile and run <a name="howtocompile"/>

To compile:

	mvn clean install -DskipTests -P renewit
	
Where `renewit` is the configuration and assembly profile that has been used for the RenewIT project.

To run:

	java -jar dist/target/demiurge.jar

## Repository structure <a name="projectstructure"/>

* `core` defines the core functionalities, models and interfaces of _Demiurge_.
* `drivers` implements the drivers for different infrastructure and monitoring managers.
* `assemblies` contains subprojects with different configurations of _Demiurge_, plus some extra code to adapt
  it to different environments.
* `client` implements a simple REST client to facilitate the integration of _Demiurge_.
* `frontend` contains subprojects for GUI and REST services (WIP).
