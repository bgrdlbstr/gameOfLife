package co.uk.bigredlobster.gol.patterns;

import co.uk.bigredlobster.gol.node.GridPosition;

import java.util.List;

public interface IGolPattern {
    int getGridWidth();

    int getGridHeight();

    List<GridPosition> getLife();
}
