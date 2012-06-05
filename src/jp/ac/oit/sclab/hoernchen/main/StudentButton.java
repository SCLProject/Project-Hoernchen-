package jp.ac.oit.sclab.hoernchen.main;

import java.awt.Point;
import java.io.IOException;
import java.util.Calendar;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jp.ac.oit.sclab.hoernchen.util.LissUtil;

public class StudentButton extends Pane{


	final String pattern = "YY-MM-dd HH:mm";
	private Parent root;
	private StudentButtonController sbController;
	private final static String fxmlFileName = "item.fxml";

	private Pane mainPane ;


	 private double inThreshold = 40.0;// 内側のフリック閾値
	 private double outThreshold = 150.0;//外側のフリック閾値
	Stage primaryStage ;
	StatePopup sPopup = new StatePopup();
	Popup popup = new Popup();



	public StudentButton(Stage stage){
		root = createRootParentFromFXML();

		setEvent();

		getChildren().add(root);
		primaryStage = stage;


	}
	boolean dragged = false;
	private Point pointPressed;
	private Point pointMoved;


	private void setEvent(){

		popup.getContent().addAll(sPopup);

		sPopup.autosize();

		inThreshold = sPopup.getWidth()/6;
		outThreshold = sPopup.getWidth()/2;


		 pointPressed  = new Point(0,0);
		 pointMoved = new Point(0,0);





		 mainPane.addEventFilter(TouchEvent.ANY, new EventHandler<TouchEvent>(){

			@Override
			public void handle(TouchEvent arg0) {
				// TODO 自動生成されたメソッド・スタブ

				if(arg0.getEventType().equals(TouchEvent.TOUCH_PRESSED)){

					pointPressed.setLocation(arg0.getTouchPoint().getScreenX(), arg0.getTouchPoint().getScreenY());



					popup.setX(arg0.getTouchPoint().getScreenX()-sPopup.getWidth()/2);
					popup.setY(arg0.getTouchPoint().getScreenY()-sPopup.getHeight()/2);
					popup.show(primaryStage);




				}
				//if(arg0.getEventType().equals(Touch))




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



					popup.setX(arg0.getScreenX()-sPopup.getWidth()/2);
					popup.setY(arg0.getScreenY()-sPopup.getHeight()/2);
					popup.show(primaryStage);

				}


				if(arg0.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
					//System.out.println("MOUSE_RELEASED");

					popup.hide();
				}

				if(arg0.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){

					pointMoved.setLocation(arg0.getX(), arg0.getY());
					System.out.println("Direction : "+LissUtil.PointDirection.getDirectMovedString(getDirection()));


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
	}
	public int getState(){
		return sbController.getState();
	}









}
