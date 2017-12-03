import java.util.ArrayList;

/**
 * Binary Knapsack tree
 */
public class KnapTree
{
	public ArrayList<KnapNode> leaves;    // leaves to consider returning to
	public ArrayList<Item> items;         // items to choose from
	public int capacity;                  // capacity of the Knapsack

	private KnapNode head;

	public KnapTree(ArrayList<Item> items, int capacity)
	{

		this.capacity = capacity;
		this.items = items;
		head = new KnapNode(null, 0, 0);
	}

	public void displayKnapSackData()
	{
		System.out.println("Capacity of knapsack is " + capacity + "\n"
				+ "Items are: ");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getIndex() + ": "
					+ items.get(i).getPrice() + " "
					+ items.get(i).getWeight());
		}
	}

	public void exploreTree()
	{
		head.exploreNode();
	}
}
