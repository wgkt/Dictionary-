package edu.iup.cosc310.bst.util;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Test class for a BSTDictionary
 * @author Meghan Cowan
 *
 */

public class TestBST {
	public static void main(String[] args) {
		
		BSTDictionary tree = new BSTDictionary();
		BSTDictionary emptyTree = new BSTDictionary();

		assert tree.isEmpty() == true;
		assert tree.noKeys() == 0;
		assert emptyTree.isEmpty() == true;
		assert emptyTree.noKeys() == 0;

		tree.put("f", 1);
		tree.put("j", 3);
		tree.put("c", 2);
		tree.put("b", 4);
		tree.put("d", 5);
		tree.put("k", 6);
		tree.put("l", 9);
		tree.put("g", 8);
		tree.put("a", 7);
		
		assert tree.noKeys() == 9;
		assert tree.isEmpty() == false;
		assert tree.get("a").equals(7);
		assert tree.get("b").equals(4);
		assert tree.get("j").equals(3);
		
		//test getting an element that does not exist in tree
		try {
			tree.get("x");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
		//test getting an element from an empty tree
		try {
			emptyTree.get("x");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		
		
		//Test put() when element already exists
		tree.put("b",  2.5);
		assert tree.get("b").equals(2.5);
		
		
		System.out.println("Original tree:");
		ItemIterator<String> iter = tree.keys();
		while (iter.hasMoreItems()) {
			System.out.print(iter.nextItem() + " ");
		}
		System.out.println();
		
		tree.remove("l"); //tests when there are no children / noKeys ==1
		tree.remove("k"); //tests when left is empty
		tree.remove("f"); //tests when neither side is empty
		tree.remove("b"); //tests when right is empty
		
		
		System.out.println("Tree after removal of l,k,f,b:");
		iter = tree.keys();
		while (iter.hasMoreItems()) {
			System.out.print(iter.nextItem() + " ");
		}
		System.out.println();
		
		assert tree.noKeys() == 5;
		
		assert false : "Test complete";
	}

}
