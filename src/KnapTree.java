import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Binary Knapsack tree
 */
public class KnapTree
{
	public static ArrayList<Item> items;      // items to choose from
	public static KnapNode max;               // maximum profit achievable Node
	public static int capacity;               // capacity of the Knapsack

	private PriorityQueue<KnapNode> leaves;   // leaves to consider returning to
	private KnapNode head;

	public KnapTree(ArrayList<Item> items, int capacity)
	{
		this.capacity = capacity;
		this.items = items;
		leaves = new PriorityQueue<>(new Comparator<KnapNode>()
		{
			@Override
			public int compare(KnapNode o1, KnapNode o2)
			{
				if (o2.bound > o1.bound) {
					return 1;
				} else if (o2.bound < o1.bound) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		head = new KnapNode(new ArrayList<Item>(), 0);
		max = head;
		leaves.add(head);
	}

	/**
	 * Displays Knapsack capacity and the items that it can potentially hold
	 */
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

	/**
	 * Traverses KnapTree by making children, determining whether those children
	 * and deciding between all children and leaves which node to traverse to in
	 * the tree next.
	 */
	public void exploreTree()
	{
		KnapNode current = head;
		System.out.print("Exploring ");
		current.printNode();
		makeChildren(current);
		while (!leaves.isEmpty()) {
			current = leaves.peek();
			System.out.print("\nExploring ");
			current.printNode();
			makeChildren(current);
		}
		printBestNode();
	}

	/**
	 * Creates a left child which won't consider the item corresponding to the next level
	 * in the tree, and a right child which will consider using that item. The parent is
	 * removed from the queue of leaves while each child if not pruned is added.
	 * @param current the parent Node in the tree
	 */
	public void makeChildren(KnapNode current)
	{
		if (current.level < items.size()) {
			if (current.bound < max.profit) {
				System.out.println("pruned because bound " + current.bound
						+ " is smaller than known achievable profit " + max.profit);
				// Remove Parent
				leaves.remove(current);
			} else {

				// Create left node list, not using next item (KnapTree.level)
				ArrayList<Item> leftList = new ArrayList<Item>();
				leftList.addAll(current.itemList);
				// Create right node list, using next item (KnapTree.level)
				ArrayList<Item> rightList = new ArrayList<Item>();
				rightList.addAll(current.itemList);
				rightList.add(KnapTree.items.get(current.level));

				// Left child
				current.left = new KnapNode(leftList, current.level + 1);
				System.out.print("Left child is ");
				current.left.printNode();
				System.out.println(current.left.message);
				if (!current.left.prune) {
					leaves.add(current.left);
				}

				// Right child
				current.right = new KnapNode(rightList, current.level + 1);
				System.out.print("Right child is ");
				current.right.printNode();
				System.out.println(current.right.message);
				if (!current.right.prune) {
					leaves.add(current.right);
				}

				// Remove Parent
				leaves.remove(current);
			}
		} else {
			leaves.remove(current);
		}
	}

	/**
	 * After no more leaves are considered the best node's data is displayed
	 */
	private void printBestNode()
	{
		System.out.print("\nBest Node: ");
		max.printNode();
	}
}
