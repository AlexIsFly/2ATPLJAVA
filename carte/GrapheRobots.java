package carte;

import java.util.LinkedList;

/**
 * Created by Nicolas on 07/11/2016.
 */
public class GrapheRobots {
    private LinkedList<LinkedList<LinkedList<int[]>>> grapheDrone;
    private LinkedList<LinkedList<LinkedList<int[]>>> grapheChenilles;
    private LinkedList<LinkedList<LinkedList<int[]>>> graphePattes;
    private LinkedList<LinkedList<LinkedList<int[]>>> grapheRoues;

    public GrapheRobots(LinkedList<LinkedList<LinkedList<int[]>>> grapheDrone, LinkedList<LinkedList<LinkedList<int[]>>> grapheChenilles, LinkedList<LinkedList<LinkedList<int[]>>> graphePattes, LinkedList<LinkedList<LinkedList<int[]>>> grapheRoues) {
        this.grapheDrone = grapheDrone;
        this.grapheChenilles = grapheChenilles;
        this.graphePattes = graphePattes;
        this.grapheRoues = grapheRoues;
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGrapheDrone() {
        return grapheDrone;
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGrapheChenilles() {
        return grapheChenilles;
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphePattes() {
        return graphePattes;
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGrapheRoues() {
        return grapheRoues;
    }
}