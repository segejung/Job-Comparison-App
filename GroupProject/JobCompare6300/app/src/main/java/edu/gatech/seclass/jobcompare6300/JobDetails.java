package edu.gatech.seclass.jobcompare6300;

public class JobDetails {

    private String title;
    private String company;
    private String location;
    private int costOfLiving;
    private int salary;
    private int bonus;
    private int retirementBenefits;
    private int relocationAmount;
    private int trainingFund;

    //Constructors

    public JobDetails() {
    }

    public JobDetails(String title, String company, String location, int costOfLiving, int salary, int bonus, int retirementBenefits, int relocationAmount, int trainingFund) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.costOfLiving = costOfLiving;
        this.salary = salary;
        this.bonus = bonus;
        this.retirementBenefits = retirementBenefits;
        this.relocationAmount = relocationAmount;
        this.trainingFund = trainingFund;
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
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getRetirementBenefits() {
        return retirementBenefits;
    }

    public void setRetirementBenefits(int retirementBenefits) {
        this.retirementBenefits = retirementBenefits;
    }

    public int getRelocationAmount() {
        return relocationAmount;
    }

    public void setRelocationAmount(int relocationAmount) {
        this.relocationAmount = relocationAmount;
    }

    public int getTrainingFund() {
        return trainingFund;
    }

    public void setTrainingFund(int trainingFund) {
        this.trainingFund = trainingFund;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", costOfLiving=" + costOfLiving +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", retirementBenefits=" + retirementBenefits +
                ", relocationAmount=" + relocationAmount +
                ", trainingFund=" + trainingFund +
                '}';
    }
}
