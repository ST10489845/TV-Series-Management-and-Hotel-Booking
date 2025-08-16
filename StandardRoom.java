package za.co.hotel;

public class StandardRoom extends Room {
    public StandardRoom(String id, double baseRate) {
        super(id, baseRate);
    }
    @Override
    protected double getRateMultiplier() { return 1.0; }
}
