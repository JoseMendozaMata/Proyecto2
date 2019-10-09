package Logica.tree;


/**
 * Clase de los nodos del arbol
 * 
 * @author Fatima y Jose
 *
 */
public class TreeNode {
	String word;
	TreeNode right;
	TreeNode left;
	
	public TreeNode(String word) {
		this.word=word;
	}
	public String getWord() {
		return this.word;
	}
	
}
