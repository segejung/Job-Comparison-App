package edu.gatech.seclass.jobcompare6300;

import android.util.Pair;

public class JobDetails {

    private String title;
    private String company;
    private Pair<String, String> location;
    private int costOfLiving;
    private int yearlySalary;
    private int yearlyBonus;
    private int retirementBenefits;
    private int relocationStipend;
    private int trainingAndDevelopmentFund;

    private boolean isCurrentJob = false;

    public JobDetails(String title, String company, Pair<String, String> location, int costOfLiving,
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

}
