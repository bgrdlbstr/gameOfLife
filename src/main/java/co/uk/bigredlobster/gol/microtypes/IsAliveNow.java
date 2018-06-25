package co.uk.bigredlobster.gol.microtypes;

public class IsAliveNow extends BooleanMicroType {
    public static final IsAliveNow EMPTY = new IsAliveNow(BooleanMicroType.EMPTY.value);

    public IsAliveNow(Boolean value) {
        super(value);
    }
}
