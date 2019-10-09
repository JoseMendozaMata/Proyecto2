package Logica.tree;


public class Tree {
	
	private TreeNode root= null; //nodo sobre el que se trabaja
	public int lenght = 0;
	
	/**
	 * Metodo que verifica si el arbol contiene o no elementos
	 * 
	 * @return: booleano true si esta vacia y false si el arbol contiene elementos
	 */
	public boolean IsEmpty() {
		return root==null;
	}
	
	/**
	 * Inserta elementos al arbol llamando al metodo Insert
	 * que hace las verificacioes correspondientes 
	 * @param element
	 */
	public void Insert(String element) {
		lenght++;
		if(IsEmpty()) {
			this.root= new TreeNode(element);
		}else {
			root= Insert(element, this.root);
		}
	}
	
	private TreeNode Insert(String element, TreeNode current) {
		if(current== null) {
			System.out.println("Se creo la rama");
			return new TreeNode(element);
		}else if(current.getWord().compareTo(element) < 0) {
			current.left= Insert(element, current.left);
		} else if(current.getWord().compareTo(element) > 0) {
			current.right= Insert(element, current.right);
		}else {
			return current;
		}
		return current;
	}
	
	public TreeNode getNode(String element) {
		if(IsEmpty()) {
			return null;
		}else{
			return getNode(element, this.root);
		}
	}
	
	private TreeNode getNode(String element, TreeNode current) {
		if(current==null) {
			System.out.println("No Se encontró el elemento");
			return null;
		}else if(current.getWord().compareTo(element) < 0) {
			current = current.left;
		}else if(current.getWord().compareTo(element) > 0) {
			current = current.right;
		}else {		// Lo encuentra
			System.out.println("Encuentra el nodo");
			return current;
		}
		
		return getNode(element,current);
	}
}
