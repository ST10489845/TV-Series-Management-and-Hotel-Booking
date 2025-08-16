package za.co.hotel;

public abstract class Room {
    private final String id;     // e.g., "2-05" (floor-room)
    private final double baseRate;

    protected Room(String id, double baseRate) {
        this.id = id;
        this.baseRate = baseRate;
    }

    public String getId() { return id; }

    public double getNightlyRate() {
        return baseRate * getRateMultiplier();
    }

    protected abstract double getRateMultiplier();
}
