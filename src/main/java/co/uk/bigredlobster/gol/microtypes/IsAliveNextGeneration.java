package co.uk.bigredlobster.gol.microtypes;

public class IsAliveNextGeneration extends BooleanMicroType {
    public static final IsAliveNextGeneration EMPTY = new IsAliveNextGeneration(BooleanMicroType.EMPTY.value);

    public IsAliveNextGeneration(Boolean value) {
        super(value);
    }
}
