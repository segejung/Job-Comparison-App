package edu.gatech.seclass.jobcompare6300;

import android.util.Pair;

public class JobRankDetails extends JobDetails {

    private double jobScore;

    private int salaryWeight;
    private int bonusWeight;
    private int retirementWeight;
    private int relocationWeight;
    private int trainingWeight;

    private int AYS;
    private int AYB;
    private int RBP;
    private int RS;
    private int TDF;


    public JobRankDetails(String title, String company, Pair<String, String> location, int costOfLiving,
                          int yearlySalary, int yearlyBonus, int retirementBenefits,
                          int relocationStipend, int trainingAndDevelopmentFund) {
        super(title, company, location, costOfLiving, yearlySalary, yearlyBonus, retirementBenefits,
                relocationStipend, trainingAndDevelopmentFund);

        // need to adjust these based on cost of living
        this.AYS = yearlySalary;
        this.AYB = yearlyBonus;
        this.RBP = retirementBenefits;
        this.RS = relocationStipend;
        this.TDF = trainingAndDevelopmentFund;

        this.jobScore = computeJobScore();
    }

    private double computeJobScore() {
        return AYS * salaryWeight + AYB * bonusWeight + (RBP * AYS / 100) * retirementWeight +
                RS * relocationWeight + TDF * trainingWeight;
    }
}
