public class BloodData {
    enum BloodType{
        O, A, B, AB
    }
    enum RhFactor{
        Positive, Negative;
    }
    private BloodType bloodType;
    private RhFactor rhFactor;

    // Normal Constructor, setting fields to O and +
    public BloodData() {
        this.bloodType = bloodType.O;
        this.rhFactor = RhFactor.Positive;
    }

    // OverLoaded Constructor
    public BloodData(BloodType bloodType, RhFactor rhFactor) {
        this.bloodType = bloodType;
        this.rhFactor = rhFactor;
    }
    // get methods
    public BloodType getBloodType() {
        return bloodType;
    }

    public RhFactor getRhFactor() {
        return rhFactor;
    }

}
