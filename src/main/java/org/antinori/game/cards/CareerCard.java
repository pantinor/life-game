package org.antinori.game.cards;

public class CareerCard extends Card {

    public static final String TITLE_CC = "COLLEGE CAREER";
    public static final String TITLE_CAR = "CAREER";
    public static final String COLLEGE_DEGREE_REQ = "Requires a College Degree ";

    public static final String CAREER_SALESPERSON = "SALESPERSON";
    public static final String CAREER_MECHANIC = "MECHANIC";
    public static final String CAREER_POLICE_OFFICER = "POLICE OFFICER";
    public static final String CAREER_ENTERTAINER = "ENTERTAINER";
    public static final String CAREER_ATHLETE = "ATHLETE";
    public static final String CAREER_HAIR_STYLIST = "HAIR STYLIST";

    public static final String CAREER_TEACHER = "TEACHER";
    public static final String CAREER_VETERINARIAN = "VETERINARIAN";
    public static final String CAREER_LAWYER = "LAWYER";
    public static final String CAREER_DOCTOR = "DOCTOR";
    public static final String CAREER_COMPUTER_DESIGNER = "COMPUTER DESIGNER";
    public static final String CAREER_ACCOUNTANT = "ACCOUNTANT";

    public enum CareerType {

        TYPE_SALESPERSON, TYPE_MECHANIC, TYPE_POLICE_OFFICER, TYPE_ENTERTAINER, TYPE_ATHLETE, TYPE_HAIR_STYLIST, TYPE_TEACHER, TYPE_VETERINARIAN, TYPE_LAWYER, TYPE_DOCTOR, TYPE_COMPUTER_DESIGNER, TYPE_ACCOUNTANT
    };

    private int base_salary = 0;
    private int max_salary = 0;
    private int taxes_due = 0;
    private CareerType careerType;

    public CareerCard(Type base_type, CareerType type, int base, int max, int tax) {

        this.type = base_type;
        this.base_salary = base;
        this.max_salary = max;
        this.taxes_due = tax;
        this.careerType = type;

        switch (type) {
            case TYPE_SALESPERSON:
                title = TITLE_CAR;
                description = CAREER_SALESPERSON;
                break;
            case TYPE_MECHANIC:
                title = TITLE_CAR;
                description = CAREER_MECHANIC;
                break;
            case TYPE_POLICE_OFFICER:
                title = TITLE_CAR;
                description = CAREER_POLICE_OFFICER;
                break;
            case TYPE_ENTERTAINER:
                title = TITLE_CAR;
                description = CAREER_ENTERTAINER;
                break;
            case TYPE_ATHLETE:
                title = TITLE_CAR;
                description = CAREER_ATHLETE;
                break;
            case TYPE_HAIR_STYLIST:
                title = TITLE_CAR;
                description = CAREER_HAIR_STYLIST;
                break;
            case TYPE_TEACHER:
                title = TITLE_CC;
                description = CAREER_TEACHER;
                break;
            case TYPE_VETERINARIAN:
                title = TITLE_CC;
                description = CAREER_VETERINARIAN;
                break;
            case TYPE_LAWYER:
                title = TITLE_CC;
                description = CAREER_LAWYER;
                break;
            case TYPE_DOCTOR:
                title = TITLE_CC;
                description = CAREER_DOCTOR;
                break;
            case TYPE_COMPUTER_DESIGNER:
                title = TITLE_CC;
                description = CAREER_COMPUTER_DESIGNER;
                break;
            case TYPE_ACCOUNTANT:
                title = TITLE_CC;
                description = CAREER_ACCOUNTANT;
                break;
        }

        //description = description += " Base Salary $" + base_salary + (max_salary == -1 ? " No Max Salary" : " Max Salary $" + max_salary) + " Pay $" + taxes_due + " on Taxes Due";
    }

    public int getBase_salary() {
        return base_salary;
    }

    public int getMax_salary() {
        return max_salary;
    }

    public int getTaxes_due() {
        return taxes_due;
    }

    public CareerType getCareerType() {
        return careerType;
    }

}
