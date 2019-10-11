package Logica.lista;

import java.io.File;

public class Lista {
	private ListaNode first;
	private ListaNode last;
	public int size;
	
	public void add_Last(String url, int line) {
		if(this.first ==null) {
			this.first= new ListaNode(url, line);
			this.last= this.first;
			this.size++;
			
		} else {
			ListaNode nuevo= new ListaNode(url, line);
			this.last.next= nuevo;
			nuevo.prev= this.last;
			this.last= nuevo;
			this.size++;
		}
		System.out.println("Se annade una ocurrencia");
	}
	
	public ListaNode getIndex(int element) {
		ListaNode temp=first;
		for(int i= 0; i< element; i++) {
			temp= temp.next;
		}
		return temp;	
	}
	
}
