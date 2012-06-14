package jp.ac.oit.sclab.hoernchen.main;

import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jp.ac.oit.sclab.hoernchen.util.LissUtil.State;

public class StatePopup extends Pane{
	public static final int TITLE_OF_TOP = 0;
	public static final int TITLE_OF_LEFT = 1;
	public static final int TITLE_OF_BOTTOM = 2;
	public static final int TITLE_OF_RIGHT = 3;
	public static final int TITLE_OF_CENTER = 4;


	private Arc[] stateChild = new Arc[4];
	private Circle stateCircle = new Circle();




	private double[] childAngles = {45,135,225,315};

	private double circleLength ;
	private double childLength;


	private int[] states = {State.STATE_IN_LECTURING,State.STATE_OFF_CAMPUS,State.STATE_ON_CAMPUS,State.STATE_UNKNOWN,State.STATE_IN_ROOM};


	Text[] titleLabels = new Text[5];



	double[] title_layoutX = {0,0,0,0,0};
	double[] title_layoutY = {0,0,0,0,0};

	private Color defaultColor;


	public StatePopup(double circleLength,double childLength,Color defaultColor){
			this.childLength = childLength;
			this.circleLength = circleLength;
			this.defaultColor = defaultColor;

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



			getChildren().add(stateChild[j]);


		}






		stateCircle.setRadius(circleLength);
		stateCircle.setFill(Color.CORAL);

		getChildren().add(stateCircle);

		for(int i  = 0; i< titleLabels.length;i++){
			titleLabels[i] = new Text();
			titleLabels[i].setText(State.createStateFromStateId(states[i]));
			titleLabels[i].setFont(new Font("Arial Black",40));
			titleLabels[i].setFill(Color.web("#BBBBBB"));
			titleLabels[i].setEffect(innerShadow);

			getChildren().add(titleLabels[i]);

		}
		setCenter(0, 0);

	}

		public void setCenterState(int state){






		}






	public void setCenter(double centerX,double centerY){
		for(Arc state : stateChild){
			state.setCenterX(centerX);
			state.setCenterY(centerY);

		}
		stateCircle.setCenterX(centerX);
		stateCircle.setCenterY(centerY);
		titleLabels[TITLE_OF_BOTTOM].autosize();

		titleLabels[TITLE_OF_TOP].setLayoutY(centerY - circleLength - (childLength - circleLength)/2 + titleLabels[TITLE_OF_TOP].getFont().getSize()/2);
		titleLabels[TITLE_OF_CENTER].setLayoutY(centerY+titleLabels[TITLE_OF_CENTER].getFont().getSize()/2-5);
		titleLabels[TITLE_OF_LEFT].setLayoutY(centerY+titleLabels[TITLE_OF_CENTER].getFont().getSize()/2-5);
		titleLabels[TITLE_OF_BOTTOM].setLayoutY(centerY + circleLength + (childLength-circleLength)/2 + titleLabels[TITLE_OF_BOTTOM].getFont().getSize()/2);
		titleLabels[TITLE_OF_RIGHT].setLayoutY(centerY+titleLabels[TITLE_OF_CENTER].getFont().getSize()/2-5);


		titleLabels[TITLE_OF_TOP].setLayoutX(centerX - (titleLabels[TITLE_OF_TOP].getText().length()*titleLabels[TITLE_OF_TOP].getFont().getSize())/2);
		titleLabels[TITLE_OF_CENTER].setLayoutX(centerX - (titleLabels[TITLE_OF_CENTER].getText().length()*titleLabels[TITLE_OF_CENTER].getFont().getSize())/2);
		titleLabels[TITLE_OF_LEFT].setLayoutX(centerX - childLength + (childLength - circleLength)/2 - (titleLabels[TITLE_OF_LEFT].getText().length()*titleLabels[TITLE_OF_LEFT].getFont().getSize()/2));
		titleLabels[TITLE_OF_BOTTOM].setLayoutX(centerX - (titleLabels[TITLE_OF_BOTTOM].getText().length()*titleLabels[TITLE_OF_BOTTOM].getFont().getSize())/2);
		titleLabels[TITLE_OF_RIGHT].setLayoutX(centerX + circleLength + (childLength-circleLength)/2 - (titleLabels[TITLE_OF_RIGHT].getText().length()*titleLabels[TITLE_OF_RIGHT].getFont().getSize()/2));





	}
	public void setText(int location,String title){

		if(location < TITLE_OF_TOP || location > TITLE_OF_CENTER){
			throw new IllegalArgumentException("値が不正です");
		}

		if(title.length() > 4){
			throw new IllegalArgumentException();
		}


		titleLabels[location].setText(title);

	}
	public void setColor(int location,Color color){

		if(location < TITLE_OF_TOP || location > TITLE_OF_CENTER){
			throw new IllegalArgumentException("値が不正です");
		}


		setDefaultColor();


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


	public void setDefaultColor(Color color){
		defaultColor = color;

		setDefaultColor();




	}


	public void setDefaultColor(){

		for(Arc arc : stateChild){
			arc.setFill(defaultColor);
			arc.setStroke(StudentButton.getColorWithAlpha(defaultColor, 1.0));
			arc.setStrokeWidth(2);
		}


		stateCircle.setFill(StudentButton.getColorWithAlpha(defaultColor, 1.0));



	}



}
