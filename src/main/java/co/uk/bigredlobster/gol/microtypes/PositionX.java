package co.uk.bigredlobster.gol.microtypes;

public class PositionX extends IntegerMicroType {
    public static final PositionX EMPTY = new PositionX(IntegerMicroType.EMPTY.value);

    public PositionX(final int x) {
        super(x);
    }
}
