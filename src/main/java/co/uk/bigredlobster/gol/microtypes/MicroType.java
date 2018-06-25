package co.uk.bigredlobster.gol.microtypes;

import java.util.Objects;

public class MicroType<T> {
    public final T value;

    MicroType(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MicroType<?> microType = (MicroType<?>) o;
        return Objects.equals(value, microType.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "MicroType{" +
                "value=" + value +
                '}';
    }
}
