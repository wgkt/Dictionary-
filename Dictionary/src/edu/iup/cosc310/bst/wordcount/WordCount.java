package edu.iup.cosc310.bst.wordcount;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import edu.iup.cosc310.bst.util.BSTDictionary;
import edu.iup.cosc310.bst.util.ItemIterator;

/**
 * Reads in a text file and organizes the words into a Binary Search Tree
 * Dictionary. Prints out a list of words sorted in order with the number of
 * times they occur in the file.
 * 
 * @author Meghan Cowan
 *
 * @param <V>
 */
public class WordCount<V> {
/**
 * Main method to read in file, and print the number of times each word occurs.
 * @param args
 * @throws FileNotFoundException
 */
	public static <K> void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Please enter args[0]");
			return;
		}

		BSTDictionary bst = new BSTDictionary();
		Scanner in = new Scanner(new File(args[0]));
		in.useDelimiter("\\W");
		while (in.hasNext()) {
			String word = in.next();
			Integer count = (Integer) bst.get(word);
			if (count == null) {
				bst.put(word, 1);
			} else {
				bst.put(word, count + 1);
			}

		}

		ItemIterator<String> iter = bst.keys();
		while (iter.hasMoreItems()) {
			K key = (K) iter.nextItem();
			System.out.println(key + ":" + bst.get(key));
		}

	}

}
