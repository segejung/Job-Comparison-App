package edu.gatech.seclass.jobcompare6300;

import android.util.Pair;

public class JobRankDetails extends JobDetails {

    private double jobScore;



    private int jobID;
    private int salaryWeight;
    private int bonusWeight;
    private int retirementWeight;
    private int relocationWeight;
    private int trainingWeight;
    private boolean currentJob;


    public JobRankDetails(String title, String company, String location, int costOfLiving,
                          int yearlySalary, int yearlyBonus, int retirementBenefits,
                          int relocationStipend, int trainingAndDevelopmentFund, boolean currentJobStatus) {
        super(title, company, location, costOfLiving, yearlySalary, yearlyBonus, retirementBenefits,
                relocationStipend, trainingAndDevelopmentFund,currentJobStatus);
    }

    public void setJobScore(double jobScore) {
        this.jobScore = jobScore;
    }

    public double getJobScore() {
        return this.jobScore;
    }

    public double getSalaryWeight() { return this.salaryWeight; }

    public void setSalaryWeight(int salaryWeight) { this.salaryWeight = salaryWeight; }

    public double getBonusWeight() { return this.bonusWeight; }

    public void setBonusWeight(int bonusWeight) { this.bonusWeight = bonusWeight; }

    public double getRetirementWeight() { return this.retirementWeight; }

    public void setRetirementWeight(int retirementWeight) { this.retirementWeight = retirementWeight; }

    public double getRelocationWeight() { return this.relocationWeight; }

    public void setRelocationWeight(int relocationWeight) { this.relocationWeight = relocationWeight; }

    public double getTrainingWeight() { return this.trainingWeight; }

    public void setTrainingWeight(int trainingWeight) { this.trainingWeight = trainingWeight; }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    @Override
    public String toString() {
        return "JobRankDetails{" +
                "jobScore=" + jobScore +
                ", salaryWeight=" + salaryWeight +
                ", bonusWeight=" + bonusWeight +
                ", retirementWeight=" + retirementWeight +
                ", relocationWeight=" + relocationWeight +
                ", trainingWeight=" + trainingWeight +
                ", currentJob=" + currentJob +
                '}';
    }
}
