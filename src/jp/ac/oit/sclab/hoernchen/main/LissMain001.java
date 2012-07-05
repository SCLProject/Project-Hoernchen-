package jp.ac.oit.sclab.hoernchen.main;





import java.io.IOException;
import java.net.URL;
import java.util.List;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jp.ac.oit.sclab.hoernchen.main.setting.RegistStudentInfoController;



public class LissMain001 extends Application {

	private static final String fxmlFileName = "layout_example.fxml";
	private static final String subFxmlFileName ="regist_window.fxml";
	MainLayoutController mlController = null;



    @Override
    public void start(final Stage stage) throws IOException{
        // ステージのタイトルを設定
        stage.setTitle("Hello World!");




       stage.addEventHandler(KeyEvent.KEY_RELEASED,new EventHandler<KeyEvent>()  {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("type : "+arg0.getEventType()+"  pressed : "+ arg0.getCode());
				if(arg0.getCode() == KeyCode.ESCAPE)
					System.exit(0);
				if(arg0.isAltDown()&&arg0.getCode().equals(KeyCode.ENTER))
					stage.setFullScreen( !stage.isFullScreen() );


				if(arg0.isShiftDown() && arg0.getCode().equals(KeyCode.F1)){

					Popup subStage = new Popup();
					FXMLLoader subFXMLLoader = new FXMLLoader();
					subFXMLLoader.setLocation(getClass().getClassLoader().getResource(subFxmlFileName));
					Parent subRoot;
					try {
						subRoot = (Parent) subFXMLLoader.load();

						subStage.getContent().addAll(subRoot);
						subStage.centerOnScreen();
						subStage.show(stage);
						RegistStudentInfoController rsic = subFXMLLoader.getController();
						rsic.setPopup(subStage);


					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}

				}
			}
		});


        FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxmlFileName));

        Parent root = (Parent)fxmlLoader.load();
        mlController = (MainLayoutController)fxmlLoader.getController();



        List<Seat> seats = Seat.getSeatArrayList();
        System.out.println("seats size : "+seats.size());
        int grade;






        for(Seat seat : seats){

        	grade = seat.getmStudent().getGrade();
        	System.out.println("grade : "+grade);

        	switch(grade){

        		case Student.GRADE_3RD:

        			mlController.setSeminar3Membar(new StudentButton(stage, seat));

        			break;

        		case Student.GRADE_4TH:

        			mlController.setSeminar4Membar(new StudentButton(stage,seat));

        			break;

        		case Student.GRADE_MASTER:
        			mlController.setMasterMembar(new StudentButton(stage,seat));
        			break;

        		case Student.GRADE_ETC:
        			mlController.setOtherMembar(new StudentButton(stage, seat));
        			break;

        		default:
        			break;




        	}

        }


        // シーンの生成
        Scene scene = new Scene(root);

        // シーンをステージに貼る
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);



        // ステージの表示
        stage.show();
    }

	public static void main(String[] args){

		launch(args);
	}


}