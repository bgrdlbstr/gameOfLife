package co.uk.bigredlobster.gol.microtypes;

class IntegerMicroType extends MicroType<Integer> {
    static final IntegerMicroType EMPTY = new IntegerMicroType(Integer.MIN_VALUE);

    IntegerMicroType(int value) {
        super(value);
    }
}
