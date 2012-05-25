package jp.ac.oit.sclab.hoernchen.main;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainLayoutController  implements Initializable{

	private static int MAX_MEMBAR_SEMINAR = 12;
	private static int MAX_MEMBAR_MASTAR = 3;
	private static int MAX_MEMBAR_OTHER = 3;
	@FXML AnchorPane topPanel;
	@FXML AnchorPane teacherPanel;
	@FXML Button calendarButton;


	@FXML AnchorPane seminar3;
	@FXML VBox seminar3_member1;
	@FXML VBox seminar3_member2;

	@FXML AnchorPane seminar4;
	@FXML VBox seminar4_member1;
	@FXML VBox seminar4_member2;


	@FXML AnchorPane master;
	@FXML VBox master_member;

	@FXML AnchorPane other;
	@FXML VBox other_member;

	@FXML AnchorPane clockPane;

	Clock clock;

	final static  String pattern = "YY-MM-dd HH:mm:ss";
	 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	 boolean runnable = true;


	 private Timeline delay,secondLine;

	public void setSeminar3Membar(Node addNode){

		int size =0;
		size += seminar3_member1.getChildren().size();
		size += seminar3_member2.getChildren().size();

		if(size >= MAX_MEMBAR_SEMINAR){
			throw new IllegalArgumentException("Memberは最大数です。");
		}




		if(size %2 == 0){
			seminar3_member1.getChildren().add(addNode);
		}else{
			seminar3_member2.getChildren().add(addNode);
		}




	}

	public void setMasterMembar(Node addNode){

		int size = master_member.getChildren().size();
		if(size >= MAX_MEMBAR_MASTAR){
			throw new IllegalArgumentException("Memberは最大数です。");
		}

		master_member.getChildren().add(addNode);

	}


	public void setOtherMembar(Node addNode){
		int size = other_member.getChildren().size();
		if(size >= MAX_MEMBAR_OTHER){
			throw new IllegalArgumentException("Memberは最大数です。");
		}

		other_member.getChildren().add(addNode);
	}


	public void setSeminar4Membar(Node addNode){
		int size =0;
		size += seminar4_member1.getChildren().size();
		size += seminar4_member2.getChildren().size();
		if(size >= MAX_MEMBAR_SEMINAR){
			throw new IllegalArgumentException("Memberは最大数です。");
		}

		if(size %2 == 0){
			seminar4_member1.getChildren().add(addNode);
		}else{
			seminar4_member2.getChildren().add(addNode);
		}



	}




	public void clockPlay(){

		delay = new Timeline();
		delay.getKeyFrames().add(
				new KeyFrame(new Duration(1000 - System.currentTimeMillis()%1000), new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {

						if(secondLine != null){
							secondLine.stop();
						}
						secondLine = new Timeline();
						secondLine.setCycleCount(Timeline.INDEFINITE);
						secondLine.getKeyFrames().add(
								new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

									@Override
									public void handle(ActionEvent event) {

										setCalendar();
									}

								}));
						secondLine.play();
					}

				}));
		delay.play();




	}
public void stop(){
	delay.stop();
	if(secondLine != null){
		secondLine.stop();
	}
}

	public void setCalendar(){

		clock.refreshClocks();



	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {




		clock = new Clock(Color.ORANGERED, Color.rgb(255, 255, 255, 0.0));

		clockPane.getChildren().add(clock);


		clockPlay();

	}








}
