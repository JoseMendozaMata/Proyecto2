package Interfaz;

import java.io.File;

import Logica.lista.Lista;
import Logica.lista.ListaNode;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

/**
 * esta clase contiene los algoritmos de ordenamiento 
 * que va a ordear la ListView de los resultados
 * 
 * @author fatimaleiva
 *
 */
public class Algoritmos {

	
	private String path;
	private File myFile;
	

	public void ordenar(ChoiceBox<String> choiceBox, Lista ocurrence, ListView<String> lista) {
		
		if(choiceBox.getValue().equals("Nombre")) {	//QuickSort
			ordenNombre(ocurrence, lista);
			
		}else if(choiceBox.getValue().equals("Fecha")) {	//BubbleSort
			ordenFecha(ocurrence, lista);
			
		}else {	//RadixSort
			ordenTamanno(ocurrence, lista);
		}
	}
	
	/**
	 * Este metodo realiza el algoritmo de ordenamiento quickSort
	 * para ordenar los elementos de la lista 
	 */
	private void ordenNombre(Lista ocurrence, ListView<String> lista) {
		
		QuickSort(ocurrence, 0, ocurrence.size-1);
	}
	private void ordenFecha(Lista ocurrence, ListView<String> lista) {
		bubbleSort(ocurrence, lista);

	}
	private void ordenTamanno(Lista ocurrence, ListView<String> lista) {
		RadixSort(ocurrence);
	}
	
	public long getFecha(ListaNode node) {
		
		File f = new File(node.getUrl());
		long lastModified = f.lastModified();
		
		return lastModified;
	}
	
	private void bubbleSort(Lista list, ListView<String> resultList) {
		ListaNode i1= list.getIndex(0);
		ListaNode i2= i1.next;
		
		if(i2==null) {
			
		}else {
			bubbleAux(list, i1, i2);
		}
		
	}
	private void bubbleAux(Lista list, ListaNode node1, ListaNode node2) {
		
		
		for(int i=0; i<list.size; i++) {
			node1= list.getIndex(0);
			node2= node1.next;
			
			while(node2 != null) {
				long date1= getFecha(node1);
				long date2= getFecha(node2);
				
				
				if(date1 > date2) {
					String auxURL= node2.getUrl();
					int auxLine= node2.getLine();
					
					node2.setUrl(node1.getUrl());
					node2.setLine(node1.getLine());
					
					node1.setLine(auxLine);
					node1.setUrl(auxURL);
				
				}
				node1= node1.next;
				node2=node2.next;
			}
		}
	}
	public String obtainName(ListaNode position) {
		this.path = position.getUrl();
        myFile = new File(path);
		
		String name = myFile.getName();
		
		int index= name.lastIndexOf(".");
		String secName= name.substring(0,index);
		

		return secName;
	}
	public static void swap(Lista array, int left, int right) {
		
		System.out.println("swap");
		String auxURL= array.getIndex(left).getUrl();
		int auxLine= array.getIndex(left).getLine();
		
		array.getIndex(left).setUrl(array.getIndex(right).getUrl());
		array.getIndex(left).setLine(array.getIndex(right).getLine());
		
		array.getIndex(right).setLine(auxLine);
		array.getIndex(right).setUrl(auxURL);
	}
	
	
	public void QuickSort(Lista list, int left, int right) {
		int i= left; 
		int j= right;
		int piv= (i+j)/2;
		System.out.println("i es: "+ i+ " y j es: "+ j);
		while(i<j) {
			if(obtainName(list.getIndex(i)).compareTo(obtainName(list.getIndex(piv))) < 0) { 
				i++;
				System.out.println("una corrida con i");
			}if(obtainName(list.getIndex(j)).compareTo(obtainName(list.getIndex(piv))) > 0) { 
				j--;
				System.out.println("una corrida con j="+j);
			}if(i<j) {
				swap(list, i, j);
				i++;
				j--;
			}
		}
		if(left < j-1) {
			QuickSort(list, left, piv-1);
		}if(j+1 < right)
			QuickSort(list, piv, right);	
	}
	
	public void RadixSort(Lista list) {
		RadixList aux= new RadixList();
		int replays= getMaxDigits(list);
		
		//Se inserta los nodos que se encuentran en la lista de ocurrencias a la lista radix
		for(int i= 0; i <= replays; i++) {
			RadixSortAux(list, aux, i);
		}
	}
	public void RadixSortAux(Lista list, RadixList auxiliar, int position) {
		
		//Se inserta los nodos que se encuentran en la lista de ocurencias a la lista radix
		for(int i= 0; i < list.size; i++) {
			
			//element es el residuo de la division de los bytes

			int element= getBytes(list.getIndex(i).getUrl())%(int) Math.pow(10, position + 1);
			
			//data es elemento que necesito del residuo que va a ser el primero
			int data= element / (int) Math.pow(10, position);
			
			auxiliar.insertIndex(list.getIndex(i), data);
		}
		
		for(int i= 0; i < list.size; i++) {
			auxiliar.returnNodes(list);
		}
		
	}
	
	public int getMaxDigits(Lista list) {
		int lenght=0;
		int max = 0;
		
		// Obtener los elementos de la lista de ocurrencias
		for(int i = 0 ; i < list.size; i++) {
			
			ListaNode node = list.getIndex(i);
			int numbytes = getBytes(node.getUrl());
			
			// Obtener cantidad de digitos del numero
			while(numbytes != 0) {
				
				lenght++;
				numbytes = numbytes / 10; 
				
			}
			
			// Se comparan para obtener la maxima cantidad de digitos
			if(lenght > max) {
				max = lenght;
			}
			
			lenght = 0;
			
		}
		
		return max;
		
	}
	
	/**
	 * Obtiene el tamanno en bytes del documento 
	 * @param url: es la direccion del documento
	 * @return: retorna el tamanno
	 */
	public int getBytes(String url) {
		File file = new File(url);
		float lenght = file.length();	//Longitud en bytes del archivo
		
		return (int)lenght;
	}

}
