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




    public JobRankDetails()
    {

    }

    public JobRankDetails(String title, String company, String location, int costOfLiving,
                          int yearlySalary, int yearlyBonus, int retirementBenefits,
                          int relocationStipend, int trainingAndDevelopmentFund, boolean isCurrentJob) {
        super(title, company, location, costOfLiving, yearlySalary, yearlyBonus, retirementBenefits,
                relocationStipend, trainingAndDevelopmentFund,isCurrentJob);
    }

    public void setJobScore(double jobScore) {
        this.jobScore = jobScore;
    }

    public double getJobScore() {
        return this.jobScore;
    }


    public int getSalaryWeight() {
        return salaryWeight;
    }

    public void setSalaryWeight(int salaryWeight) {
        this.salaryWeight = salaryWeight;
    }

    public int getBonusWeight() {
        return bonusWeight;
    }

    public void setBonusWeight(int bonusWeight) {
        this.bonusWeight = bonusWeight;
    }

    public int getRetirementWeight() {
        return retirementWeight;
    }

    public void setRetirementWeight(int retirementWeight) {
        this.retirementWeight = retirementWeight;
    }

    public int getRelocationWeight() {
        return relocationWeight;
    }

    public void setRelocationWeight(int relocationWeight) {
        this.relocationWeight = relocationWeight;
    }

    public int getTrainingWeight() {
        return trainingWeight;
    }

    public void setTrainingWeight(int trainingWeight) {
        this.trainingWeight = trainingWeight;
    }

    @Override
    public String toString() {
        return "JobRankDetails{" +
                "jobScore=" + jobScore +
                ", title='" + super.getTitle() + '\'' +
                ", company='" + super.getCompany() + '\'' +
                ", location='" + super.getLocation() + '\'' +
                ", costOfLiving=" + super.getCostOfLiving() +
                ", yearlySalary=" + super.getSalary() +
                ", yearlyBonus=" + super.getBonus() +
                ", retirementBenefits=" + super.getRetirementBenefits() +
                ", relocationStipend=" + super.getRelocationStipend() +
                ", trainingAndDevelopmentFund=" + super.getTrainingAndDevelopmentFund() +
                ", isCurrentJob=" + super.getIsCurrentJob() +
                '}';
    }
}
