import java.util.ArrayList;

/**
 * KnapNode is the node of the KnapTree which must calculate it's weight
 * and cost from a list of considered items.
 */
public class KnapNode
{
	private static int numNodes = 0;
	private int id;                     //id of node
	private double weight;              //Weight of tourList this node considers

	public ArrayList<Item> itemList;    //The item list being considered by the node
	public double profit;                  //The profit calculated by the node
	public double bound;                //The bound calculated by the node
	public boolean prune;               //Should the node be pruned?
	public String message;              //Holds the pruned message
	public int level;                   //The level in the tree this node is created at

	//node attributes
	public KnapNode left;
	public KnapNode right;

	public KnapNode(ArrayList<Item> items, int level)
	{
		numNodes++;
		id = numNodes;
		this.level = level;
		this.itemList = items;
		calculateWeight(items);
		calculateProfit(items);
		calculateBound();
		determinePruned();
	}

	/**
	 * Calculate the value of weight
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateWeight(ArrayList<Item> items)
	{
		int totalWeight = 0;
		for (int index = 0; index < items.size(); index++) {
			totalWeight += items.get(index).getWeight();
		}
		weight = totalWeight;
	}

	/**
	 * Calculate the value of profit
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateProfit(ArrayList<Item> items)
	{
		int totalPrice = 0;
		for (int index = 0; index < items.size(); index++) {
			totalPrice += items.get(index).getPrice();
		}
		profit = totalPrice;
	}

	/**
	 * Calculate the bound of the node
	 */
	private void calculateBound()
	{
		bound = profit;
		double pounds = weight;
		for (int i = level; i < KnapTree.items.size(); i++) {
				bound += KnapTree.items.get(i).getPrice();
				pounds += KnapTree.items.get(i).getWeight();
				if (pounds > KnapTree.capacity) {
					bound -= KnapTree.items.get(i).getPrice(); //reset
					pounds -= KnapTree.items.get(i).getWeight();
					bound += fractionalProfit(pounds, KnapTree.items.get(i));
					break;
				}
		}

	}

	private double fractionalProfit(double pounds, Item item)
	{
		double weightNeeded = KnapTree.capacity - pounds;
		double pricePerPound = item.getPrice() / item.getWeight();
		return weightNeeded * pricePerPound;
	}

	/**
	 * Determines if the node should be pruned, displays corresponding messages
	 * and sets a new maximum profit if greater than existing maximum.
	 */
	private void determinePruned()
	{
		if (KnapTree.max != null) {
			if (weight == KnapTree.capacity) {
				message = "hit capacity exactly so don't explore further";
				if (this.profit > KnapTree.max.profit) {
					KnapTree.max = this;
					message += "\nnote achievable profit of " + profit;
				}
				prune = true;
			} else if (weight > KnapTree.capacity) {
				message = "pruned because too heavy";
				prune = true;
			} else {
				message = "explore further";
				if (this.profit > KnapTree.max.profit) {
					KnapTree.max = this;
					message += "\nnote achievable profit of " + profit;
				}
				prune = false;
			}
		}
	}

	/**
	 * Displays node's data
	 */
	public void printNode()
	{
		System.out.print("<Node " + id + ": items: [ ");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print(itemList.get(i).getIndex() + " ");
		}
		System.out.println("] level: " + level + " profit: " + profit
				+ " weight: " + weight + " bound: " + bound + ">");
	}
}
