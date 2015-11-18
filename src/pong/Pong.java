/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.util.Random;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Javier Fernández
 */
public class Pong extends Application {
    
    //
    final int WORLD_WIDTH = 500;
    final int WORLD_HEIGHT = 400;
    final int LEFT_LIMIT = 0;
    final int TOP_LIMIT = 0;
    
    int posIniJ1 = 165;
    int posIniJ2 = 165;
    
    final int HEIGHT_PLAYERS = 70;
    final int WIDTH_PLAYERS = 10;
    
    final int POSX_J1 = 20;
    final int POSX_J2 = 470;
            
    int movLateral = 0;
    int movVertical = 0;

    byte marcadorLocal = 0;
    byte marcadorVisitante = 0;
    
    byte movimientoJugador1 = 0;
    byte movimientoJugador2 = 0;
    
    final int LINEA_CONTACTO_PALA = 40;
    final int CAMBIO_DIRECCION = -1;
    
    //Jugador 1
    Rectangle jugador1 = new Rectangle (LEFT_LIMIT,TOP_LIMIT,WIDTH_PLAYERS,HEIGHT_PLAYERS);
    double posJugador1;
    
    //jugador 2
    Rectangle jugador2 = new Rectangle (LEFT_LIMIT,TOP_LIMIT,WIDTH_PLAYERS,HEIGHT_PLAYERS);
    double posJugador2;
    
    //Bolita
    final int RADIO = 10;
    
    double posXBolita;
    double posYBolita;
    
    Random inicio= new Random();
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
        
        //Marcador
        Text marcador =new Text(80, 260, null);
        marcador.setFill(Color.GREY);
        marcador.setFont(Font.font(180));
        root.getChildren().add(marcador);
        
        //import javafx.scene.shape.Rectangle
        Circle bolita = new Circle (LEFT_LIMIT, TOP_LIMIT, RADIO);
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
        root.getChildren().add(red);
        
        // Infinite game loop 
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                posXBolita = bolita.getTranslateX();
                posYBolita = bolita.getTranslateY();
                System.out.println(posYBolita);

                String textMarcadorLocal = String.valueOf(marcadorLocal);
                String textMarcadorVisitante = String.valueOf(marcadorVisitante);

                
                if (movLateral > 0){
                    posXBolita += movLateral;
                    if (posXBolita >= (WORLD_WIDTH)){
                        movLateral *= CAMBIO_DIRECCION;
                        marcadorLocal++;
                        posXBolita = WORLD_WIDTH * 0.5;
                        posYBolita = WORLD_HEIGHT * 0.5;
                        movLateral= 0;
                        movVertical = 0;
                    }
                }
                else {if (movLateral < 0){
                    posXBolita += movLateral;
                    if (posXBolita <= LEFT_LIMIT){
                        movLateral *= CAMBIO_DIRECCION;
                        marcadorVisitante++;
                        posXBolita = WORLD_WIDTH * 0.5;
                        posYBolita = WORLD_HEIGHT * 0.5;
                        movLateral=0;
                        movVertical = 0;
                        }
                    }
                }
                
                if (movVertical < 0){
                    posYBolita += movVertical;
                    if (posYBolita <= RADIO){
                        movVertical *= CAMBIO_DIRECCION;
                    }
                }
                else {if (movVertical > 0){
                    posYBolita += movVertical;
                    if (posYBolita >= WORLD_HEIGHT - RADIO){
                        movVertical *= CAMBIO_DIRECCION;
                        }
                    }
                }
                
                bolita.setTranslateX(posXBolita);
                bolita.setTranslateY(posYBolita);
                                
                
                marcador.setText(textMarcadorLocal + "   " + textMarcadorVisitante);
            
                posJugador1 += movimientoJugador1;
                jugador1.setTranslateY(posJugador1);

                posJugador2 += movimientoJugador2;
                jugador2.setTranslateY(posJugador2);
                
                if (posJugador1 == 0){
                    movimientoJugador1 = 0;}
                else {if (posJugador1 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                    movimientoJugador1 = 0;}
                }
                
                if (posJugador2 == 0){
                    movimientoJugador2 = 0;}
                else {if (posJugador2 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                    movimientoJugador2 = 0;}
                }
                
                //Partes de la pala Jugador1
                double zona1 = (posYBolita - posJugador1) / 14;
                int zona1Trunc = (int)zona1;
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 0)){
                    movVertical = -3;
                    movLateral *= CAMBIO_DIRECCION;
                }                
    
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 1)){
                    movVertical = -2;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 2)){
                    if (movVertical > 0){
                        movVertical = 1;
                        movLateral = 2;
                    }
                    else {movVertical = -1;
                          movLateral = 2;
                    }                   
                }
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 3)){
                    movVertical = 2;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 4)){
                    movVertical = 3;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                //Partes de la pala Jugador1
                double zona2 = (posYBolita - posJugador2) / 14;
                int zona2Trunc = (int)zona2;
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 0)){
                    movVertical = -3;
                    movLateral *= CAMBIO_DIRECCION;
                }                
    
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 1)){
                    movVertical = -2;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 2)){
                    if (movVertical > 0){
                        movVertical = 1;
                        movLateral = -2;
                    }
                    else {movVertical = -1;
                          movLateral = -2;
                    }                   
                }
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 3)){
                    movVertical = 2;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 4)){
                    movVertical = 3;
                    movLateral *= CAMBIO_DIRECCION;
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
                        if (posJugador1 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
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
                        if (posJugador2 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                            movimientoJugador2 = 0;
                        }
                        else {movimientoJugador2 = 3;
                        }
                        break;
                    case L:
                        movimientoJugador2 = 0;
                        break;
                    case SPACE:
                        if(posXBolita == (WORLD_WIDTH * 0.5) && posYBolita == (WORLD_HEIGHT * 0.5)){
                            movLateral = inicio.nextInt(2) * 4 - 2;
                            movVertical = inicio.nextInt(2) * 2 - 1;
                        }
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
