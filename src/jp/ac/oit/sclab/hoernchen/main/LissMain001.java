package jp.ac.oit.sclab.hoernchen.main;




import java.util.Calendar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;



public class LissMain001 extends Application{

    @Override
    public void start(final Stage stage) {
        // ステージのタイトルを設定
        stage.setTitle("Hello World!");

        stage.addEventHandler(KeyEvent.KEY_RELEASED,new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("type : "+arg0.getEventType()+"  pressed : "+ arg0.getCode());
				if(arg0.getCode() == KeyCode.ESCAPE)
					System.exit(0);
				if(arg0.isAltDown()&&arg0.getCode().equals(KeyCode.ENTER))
					stage.setFullScreen( !stage.isFullScreen() );
			}
		});





        // ルートのコンテナ
        StackPane root = new StackPane();


        // シーンの生成
        Scene scene = new Scene(root, 640, 480);

        // シーンをステージに貼る
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);

        VBox vBox01 = VBoxBuilder.create().id("vBox01")
        		.alignment(Pos.CENTER)
        		.prefHeight(scene.getHeight())
        		.prefWidth(scene.getWidth())
        		.build();





        // ラベル




        Label label = new Label("Label 01");

        Label label2 = LabelBuilder.create().text("Label 02").build();
        Label label3 = new Label("Label 03");
        Button imageButton = new Button("text");
        StudentButton sButton = new StudentButton();
        vBox01.setPadding(new Insets(20.0));
        vBox01.setSpacing(20.0);
        String[] names = {"豊臣秀吉","徳川家康","織田信長","武田信玄","西郷隆盛","坂本龍馬","石田三成","伊藤博文"};
        StudentButton[] sButtons = new StudentButton[names.length];
        for(int i = 0 ; i < sButtons.length;i++){
        	sButtons[i] = new StudentButton();
        	sButtons[i].setParsonName(names[i]);
        	sButtons[i].setState(((i+3)*7)%6);
        	sButtons[i].setAccessTime(Calendar.getInstance());



            vBox01.getChildren().add(sButtons[i]);

        }




        // コンテナにラベルを貼る
        root.getChildren().add(0, vBox01);






        // ステージの表示
        stage.show();
    }

	public static void main(String[] args){

		launch(args);
	}





















}
