package Logica.tree;

import Logica.lista.Lista;

/**
 * Clase de los nodos del arbol
 * 
 * @author Fatima y Jose
 *
 */
public class TreeNode {
	public String word;
	
	public Lista lista;
	public TreeNode right;
	public TreeNode left;
	
	public TreeNode(String word) {
		this.word=word;
		this.lista= new Lista();
	}
	public String getWord() {
		return this.word;
	}
	
}
