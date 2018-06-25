package co.uk.bigredlobster.gol.microtypes;

class BooleanMicroType extends MicroType<Boolean> {
    static final BooleanMicroType EMPTY = new BooleanMicroType(false);

    BooleanMicroType(Boolean value) {
        super(value);
    }
}
