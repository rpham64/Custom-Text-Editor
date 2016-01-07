package spelling;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Rudolf Pham
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if (word.length() == 0 || isWord(word)) { return false; }	// Empty String
		
		// Ignore case of words
//		word = word.toLowerCase();
		
		// Case Sensitive
		// If first letter is capitalized, set rest of word to lower case
		if (Character.isUpperCase(word.charAt(0))) {
			for (int i = 1; i < word.length(); i++) {
				word[i] = 
			}
		}
		
		// Set of all children from root
		HashMap<Character, TrieNode> children = root.getChildren();
		
		// Set current node to root
		TrieNode currentNode = root;
		String text = "";
		
		// for characters in word
		// 		if character exists as child node, set currentNode to this node
		//		else, create a new child node for "text" and set currentNode to this node
		// 		Add character to this node's text
		for (int i = 0; i < word.length(); i++) {
			
			char c = word.charAt(i);
			
			text += c;
			
			if (!children.containsKey(c)) {
				currentNode = new TrieNode(text);
				children.put(c, currentNode);
			}
			
			currentNode = children.get(c);
			currentNode.insert(c);
			
			children = currentNode.getChildren();
			
			// Leaf Node
			if (i == word.length() - 1) { currentNode.setEndsWord(true); }
		}
	
		// Increment word count
		size++;
		
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode tNode = search(s);
		
		return (tNode != null && tNode.endsWord()) ? true : false;
	}

	/*Searches trie for String s 
	 * @param String s
	 * @return String s' TrieNode, if it exists. Else, return null
	 */
	public TrieNode search(String s) {
		
		if (s.length() == 0) { return null; }
		
		s = s.toLowerCase();
		HashMap<Character, TrieNode> children = root.getChildren();
		TrieNode currentNode = null;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (children.containsKey(c)) {
				currentNode = children.get(c);
			}
			else {
				return null;
			}
			
			children = currentNode.getChildren();
		}
		
		return currentNode;
	}
	
	public List<String> breadthFirstSearch(String prefix, int numCompletions) {
		
		// Breadth First Search Algorithm
		//    Create a queue (LinkedList) and add the node that completes the stem to the back
		//       of the list.
		//    Create a list of completions to return (initially empty)
		//    While the queue is not empty and you don't have enough completions:
		//       remove the first Node from the queue
		//       If it is a word, add it to the completions list
		//       Add all of its child nodes to the back of the queue
		// Return the list of completions
		
		Queue<TrieNode> queue = new LinkedList<TrieNode>();
		TrieNode node = (prefix.equals("")) ? root : search(prefix);
		
		queue.add(node);
		
		List<String> completions = new ArrayList<String>(numCompletions);
		
		// Iterate through root's children and add nodes to queue
		while (!queue.isEmpty() & numCompletions > 0) {
			
			TrieNode tNode = queue.remove();
			
			if (tNode == null) { return completions; }
			
			String nodeWord = tNode.getText();
			
			System.out.println("nodeWord: " + nodeWord);
			
			if (isWord(nodeWord)) {
				
				completions.add(nodeWord);
				numCompletions--;
				System.out.println("Added \"" + nodeWord + "\" to the list!\n");
				
			}
			
			if (tNode.getChildren() != null) {
				
				queue.addAll(tNode.getChildren().values());
				
			}
			
		}
		System.out.println("Completions: " + completions);
		
		return completions;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List<String> completions = new ArrayList<String>();
    	 
    	 // Breadth First Search Algorithm
    	 completions = breadthFirstSearch(prefix, numCompletions);
    	 
    	 return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}