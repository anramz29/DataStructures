public class BloodData {
    enum BloodType{
        O, A, B, AB
    }
    enum RhFactor{
        Positive("+"), Negative("-");

        private final String symbol;

        RhFactor(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
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

    public BloodType getBloodType() {
        return bloodType;
    }

    public RhFactor getRhFactor() {
        return rhFactor;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public void setRhFactor(RhFactor rhFactor) {
        this.rhFactor = rhFactor;
    }

    public String toString(){
        return "Blood Type " + bloodType + ", RhFactor is " + rhFactor;
    }
}
