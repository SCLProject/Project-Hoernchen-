package jp.ac.oit.sclab.hoernchen.main;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class StatePopup extends Pane{

	private final static String filename = "popup.fxml";

	FXMLLoader fxmlLoader;
	StatePopupController spController;


	public StatePopup(){
		URL location = getClass().getClassLoader().getResource(filename);

		fxmlLoader = new FXMLLoader(location);

		Parent root;
		try {
			root = (Parent)fxmlLoader.load();
			spController = (StatePopupController)fxmlLoader.getController();
			getChildren().add(root);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
	

	

}
