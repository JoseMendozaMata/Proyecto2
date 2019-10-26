package Interfaz;

import Logica.lista.Lista;
import Logica.lista.ListaNode;

public class RadixList {

	private Lista list0;
	private Lista list1;
	private Lista list2;
	private Lista list3;
	private Lista list4;
	private Lista list5;
	private Lista list6;
	private Lista list7;
	private Lista list8;
	private Lista list9;
	public int lenght;
	public int count;
	
	public RadixList() {
		this.list0= new Lista();
		this.list1= new Lista();
		this.list2= new Lista();
		this.list3= new Lista();
		this.list4= new Lista();
		this.list5= new Lista();
		this.list6= new Lista();
		this.list7= new Lista();
		this.list8= new Lista();
		this.list9= new Lista();
		this.lenght = 10;
		this.count=0;
	}
	
	public void insertIndex(ListaNode node, int element) {
		
		switch(element) {
		
		case 0:
			this.list0.add_Last(node.getUrl(), node.getLine());
			break;
		case 1:
			this.list1.add_Last(node.getUrl(), node.getLine());
			break;
		case 2:
			this.list2.add_Last(node.getUrl(), node.getLine());
			break;
		case 3:
			this.list3.add_Last(node.getUrl(), node.getLine());
			break;
		case 4:
			this.list4.add_Last(node.getUrl(), node.getLine());
			break;
		case 5:
			this.list5.add_Last(node.getUrl(), node.getLine());
			break;
		case 6:
			this.list6.add_Last(node.getUrl(), node.getLine());
			break;
		case 7:
			this.list7.add_Last(node.getUrl(), node.getLine());
			break;
		case 8:
			this.list8.add_Last(node.getUrl(), node.getLine());
			break;
		case 9:
			this.list9.add_Last(node.getUrl(), node.getLine());
			break;
			
		}
		
	}
	
	public void returnNodes(Lista myList){
		
		for(int i = 0; i < this.lenght; i++) {
			
			switch(i) {
			
			case 0:
				if(this.list0.size != 0) {
					//si la lista no esta vacia entonces cambia nodos
					for(int j = 0; j < this.list0.size; j++) {
						ListaNode node = this.list0.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list0.initialize();

				}
				break;
			case 1:
				if(this.list1.size != 0) {
					
					for(int j = 0; j < this.list1.size; j++) {
						ListaNode node = this.list1.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list1.initialize();

				}
				
				break;
			case 2:
				if(this.list2.size != 0) {
					
					for(int j = 0; j < this.list2.size; j++) {
						ListaNode node = this.list2.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list2.initialize();

				}
				
				break;
			case 3:
				if(this.list3.size != 0) {
					
					for(int j = 0; j < this.list3.size; j++) {
						ListaNode node = this.list3.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list3.initialize();

				}
				
				break;
			case 4:
				if(this.list4.size != 0) {
					
					for(int j = 0; j < this.list4.size; j++) {
						ListaNode node = this.list4.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list4.initialize();

				}
				
				break;
			case 5:
				if(this.list5.size != 0) {
					
					for(int j = 0; j < this.list5.size; j++) {
						ListaNode node = this.list5.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list5.initialize();

				}
				
				break;
			case 6:
				if(this.list6.size != 0) {
					
					for(int j = 0; j < this.list6.size; j++) {
						ListaNode node = this.list6.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list6.initialize();

				}
				
				break;
			case 7:
				if(this.list7.size != 0) {
					
					for(int j = 0; j < this.list7.size; j++) {
						ListaNode node = this.list7.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list7.initialize();
				}
				
				break;
			case 8:
				if(this.list8.size != 0) {
					
					for(int j = 0; j < this.list8.size; j++) {
						ListaNode node = this.list8.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list8.initialize();
				}
				
				break;
			case 9:
				if(this.list9.size != 0) {
					
					for(int j = 0; j < this.list9.size; j++) {
						ListaNode node = this.list9.getIndex(j);
						myList.getIndex(count).setUrl(node.getUrl());
						myList.getIndex(count).setLine(node.getLine());

						count++;
					}
					list9.initialize();
				}
				
				break;
			}
		}
		this.count=0;
		
		
		
	}
}
