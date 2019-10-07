package Prueba;

public class main{
	
	public static void main(String[] args) {
		PDFManager text= new PDFManager();
		String tt= text.getText("C:\\Users\\Acer\\Desktop\\Pruebas Pogra2\\Proyecto1.pdf");
		System.out.println(tt);
	}
}
