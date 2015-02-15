# GEDCOM Coding Challenge

**GedcomParser.java**
Main file to generate XML file.

**GedcomTestSuite.java**
Main file run junit test cases. 

# Sample configuration file 
src/main/resources/config.properties

format of the output file to represent Genealogical data.
**output.file.format**=XML

path to the text file storing Genealogical data.
**path.input.file**=src/test/resources/data/**gedcom.txt**

path to the output file, as specified by the file format
**path.output.file**=src/test/resources/**output-data.xml**

# Default Genealogical Data file
src/main/resources/**gedcom.txt**

# Setup
java version "1.7.0_65"

Apache Maven 3.2.2

Junit 4

Import as eclipse java project. 
GitHub - https://github.com/tushmish/aconex

# How to Run in Eclipse
1. Run GedcomParser as Java application.
2. Run the test suite GedcomTestSuite to excute test cases.

Run -> Maven install. 

1. executes all test cases.
2. creates a jar file aconex.jar in target folder.

# Generate XML	
1. Generate XML using configuration file

**java -jar aconex.jar <path_to_config_file>**	

2. Generate XML using default configuration.
Generates XML file using default genealogical data file. XML file named gedcom.xml gets generated.

**java -jar aconex.jar** 

# Test Results
Tested on Mac OS X 10.9.5, 64 bit and Ubuntu 12.04 LTS. 64 bit

**Sample generated XML** src/test/resources/**sample-output.xml**

**Sample jar generating xml** - release/**aconex.jar**


