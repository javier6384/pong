/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Javier Fernández
 */
public class Pong extends Application {
    
    final int WORLD_WIDTH = 500;
    final int WORLD_HEIGHT = 400;

    // Player ship position
    int shipPosX = 240;
    final int SHIP_POS_Y = 350;
    final int SHIP_SPEED = 5;

    // Bullet position
    int bulletPosX, bulletPosY;
    boolean shooting = false;
    final int BULLET_SPEED = 10;

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
        
        //import javafx.scene.shape.Rectangule
        Circle bolita = new Circle (200, 15, 10);
        bolita.setFill(Color.WHITE);
        root.getChildren().add(bolita);

        // Infinite game loop
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                double posX = bolita.getTranslateX();
                System.out.println(posX + "" + bolita.getScaleZ());
                
                
                int direccion = 0;
                
                
                if (posX>=-150){
                    posX++;}
                if (posX==151){
                    posX = 0;
                    posX++;}
                
                
                bolita.setTranslateX(posX);
                bolita.getContentBias();
                
                

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
