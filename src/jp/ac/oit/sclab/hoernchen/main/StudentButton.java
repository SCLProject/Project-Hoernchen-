package jp.ac.oit.sclab.hoernchen.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class StudentButton extends Pane{


	private Parent root;
	private StudentButtonController sbController;
	private final static String fxmlFileName = "item.fxml";
	
	public StudentButton(){
		root = createRootParentFromFXML();
		
		
		getChildren().add(root);
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	private Parent createRootParentFromFXML(){
		Parent makeRoot = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxmlFileName));
		
		
		try {
			makeRoot = (Parent) fxmlLoader.load();
			sbController = (StudentButtonController)fxmlLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return makeRoot;
	}


	public void setParsonName(String name){
		sbController.setParsonName(name);
	}
	public void setAccessTime(Calendar time){
		
		final String pattern = "YY/MM/dd HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sbController.setAccessTime(sdf.format(time.getTime()));
	}
	public void setSeatId(String seat){
		sbController.setSeatId(seat);
		
	}
	public void setState(int state){
		sbController.setState(state);
	}
	public int getState(){
		return sbController.getState();
	}








}
