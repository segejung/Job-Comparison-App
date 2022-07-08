My accomplishments for this deliverable

- SQLite Integration continued with the creation of a table that held the job score weight adjustment settings
- Created DatabaseHelper methods 
  - getJobWeights() pulls the current job weights that are in the JOB_WEIGHT_TABLE in the database
  - changeJobWeights() updates the stored job score calculation weights based on set seek values
  - setWeightsDefault() on the first database instance creation sets the default values to 1.0 for each weight type
  - computeJobScore() migrated the calculation function from JobRankDetails class to the DatabaseHelper class for just in time calculations based on changing of weights and display of jobs
  - added functionality to getOffers()
    - Now it populates a list of JobRankDetails objects, which are then sorted based on the computed jobScore for each.
- Linked the Adjust Settings button to the database where it changes to the current seekbar value on each entry
- Adding visibility of job comparison option as a dependency of a minimum of two jobs in the database.