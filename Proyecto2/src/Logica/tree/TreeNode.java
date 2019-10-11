package Logica.tree;

import Logica.lista.Lista;

/**
 * Clase de los nodos del arbol
 * 
 * @author Fatima y Jose
 *
 */
public class TreeNode {
	String word;
	public Lista lista;
	TreeNode right;
	TreeNode left;
	
	public TreeNode(String word) {
		this.word=word;
		this.lista= new Lista();
	}
	public String getWord() {
		return this.word;
	}
	
}
