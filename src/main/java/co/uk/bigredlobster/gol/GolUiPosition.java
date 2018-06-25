package co.uk.bigredlobster.gol;

public class GolUiPosition {
    private final int x;
    private final int y;

    public GolUiPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "GolUiPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
