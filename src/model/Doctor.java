package src.model;

public class Doctor extends Person {
    private double salary;
    private String specialty;
    private boolean vacation = false;

    public Doctor(String name, int id, int age, String gender, String phoneNumber, double salary, String specialty, boolean vacation) {
        super(name, id, age, gender, phoneNumber);
        this.salary = salary;
        this.specialty = specialty;
        this.vacation = vacation;
    }

    @Override
    public void displayRole() {
        System.out.println("Doctor: " + getName());
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setVacation(boolean vacation) {
        this.vacation = vacation;
    }

    public boolean getVacation() {
        return vacation;
    }

    public boolean isAvailable() {
        return !vacation;
    }

    public void salaryBonus() {
        salary += salary*1.1;
        System.out.println("New salary is: " + salary);
    }

    public void displayDoctor() {
        super.displayPerson();
        System.out.println("Salary: " + salary);
        System.out.println("Specialy: " + specialty);
        System.out.println("Vacation" + vacation);
    }

    @Override
    public String toString() {
        return super.toString() +
            "Salary: " + salary + "\n" +
            "Specialy: " + specialty + "\n" +
            "Vacation" + vacation + "\n";
    }
}
