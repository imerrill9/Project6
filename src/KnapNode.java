import java.util.ArrayList;
import java.util.TreeMap;

/**
 * KnapNode is the node of the KnapTree which must calculate it's weight and cost from a list of considered items.
 */
public class KnapNode
{
	public ArrayList<Item> itemList;    //The item list being considered by the node
	public int weight;                  //Weight of tourList this node considers
	public int profit;                  //The profit calculated by the node
	public double bound;                //The bound calculated by the node
	public boolean prune;               //Should the node be pruned?
	public static int id;

	//node attributes
	public KnapNode leftT;
	public KnapNode rightT;

	public KnapNode(ArrayList<Item> items)
	{
		if (items == null) {
			items = new ArrayList<>();
		}
		this.itemList = items;
		calculateWeight(items);
		calculateProfit(items);
		calculateBound(items);
		determinePruned();
		id++;
		printNode();
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
	 * Calculate the bound using fractions of item profits
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateBound(ArrayList<Item> items)
	{
		bound = 0;
		int pounds = 0;
		for (int i = 0; i < items.size(); i++) { //add all items to bound regardless
			pounds += items.get(i).getWeight();
			bound += items.get(i).getPrice();
		}

		if (pounds < KnapTree.capacity) {
			//need to continue with items proceeding items in local list
			//meaning we start using KnapTree full list of items from whatever
			//the last local item's index was
			int startIndex;
			if (items.size() == 0) {
				startIndex = 0; //if the items list was empty start at 0 for main list
			} else if (KnapTree.items.size() > items.get(items.size() - 1).getIndex()) {
				startIndex = items.get(items.size() - 1).getIndex(); //else it's the last items index
			} else {
				startIndex = KnapTree.items.size(); // skip loop
			}

			for (int i = startIndex; i < KnapTree.items.size(); i++) {
				pounds += KnapTree.items.get(i).getWeight();
				if (pounds < KnapTree.capacity) {
					bound += KnapTree.items.get(i).getPrice();
				} else {
					//fractional weight added
					pounds -= KnapTree.items.get(i).getWeight(); //reset
					bound += fractionalProfit(pounds, KnapTree.items.get(i));
					pounds = KnapTree.capacity;
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
		if (weight > KnapTree.capacity) {
			prune = true;
		} else {
			prune = false;
		}
	}

	public void printNode()
	{
		System.out.print("<Node " + id + ": items: [");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print(itemList.get(i).getIndex() + " ");
		}
		System.out.println("] level: " + KnapTree.level + " profit: " + profit
				+ " weight: " + weight + " profit: " + profit + ">");
	}


}
