/**
 * Item contained in Knapsack
 */
public class Item {

    private int price;
    private int weight;

    public Item(int price, int weight) {
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}