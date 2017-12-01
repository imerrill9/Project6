import java.util.ArrayList;

/**
 * Binary Knapsack tree
 */
public class KnapTree
{
	public ArrayList<Tour> leaves;    // leaves to consider returning to
	public ArrayList<Item> items;     // items to choose from
	public int capacity;              // capacity of the Knapsack

	private Tour head;

	public KnapTree(ArrayList<Item> items, int capacity)
	{

		this.capacity = capacity;
		this.items = items;
		head = new Tour(null, 0, 0);
	}


}
