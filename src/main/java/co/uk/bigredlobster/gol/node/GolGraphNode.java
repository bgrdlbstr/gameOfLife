package co.uk.bigredlobster.gol.node;

import co.uk.bigredlobster.gol.microtypes.IsAliveNextGeneration;
import co.uk.bigredlobster.gol.microtypes.IsAliveNow;

import java.util.Objects;

public class GolGraphNode {
    public static final GolGraphNode TOP_LEFT = new GolGraphNode(IsAliveNow.EMPTY, GridPosition.TOP_LEFT);

    public final MutableState mutableState;
    public final GridPosition position;

    public GolGraphNode(final IsAliveNow isAliveNow, final GridPosition position) {
        this.mutableState = new MutableState(isAliveNow, IsAliveNextGeneration.EMPTY);
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GolGraphNode that = (GolGraphNode) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "GolGraphNode{" +
                "mutableState=" + mutableState +
                ", position=" + position +
                '}';
    }
}
