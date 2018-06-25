package co.uk.bigredlobster.gol.patterns;

import co.uk.bigredlobster.gol.microtypes.PositionX;
import co.uk.bigredlobster.gol.microtypes.PositionY;
import co.uk.bigredlobster.gol.node.GridPosition;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class RPentanamoPattern implements IGolPattern {

    public static final List<GridPosition> life = ImmutableList.of(
            new GridPosition(new PositionX(32), new PositionY(6)),
            new GridPosition(new PositionX(33), new PositionY(6)),
            new GridPosition(new PositionX(31), new PositionY(7)),
            new GridPosition(new PositionX(32), new PositionY(7)),
            new GridPosition(new PositionX(32), new PositionY(8))
    );

    private static final int gridWidth = 40;
    private static final int gridHeight = 30;

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
