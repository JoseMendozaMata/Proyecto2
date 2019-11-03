package Logica.lista;

public class Lista {
	private ListaNode first;
	private ListaNode last;
	public int size;
	
	public Lista() {
		this.first=null;
		this.last=null;
		this.size=0;
	}
	public void initialize() {
		this.first=null;
		this.last=null;
		this.size=0;
	}
	
	public void add_Last(String url, int line, int posline) {
		if(this.first ==null) {
			this.first= new ListaNode(url, line, posline);
			this.last= this.first;
			this.size++;
			
		} else {
			ListaNode nuevo= new ListaNode(url, line, posline);
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
