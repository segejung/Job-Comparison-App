package edu.gatech.seclass.jobcompare6300;

import android.util.Pair;

public class JobDetails {

    private String title;
    private String company;
    private String location;
    private int costOfLiving;
    private int yearlySalary;
    private int yearlyBonus;
    private int retirementBenefits;
    private int relocationStipend;
    private int trainingAndDevelopmentFund;

    private boolean isCurrentJob = false;

    //Constructors

    public JobDetails(){
        // Empty constructor
    }

    public JobDetails(String title, String company, String location, int costOfLiving,
                      int yearlySalary, int yearlyBonus, int retirementBenefits,
                      int relocationStipend, int trainingAndDevelopmentFund) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLiving = costOfLiving;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationStipend = relocationStipend;
        this.trainingAndDevelopmentFund = trainingAndDevelopmentFund;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public int getSalary() {
        return yearlySalary;
    }

    public void setSalary(int salary) {
        this.yearlySalary = salary;
    }

    public int getBonus() {
        return yearlyBonus;
    }

    public void setBonus(int bonus) {
        this.yearlyBonus = bonus;
    }

    public int getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(int retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public int getRelocationStipend() {
        return relocationStipend;
    }

    public void setRelocationStipend(int relocationStipend) {
        this.relocationStipend = relocationStipend;
    }

    public int getTrainingAndDevelopmentFund() {
        return trainingAndDevelopmentFund;
    }

    public void setTrainingAndDevelopmentFund(int trainingAndDevelopmentFund) {
        this.trainingAndDevelopmentFund = trainingAndDevelopmentFund;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", costOfLiving=" + costOfLiving +
                ", salary=" + yearlySalary +
                ", bonus=" + yearlyBonus +
                ", retirementBenefits=" + retirementBenefits +
                ", relocationAmount=" + relocationStipend +
                ", trainingFund=" + trainingAndDevelopmentFund +
                '}';
    }

        
}
