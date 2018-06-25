package co.uk.bigredlobster.gol.node;

import co.uk.bigredlobster.gol.microtypes.IsAliveNextGeneration;
import co.uk.bigredlobster.gol.microtypes.IsAliveNow;

import java.util.Objects;

public class MutableState {
    public IsAliveNow isAliveNow;
    public IsAliveNextGeneration isAliveNextGeneration;

    MutableState(final IsAliveNow isAliveNow, final IsAliveNextGeneration isAliveNextGeneration) {
        this.isAliveNow = isAliveNow;
        this.isAliveNextGeneration = isAliveNextGeneration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableState that = (MutableState) o;
        return Objects.equals(isAliveNow, that.isAliveNow) &&
                Objects.equals(isAliveNextGeneration, that.isAliveNextGeneration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isAliveNow, isAliveNextGeneration);
    }

    @Override
    public String toString() {
        return "MutableState{" +
                "isAliveNow=" + isAliveNow +
                ", isAliveNextGeneration=" + isAliveNextGeneration +
                '}';
    }
}
