import java.util.ArrayList;

/**
 * KnapNode is the node of the KnapTree which must calculate it's weight and cost from a list of considered items.
 */
public class KnapNode
{
	public static int numNodes = 0;
	public ArrayList<Item> itemList;    //The item list being considered by the node
	public int weight;                  //Weight of tourList this node considers
	public int profit;                  //The profit calculated by the node
	public double bound;                //The bound calculated by the node
	public boolean prune;               //Should the node be pruned?
	public String message;
	public int id;
	public int level;

	//node attributes
	public KnapNode leftT;
	public KnapNode rightT;

	public KnapNode(ArrayList<Item> items, int level)
	{
		numNodes++;
		id = numNodes;
		this.itemList = items;
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

	private void calculateBound()
	{
		bound = profit;
		int pounds = weight;
		for (int i = level; i < KnapTree.items.size(); i++) {
			if (pounds >= KnapTree.capacity) {
				break;
			} else {
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
	}

	private double fractionalProfit(int pounds, Item item)
	{
		int weightNeeded = KnapTree.capacity - pounds;
		double pricePerPound = item.getPrice() / item.getWeight();
		return weightNeeded * pricePerPound;
	}

	public void determinePruned()
	{
		if (KnapTree.max != null) {
			if (weight == KnapTree.capacity) {
				message = "hit capacity exactly so don't explore further";
				if (this.profit > KnapTree.max.profit) {
					KnapTree.max = this;
					System.out.println("note achievable profit of " + profit);
				}
				prune = true;
			} else if (weight > KnapTree.capacity) {
				message = "pruned because too heavy";
				prune = true;
			} else if (bound < KnapTree.max.profit) {
				message = "pruned because bound " + bound + " is smaller than known achievable profit "
						+ KnapTree.max.profit;
				prune = true;
			} else {
				message = "explore further";
				if (this.profit > KnapTree.max.profit) {
					KnapTree.max = this;
					System.out.println("note achievable profit of " + profit);
				}
				prune = false;
			}
		}
	}

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
