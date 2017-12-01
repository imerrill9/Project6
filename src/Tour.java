import java.util.ArrayList;

/**
 * Tour is the node of the KnapTree which must calculate it's weight and cost from a list of considered items.
 */
public class Tour
{

	private ArrayList<Item> itemList;   //The item list being considered by the node
	private int weight;                  //Weight of tourList this node considers
	private int cost;                    //The cost calculated by the node
	private int potential;               //The potential profit gained from this node's children
	private boolean prune;               //Should the node be pruned?

	//node attributes
	private Tour leftT;
	private Tour rightT;

	public Tour(ArrayList<Item> items, int weight, int cost)
	{
		this.itemList = items;
		calculateWeight(items);
		calculateCost(items);
		calculatePotentialCost(items);
	}

	/**
	 * Calculate the value of weight
	 *
	 * @param tourList tour of nodes considered
	 */
	private void calculateWeight(ArrayList<Item> tourList)
	{

	}

	/**
	 * Calculate the value of cost
	 *
	 * @param tourList tour of nodes considered
	 */
	private void calculateCost(ArrayList<Item> tourList)
	{

	}

	/**
	 * Calculate the potential cost using fractions of costs
	 *
	 * @param tourList tour of nodes considered
	 */
	private void calculatePotentialCost(ArrayList<Item> tourList)
	{

	}


}
