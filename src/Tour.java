import java.util.ArrayList;

public class Tour {

    private ArrayList<Integer> tourList; //The current tour being considered by the node
    private int weight;                  //Weight of tourList this node considers
    private int cost;                    //The cost calculated by the node
    private int potential;               //The potential profit gained from this node's children
    private boolean prune;               //Should the node be pruned?

    //node attributes
    private Tour leftT;
    private Tour rightT;

    public Tour(ArrayList<Integer> tourList, int weight, int cost) {
        this.tourList = tourList;
        calculateWeight(tourList);
        calculateCost(tourList);
        calculatePotentialCost(tourList);
    }

    /**
     * Calculate the value of weight
     *
     * @param tourList tour of nodes considered
     */
    private void calculateWeight(ArrayList<Integer> tourList) {

    }

    /**
     * Calculate the value of cost
     *
     * @param tourList tour of nodes considered
     */
    private void calculateCost(ArrayList<Integer> tourList) {

    }

    /**
     * Calculate the potential cost using fractions of costs
     *
     * @param tourList tour of nodes considered
     */
    private void calculatePotentialCost(ArrayList<Integer> tourList) {

    }


}
