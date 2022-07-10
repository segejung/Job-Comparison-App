# Test Plan

**Author**: Team118

## 1 Testing Strategy

### 1.1 Overall strategy

We first create unit tests for each component in the UML class diagram that we previously came up with, which is based on the initial requirements for this application. This involves functionally testing each feature (e.g. the current job details can be saved for the user), one component at a time, before moving on to integration testing that involves one or more components. While most unit tests as well as regression tests are written using JUnit, there are some test cases where UI testing is also necessary for the integration testing (entering the job offers and comparing them is one example). The system testing strategy will leverage more complex use cases that would mock a series of actions that a single typical user would perform; this ensures that the application yields a correct behavior despite touching on multiple components. Lastly, error validation checks shall also be implemented in order to prevent any illegal actions that can be performed by a user.

We will have a designated tester to perform these activities in addition to the team members who will also help test it upon the alpha release.

### 1.2 Test Selection

In order to select the test cases, we will primarily use black-box testing. Specficially, at the unit testing level, category partitioning method will be used to derive test cases and ensure that we cover all cases, appropriately handling the error for some inputs. At the system testing level, we will use the FSM model to validate the states and the transitions among them in order to ensure the functionality is working as intended.


### 1.3 Adequacy Criterion

To assess the quality of the test cases, functional coverage is used at both the unit and the system testing level, though more heavily in the latter. Structural coverage in non-system level testing will utilize the coverage tool in an IDE (e.g. method usage). There will be some manual testing and analysis from developers and the tester to ensure that all the requirements are met.

### 1.4 Bug Tracking

Any enhancement request or bug will be tracked in a text document in the repository, given the single-user nature of the app. As the list grows, however, we may opt to use a more formal, dynamic bug-tracking system such as a Trello board.

### 1.5 Technology

JUnit is used to automate the functional testing for applicable test cases, namely computing the job score.

## 2 Test Cases


Test case ID | Purpose | Steps | Expected result | Actual result | Pass/Fail
:---: | :---: | :---: | :---: | :---: | :---: |
U1.1 | User can enter the current job | Fill out the job details and click Save | currentJob is updated in JobHandler and saved in the SQLite database | As expected | Pass
U1.2 | User can cancel entering the current job | Fill out the job details and click Cancel | if currentJob is null, it is not created and saved in the database | As expected | Pass
U1.3 | Location field in the job entry is valid (letters only) | Enter a city containing a number (e.g. "29 palms") | Error is thrown | As expected | Pass
U1.4 | Cost Of Living index in the job entry is between 1-500 | Enter the number 700 in the field | Error is thrown | As expected | Pass
U1.5 | The retirement benefits in the job entry is between 0-100 | Enter the number 102 in the field | Error is thrown | As expected | Pass
U1.6 | The training and development fund in the job entry is between 0 to 18000 | Enter the number 20000 in the field | Error is thrown | As expected | Pass
U2.1 | User can enter a job offer | Fill out the offer details and save | jobOffer is updated in JobOfferHandler and saved in the SQLite database | As expected | Pass
U2.2 | User can compare the offer with the current job | Enter the current job details and enter the job offer details, and then invoke compareWithCurrent() | Table is shown with the two jobs containing the correct details | As expected | Pass
U2.3 | User cannot compare the offer with the current job that is not entered | Enter the job offer details only (no current job) and then invoke compareWithCurrent() | Error is thrown | As expected | Pass
U3.1 | A list of jobs is displayed for comparison | Enter the current job details and enter two job offer details, and then click Compare Jobs, invoking compareJobOffers() | jobs List in JobComparer contains the correct three JobRankDetails and a list shows each as Title and Company | As expected | Pass
U3.2 | The job score is correctly computed with default settings | Enter the current job details only with: AYS (100000 where COL is 100 and YS is 100000), AYB (10000), RBP (50), RS (1000), TDF (1000). Invoke computeJobScore() | 32400.0 | As expected | Pass
U3.3 | The job score is correctly computed with custom settings | Enter the current job details only with: AYS (100000 where COL is 100 and YS is 100000), AYB (10000), RBP (50), RS (1000), TDF (1000). Adjust settings: YS (2), YB (1), RBP (1), RS (2), TDF (2). Invoke computeJobScore() | 33000.0 | As expected | Pass
I1.1 | Saving the current job returns to the main menu | Enter the current job details and click Save | The Main Menu activity is displayed | As expected | Pass
I1.2 | Canceling the current job entry returns to the main menu | Enter the current job details and click Cancel | The Main Menu activity is displayed | As expected | Pass
I2.1 | The list of jobs in comparison containing different factors is correctly ranked | Set weights to (3,1,3,2,2). Enter the current job details (100000,10000,50,1000,1000). Enter offer 1 details (120000, 5000, 40, 1000, 5000). Enter offer 2 details (120000, 20000, 35, 3200, 4000). | Display in this order: Offer 1, Offer 2, Current job | As expected | Pass

## 3 Test case updates

- The test case to validate the State check in the original test plan is now removed since the location entry field will contain and validate both city and state.
- The expected result for the test case U3.2 is updated with the correct value because the original formula used to calculate the job score was incorrect.

## 4 Additional notes
- The tests were mostly conducted manually given the many different possible states for the system. This approach also made it easier to identify bugs during regression testing. 
- Although not automated, the test suites were continually run throughout the development such as ensuring that the correct job offer entry is retrieved from the database.
- The input validation checks for the weight settings are unnecessary since the weight limits are built-in by the design through sliders.
- Due to non-trivial architectural changes that were made throughout the later stagse of construction and transition (for example, decoupling the weight setting attributes from JobRankDetails class to independent table in the database), the original test plan (that aimed for a substantial unit test coverage) was revised, in order to allow more time and accommodate these changes.