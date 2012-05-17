package jp.ac.oit.sclab.hoernchen.main;

import java.io.IOException;



import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class StudentButton extends Pane implements EventHandler<MouseEvent>{


	private Parent root;
	private StudentButtonController sbController;
	private final static String fxmlFileName = "item.fxml";
	
	public StudentButton(){
		root = createRootParentFromFXML();
		
		
		getChildren().add(root);
		this.setEventHandler(MouseEvent.MOUSE_CLICKED,this);

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
	
	@Override
	public void handle(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		System.exit(0);
		
		
		
	}












}
