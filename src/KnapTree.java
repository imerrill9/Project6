import java.util.ArrayList;
import java.util.HashMap;

/**
 * Binary Knapsack tree
 */
public class KnapTree
{
	public ArrayList<KnapNode> leaves;           // leaves to consider returning to
	public static ArrayList<Item> items;         // items to choose from
	public static HashMap<Integer, KnapNode> nodes;
	public static int capacity;                    // capacity of the Knapsack
	private KnapNode head;

	public KnapTree(ArrayList<Item> items, int capacity)
	{
		this.capacity = capacity;
		this.items = items;
		leaves = new ArrayList<KnapNode>();
		nodes = new HashMap<Integer, KnapNode>();
		head = new KnapNode(null, 0);
		nodes.put(head.id, head);
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
		// Start with the head.
		KnapNode current = head;
      KnapNode max = current;
		System.out.print("Exploring ");
		current.printNode();
		makeChildren(current);

		while ((!current.leftT.prune || !current.rightT.prune) || (hasValidLeaf(current.bound))) {
         KnapNode temp = current;
         System.out.print("Left child is ");
		   current.leftT.printNode();
         if(current.leftT.canBeExplored(max)) {
 			   temp = current.leftT;
         }
         System.out.print("Right child is ");
		   current.rightT.printNode();
 			if ((current.leftT.canBeExplored(max)) && (current.leftT.bound < current.rightT.bound)) {
 				temp = current.rightT;
 			}
 			for (int i = 0; i < leaves.size(); i++) {
 				if (!(leaves.get(i).prune) && (leaves.get(i).canBeExplored(max))) {
 					temp = leaves.get(i);
 				}
 			}
			addLeaves(current, max.id);
         if(max.profit < temp.profit) {
            max = temp;
         }
 			current = nodes.get(temp.id);
			System.out.print("\nExploring ");
			current.printNode();
			makeChildren(current);      
		}

	}

	private void addLeaves(KnapNode current, int maxID)
	{
		if (current.leftT.id == maxID) {
			leaves.add(current.rightT);
		} else if (current.rightT.id == maxID) {
			leaves.add(current.leftT);
		} else {
			leaves.add(current.rightT);
			leaves.add(current.leftT);
		}
	}

	private boolean hasValidLeaf(double bound)
	{
		// Are there any leaves to consider?
		if (leaves.isEmpty()) {
			return false;
		} else {
			for (int index = 0; index < leaves.size(); index++) {
				if (leaves.get(index).bound >= bound) {
					return true;
				}
			}
			return false;
		}
	}

	public void makeChildren(KnapNode current)
	{
		// Create left node list, not using next item (KnapTree.level)
		ArrayList<Item> leftList = new ArrayList<Item>();
		leftList.addAll(current.itemList);
		// Create right node list, using next item (KnapTree.level)
		ArrayList<Item> rightList = new ArrayList<Item>();
		rightList.addAll(current.itemList);
		rightList.add(KnapTree.items.get(current.level));

		// Left child
		current.leftT = new KnapNode(leftList, current.level + 1);
		nodes.put(current.leftT.id, current.leftT);

		// Right child
      current.rightT = new KnapNode(rightList, current.level + 1);
		nodes.put(current.rightT.id, current.rightT);
	}
}
