package edu.gatech.seclass.jobcompare6300;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import edu.gatech.seclass.jobcompare6300.JobRankDetails;

public class JobRankDetailsTest {
    @Test
    public void testJobScore() {
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

        int salaryWeight = 2;
        int bonusWeight = 1;
        int retirementWeight = 1;
        int relocationWeight = 2;
        int trainingWeight = 2;

        job.setSalaryWeight(salaryWeight);
        job.setBonusWeight(bonusWeight);
        job.setRetirementWeight(retirementWeight);
        job.setRelocationWeight(relocationWeight);
        job.setTrainingWeight(trainingWeight);

        double delta = 0.5;

        double actualScore = job.getJobScore();

        assertEquals(37714.29, job.getJobScore(), delta);
    }
}