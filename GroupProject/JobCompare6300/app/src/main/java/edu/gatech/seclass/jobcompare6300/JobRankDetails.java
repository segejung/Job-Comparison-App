package edu.gatech.seclass.jobcompare6300;

import android.util.Pair;

public class JobRankDetails extends JobDetails {

    private double jobScore;

    private int salaryWeight;
    private int bonusWeight;
    private int retirementWeight;
    private int relocationWeight;
    private int trainingWeight;
    private boolean currentJob;

    private int AYS;
    private int AYB;
    private int RBP;
    private int RS;
    private int TDF;


    public JobRankDetails(String title, String company, String location, int costOfLiving,
                          int yearlySalary, int yearlyBonus, int retirementBenefits,
                          int relocationStipend, int trainingAndDevelopmentFund, boolean currentJobStatus) {
        super(title, company, location, costOfLiving, yearlySalary, yearlyBonus, retirementBenefits,
                relocationStipend, trainingAndDevelopmentFund,currentJobStatus);

        // need to adjust these based on cost of living
        this.AYS = yearlySalary;
        this.AYB = yearlyBonus;
        this.RBP = retirementBenefits;
        this.RS = relocationStipend;
        this.TDF = trainingAndDevelopmentFund;

        this.jobScore = computeJobScore();
    }

    private double computeJobScore() {
        return AYS * (salaryWeight/7) + AYB * (bonusWeight/7) + (RBP * AYS / 100) * (retirementWeight/7) +
                RS * (relocationWeight/7) + TDF * (trainingWeight/7);
    }

    public double getJobScore() {
        return this.jobScore;
    }

    public double getSalaryWeight() { return this.salaryWeight; }

    public double getBonusWeight() { return this.bonusWeight; }

    public double getRetirementWeight() { return this.retirementWeight; }

    public double getRelocationWeight() { return this.relocationWeight; }

    public double getTrainingWeight() { return this.trainingWeight; }

    public int getAYS() { return this.AYS; }

    public int getAYB() { return this.AYB; }

    public int getRBP() { return this.RBP; }

    public int getRS() { return this.RS; }

    public int getTDF() { return this.TDF; }
}
