package co.uk.bigredlobster.gol;

import co.uk.bigredlobster.gol.microtypes.IsAliveNextGeneration;
import co.uk.bigredlobster.gol.microtypes.IsAliveNow;

public class LifeStatus {
    public static IsAliveNextGeneration nextGenerationIsAlive(final IsAliveNow isAlive, final long numNeighbours) {
        if (isAlive.value) {
            if (numNeighbours < 2) {
                return new IsAliveNextGeneration(false);
            } else return new IsAliveNextGeneration(numNeighbours == 2 || numNeighbours == 3);
        } else {
            return new IsAliveNextGeneration(numNeighbours == 3);
        }
    }
}
