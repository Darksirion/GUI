package Core;

import Controll.Close;
import UI.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StartUI extends Application {

    private Stage window;
    private Core core;


    public StartUI() {
        this.core = new Core();
    }


    public static void main(String[] args) {
        new StartUI();
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {


        window = primaryStage;

        // load the scene fxml UI.
        // grabs the UI scenegraph view from the loader.


        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("../UI/main.fxml"));
        Parent root = (Parent) fxmlloader.load();
        Controller controller = fxmlloader.<Controller>getController();
        controller.setProxy(core.getProxy());
        core.getProxy().setController(controller);


        //interaction closeButton
        window.setOnCloseRequest(e -> {
            e.consume();
            Close.closeProgramm();
        });


        window.setTitle("Codeschnipselverwaltung");
        window.setScene(new Scene(root));
        window.show();

    }

    //   public StartUI(){
    //       programmData.add(new Programmer("Name", "Group", "JavaFX", "int main1", "Notiz1", "Legenden1"));
    //   }
    //   public ObservableList<Programmer> getPersonData() {
    //       return programmData;
    //   }


}
