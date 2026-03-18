public class Doctor extends Person {
    private int salary;
    private String specialty;
    private boolean vacation;

    public Doctor(String name, int id, int age, String gender, int phoneNumber, int salary, String specialty, boolean vacation) {
        super(name, id, age, gender, phoneNumber);
        
        this.salary = salary;
        this.specialty = specialty;
        this.vacation = vacation;
    }

    
}
