/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Javier Fernández
 */
public class Pong extends Application {
    
    final int WORLD_WIDTH = 500;
    final int WORLD_HEIGHT = 400;
  
    int posIniJugadores = 165;
    final int LARGO_JUGADORES = 70;
    final int ANCHO_JUGADORES = 10;
    
    final int LINEA_JUGADOR1 = 20;
    final int LINEA_JUGADOR2 = 470;
            
    String movLateral = "derecha";
    String movVertical = "arriba";


    
    Image imgShip = new Image(getClass().getResourceAsStream("/image/ship.png"));
    Image imgBullet = new Image(getClass().getResourceAsStream("/image/bullet.png"));
    
    @Override
    public void start(Stage primaryStage) {


        // Create scene
        Group root = new Group();
        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT, Color.BLACK);
        primaryStage.setTitle("Pong");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //import javafx.scene.shape.Rectangle
        Circle bolita = new Circle (250, 200, 10);
        bolita.setFill(Color.WHITE);
        root.getChildren().add(bolita);
        
        //Jugador 1
        Rectangle jugador1 = new Rectangle (LINEA_JUGADOR1,posIniJugadores,ANCHO_JUGADORES,LARGO_JUGADORES);
        jugador1.setFill(Color.WHITE);
        root.getChildren().add(jugador1);
        
        //jugador 2
        Rectangle jugador2 = new Rectangle (LINEA_JUGADOR2,posIniJugadores,ANCHO_JUGADORES,LARGO_JUGADORES);
        jugador2.setFill(Color.WHITE);
        root.getChildren().add(jugador2);
        
        //Línea divisoria
        Line red = new Line (250, 0 , 250, 400);
        red.setStroke(Color.WHITE);
//        red.setStroke(tipo de linea);
        root.getChildren().add(red);
        
        //Marcador
        
        Text marcador =new Text();
        marcador.setFill(Color.YELLOWGREEN);
//        marcador.setFont(TAHOMA, (20));
        root.getChildren().add(marcador);

        // Infinite game loop 
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                double posX = bolita.getTranslateX();
                double posY = bolita.getTranslateY();
                System.out.println(posX + "      " + posY);
                
                byte marcadorLocal = 0;
                byte marcadorVisitante = 0;
                String textMarcadorLocal = String.valueOf(marcadorLocal);
                String textMarcadorVisitante = String.valueOf(marcadorVisitante);
                Text marcador =new Text(240, 20, textMarcadorLocal + "   " + textMarcadorVisitante);
                
                if (movLateral == "derecha"){
                    posX++;
                    if (posX == 240){
                        movLateral = "izquierda";
                    }
                }
                else {if (movLateral == "izquierda"){
                    posX--;
                    if (posX == -240){
                        movLateral = "derecha";
                        }
                    }
                }
                
                if (movVertical == "arriba"){
                    posY--;
                    if (posY == -190){
                        movVertical = "abajo";
                    }
                }
                else {if (movVertical == "abajo"){
                    posY++;
                    if (posY == 190){
                        movVertical = "arriba";
                        }
                    }
                }
                bolita.setTranslateX(posX);
                bolita.setTranslateY(posY);
                jugador1.setTranslateY(posY);
                jugador2.setTranslateY(posY);
                
                
                if (posX == 240){
                    marcadorLocal++;
                }
                else {if (posX == -240){
                    marcadorVisitante++;}

                }
            }
            
    }.start();
}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
