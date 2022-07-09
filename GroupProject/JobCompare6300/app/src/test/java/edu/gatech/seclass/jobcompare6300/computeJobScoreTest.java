package edu.gatech.seclass.jobcompare6300;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import edu.gatech.seclass.jobcompare6300.JobRankDetails;

/***
 * Test the computeJobScore() method in DB helper class
 */
public class computeJobScoreTest {
    @Test
    public void testComputeJobScore() {
        String title = "Network Engineer";
        String company = "Cisco";
        String location = "Raleigh, NC";
        int costOfLiving = 100;
        int salary = 100000;
        int bonus = 10000;
        int retirementBenefits = 50;
        int relocationStipend = 1000;
        int trainingFund = 1000;

        JobRankDetails job = new JobRankDetails(title, company, location, costOfLiving, salary,
                bonus, retirementBenefits, relocationStipend, trainingFund, false);
        DataBaseHelper dbHelper = new DataBaseHelper(null);
        // set weights for AYS, AYB, RBP, RS, and TDF
        double[] weights = {2.0, 1.0, 1.0, 2.0, 2.0};
        double delta = 0.5;

        double actualScore = dbHelper.computeJobScore(job, weights);

        assertEquals(33000.0, actualScore, delta);
    }
}