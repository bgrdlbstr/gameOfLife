package co.uk.bigredlobster.gol.microtypes;

public class PositionY extends IntegerMicroType {
    public static final PositionY EMPTY = new PositionY(IntegerMicroType.EMPTY.value);

    public PositionY(int y) {
        super(y);
    }
}
