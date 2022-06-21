
# Assignment 5 for NRaphael7 [Semester: Summer 2022 CS 6300]

## Overview

## Requirements (Copied from the Assignment document with responses inserted)

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  
   - When choosing to enter current job details, a user will:
     - Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
       - Title, Company, Location (entered as city and state), Cost of living in the location (expressed as an index), Yearly salary, Yearly bonus, Retirement benefits (as percentage matched) (given as an integer from 0 to 100 inclusive), Relocation stipend, and Training and development fund ($0 to $18000 inclusive annually)
     - Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.


> Design Response for Requirement #1:
> 
> The qualities and or attributes of this class are captured by the Job Offer class, which holds the aforementioned attributes as well as methods to change the attributes of that class (setters and getters).
> 
> The ability to save the job details is left in the implementation of the JobEntryManipulator interface through the saveJobOfferObj() method, which is implemented by the Main Menu class. It also catches a boolean variable which can be sent on the OnClick method of the save or the cancel button present on the Job entry screen.
> 
> Therefore if a the cancel button is pressed a false boolean is given, while if the save button is pressed a true boolean is given.
> 
> Constraints will be applied to the retirement benefits variable as well as the training and development fund, where they can only be integer values between 0 and 100 and 0 and 18000 respectively.


2. When choosing to enter job offers, a user will:
      - Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
      - Be able to either save the job offer details or cancel.
      - Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

> Design Response for Requirement #2:
> 
> This is initially tied to the method of displayJobEntryPage within the Main Menu class, where a boolean flag is given. This is anticipated to be tied to a button on the main page and its onClick action.
> 
> After being shown the interface presented by the prior method then you have the option to save the job entry information, whose default method (saveJobOffer()) is implemented by the JobEntryManipulator interface. The Main Method Class will override the original functionality to pull the attributes entered in on the interface and store them in the buildJobOfferObj() method that is implemented from the same interface and also overriden by the Main Menu class. 
> 
> This will return a Job Offer object in the overriden version which can then be added to the User's job list using the addJobOfferToList() method, which is used only for singular additions.
> 
> Lastly once that action has been completed they can have the option to enter another offer. This is done via building the Job offer using the method from JobEntryManipulator after the completed action wipes all of the entry fields. They could also choose to return to the main menu using the returnToMainMenu() method provided by the Main Menu class or comparing the offer using displayJobComparison() by inserting the user's current job as the parameter of Job offer 1.
> 
>  This last action will require getting that object from the class using the getCurrentJobDetails() method within the User class. Once returned this Job Offer object will be passed to the displayJobComparison() function along with the prior filled in entry.


3. When adjusting the comparison settings, the user can assign integer weights to:
   - Yearly salary
   - Yearly bonus
   - Retirement benefits
   - Relocation stipend
   - Training and development fund 
   - If no weights are assigned, all factors are considered equal.

> Design Response for Requirement # 3:
> 
> When interpreting this requirement it made me think of designing and interface called Comparator, this way the unifying class of the Main Menu can implement these functions from the interface which ever way necessary.
> 
> Within this example the User which is an attribute of the Main Menu class, which implements the Comparator interface, would require the Main Menu class to invoke the changeJobWeightedSumParameters() method. This method only takes on 5 integers as described by requirement 3. This can be stored in an array and referenced by index. These values are then set as attributes within the Main Menu Class using the setWeightedSumParams() method.
>
> As mentioned in the requirements if no weights are assigned then all factors are considered equal, meaning all integer values are 1.

4. When choosing to compare job offers, a user will:
   - Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
   - Select two jobs to compare and trigger the comparison.
   - Be shown a table comparing the two jobs, displaying, for each job:
     - Title, Company, Location , Yearly salary adjusted for cost of living, Yearly bonus adjusted for cost of living, Retirement benefits, Relocation stipend, Training and development fund
   - Be offered to perform another comparison or go back to the main menu.

> Design Response for Requirement # 4:
> 
> A User selecting this option will invoke the displayFullJobList() method that is overriden by the Main Menu Class that implements the Comparator interface. When doing this the Main Menu class will already contain the array of Job Offers of the user and that variable will be passed to the interface method.
> 
> Once the user decides to compare just two off the list, job offers 1 and 2 will be passed to the selectForComparison() method where the attributes of each job can be retrieved using the getter methods of the Job Offer class itself. This information is then displayed accordingly on the table.
> 
> If the user would like to perform another comparison, that option will be available as an option on the bottom of that interface and tied to the displayFullJobList() method, which begins this cycle over again.
> 
5. When ranking jobs, a job’s score is computed as the weighted sum of:

   - AYS + AYB + (RBP * AYS / 100) + RS + TDF

   - where:
     - AYS = yearly salary adjusted for cost of living
     - AYB = yearly bonus adjusted for cost of living
     - RBP = retirement benefits percentage
     - RS = relocation stipend
     - TDF = training and development fund

   - For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:


   - 2/7 * AYS + 1/7 * AYB + 1/7 * (RBP * AYS / 100) + 2/7 * RS + 1/7 * TDF

The user interface must be intuitive and responsive.
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

> Design Response for Requirement # 5:
> 
> When calculating the weighted sums the jobList attribute within the Main Menu class needs to be iterated through. Thanks to the getJobWeightedSum() overridden method originating from Compartor interface this will be possible. 
> 
> They can then be subsequently set for the private attribute within the Main Menu class using the setJobListSums() method. These are then placed in a HashMap, with their score being the integer (key) the Job Offer (value) is tied to. If this attribute is then sorted from greatest to least, by keys, it will be ordered correctly for the displayed table for the displayFullJobList() method. 
> If the user would like to change the weighted sum parameters it can be done using the changeJobWeightedSumParameters() method overriden by the Main Menu class. After the successful change they are set for the Main Menu class using the setWeightedSumParams() before another comparison can be done for the entire job list.

## Design Considerations

- When designing this entire UML the unifying class present here in the diagram is the Main Menu class. It implements two interfaces, one for job comparison and one for job detail entry or manipulation, while containing relevant methods that only applied to those categories.
- It is essentially as described in the assignment the entry point of the application. 
- Implementing interfaces also allows me to create a default implementation of those same methods before they are overridden, which is helpful given the default conditions given to me in the requirements (i.e. all equal weights for the weighted sum integers)
- It also assists me with design given if there is a different type of user in the future, who will have a different type of comparator or entry style it allows for that method to be overriden more than once depending on the required actions.
- Also here are some assumptions I made
  - A User can exist with no jobs, but a Job offer can only have one user.
  - The main menu class has a user, which then can hold an aggregation of job offers or none at all, thus being prompted to enter at least two in for the comparison to be enabled.
  - Also, let's say a user does not have a current job yet, but has many job offers to compare before taking on a job, this should be accounted for in the fact that the user has the option of using the current job for comparison or just two jobs on the list.
    - This is also why the provision was made within the User class of having a currentJob Job Offer attribute was made as well as the hasACurrentJob boolean attribute.
  - There may be a need to refresh the user's memory about which integer values they chose for the weighted sum so having those readily on hand via getter functions in the Job Offer and could be displayed in for the user on whatever kind of interface necessary.