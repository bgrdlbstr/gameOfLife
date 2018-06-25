package co.uk.bigredlobster.gol;

import co.uk.bigredlobster.gol.microtypes.IsAliveNextGeneration;
import co.uk.bigredlobster.gol.microtypes.IsAliveNow;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LifeStatusTest {

    @Test
    public void isDeadAndLessThanThreeNeighboursShouldBeDead() {
        for (int i = 0; i < 3; i++) {
            final IsAliveNextGeneration isAlive = LifeStatus.nextGenerationIsAlive(new IsAliveNow(false), i);
            assertThat(isAlive.value, is(false));
        }
    }

    @Test
    public void isDeadAndThreeNeighboursShouldBeAlive() {
        final IsAliveNextGeneration isAlive = LifeStatus.nextGenerationIsAlive(new IsAliveNow(false), 3);
        assertThat(isAlive.value, is(true));
    }

    @Test
    public void isDeadAndMoreThanThreeNeighboursShouldBeDead() {
        for (int i = 4; i < 10; i++) {
            final IsAliveNextGeneration isAlive = LifeStatus.nextGenerationIsAlive(new IsAliveNow(false), i);
            assertThat(isAlive.value, is(false));
        }
    }

    @Test
    public void isAliveAndLessThanTwoNeighboursShouldBeDead() {
        for (int i = 0; i < 2; i++) {
            final IsAliveNextGeneration isAlive = LifeStatus.nextGenerationIsAlive(new IsAliveNow(true), i);
            assertThat(isAlive.value, is(false));
        }
    }

    @Test
    public void isAliveAndTwoOrThreeNeighboursShouldBeAlive() {
        for (int i = 2; i < 4; i++) {
            final IsAliveNextGeneration isAlive = LifeStatus.nextGenerationIsAlive(new IsAliveNow(true), i);
            assertThat(isAlive.value, is(true));
        }
    }

    @Test
    public void isAliveAndMoreThanThreeNeighboursShouldBeDead() {
        for (int i = 4; i < 10; i++) {
            final IsAliveNextGeneration isAlive = LifeStatus.nextGenerationIsAlive(new IsAliveNow(true), i);
            assertThat(isAlive.value, is(false));
        }
    }
}