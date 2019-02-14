package sample;

import Popcorn.Popcorn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Main extends Application {
    private HBox hbox;
    private VBox vBox;
    private Button btnIniciar;
    private Button btnReset;
    private Label  lblpopcorn = new Label("Palomitas: 0");
    private Label lbltime = new Label("Tiempo: 00:00");
    private ImageView imageView;
    private Scene scene;
    Image[] imagepopcorn = new Image[10];
    Popcorn popcorn = new Popcorn();
    private int s,m;
    private Timer t;
    private int x=0;
    private int n=1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        t = new Timer(1000, updates);
        x = 0;
        hbox = new HBox();
        vBox = new VBox();
        btnIniciar = new Button("INICIAR");
        btnReset = new Button("RESET");

        lblpopcorn.setId("labeltittle");
        lbltime.setId("labeltittle");

        btnReset.setOnAction(event -> Reset());
        btnIniciar.setOnAction(event -> Iniciar());

        //imagepopcorn[0] = new Image("Images/0popcorn.jpg",950,720,false,false);

        for (int i = 0; i < 9; i++)
            imagepopcorn[i] = new Image("Images/"+i+"popcorn.jpg",950,720,false,false);

        imageView = new ImageView(imagepopcorn[0]);
        hbox.getChildren().addAll(btnIniciar,btnReset,lbltime,lblpopcorn);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(80);
        vBox.getChildren().addAll(hbox,imageView);
        vBox.setSpacing(10);

        scene = new Scene(vBox,950,720);
        scene.getStylesheets().add("Styles/Popcorn.css");

        primaryStage.setTitle("PALOMITAS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Reset(){
        t.stop();
        lblpopcorn.setText("Palomitas: 0");
        lbltime.setText("Tiempo: 00:00");
        imageView.setImage(imagepopcorn[0]);
        btnReset.setDisable(true);
        btnIniciar.setDisable(false);
    }
    public void Iniciar(){
         btnIniciar.setDisable(true);
         t.start();
         popcorn.calculateRandomTime();
         popcorn.calculateRandomPop();
         int randomtime = popcorn.getRandomTime();
         int randompop = popcorn.getRandomPop();
         System.out.println(randomtime+"/////////randomtime");
         System.out.println(randompop+"/////////randomtime");

    }
    private ActionListener updates = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            ++s;
            ++x;
            if(x==(popcorn.getRandomPop()/popcorn.getRandomTime())){
                lblpopcorn.setText("Palomitas: "+x);
                x = 0;
                imageView.setImage(imagepopcorn[n]);
                n++;
                updatelabel();
            }
            if(s==60)
            {
                s = 0;
                ++m;
            }
            if(m==60)
            {
                m = 0;
            }
            if(m*60+s>popcorn.getRandomTime())
                finished();

        }
    };
    private void updatelabel() {
        String tiempo = "TIEMPO : 0"+m+":"+s;
        lbltime.setText(tiempo);
    }

    public void finished(){
        t.stop();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("PALOMITAS LISTAS");
        alert.setHeaderText(null);
        alert.setContentText("XD");

    }
    public static void main(String[] args) {
        launch(args);
    }
}
