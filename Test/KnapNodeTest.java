import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KnapNodeTest
{
	Item one = new Item(1, 100, 4);
	Item two = new Item(2, 120, 5);
	Item three = new Item(3, 88, 4);
	Item four = new Item(4, 80, 4);
	Item five = new Item(5, 54, 3);
	Item six = new Item(6, 80, 5);

	ArrayList<Item> list1 = new ArrayList<>();
	ArrayList<Item> list2 = new ArrayList<>();
	ArrayList<Item> list3 = new ArrayList<>();
	ArrayList<Item> list4 = new ArrayList<>();

	ArrayList<Item> emptyList = new ArrayList<>();
	ArrayList<Item> fullList = new ArrayList<>();

	KnapNode oneN;
	KnapNode twoN;
	KnapNode threeN;
	KnapNode fourN;
	KnapNode emptyN;
	KnapNode fullN;

	KnapTree tree = new KnapTree(fullList, 12);

	public KnapNodeTest()
	{
		list1.add(one);

		list2.add(one);
		list2.add(two);

		list3.add(one);
		list3.add(two);
		list3.add(three);

		list4.add(one);
		list4.add(three);
		list4.add(five);
		list4.add(six);

		fullList.add(one);
		fullList.add(two);
		fullList.add(three);
		fullList.add(four);
		fullList.add(five);
		fullList.add(six);

		oneN = new KnapNode(list1, 0);
		twoN = new KnapNode(list2, 0);
		threeN = new KnapNode(list3, 0);
		fourN = new KnapNode(list4, 0);
		emptyN = new KnapNode(emptyList, 0);
		fullN = new KnapNode(fullList, 0);
	}

	@Test
	public void calculateWeightTest()
	{
		assertEquals(oneN.weight, 4);
		assertEquals(twoN.weight, 9);
		assertEquals(threeN.weight, 13);
		assertEquals(fourN.weight, 16);
		assertEquals(emptyN.weight, 0);
		assertEquals(fullN.weight, 25);
	}

	@Test
	public void calculateProfitTest()
	{
		assertEquals(oneN.profit, 100);
		assertEquals(twoN.profit, 220);
		assertEquals(threeN.profit, 308);
		assertEquals(fourN.profit, 322);
		assertEquals(emptyN.profit, 0);
		assertEquals(fullN.profit, 522);
	}

	@Test
	public void calculateBoundTest()
	{
		assertEquals(oneN.bound, 286, 0.001);
		assertEquals(twoN.bound, 286, 0.001);
		assertEquals(threeN.bound, 308, 0);
		assertEquals(fourN.bound, 322, 0);
		assertEquals(emptyN.bound, 286, 0.001);
		assertEquals(fullN.bound, 522, 0);
	}

	@Test
	public void pruneTest()
	{
		assertFalse(oneN.prune);
		assertFalse(twoN.prune);
		assertTrue(threeN.prune);
		assertTrue(fourN.prune);
		assertFalse(emptyN.prune);
		assertTrue(fullN.prune);
	}
}