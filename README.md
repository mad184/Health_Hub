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
6. clientTests: integration
7. staff: UserIDTest
8. staff: StaffModelTest
9. staff: IntegrationTesting
10. apiTests: APITest

**Note:** It has been tested that all unit testing were passing. I am just worried that since the
test suite is not thoroughly tested, it might cause an environment issue when another test case execute.

#### How to execute the test cases?

1. Open the test folder within your IDE
2. Run the Tests within specific packages