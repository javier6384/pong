/*
 * Juego basado en el Pong Original 
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
import javafx.stage.StageStyle;

/**
 *
 * @author Javier Fernández
 */
public class Pong extends Application {
    
    //Tamaño del mundo (márgenes)
    final int WORLD_WIDTH = 500;
    final int WORLD_HEIGHT = 400;
    final int LEFT_LIMIT = 0;
    final int TOP_LIMIT = 0;
    
    //Posiciones verticales de los jugadores al iniciar
    int posYIniJ1 = 165;
    int posYIniJ2 = 165;
    
    //Tamaño de los jugadores
    final int HEIGHT_PLAYERS = 70;
    final int WIDTH_PLAYERS = 10;
    
    //Posiciones horizontales de los jugadores
    final int POSX_J1 = 20;
    final int POSX_J2 = 470;
            
    //Velocidades
    final int STOP = 0;
    final int LENTO = 1;
    final int MEDIO = 2;
    final int RAPIDO = 3;
    
    //Movimientos para la bola y jugadores
    int movLateral = STOP;
    int movVertical = STOP;
    int movJugador1 = STOP;
    int movJugador2 = STOP;    
    final int CAMBIO_DIRECCION = -1;

    //Marcador
    int marcadorLocal = 0;
    int marcadorVisitante = 0;

    final int LINEA_CONTACTO_PALA = 40;

    //Jugador 1
    Rectangle jugador1 = new Rectangle (LEFT_LIMIT,TOP_LIMIT,WIDTH_PLAYERS,HEIGHT_PLAYERS);
    double posJugador1;
    
    //jugador 2
    Rectangle jugador2 = new Rectangle (LEFT_LIMIT,TOP_LIMIT,WIDTH_PLAYERS,HEIGHT_PLAYERS);
    double posJugador2;
    
    //Bolita
    final int RADIO = 10;
    
    //Variables posiciones de la bola
    double posXBolita;
    double posYBolita;
    
    //Objeto random
    Random inicio= new Random();
    
