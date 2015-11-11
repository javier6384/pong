/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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
  
    int posYJugador1 = 0;
    int posYJugador2 = 0;
    
    final int LARGO_JUGADORES = 70;
    final int ANCHO_JUGADORES = 10;
    
    final int LINEA_JUGADOR1 = 20;
    final int LINEA_JUGADOR2 = 470;
            
    String movLateral = "derecha";
    String movVertical = "arriba";

    byte marcadorLocal = 0;
    byte marcadorVisitante = 0;
    
    byte movimientoJugador1 = 0;
    byte movimientoJugador2 = 0;
    
    //Jugador 1
    Rectangle jugador1 = new Rectangle (LINEA_JUGADOR1,165,ANCHO_JUGADORES,LARGO_JUGADORES);
//        jugador1.setFill(Color.WHITE);
    double posJugador1 = jugador1.getTranslateY();
    
    //jugador 2
    Rectangle jugador2 = new Rectangle (LINEA_JUGADOR2,165,ANCHO_JUGADORES,LARGO_JUGADORES);
    double posJugador2 = jugador2.getTranslateY();
    
//    Image imgShip = new Image(getClass().getResourceAsStream("/image/ship.png"));
//    Image imgBullet = new Image(getClass().getResourceAsStream("/image/bullet.png"));
    
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
//        Rectangle jugador1 = new Rectangle (LINEA_JUGADOR1,165,ANCHO_JUGADORES,LARGO_JUGADORES);
        jugador1.setFill(Color.WHITE);
        root.getChildren().add(jugador1);
        
        //jugador 2
//        Rectangle jugador2 = new Rectangle (LINEA_JUGADOR2,165,ANCHO_JUGADORES,LARGO_JUGADORES);
        jugador2.setFill(Color.WHITE);
        root.getChildren().add(jugador2);
        
        //Línea divisoria
        Line red = new Line (250, 0 , 250, 400);
        red.setStroke(Color.WHITE);
//        red.setStroke(tipo de linea);
        root.getChildren().add(red);
        
        //Marcador
        
        Text marcador =new Text(240, 20, null);
        marcador.setFill(Color.YELLOWGREEN);
//        marcador.setFont(TAHOMA, (20));
        root.getChildren().add(marcador);

        // Infinite game loop 
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                double posX = bolita.getTranslateX();
                double posY = bolita.getTranslateY();
                System.out.println(posY);
                System.out.println(posJugador1);
                System.out.println(posJugador2);

                String textMarcadorLocal = String.valueOf(marcadorLocal);
                String textMarcadorVisitante = String.valueOf(marcadorVisitante);
//                Text marcador =new Text(240, 20, textMarcadorLocal + "   " + textMarcadorVisitante);
                
                if (movLateral == "derecha"){
                    posX+=2;
                    if (posX == 240){
                        movLateral = "izquierda";
                    }
                }
                else {if (movLateral == "izquierda"){
                    posX-=2;
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
                                
                if (posX == 240){
                    marcadorLocal++;
                }
                else {if (posX == -240){
                    marcadorVisitante++;}
                }
                
                marcador.setText(textMarcadorLocal + "   " + textMarcadorVisitante);
            
 
                posJugador1 += movimientoJugador1;
                jugador1.setTranslateY(posJugador1);
                

                posJugador2 += movimientoJugador2;
                jugador2.setTranslateY(posJugador2);
                
                if (posJugador1 == -165){
                    movimientoJugador1 = 0;}
                else {if (posJugador1 == 165){
                    movimientoJugador1 = 0;}
                }
                
                if (posJugador2 == -165){
                    movimientoJugador2 = 0;}
                else {if (posJugador2 == 165){
                    movimientoJugador2 = 0;}
                }
                
                if ((posX == -210) && (posY >= posJugador1 -35) && (posY <=posJugador1 + 35 )){
                    movLateral ="derecha";
                }
                
                if ((posX == 210) && (posY >= posJugador2 -35) && (posY <=posJugador2 + 35 )){
                    movLateral = "izquierda";
                }
            }
            
            
            
            
    }.start();
        
        // Detect keys pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case S:
                        if (posJugador1 == -165){
                            movimientoJugador1 = 0;
                        }
                        else {movimientoJugador1 = -3;
                        }
                        break;    
                    case X:
                        if (posJugador1 == 165){
                            movimientoJugador1 = 0;
                        }
                        else {movimientoJugador1 = 3;
                        }
                        break;
                    case A:
                        movimientoJugador1 = 0;
                        break;
                    case K:
                        if (posJugador2 == -165){
                            movimientoJugador2 = 0;
                        }
                        else {movimientoJugador2 = -3;
                        }
                        break; 
                    case M:
                        if (posJugador2 == 165){
                            movimientoJugador2 = 0;
                        }
                        else {movimientoJugador2 = 3;
                        }
                        break;
                    case L:
                        movimientoJugador2 = 0;
                        break;
                }
            }
        });
}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
