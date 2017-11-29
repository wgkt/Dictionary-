package edu.iup.cosc310.bst.util;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Creates a binary search tree composed of a key, a value, and left and right
 * pointers
 * 
 * @author Meghan Cowan
 *
 * @param <V>
 * @param <K>
 */
public class BSTDictionary<V, K> implements DictionaryI<K, V> {

	BSTDictionary<V, K> left;
	BSTDictionary<V, K> right;
	K key;
	V value;
	int noKeys = 0;

	/**
	 * Binary Search Tree Dictionary constructor
	 */
	public BSTDictionary() {
		super();
	}

	/**
	 * Method to add an item to the tree. If the item already exists, replace
	 * its value with the new value passed in
	 */
	public V put(K key, V value) {
		if (this.key == null) {
			this.left = new BSTDictionary<V, K>();
			this.key = key;
			this.value = value;
			this.right = new BSTDictionary<V, K>();
			noKeys++;
			return null;
		}

		if (((Comparable) key).compareTo(this.key) < 0) {
			V returnValue = left.put(key, value);
			if (returnValue == null) {
				noKeys++;
			}

			return returnValue;

		} else if ((((Comparable) key).compareTo(this.key)) > 0) {
			V returnValue = right.put(key, value);

			if (returnValue == null) {
				noKeys++;
			}
			return returnValue;

		}

		V returnValue = this.value;
		this.value = value;

		return returnValue;

	}

	/**
	 * Method that returns the value of a key passed in
	 */
	public V get(K key) {
		if (isEmpty()) {
			return null;
		}
		if (this.key.equals(key)) {
			return value;
		}
		if (((Comparable) key).compareTo(this.key) < 0) {
			return left.get(key);
		} else {
			return right.get(key);
		}
	}

	/**
	 * Method to remove a key
	 */
	public V remove(K key) {
		if (isEmpty()) {
			return null;
		}
		V returnValue;
		int comp = ((Comparable) key).compareTo(this.key);

		if (comp == 0) {
			returnValue = this.value;
			if (noKeys == 1) {
				this.key = null;
				this.value = null;
				this.left = new BSTDictionary<V, K>();
				this.right = new BSTDictionary<V, K>();

				noKeys--;
			} else if (right.isEmpty()) {
				this.key = (K) left.key;
				this.value = (V) left.value;
				this.right = left.right;
				this.left = left.left;
				noKeys--;
			} else if (left.isEmpty()) {
				this.key = (K) right.key;
				this.value = (V) right.value;
				this.left = right.left;
				this.right = right.right;
				noKeys--;
			} else {
				BSTDictionary bst = right.leftmost();
				this.key = (K) bst.key;
				this.value = (V) bst.value;
				this.noKeys--;
				right.remove(this.key);
			}
			return returnValue;

		} else if ((comp > 0)) {
			V returnVal = right.remove(key);

			if (returnVal != null) {
				noKeys--;
				return returnVal;
			}
		} else {
			if ((comp < 0)) {
				V returnVal = left.remove(key);

				if (returnVal != null) {
					noKeys--;
				}
				return returnVal;
			}
		}
		return null;
	}

	/**
	 * Method to find the leftmost key
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private BSTDictionary<K, V> leftmost() {
		if (left.isEmpty()) {
			return (BSTDictionary<K, V>) this;
		} else {
			return left.leftmost();
		}
	}

	/**
	 * Method to create an iterator over the keys
	 */
	public ItemIterator<K> keys() {
		return new Iterator();
	}

	/**
	 * Method to find if the tree contains no elements
	 */
	public boolean isEmpty() {
		return noKeys == 0;
	}

	/**
	 * Method to return the number of keys
	 */
	public int noKeys() {
		return noKeys;
	}

	/**
	 * Inner class to iterate over the tree
	 * 
	 * @author Meghan
	 *
	 */
	private class Iterator implements ItemIterator {
		Stack<BSTDictionary<K, V>> stack = new Stack<BSTDictionary<K, V>>();

		/**
		 * Constructor of iterator
		 */
		public Iterator() {
			pushLeft((BSTDictionary<K, V>) BSTDictionary.this);
		}

		/**
		 * Method to find if the tree contains more items
		 */
		public boolean hasMoreItems() {
			return !stack.isEmpty();
		}

		/**
		 * Method to return the next item in the tree
		 */
		public Object nextItem() {
			BSTDictionary<K, V> tree = stack.pop();
			pushLeft(tree.right);
			return tree.key;
		}

		/**
		 * Method to traverse the left side of the tree
		 * 
		 * @param tree
		 */
		public void pushLeft(BSTDictionary<K, V> tree) {
			if (!tree.isEmpty()) {
				stack.push(tree);
				pushLeft(tree.left);
			}
		}
	}// end of Iterator
}
