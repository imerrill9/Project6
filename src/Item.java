/**
 * Item contained in Knapsack
 */
public class Item
{
	private final int INDEX;
	private final int PRICE;
	private final int WEIGHT;

	public Item(int index, int price, int weight)
	{
		INDEX = index;
		PRICE = price;
		WEIGHT = weight;
	}

	public int getPrice()
	{
		return PRICE;
	}

	public int getWeight()
	{
		return WEIGHT;
	}

	public int getIndex()
	{
		return INDEX;
	}
}