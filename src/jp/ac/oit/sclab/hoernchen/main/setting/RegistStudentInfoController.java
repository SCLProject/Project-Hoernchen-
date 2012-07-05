package jp.ac.oit.sclab.hoernchen.main.setting;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class RegistStudentInfoController  implements Initializable{
	@FXML ComboBox<String> type_comb;


	@FXML ComboBox<String> seat_comb;
	@FXML ComboBox<String> felica_comb;
	@FXML TextField studentNum_text;
	@FXML TextField name_text;
	@FXML Button felica_plus;

	@FXML Label debug_label;


	@FXML Button felica_minus;
	@FXML Button ok_button;

	@FXML Button apply_button;
	Popup popup = null;

	EventHandler<ActionEvent> typeCombEvent = new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			// TODO 自動生成されたメソッド・スタブ
			if(type_comb.getValue() != null){


				debug(""+type_comb.getValue());


			}
		}

	};

	private void debug(String text){
		debug_label.setText("debug : "+ text);
		System.out.println("debug : "+text);
	}



	EventHandler<MouseEvent> applyButtonEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			// TODO 自動生成されたメソッド・スタブ
			if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){









			}
		}
	};







	private void setStudentName(String name){
		name_text.setText(name);
	}
	private void setStudentNumber(String number){
		studentNum_text.setText(number);
	}






	public void setPopup(final Popup popup){

		popup.centerOnScreen();
		ok_button.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				// TODO 自動生成されたメソッド・スタブ
				if(arg0.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
					popup.hide();

				}
			}
		});

	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 自動生成されたメソッド・スタブ

		System.out.println("debug");

		type_comb.getItems().clear();
		felica_comb.getItems().clear();
		seat_comb.getItems().clear();
		type_comb.setOnAction(typeCombEvent);



		debug("TEXT");







		if(type_comb.getItems().size()<1){

			type_comb.getItems().setAll("TEST01","TEST02");

		}










	}







}
