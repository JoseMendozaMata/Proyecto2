package Logica.lista;

import java.io.File;

/**
 * Clase de los nodos de la Lista de ocurrencias
 * @author fatima y Jose
 *
 */
public class ListaNode {
	private int line;
	private int posline;
	private String url;
	public ListaNode next;
	public ListaNode prev;

	public ListaNode(String url, int line, int posline) {
		this.line= line;
		this.posline = posline;
		this.url=url;
		this.next=null;
		this.prev=null;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	public int getPosLine() {
		return posline;
	}

	public void setPosLine(int posline) {
		this.posline = posline;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		File file= new File(this.url);
		
		return file.getName();
	}
	
	
}
