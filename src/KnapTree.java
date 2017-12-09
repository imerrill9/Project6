import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Binary Knapsack tree
 */
public class KnapTree
{
	public PriorityQueue<KnapNode> leaves;      // leaves to consider returning to
	public static ArrayList<Item> items;        // items to choose from
	public static int capacity;                 // capacity of the Knapsack
	private KnapNode head;
	public static KnapNode max;

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
		KnapNode current = head;
		System.out.print("Exploring ");
		current.printNode();
		makeChildren(current);
		while (!current.leftT.prune || !current.rightT.prune || hasValidLeaf(current.bound)) {
			current = getNextNode(current);
			System.out.print("\nExploring ");
			current.printNode();
			if (current.prune) {
				System.out.println(current.message);
			} else {
				makeChildren(current);
			}
		}
	}

	private KnapNode getNextNode(KnapNode current)
	{
		KnapNode nextNode;
		if ((!current.leftT.prune) && (!current.rightT.prune) && (hasValidLeaf(current.bound))) {
			//left child, child and leaves can all be considered.
			if (current.leftT.bound >= current.rightT.bound) {
				nextNode = current.leftT;
			} else {
				nextNode = current.rightT;
			}
			if (leaves.peek().bound > nextNode.bound) {
				nextNode = leaves.peek();
			}
		} else if ((current.leftT.prune) && (!current.rightT.prune) && (hasValidLeaf(current.bound))) {
			//right right child and leaves can be considered
			if (leaves.peek().bound < current.rightT.bound) {
				nextNode = current.rightT;
			} else {
				nextNode = leaves.peek();
			}
		} else if ((!current.leftT.prune) && (current.rightT.prune) && (hasValidLeaf(current.bound))) {
			//left child and leaves can be considered
			if (leaves.peek().bound < current.leftT.bound) {
				nextNode = current.leftT;
			} else {
				nextNode = leaves.peek();
			}
		} else if ((!current.leftT.prune) && (!current.rightT.prune) && (!hasValidLeaf(current.bound))) {
			//left child and right child but no leaves can be considered
			if (current.leftT.bound >= current.rightT.bound) {
				nextNode = current.leftT;
			} else {
				nextNode = current.rightT;
			}
		} else if ((!current.leftT.prune) && (current.rightT.prune) && (!hasValidLeaf(current.bound))) {
			//left child only
			nextNode = current.leftT;
		} else if ((current.leftT.prune) && (!current.rightT.prune) && (!hasValidLeaf(current.bound))) {
			//right child only
			nextNode = current.rightT;
		} else {
			//should never occur
			nextNode = null;
		}
		return nextNode;
	}

	private boolean hasValidLeaf(double bound)
	{
		if (leaves.peek().bound >= bound) {
			return true;
		} else {
			return false;
		}
	}

	public void makeChildren(KnapNode current)
	{
		if (current.level < items.size()) {
			// Create left node list, not using next item (KnapTree.level)
			ArrayList<Item> leftList = new ArrayList<Item>();
			leftList.addAll(current.itemList);
			// Create right node list, using next item (KnapTree.level)
			ArrayList<Item> rightList = new ArrayList<Item>();
			rightList.addAll(current.itemList);
			rightList.add(KnapTree.items.get(current.level));

			// Left child
			current.leftT = new KnapNode(leftList, current.level + 1);
			System.out.print("Left child is ");
			current.leftT.printNode();
			System.out.println(current.leftT.message);
			if (!current.leftT.prune) {
				leaves.add(current.leftT);
			}

			// Right child
			current.rightT = new KnapNode(rightList, current.level + 1);
			System.out.print("Right child is ");
			current.rightT.printNode();
			System.out.println(current.rightT.message);
			if (!current.rightT.prune) {
				leaves.add(current.rightT);
			}

			leaves.remove(current);
		}
	}
}
