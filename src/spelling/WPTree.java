/**
 * 
 */
package spelling;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 * 
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	// This constructor is used by the Text Editor Application
	// You'll need to create your own NearbyWords object here.
	public WPTree () {
		this.root = null;
		// TODO initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}
	
	//This constructor will be used by the grader code
	// Constructs a WPTree of NearbyWords objects
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	
	 /** Return a path from word1 to word2 through dictionary words with
	 *  the restriction that each step in the path can involve only a
	 *  single character mutation  
	 * @param word1 The first word
	 * @param word2 The second word
	 * @return list of Strings which are the path from word1 to word2
	 *         including word1 and word2
	 */
	public List<String> findPath(String word1, String word2) 
	{
	    // TODO: Implement this method.
		Queue<WPTreeNode> queue = new LinkedList<WPTreeNode>();		// WPTreeNodes to explore
		HashSet<String> visited = new HashSet<String>();			// To avoid checking the
																	// same strings
		// Root node
		WPTreeNode root = new WPTreeNode(word1, null);
		
		// Add root to queue and visited
		queue.add(root);
		visited.add(root.getWord());
		
		WPTreeNode curr = null;
		
		// Breadth first search Algorithm
//		While queue is not empty and we haven't found word2
//			Remove first node from queue and assign to curr
//			Set word equal to curr's word
//			Create a list of words one step from curr's word. Assign to list "neighbors"
//			for each word in list neighbors
//				if we did not visit this word (not in list "visited")
//					assign this word to a child node (curr's child)
//					add this child node to queue
//					add this word to list "visited"
//					if this word is the same as word2
//						return the path from this word's node to root
		while (!queue.isEmpty()) {
			
			curr = queue.remove();
			String word = curr.getWord();
			
			List<String> neighbors = nw.distanceOne(word, true);
			
			for (String neighbor : neighbors) {
				if (!visited.contains(neighbor)) {
					
					WPTreeNode child = curr.addChild(neighbor);
					queue.add(child);
					visited.add(neighbor);
					
					if (neighbor.equals(word2)) {
						return child.buildPathToRoot();
					}
					
				}
			}
		}
		
		return null;
	}
	
	// Method to print a list of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
	public static void main(String[] args) {

		Dictionary dict = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(dict, "data/dict.txt");
		WPTree tree = new WPTree(new NearbyWords(dict));
		
		// Test #1
		String word1 = "pool";
		String word2 = "spoon";
		
		System.out.println("Testing for WPTree");
		System.out.println("Path from \"" + word1 + "\" to \"" + word2 + "\" is: ");
		System.out.println(tree.findPath(word1, word2));
		
		// Test #2
		String word3 = "stools";
		String word4 = "moon";
		
		System.out.println("Testing for WPTree");
		System.out.println("Path from \"" + word3 + "\" to \"" + word4 + "\" is: ");
		System.out.println(tree.findPath(word3, word4));
	}
	
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.  Each node should only
 * contain a word in the dictionary and the relationship between nodes is
 * that a child is one character mutation (deletion, insertion, or
 * substitution) away from its parent
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    /** Construct a node with the word w and the parent p
     *  (pass a null parent to construct the root)  
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    /** Add a child of a node containing the String s
     *  precondition: The word is not already a child of this node
     * @param s The child node's word
	 * @return The new WPTreeNode
	 */
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /** Get the list of children of the calling object
     *  (pass a null parent to construct the root)  
	 * @return List of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /** Allows you to build a path from the root node to 
     *  the calling object
     * @return The list of strings starting at the root (index 0) and 
     *         ending at the calling object
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /** Get the word for the calling object
     *
	 * @return Getter for calling object's word
	 */
    public String getWord() {
        return this.word;
    }
    
    /** toString method
    *
	 * @return The string representation of a WPTreeNode
	 */
    public String toString() {
        String ret = "Word: "+word+", parent = ";
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        ret+="[ ";
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }

}


