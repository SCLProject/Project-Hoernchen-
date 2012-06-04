package jp.ac.oit.sclab.hoernchen.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import jp.ac.oit.sclab.hoernchen.util.LissUtil;
import jp.ac.oit.sclab.hoernchen.util.LissUtil.State;

public class StatePopupController implements Initializable{
	@FXML Button btn_top;
		int top_state = State.STATE_UNKNOWN;
	@FXML Button btn_bottom;
	int bottom_state = State.STATE_UNKNOWN;
	@FXML Button btn_right;
	int right_state = State.STATE_UNKNOWN;
	@FXML Button btn_left;
	int left_state = State.STATE_UNKNOWN;
	@FXML Button btn01;
	int center_state = State.STATE_UNKNOWN;


	private void setTopButton(String s){
		btn_top.setText(s);
	}
	private void setBottomButton(String s){
		btn_bottom.setText(s);
	}
	private void setRightButton(String s){
		btn_right.setText(s);
	}
	private void setLeftButton(String s){
		btn_left.setText(s);
	}
	private void setCenterButton(String s){
		btn01.setText(s);

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 自動生成されたメソッド・スタブ
		setTopButton(LissUtil.State.createStateFromStateId(State.STATE_OFF_CAMPUS));
		top_state = State.STATE_OFF_CAMPUS;

		setBottomButton(LissUtil.State.createStateFromStateId(State.STATE_IN_LECTURING));
		bottom_state = State.STATE_IN_LECTURING;

		setRightButton(LissUtil.State.createStateFromStateId(State.STATE_ON_CAMPUS));
		right_state = State.STATE_ON_CAMPUS;

		setLeftButton(LissUtil.State.createStateFromStateId(State.STATE_RETURN_HOME));
		left_state = State.STATE_RETURN_HOME;

		setCenterButton(LissUtil.State.createStateFromStateId(State.STATE_UNKNOWN));
		center_state = State.STATE_UNKNOWN;



	}

	private void setBottomColor(Color c){

		btn_bottom.setTextFill(c);


	}
	private void setTopColor(Color c){
		btn_top.setTextFill(c);

	}
	private void setRightColor(Color c){
		btn_right.setTextFill(c);

	}
	private void setLeftColor(Color c){
		btn_left.setTextFill(c);

	}
	private void setCenterColor(Color c){
		btn01.setTextFill(c);

	}
	private void setDefaultColor(){
		Color c = Color.WHITE;

		setBottomColor(c);
		setTopColor(c);
		setRightColor(c);
		setLeftColor(c);
		setCenterColor(c);
	}

	public void setColor(int state){


		setDefaultColor();












	}


}
