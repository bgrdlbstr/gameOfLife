package co.uk.bigredlobster.gol;

import java.util.Arrays;
import java.util.List;

public class GolUiData {
    private Long id;
    private List<GolUiPosition> life;

    GolUiData(final Long id, final List<GolUiPosition> life) {
        this.id = id;
        this.life = life;
    }

    public Long getId() {
        return id;
    }

    public List<GolUiPosition> getLife() {
        return life;
    }

    @Override
    public String toString() {
        return "GolUiData{" +
                "id=" + id +
                ", life=" + Arrays.toString(life.toArray()) +
                '}';
    }
}
