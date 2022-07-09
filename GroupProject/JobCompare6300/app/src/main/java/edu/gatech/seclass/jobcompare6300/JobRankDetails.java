package edu.gatech.seclass.jobcompare6300;

import android.util.Pair;

public class JobRankDetails extends JobDetails {

    private double jobScore;

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
