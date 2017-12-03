import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project 6 Main program functionality
 */
public class Main
{
	private KnapTree knapsackTree;

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
		Main main = new Main();
		main.Run();
	}

	private void Run()
	{
		System.out.println("******* Project 6 *******");
		System.out.print("Enter data filename: ");
		String fileName = scan.nextLine();

		try {
			enterData(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found.");
		}
	}

	private void enterData(String fileName) throws FileNotFoundException
	{
		Scanner fileScanner = new Scanner(new File(fileName));
		int capacity = fileScanner.nextInt();
		fileScanner.nextLine();

		int numItems = fileScanner.nextInt();
		fileScanner.nextLine();

		ArrayList<Item> items = new ArrayList<>();

		for (int i = 0; i < numItems; i++) {
			int price = fileScanner.nextInt();
			int weight = fileScanner.nextInt();

			Item item = new Item(price, weight);
			items.add(item);

			fileScanner.nextLine();
		}

		knapsackTree = new KnapTree(items, capacity);
	}
}