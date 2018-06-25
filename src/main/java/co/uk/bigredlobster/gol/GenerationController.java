package co.uk.bigredlobster.gol;

import co.uk.bigredlobster.gol.graph.BreadthFirst;
import co.uk.bigredlobster.gol.graph.GolGridGraph;
import co.uk.bigredlobster.gol.node.GolGraphNode;
import co.uk.bigredlobster.gol.node.GridPosition;
import co.uk.bigredlobster.gol.patterns.IGolPattern;
import co.uk.bigredlobster.gol.patterns.RPentanamoPattern;
import com.google.common.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class GenerationController {

    private final AtomicLong generationNumber = new AtomicLong();

    private final IGolPattern pattern;
    private BreadthFirst breadthFirst;

    @Autowired
    public GenerationController() {
        pattern = new RPentanamoPattern();
        resetToStart();
    }

    @RequestMapping("/generation")
    public GolUiData generation() {
        generationNumber.incrementAndGet();

        final Graph<GolGraphNode> newGeneration = breadthFirst.runGeneration();
        @SuppressWarnings("UnnecessaryLocalVariable") final GolUiData uiData = getUiData(newGeneration);
//        System.out.println("uiData = " + uiData);
        return uiData;
    }

    private GolUiData getUiData(Graph<GolGraphNode> newGeneration) {
        final Set<GridPosition> lifePositions = GolGridGraph.getLifePositions(newGeneration);
        final List<GolUiPosition> uiPositions = getLifePositions(lifePositions);
        return new GolUiData(generationNumber.get(), uiPositions);
    }

    private List<GolUiPosition> getLifePositions(Set<GridPosition> lifePositions) {
        return lifePositions.stream().map(p -> new GolUiPosition(p.x.value, p.y.value)).collect(Collectors.toList());
    }

    @RequestMapping("/reset")
    public GolUiData reset() {
        final GolGridGraph startGraph = resetToStart();
        @SuppressWarnings("UnnecessaryLocalVariable") final GolUiData uiData = getUiData(startGraph.graph);
//        System.out.println("uiData = " + uiData);
        return uiData;
    }

    private GolGridGraph resetToStart() {
        generationNumber.set(0);
        final GolGridGraph golGridGraph = new GolGridGraph(pattern.getGridWidth(), pattern.getGridHeight(), pattern.getLife());
        breadthFirst = new BreadthFirst(golGridGraph.graph);
        return golGridGraph;
    }
}