public class Patient {

    private int idNumber;
    private int age;
    private BloodData bloodData;

    // default constructor as specfied in the instructions
    public Patient() {
        this.idNumber = 0;
        this.age = 0;
        this.bloodData = bloodData;
    }
    //Overload constructor
    public Patient(int idNumber, int age, BloodData bloodData) {
        this.idNumber = idNumber;
        this.age = age;
        this.bloodData = bloodData;
    }
    // Id number getter
    public int getIdNumber() {
        return idNumber;
    }
    // Age getter
    public int getAge() {
        return age;
    }
    // Blood Data getter
    public BloodData getBloodData() {
        return bloodData;
    }
}
