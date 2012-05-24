package jp.ac.oit.sclab.hoernchen.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.scene.control.Button;

public class CalendarButton extends Thread{

	final static  String pattern = "YY/MM/dd HH:mm:ss";
	 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	 Button calendarButton;
		public CalendarButton(Button calendarButton){
		this.calendarButton = calendarButton;

	}
	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		CalendarTh cTh = new CalendarTh();
		while(true){
			cTh.start();
			try {
				cTh.join();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			setCalendarButton();
		}

	}
public void setCalendarButton() {
	calendarButton.setText(sdf.format(Calendar.getInstance().getTime()));

}

	class CalendarTh extends Thread{

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	}
}
