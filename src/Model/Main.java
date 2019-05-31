package Model;
 
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.AdminaccountPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
/**
 *
 * @author itwang
 */
public class Main extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 250.0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("FXML Login Sample");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        gotologin();
        stage.show();
    }
    public void gotologin(){
      try {
            AdminaccountPane login = (AdminaccountPane) replaceSceneContent("../View/Adminaccount1.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*public void gotomain(){
     try {
            MainController main = (MainController) replaceSceneContent("FXML_MAIN.fxml");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(FXMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void userlogin(String account,String password){
    if(Check.checkreturn(account,password)){
      gotomain();
    }
    }
    public void useroutmain(){
      gotologin();
    }*/
   private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally { in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

