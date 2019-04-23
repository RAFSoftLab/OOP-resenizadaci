package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

abstract class AbstractStage extends Stage {

	AbstractStage() {
		setTitle("Lazar Jelic RN 27/17");
	}
	
	protected abstract Scene updateScene();
}