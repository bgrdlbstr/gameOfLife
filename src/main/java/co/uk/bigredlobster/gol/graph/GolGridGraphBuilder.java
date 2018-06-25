package co.uk.bigredlobster.gol.graph;

import co.uk.bigredlobster.gol.microtypes.IsAliveNow;
import co.uk.bigredlobster.gol.microtypes.PositionX;
import co.uk.bigredlobster.gol.microtypes.PositionY;
import co.uk.bigredlobster.gol.node.GolGraphNode;
import co.uk.bigredlobster.gol.node.GridPosition;
import com.google.common.collect.ImmutableMap;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.util.List;

class GolGridGraphBuilder {
    private final int width;
    private final int height;
    private final MutableGraph<GolGraphNode> graph;
    private final ImmutableMap<GridPosition, GolGraphNode> nodes;

    GolGridGraphBuilder(int width, int height, final List<GridPosition> life) {
        this.width = width;
        this.height = height;
        graph = GraphBuilder.directed().allowsSelfLoops(false).expectedNodeCount(width * height).build();
        nodes = makeNodes(life);
    }

    MutableGraph<GolGraphNode> build() {
        addEdges();
        return graph;
    }

    private ImmutableMap<GridPosition, GolGraphNode> makeNodes(final List<GridPosition> life) {
        ImmutableMap.Builder<GridPosition, GolGraphNode> builder = ImmutableMap.builder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final GridPosition position = new GridPosition(new PositionX(x), new PositionY(y));
                builder.put(position, new GolGraphNode(new IsAliveNow(life.contains(position)), position));
            }
        }
        return builder.build();
    }

    private void addEdges() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                addNorth(x, y);
                addNorthEast(x, y);
                addEast(x, y);
                addSouthEast(x, y);
                addSouth(x, y);
                addSouthWest(x, y);
                addWest(x, y);
                addNorthWest(x, y);
            }
        }
    }

    private void addNorthWest(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x - 1), new PositionY(y + 1));
        addEdge(current, edgeTo);
    }

    private void addWest(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x - 1), new PositionY(y));
        addEdge(current, edgeTo);
    }

    private void addSouthWest(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x - 1), new PositionY(y - 1));
        addEdge(current, edgeTo);
    }

    private void addSouth(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x), new PositionY(y - 1));
        addEdge(current, edgeTo);
    }

    private void addSouthEast(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x + 1), new PositionY(y - 1));
        addEdge(current, edgeTo);
    }

    private void addEast(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x + 1), new PositionY(y));
        addEdge(current, edgeTo);
    }

    private void addNorthEast(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x + 1), new PositionY(y + 1));
        addEdge(current, edgeTo);
    }

    private void addNorth(int x, int y) {
        final GridPosition current = new GridPosition(new PositionX(x), new PositionY(y));
        final GridPosition edgeTo = new GridPosition(new PositionX(x), new PositionY(y + 1));
        addEdge(current, edgeTo);
    }

    private void addEdge(final GridPosition current, final GridPosition edgeTo) {
        if (inBounds(edgeTo)) {
            graph.putEdge(
                    nodes.get(current),
                    nodes.get(edgeTo)
            );
        }
    }

    private boolean inBounds(final GridPosition toCheck) {
        return toCheck.x.value >= 0 && toCheck.x.value < width && toCheck.y.value >= 0 && toCheck.y.value < height;
    }
}
