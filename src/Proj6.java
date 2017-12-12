import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project 6 Main program functionality. This program demonstrates a branch and
 * bound algorithm for the 0-1 knapsack problem. Given a file of item data and
 * knapsack capacity the algorithm finds which items to carry in the sack to
 * maximize profit.
 *
 * @author Ian Merrill
 * @author Erin Gurnett
 * @author Joseph Medina
 */
public class Proj6
{
	private KnapTree knapsackTree;

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
		Proj6 main = new Proj6();
		main.Run();
	}

	private void Run()
	{
		System.out.println("******* Project 6 *******");

		boolean fileNotFound;
		do {
			fileNotFound = false;
			try {
				System.out.print("Enter data filename: ");
				String fileName = scan.nextLine();
				enterData(fileName);
			} catch (FileNotFoundException e) {
				System.out.println("The file was not found.");
				fileNotFound = true;
			}
		} while (fileNotFound);

		System.out.println("\nBegin exploration of the possibilities tree:\n");
		knapsackTree.exploreTree();
	}

	private void enterData(String fileName) throws FileNotFoundException
	{
		Scanner fileScanner = new Scanner(new File(fileName));
		int capacity = fileScanner.nextInt();
		int numItems = fileScanner.nextInt();

		ArrayList<Item> items = new ArrayList<>();

		for (int i = 0; i < numItems; i++) {
			int itemIdx = i + 1;
			int price = fileScanner.nextInt();
			int weight = fileScanner.nextInt();
			Item item = new Item(itemIdx, price, weight);
			items.add(item);
		}

		knapsackTree = new KnapTree(items, capacity);
		knapsackTree.displayKnapSackData();
	}
}
