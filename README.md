# How to run

You will need Java 8 and maven to compile and run this project.
The main entry point is the `io.github.metacosm.beebuzziness.MatrixConverter` class. Javadoc is provided as well as unit tests.

Simply running: `mvn test` at the root of the project should run the tests as well as execute the main class with a set of
arguments that can be modified in the `exec-maven-plugin` section of the `pom.xml` file under the `configuration/arguments` section.
