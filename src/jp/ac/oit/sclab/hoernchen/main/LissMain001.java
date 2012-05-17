package jp.ac.oit.sclab.hoernchen.main;



import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;



public class LissMain001 extends Application{

    @Override
    public void start(Stage stage) {
        // ステージのタイトルを設定
        stage.setTitle("Hello World!");

        // ルートのコンテナ
        StackPane root = new StackPane();

        // シーンの生成
        Scene scene = new Scene(root, 640, 480);

        // シーンをステージに貼る
        stage.setScene(scene);


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


        vBox01.getChildren().add(label);
        vBox01.getChildren().add(label2);
        vBox01.getChildren().add(label3);
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
