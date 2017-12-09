import java.util.ArrayList;
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
		leaves = new PriorityQueue<>();
		head = new KnapNode(new ArrayList<Item>(), 0);
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
			current = getMaxNode(current);
			System.out.print("\nExploring ");
			current.printNode();
			makeChildren(current);
		}

	}

	private KnapNode getMaxNode(KnapNode current)
	{
		KnapNode maxNode;
		if ((!current.leftT.prune) && (!current.rightT.prune) && (hasValidLeaf(current.bound))) {
			//left child, child and leaves can all be considered.
			if (current.leftT.bound >= current.rightT.bound) {
				maxNode = current.leftT;
			} else {
				maxNode = current.rightT;
			}
			if (leaves.peek().bound > maxNode.bound) {
				maxNode = leaves.peek();
			}
		} else if ((current.leftT.prune) && (!current.rightT.prune) && (hasValidLeaf(current.bound))) {
			//right right child and leaves can be considered
			if (leaves.peek().bound < current.rightT.bound) {
				maxNode = current.rightT;
			} else {
				maxNode = leaves.peek();
			}
		} else if ((!current.leftT.prune) && (current.rightT.prune) && (hasValidLeaf(current.bound))) {
			//left child and leaves can be considered
			if (leaves.peek().bound < current.leftT.bound) {
				maxNode = current.leftT;
			} else {
				maxNode = leaves.peek();
			}
		} else if ((!current.leftT.prune) && (!current.rightT.prune) && (!hasValidLeaf(current.bound))) {
			//left child and right child but no leaves can be considered
			if (current.leftT.bound >= current.rightT.bound) {
				maxNode = current.leftT;
			} else {
				maxNode = current.rightT;
			}
		} else {
			//should never occur
			maxNode = null;
		}
		return maxNode;
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
			if (!current.leftT.prune) {
				leaves.add(current.leftT);
			}

			// Right child
			current.rightT = new KnapNode(rightList, current.level + 1);
			System.out.print("Right child is ");
			current.rightT.printNode();
			if (!current.rightT.prune) {
				leaves.add(current.rightT);
			}

			leaves.remove(current);
		}
	}
}
