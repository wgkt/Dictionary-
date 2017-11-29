package edu.iup.cosc310.bst.util;
/**
 * An abstract data type to iterator over a set of items
 */
public interface ItemIterator<E> {
	/**
	 * Return true if the iteration has more items 
	 *
	 * @return true if the iteration has more items, otherwise false
	 */
	public boolean hasMoreItems();

	/**
	 * Return next item from iteration
	 *
	 * @return next item from iteration
	 * @throw NoSuchElementException if the iteration has no more items
	 */
	public E nextItem();
}
