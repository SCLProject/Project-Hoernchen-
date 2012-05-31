package jp.ac.oit.sclab.hoernchen.main;

import java.io.IOException;
import java.util.Calendar;

import javafx.event.EventHandler;
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

	Stage primaryStage ;



	public StudentButton(Stage stage){
		root = createRootParentFromFXML();


		getChildren().add(root);
		primaryStage = stage;

	}
	private Parent createRootParentFromFXML(){
		Parent makeRoot = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxmlFileName));
		final Circle c1 = new Circle(25,25,50,Color.AQUAMARINE);
		Circle c2 = new Circle(25,25,25,Color.GOLD);
		final Popup popup = new Popup();
		popup.getContent().addAll(c1);

		final Popup popup2 = new Popup();
		popup2.getContent().addAll(c2);

		final double threshold = 40.0;

		try {
			makeRoot = (Parent) fxmlLoader.load();
			sbController = (StudentButtonController)fxmlLoader.getController();
			sbController.setNotify(0);
			sbController.getPane().setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO 自動生成されたメソッド・スタブ

					popup.setX(arg0.getScreenX()-popup.getWidth()/2);
					popup.setY(arg0.getScreenY()-popup.getHeight()/2);
					popup.show(primaryStage);

				}

			});

			sbController.getPane().setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO 自動生成されたメソッド・スタブ

					// get diff
					double diffMouseX = ((popup.getX() + popup.getWidth()/2) - (popup2.getX() + popup2.getWidth()/2));
					double diffMouseY = ((popup.getY() + popup.getHeight()/2) - (popup2.getY() + popup2.getHeight()/2));


					//
					if((Math.abs(diffMouseX) < threshold) && (Math.abs(diffMouseY) < threshold)){
						System.out.println("center");
						c1.setFill(Color.AQUAMARINE);
					} else if (Math.abs(diffMouseX) < threshold) { // 上下判定
						if(diffMouseY > 0){
							System.out.println("up");
						} else {
							System.out.println("down");
						}
					} else { // 左右判定
						if(diffMouseX > 0){
							System.out.println("left");
						} else {
							System.out.println("right");
						}

					}

					popup.hide();
					popup2.hide();
				}

			});


			sbController.getPane().setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO 自動生成されたメソッド・スタブ

					//System.out.println(arg0.toString());

					popup2.setX(arg0.getScreenX()-popup2.getWidth()/2);
					popup2.setY(arg0.getScreenY()-popup2.getHeight()/2);


					// TODO 自動生成されたメソッド・スタブ

					// get diff
					double diffMouseX = ((popup.getX() + popup.getWidth()/2) - (popup2.getX() + popup2.getWidth()/2));
					double diffMouseY = ((popup.getY() + popup.getHeight()/2) - (popup2.getY() + popup2.getHeight()/2));


					//
					if((Math.abs(diffMouseX) < threshold) && (Math.abs(diffMouseY) < threshold)){
						c1.setFill(Color.AQUAMARINE);
					} else if (Math.abs(diffMouseX) < threshold) { // 上下判定
						if(diffMouseY > 0){
							c1.setFill(Color.RED);
							} else {
								c1.setFill(Color.ORANGE);
						}
					} else { // 左右判定
						if(diffMouseX > 0){
							c1.setFill(Color.YELLOW);
						} else {
							c1.setFill(Color.GREEN);
						}

					}


					popup2.show(primaryStage);




















				}
			});

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
