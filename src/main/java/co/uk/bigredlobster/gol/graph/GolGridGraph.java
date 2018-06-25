package co.uk.bigredlobster.gol.graph;

import co.uk.bigredlobster.gol.node.GolGraphNode;
import co.uk.bigredlobster.gol.node.GridPosition;
import com.google.common.graph.Graph;
import com.google.common.graph.MutableGraph;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GolGridGraph {
    public final MutableGraph<GolGraphNode> graph;

    public GolGridGraph(final int width, final int height, final List<GridPosition> life) {
        this.graph = new GolGridGraphBuilder(width, height, life).build();
    }

    public static Set<GridPosition> getLifePositions(Graph<GolGraphNode> graph) {
        return graph.nodes().stream()
                .filter(n -> n.mutableState.isAliveNow.value)
                .map(n -> n.position)
                .collect(Collectors.toSet());
    }
}
