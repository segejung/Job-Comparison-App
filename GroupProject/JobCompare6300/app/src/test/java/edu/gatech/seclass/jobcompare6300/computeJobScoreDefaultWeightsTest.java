package edu.gatech.seclass.jobcompare6300;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/***
 * Test the computeJobScore() method in DB helper class
 */
public class computeJobScoreDefaultWeightsTest {
    @Test
    public void testComputeJobScoreDefaultWeights() {
        String title = "Solution Architect";
        String company = "AWS";
        String location = "Seattle, WA";
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
        double[] weights = {1.0, 1.0, 1.0, 1.0, 1.0};
        double delta = 0.5;

        double actualScore = dbHelper.computeJobScore(job, weights);

        assertEquals(32400.0, actualScore, delta);
    }
}