package jp.ac.oit.sclab.hoernchen.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import jp.ac.oit.sclab.hoernchen.util.LissUtil;

public class StudentButtonController implements Initializable {
	@FXML Label nameText;
	@FXML Label seatText;
	@FXML Label accessText;
	@FXML Label accessTimeText;
	@FXML Button stateButton;
	@FXML Button notifyButton;
	@FXML AnchorPane mainPane;

	int num = 0;

	@SuppressWarnings("unused")
	private static final String pattern = "YY/MM/dd HH:mm";



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub


		setAccessTime("12/05/16 22:16");
		setParsonName("豊臣 秀吉");
		setSeatId("A-002");
		setState(LissUtil.State.STATE_UNKNOWN);






	}
	public AnchorPane getPane(){
		return mainPane;
	}
	public void setParsonName(String s){

		nameText.setText(s);
	}
	public String getParsonName(){
		return nameText.getText();
	}
	public void setSeatId(String seatId){
		seatText.setText(seatId);
	}
	public String getSeatId(){
		return seatText.getText();
	}
	public void setAccessString(String str){
		accessText.setText(str);
	}
	public String getAccessString(){
		return accessText.getText();
	}

	public void setAccessTime(String time){
		accessTimeText.setText(time);

	}
	public String getAccessTime(){
		return accessTimeText.getText();
	}
	public void setState(int state){
		stateButton.setText(LissUtil.State.createStateFromStateId(state));
	}
	public int getState(){
		return LissUtil.State.findStateIdFromStateText(stateButton.getText());
	}
	public void setNotify(int notifyNum){
		if(notifyNum < 100) {
		notifyButton.setText(""+notifyNum);
		}
		else{
			notifyButton.setText("+");
		}
	}
	public int getNotifyNum(){
		int num;
		try{num =Integer.parseInt(notifyButton.getText());
		}catch(NumberFormatException e){
			num = 100;
		}
		return num;
	}

}