    @Override
    public void start(Stage primaryStage) {
        //Desactiva el botón maximizar
//        primaryStage.initStyle(StageStyle.UTILITY); 
//        primaryStage.setResizable(false);


        //Create scene
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
        
        //Bolita
        Circle bolita = new Circle (LEFT_LIMIT, TOP_LIMIT, RADIO);
        bolita.setTranslateX(250);
        bolita.setTranslateY(200);
        bolita.setFill(Color.WHITE);
        root.getChildren().add(bolita);
        
        //Jugador 1
        jugador1.setFill(Color.WHITE);
        jugador1.setTranslateX(POSX_J1);
        jugador1.setTranslateY(posYIniJ1);
        posJugador1 = jugador1.getTranslateY();
        root.getChildren().add(jugador1);
        
        //jugador 2
        jugador2.setFill(Color.WHITE);
        jugador2.setTranslateX(POSX_J2);
        jugador2.setTranslateY(posYIniJ2);
        posJugador2 = jugador2.getTranslateY();
        root.getChildren().add(jugador2);
        
        //Línea divisoria
        Line red = new Line (WORLD_WIDTH * 0.5, TOP_LIMIT , WORLD_WIDTH * 0.5, WORLD_HEIGHT);
        red.setStroke(Color.WHITE);
        root.getChildren().add(red);
        
        // Infinite game loop 
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                posXBolita = bolita.getTranslateX();
                posYBolita = bolita.getTranslateY();

                //Acciones al llegar a los fondos
                if (movLateral > STOP){
                    posXBolita += movLateral;
                    if (posXBolita >= (WORLD_WIDTH)){
                        movLateral *= CAMBIO_DIRECCION;
                        marcadorLocal++;
                        posXBolita = WORLD_WIDTH * 0.5;
                        posYBolita = WORLD_HEIGHT * 0.5;
                        movLateral= STOP;
                        movVertical = STOP;
                    }
                }
                else {if (movLateral < STOP){
                    posXBolita += movLateral;
                    if (posXBolita <= LEFT_LIMIT){
                        movLateral *= CAMBIO_DIRECCION;
                        marcadorVisitante++;
                        posXBolita = WORLD_WIDTH * 0.5;
                        posYBolita = WORLD_HEIGHT * 0.5;
                        movLateral= STOP;
                        movVertical = STOP;
                        }
                    }
                }
                
                //Acciones al llegar a los márgenes superior e nferior
                if (movVertical < STOP){
                    posYBolita += movVertical;
                    if (posYBolita <= RADIO){
                        movVertical *= CAMBIO_DIRECCION;
                    }
                }
                else {if (movVertical > STOP){
                    posYBolita += movVertical;
                    if (posYBolita >= WORLD_HEIGHT - RADIO){
                        movVertical *= CAMBIO_DIRECCION;
                        }
                    }
                }
                
                //Muestra la bolita y el marcador
                bolita.setTranslateX(posXBolita);
                bolita.setTranslateY(posYBolita);
                String textMarcadorLocal = String.valueOf(marcadorLocal);
                String textMarcadorVisitante = String.valueOf(marcadorVisitante);
                marcador.setText(textMarcadorLocal + "   " + textMarcadorVisitante);
               
                //Restablece la partida al llegara a los diez puntos
                if(marcadorLocal == 10) {
                    marcadorLocal = 0;
                    marcadorVisitante = 0;
                    movJugador1 = STOP;
                    movJugador2 = STOP;
                    posJugador1 = posYIniJ1;
                    posJugador2 = posYIniJ2;
                }
                else{if(marcadorVisitante == 10) {
                        marcadorLocal = 0;
                        marcadorVisitante = 0;
                        movJugador1 = STOP;
                        movJugador2 = STOP;
                        posJugador1 = posYIniJ1;
                        posJugador2 = posYIniJ2; 
                    }
                }
                
                //Muestra los jugadores
                posJugador1 += movJugador1;
                jugador1.setTranslateY(posJugador1);

                posJugador2 += movJugador2;
                jugador2.setTranslateY(posJugador2);
                
                //Para la pala al llegar a los extremos
                if (posJugador1 == TOP_LIMIT){
                    movJugador1 = STOP;}
                else {if (posJugador1 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                        movJugador1 = STOP;}
                }
                
                if (posJugador2 == TOP_LIMIT){
                    movJugador2 = STOP;}
                else {if (posJugador2 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                        movJugador2 = STOP;}
                }
                
                //Partes de la pala Jugador1
                double zona1 = (posYBolita - posJugador1) / 14;
                int zona1Trunc = (int)zona1;
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 0)){
                    movVertical = -RAPIDO;
                    movLateral *= CAMBIO_DIRECCION;
                }                
    
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 1)){
                    movVertical = -MEDIO;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 2)){
                    if (movVertical > 0){
                        movVertical = LENTO;
                        movLateral = MEDIO;
                    }
                    else {movVertical = -LENTO;
                          movLateral = MEDIO;
                    }                   
                }
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 3)){
                    movVertical = MEDIO;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == LINEA_CONTACTO_PALA) && (zona1Trunc == 4)){
                    movVertical = RAPIDO;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                //Partes de la pala Jugador2
                double zona2 = (posYBolita - posJugador2) / 14;
                int zona2Trunc = (int)zona2;
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 0)){
                    movVertical = -RAPIDO;
                    movLateral *= CAMBIO_DIRECCION;
                }                
    
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 1)){
                    movVertical = -MEDIO;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 2)){
                    if (movVertical > 0){
                        movVertical = LENTO;
                        movLateral = -MEDIO;
                    }
                    else {movVertical = -LENTO;
                          movLateral = -MEDIO;
                    }                   
                }
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 3)){
                    movVertical = MEDIO;
                    movLateral *= CAMBIO_DIRECCION;
                }
                
                if ((posXBolita == WORLD_WIDTH - LINEA_CONTACTO_PALA) && (zona2Trunc == 4)){
                    movVertical = RAPIDO;
                    movLateral *= CAMBIO_DIRECCION;
                }
            }  
            
    }.start();
        
        // Detect keys pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    //Acciones con las teclas
                    case S:
                        if (posJugador1 == TOP_LIMIT){
                            movJugador1 = STOP;
                        }
                        else {movJugador1 = -RAPIDO;
                        }
                        break;    
                    case X:
                        if (posJugador1 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                            movJugador1 = STOP;
                        }
                        else {movJugador1 = RAPIDO;
                        }
                        break;
                    case A:
                        movJugador1 = STOP;
                        break;
                    case K:
                        if (posJugador2 == TOP_LIMIT){
                            movJugador2 = STOP;
                        }
                        else {movJugador2 = -RAPIDO;
                        }
                        break; 
                    case M:
                        if (posJugador2 == (WORLD_HEIGHT - HEIGHT_PLAYERS)){
                            movJugador2 = STOP;
                        }
                        else {movJugador2 = RAPIDO;
                        }
                        break;
                    case L:
                        movJugador2 = STOP;
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
