package jp.ac.oit.sclab.hoernchen.main;




import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class LissMain001 extends Application {

	private static final String fxmlFileName = "layout_example.fxml";
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
			}
		});


        FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxmlFileName));

        Parent root = (Parent)fxmlLoader.load();
        mlController = (MainLayoutController)fxmlLoader.getController();

        String[] s= {"初春飾利","佐天涙子","白井黒子","上条当麻","ニャル子さん","平沢唯","中野梓","牧瀬紅莉栖","巴マミ","鹿目まどか"};

        String[] master = {"ちゃぬさん","りんご","みくし"};

        int[] state = {1,4,5,3,1,2,4,2,6,5,3};

        String[] seat  = {"C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001","C-001",};



        for(int i = 0; i < s.length;i++){
        	StudentButton sButton = new StudentButton();


        	sButton.setParsonName(s[i]);
        	sButton.setState(i%5);
        	sButton.setAccessTime(Calendar.getInstance());
        	sButton.setSeatId(seat[i]+i);


        	try{
        		if(i % 2 == 0){

        			mlController.setSeminar3Membar(sButton);
        		}else{
        			mlController.setSeminar4Membar(sButton);
        		}
        	}catch (IllegalArgumentException e) {
			// TODO: handle exception



        	}

        }
        for(int i = 0; i< master.length;i++){
        	StudentButton sButton = new StudentButton();


        	sButton.setParsonName(master[i]);
        	sButton.setState(i%5);
        	sButton.setAccessTime(Calendar.getInstance());
        	sButton.setSeatId(seat[i]+i);

        	try{

        		mlController.setMasterMembar(sButton);
        	}catch (IllegalArgumentException e) {
			// TODO: handle exception



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
