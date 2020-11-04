# Database Readme file

## Summary
This branch has 3 main components: Reader, Writer and Dbms. Dbms is what SHOULD be
use to contact anything outside the package
These components exist within the src folder

## Testing

Unit and Integration testing has been done to this branch. They are inside the
test folder and within database.test package

## Libraries you need for database
* jdk 14-0.2.12
* org.jmock:jmock-junit5:2.12.0
* org.json:json:20200518
* org.junit.jupiter:junit-jupiter:5.7.0
* org.mongodb:bson:3.12.7
* org.mongodb:mongodb-driver-core:3.12.7
* org.mongodb:mongodb-driver:3.12.7

Note: If you need to, you can add these libraries through the 
File > Project Structure > Libraries > From Maven > Search the library > 
Download > Add to your project