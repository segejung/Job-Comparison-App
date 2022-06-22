1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

To realize this requirement, I added a 'MainMenu' class where a GUI prompt (upon start of the app) would allow the user to do the operations listed above, respectively, by adding the following methods to the class: updateJobDetails(), addJobOffer(), adjustComparisonSettings(), and compareJobOffers().


2. When choosing to enter current job details, a user will:
	a. Be shown a user interface to enter (if it is the first time) or edit all the details of their current job, which consist of:
		i. 		Title
		ii. 	Company
		iii. 	Location (entered as city and state)
		iv.		Cost of living in the location (expressed as an index)
		v.		Yearly salary
		vi. 	Yearly bonus
		vii. 	Retirement benefits (as percentage matched) (given as an integer from 0 to 100 inclusive)
		viii.	Relocation stipend
		ix.		Training and development fund ($0 to $18000 inclusive annually)
	b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

To realize this requirement, I created a JobHandler class which will allow the user to enter the current job details. The JobDetails class handles this, encompassing all the necessary fields of the job (title, company, location, salary, etc). The field 'currentJob' in JobHandler will contain the details of at most one job (i.e. no current job or one current job), which is indicated in the design. The JobHandler also allows the user to save, cancel, or return to the main menu.

	
3. When choosing to enter job offers, a user will:
	a. Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.
	b. Be able to either save the job offer details or cancel.
	c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

To realize this requirement, I created a JobOfferHandler for job offers, similar to the JobHandler, but for the job offers only. The JobOfferHandler likewise contains a list of potentially multiple JobDetails, in addition to allowing the user to add another job offer or compare with the current job details (if it exists). In order to compare the offer with the current job, I included into the design an associated between JobOfferHandler and JobComparer to signify the call compareJobs() method from the former to the latter. 

	
4. When adjusting the comparison settings, the user can assign integer weights to:
	a. Yearly salary
	b. Yearly bonus
	c. Retirement benefits
	d. Relocation stipend
	e. Training and development fund 
	If no weights are assigned, all factors are considered equal.

To realize this requirement, I created a ComparisonSettings class where the user can navigate from the main menu to enter the settings, which contain the weight to each of the field listed above. The settings will be used by the JobScorer class, which is explained in the later requirements.
	

5. When choosing to compare job offers, a user will:
	a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
	b. Select two jobs to compare and trigger the comparison.
	c. Be shown a table comparing the two jobs, displaying, for each job:
		i. 		Title
		ii. 	Company
		iii. 	Location (entered as city and state)
		iv.		Cost of living in the location (expressed as an index)
		v.		Yearly salary
		vi. 	Yearly bonus
		vii. 	Retirement benefits (as percentage matched) (given as an integer from 0 to 100 inclusive)
		viii.	Relocation stipend
		ix.		Training and development fund ($0 to $18000 inclusive annually)
	d. Be offered to perform another comparison or go back to the main menu.

To realize this requirement, I added a JobComparer class that would track a list of jobs which include both the current job and job offers. This new job list will be created when they are ranked by getting the offers from JobOfferHandler and the current job from JobHandler. Each job in the new list is added as a JobRankDetails, which is a type of JobDetails, but with the job score attribute added (see the following requirement). JobComparer then uses this score attribute for each job to rank and display them, as well as comparing two jobs with the details of the two compared jobs side by side. The table comparing two jobs will be handled within the GUI implementation when compareJobs() is invoked.
	

6. When ranking jobs, a job’s score is computed as the weighted sum of:

   AYS + AYB + (RBP * AYS / 100) + RS + TDF

   where:
   AYS = yearly salary adjusted for cost of living
   AYB = yearly bonus adjusted for cost of living
   RBP = retirement benefits percentage
   RS = relocation stipend
   TDF = training and development fund

   For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:

   2/7 * AYS + 1/7 * AYB + 1/7 * (RBP * AYS / 100) + 2/7 * RS + 1/7 * TDF
   
To realize this requirement, I added a JobScorer class to serve as a utility class where it calculates the job score by using the comparison settings entered and also each of the quantifiable job detail fields (salary, bonus, retirement benefits, etc). Every job represented as a JobRankDetails will contain this computed sum, represented as a double type. The jobScore will then be used by the JobComparer class as previously mentioned when ranking the jobs.


7. The user interface must be intuitive and responsive.

This is not represented in my design because it will be handled entirely within the GUI implementation.


8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

To realize this requirement, I created the design as if to represent the single system which is simply the single-user job offer comparison. 
	
