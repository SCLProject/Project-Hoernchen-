package jp.ac.oit.sclab.hoernchen.main;

import java.io.IOException;
import java.util.Calendar;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jp.ac.oit.sclab.hoernchen.util.LissUtil;

public class StudentButton extends Pane{


	final String pattern = "YY-MM-dd HH:mm";
	private Parent root;
	private StudentButtonController sbController;
	private final static String fxmlFileName = "item.fxml";


	private static final int STAY = 0;
	private static final int UP = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final int OUT_SIDE = 5;

	static final double threshold = 50.0;
	static final double out_threshold = 250.0;

	Stage primaryStage ;

	Circle c3;
	Circle c1;
	Circle c2;
	Popup popup = new Popup();
	Popup popup2 = new Popup();
	Popup popup3 = new Popup();







	public StudentButton(Stage stage){
		root = createRootParentFromFXML();

		setEvent();

		getChildren().add(root);
		primaryStage = stage;


	}
	boolean dragged = false;
	private void setEvent(){


		c1 = new Circle(25,25,50,Color.AQUAMARINE);
		c2 = new Circle(25,25,25,Color.GOLD);
		c3 = new Circle(25,25,100,Color.AQUAMARINE);
		popup.getContent().addAll(c1);
		popup2.getContent().addAll(c2);
		popup3.getContent().addAll(new StatePopup());





		addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub


				if(arg0.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
					//System.out.println("MOUSE_PRESSED");
					popup.setX(arg0.getScreenX()-popup.getWidth()/2);
					popup.setY(arg0.getScreenY()-popup.getHeight()/2);
					//popup.show(primaryStage);

					popup3.setX(arg0.getScreenX()-popup3.getWidth()/2);
					popup3.setY(arg0.getScreenY()-popup3.getHeight()/2);
					popup3.show(primaryStage);


				}


				if(arg0.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
					//System.out.println("MOUSE_RELEASED");
					popup.hide();
					popup2.hide();
					popup3.hide();
				}

				if(arg0.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){


					//System.out.println("MOUSE_DRAGGED");
					popup2.setX(arg0.getScreenX()-popup2.getWidth()/2);
					popup2.setY(arg0.getScreenY()-popup2.getHeight()/2);


					// TODO 自動生成されたメソッド・スタブ


					Color color = Color.AQUAMARINE;
					switch(getMouseMovedDerection(getDiffMouseY(), getDiffMouseX())){
						case UP:
							color = Color.CORNSILK;
							break;
						case DOWN:
							color = Color.LIGHTGREEN;
							break;
						case LEFT:
							color = Color.LIGHTPINK;
							break;
						case RIGHT:
							color = Color.ORANGERED;
							break;
						case STAY:
						case OUT_SIDE:
						default:
							color = Color.AQUAMARINE;
							break;
					}

					c3.setFill(color);



					popup3.show(primaryStage);
				}


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
			sbController.setNotify(0);

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
		LissUtil.DifferenceTime dT = new LissUtil.DifferenceTime(time);
		sbController.setAccessTime(dT.getDifferenceTime());
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




	private int getMouseMovedDerection(double y,double x){



		int angle = (int)((Math.atan2(y, x) * 180.0) / Math.PI);




		if(Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)) <= threshold){
			return STAY;
		}else if(Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)) > out_threshold){
			return OUT_SIDE;
		}else{

			if((45 <= angle) && (angle <= 135)){ // 上
				return UP;
			} else if((-45 >= angle) && (angle >= -135)){ // 下
				return DOWN;
			} else if((45 > angle) && (angle > -45)) { // 右
				return  LEFT;
			} else {
				return  RIGHT;
			}
		}
	}


		private double getDiffMouseX(){
			return  ((popup.getX() + popup.getWidth()/2) - (popup2.getX() + popup2.getWidth()/2));

		}

		private double getDiffMouseY(){
			return  ((popup.getY() + popup.getHeight()/2) - (popup2.getY() + popup2.getHeight()/2));

		}






}
