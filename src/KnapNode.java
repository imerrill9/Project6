import java.util.ArrayList;

/**
 * KnapNode is the node of the KnapTree which must calculate it's weight and cost from a list of considered items.
 */
public class KnapNode
{
	private ArrayList<Item> itemList;    //The item list being considered by the node
	private int weight;                  //Weight of tourList this node considers
	private int cost;                    //The cost calculated by the node
	private double bound;                //The bound calculated from this node's children
	private boolean prune;               //Should the node be pruned?

	//node attributes
	private KnapNode leftT;
	private KnapNode rightT;

	public KnapNode(ArrayList<Item> items, int weight, int cost)
	{
		this.itemList = items;
		calculateWeight(items);
		calculateProfit(items);
		calculateBound(items);
	}

	/**
	 * Calculate the value of weight
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateWeight(ArrayList<Item> items)
	{

	}

	/**
	 * Calculate the value of cost
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateProfit(ArrayList<Item> items)
	{

	}

	/**
	 * Calculate the bound cost using fractions of item costs
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateBound(ArrayList<Item> items)
	{

	}

	public void exploreNode()
	{

	}


}
