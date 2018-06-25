package co.uk.bigredlobster.gol.patterns;

import co.uk.bigredlobster.gol.microtypes.PositionX;
import co.uk.bigredlobster.gol.microtypes.PositionY;
import co.uk.bigredlobster.gol.node.GridPosition;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class BlinkerPattern implements IGolPattern {

    public static final List<GridPosition> life = ImmutableList.of(
            new GridPosition(new PositionX(1), new PositionY(2)),
            new GridPosition(new PositionX(2), new PositionY(2)),
            new GridPosition(new PositionX(3), new PositionY(2))
    );

    private static final int gridWidth = 10;
    private static final int gridHeight = 10;

    @Override
    public int getGridWidth() {
        return gridWidth;
    }

    @Override
    public int getGridHeight() {
        return gridHeight;
    }

    @Override
    public List<GridPosition> getLife() {
        return life;
    }

}
