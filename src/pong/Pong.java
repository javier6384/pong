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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
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
    final int RIGHT_LIMIT = 0;
    final int TOP_LIMIT = 0;
    
    int posIniJ1 = 165;
    int posIniJ2 = 165;
    
    final int HEIGHT_PLAYERS = 70;
    final int WIDTH_PLAYERS = 10;
    
    final int POSX_J1 = 20;
    final int POSX_J2 = 470;
            
    String movLateral = "derecha";
    String movVertical = "arriba";

    byte marcadorLocal = 0;
    byte marcadorVisitante = 0;
    
    byte movimientoJugador1 = 0;
    byte movimientoJugador2 = 0;
    
    //Jugador 1
    Rectangle jugador1 = new Rectangle (0,0,WIDTH_PLAYERS,HEIGHT_PLAYERS);
//        jugador1.setFill(Color.WHITE);
    double posJugador1;
    
    //jugador 2
    Rectangle jugador2 = new Rectangle (0,0,WIDTH_PLAYERS,HEIGHT_PLAYERS);
    double posJugador2;
    
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
        Circle bolita = new Circle (0, 0, 10);
        bolita.setTranslateX(250);
        bolita.setTranslateY(200);
        bolita.setFill(Color.WHITE);
        root.getChildren().add(bolita);
        
        //Jugador 1
//        Rectangle jugador1 = new Rectangle (POSX_J1,165,WIDTH_PLAYERS,HEIGHT_PLAYERS);
        jugador1.setFill(Color.WHITE);
        jugador1.setTranslateX(POSX_J1);
        jugador1.setTranslateY(posIniJ1);
        posJugador1 = jugador1.getTranslateY();
        root.getChildren().add(jugador1);
        
        //jugador 2
//        Rectangle jugador2 = new Rectangle (POSX_J2,165,WIDTH_PLAYERS,HEIGHT_PLAYERS);
        jugador2.setFill(Color.WHITE);
        jugador2.setTranslateX(POSX_J2);
        jugador2.setTranslateY(posIniJ2);
        posJugador2 = jugador2.getTranslateY();
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
//                System.out.println(posJugador1);
//                System.out.println(posJugador2);

                String textMarcadorLocal = String.valueOf(marcadorLocal);
                String textMarcadorVisitante = String.valueOf(marcadorVisitante);
//                Text marcador =new Text(240, 20, textMarcadorLocal + "   " + textMarcadorVisitante);
                
                if (movLateral == "derecha"){
                    posX+=2;
                    if (posX == (WORLD_WIDTH - 10)){
                        movLateral = "izquierda";
                    }
                }
                else {if (movLateral == "izquierda"){
                    posX-=2;
                    if (posX == RIGHT_LIMIT + 10){
                        movLateral = "derecha";
                        }
                    }
                }
                
                if (movVertical == "arriba"){
                    posY--;
                    if (posY == TOP_LIMIT + 10){
                        movVertical = "abajo";
                    }
                }
                else {if (movVertical == "abajo"){
                    posY++;
                    if (posY == 390){
                        movVertical = "arriba";
                        }
                    }
                }
                
                bolita.setTranslateX(posX);
                bolita.setTranslateY(posY);
                                
                if (posX == (WORLD_WIDTH - 10)){
                    marcadorLocal++;
                }
                else {if (posX == 10){
                    marcadorVisitante++;}
                }
                
                marcador.setText(textMarcadorLocal + "   " + textMarcadorVisitante);
            
                posJugador1 += movimientoJugador1;
                jugador1.setTranslateY(posJugador1);

                posJugador2 += movimientoJugador2;
                jugador2.setTranslateY(posJugador2);
                
                if (posJugador1 == 0){
                    movimientoJugador1 = 0;}
                else {if (posJugador1 == (WORLD_HEIGHT - 10)){
                    movimientoJugador1 = 0;}
                }
                
                if (posJugador2 == 0){
                    movimientoJugador2 = 0;}
                else {if (posJugador2 == (WORLD_HEIGHT - 10)){
                    movimientoJugador2 = 0;}
                }
                
                if ((posX == 40) && (posY >= posJugador1) && (posY <=posJugador1 + HEIGHT_PLAYERS )){
                    movLateral ="derecha";
                }
                
                if ((posX == 460) && (posY >= posJugador2) && (posY <=posJugador2 + HEIGHT_PLAYERS )){
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
                        if (posJugador1 == 0){
                            movimientoJugador1 = 0;
                        }
                        else {movimientoJugador1 = -3;
                        }
                        break;    
                    case X:
                        if (posJugador1 == (WORLD_HEIGHT - 10)){
                            movimientoJugador1 = 0;
                        }
                        else {movimientoJugador1 = 3;
                        }
                        break;
                    case A:
                        movimientoJugador1 = 0;
                        break;
                    case K:
                        if (posJugador2 == 0){
                            movimientoJugador2 = 0;
                        }
                        else {movimientoJugador2 = -3;
                        }
                        break; 
                    case M:
                        if (posJugador2 == (WORLD_HEIGHT - 10)){
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
