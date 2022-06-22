
# Design Description
This document is to detail the description of the design assigned accompanied by the UML diagram. This document elaborates on the diagram as well as addresses missing requirements that couldn't be shown in the diagram.

## Requirement - Main Menu
>**When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**

This requirement is represented by the main menu and the 4 actions that can be taken from that class. Each option leads the user to the associated interface to perform the necessary action.

The user can also return to this main menu from other actions such as save and cancel actions, but cannot be explicitly shown in the context of a UML diagram.

## Requirement - Current Job
>**Be shown a user interface to enter (if it is the first time) or edit all the details of their current job**<

The user interface itself cannot be conveyed. However, the UML does show the Job Details interface interacting with the current job class, which has all the specified parameters. 

>****Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu****

These functionalities of save and cancel exist as functions in the Job Details. Whiles these functionalities exist in the diagram, returning to the main menu after is not represented in my design, as it will be handled entirely within the GUI implementation.

## Requirement - Job Offer
>****Be shown a user interface to enter all the details of the offer, which are the same ones listed above for the current job.****

The UML does not represent this interface display, however it will be handled in the GUI implementation. This action would create a new Job object.

>**Be able to either save the job offer details or cancel.**

Saving the new Job offer would create a job object and save it into the JobOptions list.

>**Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).**

All of these options are listed as functionalities of the interface, however, the UML design does not show the navigation of where the user is directed towards as that functionality will be handled by the GUI.

## Requirement - Comparison Settings
>**When adjusting the comparison settings, the user can assign integer weights...**

This requirement is satisfied by the Comparison settings class. This class contains the 5 different weights that were specified in the requirements. 
> **If no weights are assigned, all factors are considered equal.**

This requirement is satisfied by the default value of all weights being 1. so if no weights are added, they are all equal.

## Requirement - Compare Job Offers
> **Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.**

This functionality cannot be shown in the UML diagram as it will be handled solely by the GUI implementation, using data related to the JobOptions class.

>**Select two jobs to compare and trigger the comparison.**

This requirement is satisfied by the compare function in the comparison class taking in 2 jobs as input parameters. These jobs can be either the related currentJob or other jobOffers

> **Be shown a table comparing the two jobs, displaying, for each job**

This functionality will be handled by the GUI implementation. The data for each job would be taken from the respective Job objects.

## Requirement - Ranking Jobs
These requirements could not be addressed using the UML diagram. The only representation in the diagram is the rank function in the Comparison class. This function takes in the data of the jobOffers, currentJob, and the comparison settings, and performs the math operation to get a ranking score. The GUI implementation would handle the visual placement of the jobs.

## Requirement - UI
>**The user interface must be intuitive and responsive.**

This is not represented in my UML design. It will be handled by the GUI implementation.

## Requirement - Single System
>**For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).**

This is not represented in my UML design. It will be handled by the GUI implementation.
