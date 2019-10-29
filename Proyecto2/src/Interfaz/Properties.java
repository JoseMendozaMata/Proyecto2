package Interfaz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Properties {

	public void SeeProperties(ObservableList<String> files) {
						
			Stage stage= new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Pane root= new Pane();
			Scene scene= new Scene(root);
			
			Label l1= new Label("El tamaño del documento en bytes es: " + getProperties(files, "Size"));
			l1.setLayoutX(20);
			l1.setLayoutY(80);
			
			Label l2= new Label("Nombre del archivo: "+ getProperties(files, "Name"));
			l2.setLayoutX(20);
			l2.setLayoutY(20);
			l2.setFont(new Font("Arial", 16));
			
			Label l3= new Label("La última fecha de modificación fue el " + getProperties(files, "Date"));
			l3.setLayoutX(20);
			l3.setLayoutY(100);
			
			Label l4= new Label("Se encuentra en "+ getProperties(files, "path"));
			l4.setLayoutX(20);
			l4.setLayoutY(120);
			
			Rectangle rect= new Rectangle(520,185);
			rect.setFill(Color.WHITE);
			rect.setLayoutX(15);
			rect.setLayoutY(15);
			
			Button close= new Button("Listo");
			close.setOnAction(e -> stage.close());
			close.setLayoutX(200);
			close.setLayoutY(170);
			close.setStyle("-fx-background-color:\n" + 
					"        linear-gradient(#8399E4, #5678EC),\n" + 
					"        radial-gradient(center 50% -40%, radius 200%, #A09AC8 45%, #D2CEEB 50%);\n" + 
					"    -fx-background-radius: 6, 5;\n" + 
					"    -fx-background-insets: 0, 1;\n" + 
					"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" + 
					"    -fx-text-fill: #132665;"); 
			
			root.getChildren().addAll(rect, l1,l2, l3, l4, close);
			
			root.setPrefSize(550, 215);
			
			root.getChildren().addAll();
			root.setStyle("-fx-background-color: #E4DAF5");
			
			stage.setTitle("Document properties");
			stage.setScene(scene);
			stage.showAndWait();
		}
	
	public String getPP(ObservableList<String> files, String show) {
		
		
		return show;
	}
	public String getProperties(ObservableList<String> files, String show) {
			
			for(int i = 0; i < files.size(); i++) {
				
				String text= files.get(i).split("\n")[0];
				
				File f = new File(text);
				
				long lastModified = f.lastModified(); 
				System.out.println(lastModified);
				
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				
				String name = f.getName();
				
				Date lastModifiedDate = new Date( lastModified );
				System.out.println(text);
				
				
				float lenght = f.length();	//Longitud en bytes del archivo
				
				if(show.equals("Size")) {
					return Integer.toString((int)lenght);
				}
				else if(show.equals("Date")) {
					return simpleDateFormat.format( lastModifiedDate );
				}else if(show.equals("Name")) {
					return name;
				}else {
					return f.getPath();
				}
			}
			return "Nada"; 
	}


}
