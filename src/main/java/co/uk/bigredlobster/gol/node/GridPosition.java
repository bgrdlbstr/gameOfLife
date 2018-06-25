package co.uk.bigredlobster.gol.node;

import co.uk.bigredlobster.gol.microtypes.PositionX;
import co.uk.bigredlobster.gol.microtypes.PositionY;

import java.util.Objects;

public class GridPosition {
    public static final GridPosition TOP_LEFT = new GridPosition(new PositionX(0), new PositionY(0));

    public final PositionX x;
    public final PositionY y;

    public GridPosition(final PositionX x, final PositionY y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPosition position = (GridPosition) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "GridPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
