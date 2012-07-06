package jp.ac.oit.sclab.hoernchen.main.setting;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import jp.ac.oit.sclab.hoernchen.db.AccessDB;
import jp.ac.oit.sclab.hoernchen.main.Student;

/**
 * @author Masanrori Kato
 * 学生情報の登録画面用のコントローラークラス
 * 
 *
 */
public class RegistStudentInfoController  implements Initializable{




	
	@FXML ComboBox<String> type_comb;	//学年種別のコンボボックス


	@FXML ComboBox<String> seat_comb;	//席リストのコンボボックス
	@FXML ComboBox<String> felica_comb; //feicaのコンボボックス
	@FXML TextField studentNum_text;	//学生番号
	@FXML TextField name_text;			//名前
	@FXML Button felica_plus;			//Felica情報の追加 使うかな？

	@FXML Label debug_label;			//デバッグ用エリア


	@FXML Button felica_minus;			//Felica情報の削除 使うかな？
	@FXML Button ok_button;				//完了ボタン

	@FXML Button apply_button;			//更新ボタン
	Popup popup = null;					//ポップアップのインスタンス保存用
	Map<String , Integer> typeTM;		//学年種別リストの保存
	List<Integer> seatList;				//席情報リストの保存

	Student selectedStudent;			//現在編集中のStudentクラス

	/**
	 * 	席情報コンボボックス用のイベントハンドラ
	 * 	選択された値が変わると呼ばれる。
	 */
	EventHandler<ActionEvent> seatCombEvent = new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0) {


			int seatID = 0;
			int typeID = 0;

			if(type_comb.getValue() != null && seat_comb.getValue() != null){
				if(!seat_comb.getValue().equals("")){
					try{
						typeID = typeTM.get(type_comb.getValue());
						seatID = Integer.parseInt(seat_comb.getValue());
					}catch (Exception e){
						e.printStackTrace();
						typeID = 0;
						seatID = 0;
					}
				}
			}
			if(seatID >= Student.GRADE_3RD && seatID <= Student.GRADE_ETC){
				debug("typeID = "+typeID+", seatID = "+seatID+", 学年 = "+Student.GRADE_NAME[typeID]);

				selectedStudent = new Student(seatID);


				debug("student | "+ selectedStudent.toString());


				setStudentName(selectedStudent.getName());
				setStudentNumber(selectedStudent.getUserId());

			}else{
				setStudentName("");
				setStudentNumber("");

			}



		}
	};




	/**
	 * 	学年コンボボックス用のイベントハンドラ
	 * 	選択された値が変わると呼ばれる。
	 */
	EventHandler<ActionEvent> typeCombEvent = new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {

			if(type_comb.getValue() != null){


				debug(""+typeTM.get(type_comb.getValue()));

				AccessDB adb = new AccessDB();

				seatList = adb.getSeatIdListByGrade(typeTM.get(type_comb.getValue()));

				seat_comb.getItems().clear();
				seat_comb.getItems().add("");
				for(Integer i :seatList){
					seat_comb.getItems().add(i.toString());
				}



			}
		}

	};

	private void debug(String text){
		debug_label.setText("debug : "+ text);
		System.out.println("debug : "+text);
	}


	/**
	 * 	更新ボタン用のイベントハンドラ
	 * 	
	 */
	EventHandler<MouseEvent> applyButtonEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {

			if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){

				String studentName=null;
				String studentId=null;


				studentName = name_text.getText();
				studentId = studentNum_text.getText();




				if(selectedStudent!= null){

					selectedStudent.setName(studentName);
					selectedStudent.setUserId(studentId);

				}

				//TODO : フィールドselectedStudentの情報をデータベースに書き込む。

				setStudentName("");
				setStudentNumber("");

				seat_comb.setValue("");


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

				if(arg0.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
					popup.hide();

				}
			}
		});

	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		System.out.println("debug");

		type_comb.getItems().clear();
		felica_comb.getItems().clear();
		seat_comb.getItems().clear();
		type_comb.setOnAction(typeCombEvent);


		seat_comb.setOnAction(seatCombEvent);


		typeTM = new TreeMap<String ,Integer>();
		typeTM.clear();

		 apply_button.setOnMouseClicked(applyButtonEvent);
		//apply_button.setText("debug");




		typeTM.put("Etc", Student.GRADE_ETC);
		typeTM.put("Master grade", Student.GRADE_MASTER);
		typeTM.put("4th grade", Student.GRADE_4TH);
		typeTM.put("3rd grade", Student.GRADE_3RD);

		for(int i = Student.GRADE_ETC;i < Student.GRADE_3RD ; i--){
			typeTM.put(Student.GRADE_NAME[i], i);
		}


		for(Iterator<String> it = typeTM.keySet().iterator();it.hasNext(); ){

			type_comb.getItems().add(it.next());

		}










	}







}
