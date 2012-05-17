package jp.ac.oit.sclab.hoernchen.main;




import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;



public class LissMain001 extends Application{

    @Override
    public void start(final Stage stage) {
        // ステージのタイトルを設定
        stage.setTitle("Hello World!");

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


        Image image = new Image(getClass().getResourceAsStream("profile.gif"));
        ImageView iView = ImageViewBuilder.create().image(image).build();

        Label label = new Label("Label 01");

        Label label2 = LabelBuilder.create().text("Label 02").build();
        Label label3 = new Label("Label 03");
        Button imageButton = new Button("text",iView);
        StudentButton sButton = new StudentButton();
        vBox01.setPadding(new Insets(20.0));
        vBox01.setSpacing(20.0);


        vBox01.getChildren().add(sButton);
        vBox01.getChildren().add(new StudentButton());
        vBox01.getChildren().add(new StudentButton());
        vBox01.getChildren().add(new StudentButton());
        vBox01.getChildren().add(new StudentButton());
        vBox01.getChildren().add(imageButton);
        
        // コンテナにラベルを貼る
        root.getChildren().add(0, vBox01);






        // ステージの表示
        stage.show();
    }

	public static void main(String[] args){

		launch(args);
	}





















}
