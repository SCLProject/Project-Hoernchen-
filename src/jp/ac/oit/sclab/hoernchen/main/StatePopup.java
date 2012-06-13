package jp.ac.oit.sclab.hoernchen.main;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

public class StatePopup extends Pane{

private Arc[] stateChild = new Arc[4];
private Circle stateCircle = new Circle();
private double[] childAngles = {45,135,225,315};
private Color[] childColors = {Color.ALICEBLUE,Color.RED,Color.GREEN,Color.YELLOW,Color.ORANGE};

	public StatePopup(double circleLength,double childLength){
		int i= 0; 
		for(int j = 0;j<stateChild.length;j++){
			stateChild[j] = new Arc();
			
			stateChild[j].setRadiusX(childLength);
			stateChild[j].setRadiusY(childLength);
			stateChild[j].setStartAngle(childAngles[j]);
			stateChild[j].setLength(90.0f);
			stateChild[j].setType(ArcType.ROUND);
			stateChild[j].setFill(childColors[j]);
			
		
			getChildren().add(stateChild[j]);
		}
		
		
		stateCircle.setRadius(circleLength);
		stateCircle.setFill(Color.CORAL);
		getChildren().add(stateCircle);
		setCenter(0, 0);
	}
	
	public void setCenter(double centerX,double centerY){
		System.out.println("StatePopup:center :" +centerX + " , " + centerY);
		for(Arc state : stateChild){
			state.setCenterX(centerX);
			state.setCenterY(centerY);
			
		}
		stateCircle.setCenterX(centerX);
		stateCircle.setCenterY(centerY);
		
		
		
		
		
	}
	

	

}
