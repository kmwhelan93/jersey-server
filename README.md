This project was generated by a script on the Jersey tutorial page https://jersey.java.net/documentation/latest/getting-started.html

It requires Maven to use. Download Maven and add it to the build path.

To test: mvn clean test or run as test in eclipse. Note you may have to hit junit tab to see results
To run: mvn exec:java or just hit play from eclipse

If you want it in eclipse, you can use the m2eclipse plugin. After installing, go File -> import -> Maven, etc


I like this project because it has tests ready and configured. It would be nice if we could add Apache Tomcat or something.



On Database
schema can be found in schema.txt
For production, database is located at stardock.cs.virginia.edu/cs4720cem6at
For tests, database is located at localhost:3306/cs4720cem6at... so you need to run wamp or something
	For each test, database is cleared and rebuilt every time
NOTE: schema must be exactly the same on test and production server