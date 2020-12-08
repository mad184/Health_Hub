# Group 1

## How to launch program
1. Launch Intellij and create project from vcs using git clone http
2. Mark src as source root directory
3. Mark test as test source directory
4. CLick on file -> project structure -> libraries
5. You'll now have to add all the listed libraries from maven
    I.    org.junit.jupiter:junit-jupiter:5.7.0
    II.   org.json:json:20200518 
    III.  org.jmock:jmock-junit5:2.12.0
    IV.   com.mashape.unirest:unirest-java:1.4.9
    V.    com.google.code.gson:gson2.8.6
    VI.   org.mongodb:bson:3.12.7
    VII.  org.mongodb:mongodb-driver-core:3.12.7
    VIII. org.mongodb:mongodb-driver:3.12.7
    IX.   org.openjfx:javafx-controls:13
    X.    org.openjfx:javafx-fxml:11.01
    XI.   org.openjfx:javafx-maven-plugin:0.0.5
    XII.  org.skyscreamer:jsonassert:1.5.0
 6. Once this is done go to modules in project structure and check every box and use the up arrow to move all the libraries
    above module source and bellow java 15. 
 7. Once this is done you'll want to download javafx 15 sdk from https://gluonhq.com/products/javafx/
    Make sure to download version 15.0.1 and for your operating system
 8. You'll now want to setup the run config, start by going to the run config setup in the upper right corner
        i. Set main class to healthub.views.LoginView\
        ii. under vm options enter
             --module-path
             (path to lib folder in javafx 15 you download)
             --add-modules
             javafx.controls,javafx.fxml
        iii. Make sure module it declared as the project
        iv. Make sure sdk is 15
9. You can now run the project using the green arrow beside run config
    i. If intellij says it cannot run or find main class go to file and click restart and invalidate.
    ii. May need to resetup run config after doing this    

## Testing Instructions

**Note:** Testing for specific package can be done individually. However, I recommend
executing the test in an orderly fashion as that is the way I have tested the test suite to
guarantee work

#### Here is the recommended order of test to execute
1. databaseTests: Unit
2. databaseTests: integration
3. healthhubTests: integration
4. healthHubUnitTests: FormValidationTesting
5. clientTests: unit
6. staff: UserIDTest
7. staff: StaffModelTest
8. staff: IntegrationTesting
9. apiTests: APITest

**IMPORTANT Note:** 

* It has been tested that all unit testing were passing. I am just worried that since the
test suite is not thoroughly tested, it might cause an environment issue when another test case execute.
* There are few issues with the tests. The client DOES NOT PERFORM POSTDELETE. Therefore,
it not recommended to run it. The issue was not about the actual code but more on the test Code
* Staff Integration Testing has some disabled test case due to an issue on the test code.
The StaffIntegrationTesting was illegally connected to the production database which may cause
issue when the actual user is using the application. Again, it is not an issue on the dev code
but more on the test code

#### How to execute the test cases?

1. Open the test folder within your IDE
2. Run the Tests within specific packages/ You can just run the whole Test Cases


