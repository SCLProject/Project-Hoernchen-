package jp.ac.oit.sclab.hoernchen.main;

import java.awt.Point;
import java.io.IOException;
import java.util.Calendar;

import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jp.ac.oit.sclab.hoernchen.util.LissUtil;
import jp.ac.oit.sclab.hoernchen.util.LissUtil.PointDirection;
import jp.ac.oit.sclab.hoernchen.util.LissUtil.State;

public class StudentButton extends Pane{


	final String pattern = "YY-MM-dd HH:mm";
	private Parent root;
	private StudentButtonController sbController;
	private final static String fxmlFileName = "item.fxml";

	private Pane mainPane ;
	Color nonSelectedColor = Color.SKYBLUE;
	Color selectedColor = Color.AQUA;


	 private double inThreshold = 60.0;// 内側のフリック閾値
	 private double outThreshold = 200.0;//外側のフリック閾値

	 private int prevState = -1;

	 private int stateNow = State.STATE_RETURN_HOME;



	Stage primaryStage ;
	StatePopup sPopup = new StatePopup(100,300,nonSelectedColor);
	Popup popup = new Popup();

	static public Color getColorWithAlpha(Color color , double alpha){
		return  Color.rgb((int)(color.getRed()*255),(int)(color.getGreen()*255),(int)(color.getBlue()*255),alpha);

	}


	State nowState = new State();

	public StudentButton(Stage stage,Seat user){
		root = createRootParentFromFXML();

		setEvent();

		getChildren().add(root);
		primaryStage = stage;


		setAccessTime(user.getRecentAccess());
		setSeatId(""+user.getSeatId());
		setState(nowState.getNowState());
		setParsonName(user.getmStudent().getName());



	}




	public StudentButton(Stage stage){

		nonSelectedColor = getColorWithAlpha(nonSelectedColor, 0.8);
		root = createRootParentFromFXML();

		setEvent();

		getChildren().add(root);
		primaryStage = stage;


	}

	private Point pointPressed;
	private Point pointMoved;


	private void setEvent(){




		sPopup = new StatePopup(inThreshold, outThreshold,nonSelectedColor);

		popup.getContent().addAll(sPopup);

		popup.setWidth(outThreshold);
		popup.setHeight(outThreshold);


		System.out.println("popup:"+popup.getHeight()+","+popup.getWidth());



		 pointPressed  = new Point(0,0);
		 pointMoved = new Point(0,0);





		 mainPane.addEventHandler(TouchEvent.ANY, new EventHandler<TouchEvent>(){

			@Override
			public void handle(TouchEvent arg0) {
				// TODO 自動生成されたメソッド・スタブ
	/*
				if(arg0.getEventType().equals(TouchEvent.TOUCH_MOVED)){

				}
*/


				if(arg0.getEventType().equals(TouchEvent.TOUCH_PRESSED)){





					pointPressed.setLocation(arg0.getTouchPoint().getScreenX(), arg0.getTouchPoint().getScreenY());

					System.out.println("X:"+arg0.getTouchPoint().getX());
					System.out.println("Y:"+arg0.getTouchPoint().getY());

					popup.show(primaryStage);




				}




				if(arg0.getEventType().equals(TouchEvent.TOUCH_RELEASED)){
					popup.hide();
				}



			}

		 });




		mainPane.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {




				if(arg0.getEventType().equals(MouseEvent.MOUSE_PRESSED)){



					pointPressed.setLocation(arg0.getX(), arg0.getY());


					popup.setX(arg0.getScreenX() - outThreshold);
					popup.setY(arg0.getScreenY() - outThreshold);

					popup.show(primaryStage);


				}


				if(arg0.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
					//System.out.println("MOUSE_RELEASED");
					pointMoved.setLocation(arg0.getX(),arg0.getY());
					int direction = getDirection();
					switch(direction){
						case PointDirection.STAY:
							if(stateNow == State.STATE_IN_ROOM){
								setState(State.STATE_RETURN_HOME);
								stateNow = State.STATE_RETURN_HOME;
							}
							else{
								setState(State.STATE_IN_ROOM);
								stateNow = State.STATE_IN_ROOM;
								}
						break;
					case PointDirection.UP:
						setState(State.STATE_IN_LECTURING);
						stateNow = State.STATE_IN_LECTURING;
						break;
					case PointDirection.DOWN:
						setState(State.STATE_ON_CAMPUS);
						stateNow = State.STATE_ON_CAMPUS;
						break;
					case PointDirection.LEFT:
						setState(State.STATE_OFF_CAMPUS);
						stateNow = State.STATE_OFF_CAMPUS;
						break;

					case PointDirection.RIGHT:
						setState(State.STATE_UNKNOWN);
						stateNow = State.STATE_UNKNOWN;
						break;
					case PointDirection.OUT_SIDE:

					default :
						break;
				}





					popup.hide();
				}

				if(arg0.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){
					pointMoved.setLocation(arg0.getX(), arg0.getY());
					int direction = getDirection();



					switch(direction){
						case PointDirection.STAY:
							if(direction != prevState){





								sPopup.setColor(StatePopup.TITLE_OF_CENTER, selectedColor);
								System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(direction));

							}
							break;
						case PointDirection.UP:
							if(direction != prevState)	{
								sPopup.setColor(StatePopup.TITLE_OF_TOP, selectedColor);
								System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(direction));

							}
							break;
						case PointDirection.DOWN:
							if(direction != prevState)	{
								sPopup.setColor(StatePopup.TITLE_OF_BOTTOM, selectedColor);
								System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(direction));

							}
							break;
						case PointDirection.RIGHT:
							if(direction != prevState)	{
								sPopup.setColor(StatePopup.TITLE_OF_RIGHT, selectedColor);
								System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(direction));

							}
							break;

						case PointDirection.LEFT:
							if(direction != prevState)	{
								sPopup.setColor(StatePopup.TITLE_OF_LEFT, selectedColor);
								System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(direction));

							}
							break;
						case PointDirection.OUT_SIDE:
							if(direction != prevState)	{
								sPopup.setColor(StatePopup.TITLE_OF_LEFT, selectedColor);
								System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(direction));

							}
						default :
							sPopup.setDefaultColor();

							break;
					}


					prevState = direction;









				}


			}

		});




	}




	private int getDirection(){
		return LissUtil.PointDirection.getDirectMoved(pointPressed, pointMoved, inThreshold, outThreshold);
	}

	private Parent createRootParentFromFXML(){
		Parent makeRoot = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxmlFileName));





		try {
			makeRoot = (Parent) fxmlLoader.load();
			sbController = (StudentButtonController)fxmlLoader.getController();
			sbController.setNotify(0);
			mainPane = sbController.getPane();
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
		if(state == State.STATE_IN_ROOM)
			sPopup.setCenterState(State.STATE_RETURN_HOME);
		else
			sPopup.setCenterState(State.STATE_IN_ROOM);
		}
	public int getState(){
		return sbController.getState();
	}









}
