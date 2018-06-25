package co.uk.bigredlobster.gol.graph;

import co.uk.bigredlobster.gol.LifeStatus;
import co.uk.bigredlobster.gol.microtypes.IsAliveNextGeneration;
import co.uk.bigredlobster.gol.microtypes.IsAliveNow;
import co.uk.bigredlobster.gol.node.GolGraphNode;
import com.google.common.graph.Graph;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Function;

import static co.uk.bigredlobster.gol.node.GridPosition.TOP_LEFT;

public class BreadthFirst {

    private final Graph<GolGraphNode> graph;

    public BreadthFirst(final Graph<GolGraphNode> graph) {
        this.graph = graph;
    }

    private void runSearch(Graph<GolGraphNode> graph, final Function<GolGraphNode, Boolean> fn) {
        final Deque<GolGraphNode> frontier = new ConcurrentLinkedDeque<>();
        frontier.add(new GolGraphNode(IsAliveNow.EMPTY, TOP_LEFT));

        final Map<GolGraphNode, Boolean> visited = new HashMap<>();
        visited.put(GolGraphNode.TOP_LEFT, true);

        while (!frontier.isEmpty()) {
            final GolGraphNode current = frontier.pop();

            fn.apply(current);

            for (final GolGraphNode next : graph.successors(current)) {
                if (!visited.containsKey(next)) {
                    frontier.add(next);
                    visited.put(next, true);
                }
            }
        }
    }

    private Graph<GolGraphNode> mutate(final Graph<GolGraphNode> graph) {
        runSearch(graph, this::setIsAlive);
        return graph;
    }

    private long countNumLivingNeighbours(final Graph<GolGraphNode> graph, final GolGraphNode next) {
        return graph.adjacentNodes(next).stream()
                .filter(x -> x.mutableState.isAliveNow.value)
                .count();
    }

    public Graph<GolGraphNode> runGeneration() {
        runSearch(graph, this::setIsAliveNextGeneration);
        return mutate(graph);
    }

    private Boolean setIsAliveNextGeneration(final GolGraphNode next) {
        final long numLivingNeighbours = countNumLivingNeighbours(graph, next);
        next.mutableState.isAliveNextGeneration = LifeStatus.nextGenerationIsAlive(next.mutableState.isAliveNow, numLivingNeighbours);
        return next.mutableState.isAliveNextGeneration.value;
    }

    private Boolean setIsAlive(final GolGraphNode node) {
        node.mutableState.isAliveNow = new IsAliveNow(node.mutableState.isAliveNextGeneration.value);
        node.mutableState.isAliveNextGeneration = IsAliveNextGeneration.EMPTY;
        return node.mutableState.isAliveNow.value;
    }
}
