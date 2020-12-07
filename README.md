# Group 1

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
2. Run the Tests within specific packages


