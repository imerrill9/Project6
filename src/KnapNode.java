import java.util.ArrayList;

/**
 * KnapNode is the node of the KnapTree which must calculate it's weight and cost from a list of considered items.
 */
public class KnapNode
{
	private ArrayList<Item> itemList;    //The item list being considered by the node
	public int weight;                  //Weight of tourList this node considers
	public int cost;                    //The cost calculated by the node
	public double bound;                //The bound calculated from this node's children
	public boolean prune;               //Should the node be pruned?
	public static int id;

	//node attributes
	public KnapNode leftT;
	public KnapNode rightT;

	public KnapNode(ArrayList<Item> items)
	{
		this.itemList = items;
		calculateWeight(items);
		calculateProfit(items);
		calculateBound(items);
		determinePruned(items);
		id ++;
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
		for(int index = 0; index < items.size(); index ++)
		{
			totalWeight += items.get(index).getWeight();
		}
		weight = totalWeight;
	}

	/**
	 * Calculate the value of cost
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateProfit(ArrayList<Item> items)
	{
		int totalPrice = 0;
		for(int index = 0; index < items.size(); index ++)
		{
			totalPrice += items.get(index).getPrice();
		}
		cost = totalPrice;
	}

	/**
	 * Calculate the bound cost using fractions of item costs
	 *
	 * @param items list of items being considered by this node
	 */
	private void calculateBound(ArrayList<Item> items)
	{
		// Calculate the price per pound of an item.
		ArrayList<Double> pricePerUnit = new ArrayList<Double>();
		for(int index = 0; index < items.size(); index++)
		{
			Item current = items.get(index);
			pricePerUnit.add(current.getPrice() / current.getWeight());
		}

		int index = 0; // Which item are we on?
		int currentWeight = 0; // How much weight we have accumulated between all the parts of items so far.
		double bound = 0;
		Item current = items.get(index);
		int currentPartsAcquired = 0; // How much of the current item we are using.
		int currentTotalParts = current.getWeight(); // How much the current item weighs.

		// calculate total weight needed using fractions of weights
		while(currentWeight < KnapTree.capacity)
		{
			// Add one pound at a time.
			currentWeight ++;
			currentPartsAcquired ++;
			bound += pricePerUnit.get(index);
			// Have we gotten all the parts of current?
			if(currentPartsAcquired == currentTotalParts)
			{
				index ++;
				current = items.get(index);
				int currentPartsAcquired = 0;
				int currentTotalParts = current.getWeight();
			}
		}
	}

	public void determinePruned()
	{
		if(weight > KnapTree.capacity)
		{
			prune = true;
		}
		else
		{
			prune = false;
		}
	}

	public void printNode()
	{
		System.out.print("< Node " + id + ": items: [");
		for (int i = 0; i < itemList.size(); i++){
			System.out.print(itemList.get(i).getIndex() + ", ");
		}
		System.out.println("] level: " + KnapTree.level + " profit: " + cost + " weight: " + weight + " bound: " + bound);
	}


}
