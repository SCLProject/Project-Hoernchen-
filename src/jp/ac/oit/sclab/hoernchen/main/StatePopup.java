package jp.ac.oit.sclab.hoernchen.main;

import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatePopup extends Pane{
	public static final int TITLE_OF_TOP = 0;
	public static final int TITLE_OF_LEFT = 1;
	public static final int TITLE_OF_BOTTOM = 2;
	public static final int TITLE_OF_RIGHT = 3;
	public static final int TITLE_OF_CENTER = 4;
	

	private Arc[] stateChild = new Arc[4];
	private Circle stateCircle = new Circle();
	
	
	
	
	private double[] childAngles = {45,135,225,315};
	private Color[] childColors = {Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE,Color.ORANGE};

	private double circleLength ;
	private double childLength;
	
	private String[] titles = {"学内","学外","授業","ﾅｶｶﾞﾜ","在室"};
	Text[] titleLabels = new Text[5];
	
	

	double[] title_layoutX = {0,0,0,0,0};
	double[] title_layoutY = {0,0,0,0,0};		
	
	private double centerX = 0;
	private double centerY = 0;
	
	
	public StatePopup(double circleLength,double childLength){
			this.childLength = childLength;
			this.circleLength = circleLength;
		
			final InnerShadow innerShadow = new InnerShadow();
			innerShadow.setRadius(5d);
			innerShadow.setOffsetX(2);
			innerShadow.setOffsetY(2);
		 
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
		
		for(int i  = 0; i< titleLabels.length;i++){			 
			titleLabels[i] = new Text();
			titleLabels[i].setText(titles[i]);
			titleLabels[i].setFont(new Font("Arial Black",30));
			titleLabels[i].setFill(Color.web("#BBBBBB"));
			titleLabels[i].setEffect(innerShadow);
			
			getChildren().add(titleLabels[i]);
			
		}
		setCenter(0, 0);
		

		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void setCenter(double centerX,double centerY){
		this.centerX =centerX;
		this.centerY = centerY;
		
		
		
		
		
		
		
		
		for(Arc state : stateChild){
			state.setCenterX(centerX);
			state.setCenterY(centerY);
			
		}
		stateCircle.setCenterX(centerX);
		stateCircle.setCenterY(centerY);
		titleLabels[TITLE_OF_BOTTOM].autosize();
		
		System.out.println("TextSize ,width : "+titleLabels[TITLE_OF_BOTTOM].getWrappingWidth()+"\nHeight : "+ titleLabels[TITLE_OF_BOTTOM].getFont().getSize());
		titleLabels[TITLE_OF_TOP].setLayoutY(centerY-130);
		titleLabels[TITLE_OF_CENTER].setLayoutY(centerY+10);
		titleLabels[TITLE_OF_LEFT].setLayoutY(centerY+15);
		titleLabels[TITLE_OF_BOTTOM].setLayoutY(centerY+130+30);
		titleLabels[TITLE_OF_RIGHT].setLayoutY(centerY+15);
		
		
		titleLabels[TITLE_OF_TOP].setLayoutX(centerX-30);
		titleLabels[TITLE_OF_CENTER].setLayoutX(centerX-30);
		titleLabels[TITLE_OF_LEFT].setLayoutX(centerX-110-60);
		titleLabels[TITLE_OF_BOTTOM].setLayoutX(centerX-30);
		titleLabels[TITLE_OF_RIGHT].setLayoutX(centerX+110);
		
		
		
		
		
	}
	public void setText(int location,String title){
		
		if(location < TITLE_OF_TOP || location > TITLE_OF_CENTER){
			throw new IllegalArgumentException("値が不正です");
		}
		
		if(title.length() > 4){
			throw new IllegalArgumentException();
		}
		
		
		titles[location] = title;
		titleLabels[location].setText(title);
		
	}
	public void setColor(int location,Color color){
		
		if(location < TITLE_OF_TOP || location > TITLE_OF_CENTER){
			throw new IllegalArgumentException("値が不正です");
		}
		
		
		for(Arc arc : stateChild){
			arc.setFill(Color.ORANGE);
		}

		stateCircle.setFill(Color.ORANGE);
		
		
		
		switch(location){
			case TITLE_OF_BOTTOM:
			case TITLE_OF_LEFT:
			case TITLE_OF_RIGHT:
			case TITLE_OF_TOP:
				stateChild[location].setFill(color);
				
				
				break;
			case TITLE_OF_CENTER:
				stateCircle.setFill(color);
				break;
			default:
				break;
		}
		
		
		
		
		
		
	}
	public void setColor(){
		
		for(Arc arc : stateChild){
			arc.setFill(Color.ORANGE);
		}

		stateCircle.setFill(Color.ORANGE);
		
		
		
	}

	

}
