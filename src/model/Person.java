package src.model;
public abstract class Person {
    private String name;
    private int id;
    private int age;
    private String gender;
    private String phoneNumber;

    public Person(String name, int id, int age, String gender, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public abstract void displayRole();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
    if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
        System.out.println("Invalid phone number.");
    } else {
        this.phoneNumber = phoneNumber;
    }
}

    @Override
    public String toString() {
        return "name: " + name + "\n" +
               "id: " + id + "\n" +
               "age: " + age + "\n" +
               "gender: " + gender + "\n" +
               "phone number: " + phoneNumber + "\n";
    }

}
