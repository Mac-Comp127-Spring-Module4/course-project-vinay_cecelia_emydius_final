package spacegame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

//comment to push the file, delete now

    /**
     * This class sets up the game, using methods to add graphic objects to canvas,
     * a method to dictate the conditions when the player wins or loses, and an handler method
     * so the aliens shoot in a coordinated fashion. There is also a main method at the end. 
     */
public class GameSetUp {
    Scanner sc = new Scanner(System.in);
    private static final int CANVAS_WIDTH = 1100;
    private static final int CANVAS_HEIGHT = 700;

    private CanvasWindow canvas;
    private Alien alien;
    private Player player;
    private List<Image> lifeMeter;
    private boolean gameRunning;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
   
    /**
     * Game Set Up Method that creates canvas, adds the alien army and player to canvas, 
     * calls other methods, and gets the player to move on mouse command. 
     */
    public GameSetUp(){
        canvas = new CanvasWindow("Space Shooter!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        player = new Player(550-30, 600);
        player.addToCanvas(canvas);
        lifeMeter = new ArrayList<>();
        resetEnvironment();
    //     player = new Player(550-30, 600);
    //     player.addToCanvas(canvas);


    //     // Alien alien = new Alien(300, 100);
    //     // alien.addToCanvas(canvas);
    //     Alien.createAlienArmy(canvas, this);
    // //    alien.removeAlien(laser, canvas);
        // alienShootingHandler();
        canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));
        canvas.animate(() -> {
            if (gameRunning) {
                if (player.getImage().getCanvas() == null) {
                    player.setLives(player.getLives() - 1);
                    Laser.clearLasers();
                    executor.shutdownNow();
                    canvas.pause(3000);
                    canvas.add(player.getImage());
                    executor = Executors.newSingleThreadScheduledExecutor();
                    alienShootingHandler();
                }
                if (lifeMeter.size() != player.getLives()) {
                    for (int i = 0; i < lifeMeter.size(); i++) {
                        canvas.remove(lifeMeter.get(i));
                    }
                    lifeMeter.clear();
                    for (int i = 0; i < player.getLives(); i++) {
                        lifeMeter.add(new Image(800 + 35*i, 20, "sprites/heart.png"));
                        canvas.add(lifeMeter.get(i));
                    }
                }
                gameWinGameLoss();
            }
        });
    }

    public void resetEnvironment() {
        // player = new Player(550-30, 600);
        // player.addToCanvas(canvas);
        gameRunning = true;
        canvas.add(player.getImage());
        player.setLives(3);
        for (int i = 0; i < player.getLives(); i++) {
            lifeMeter.add(new Image(800 + 35*i, 20, "sprites/heart.png"));
            canvas.add(lifeMeter.get(i));
        }
        // Alien alien = new Alien(300, 100);
        // alien.addToCanvas(canvas);
        Alien.createAlienArmy(canvas, this);
        System.out.println("numAliens = " + Alien.getNumAliens());
    //    alien.removeAlien(laser, canvas);
        canvas.draw();
        canvas.pause(3000);
        executor = Executors.newSingleThreadScheduledExecutor();
        alienShootingHandler();
        // canvas.onMouseMove(event -> player.updatePosition(event.getPosition().getX(), canvas));
        // canvas.animate(() -> {
        //     gameWinGameLoss();
        // });
    }

    /**
     * Method that either ends the game is lives = 0 or tells the player they won if aliens = 0
     */
    public void gameWinGameLoss(){
        if(player.getLives() == 0){
            executor.shutdownNow();
            endGame();
        }
        if(Alien.getNumAliens() == 0){
            executor.shutdownNow();
            gameWinner();
        }
            
    }

    /**
     * Print method for end game and then asks if the player wants to start the game over again
     */
    public void endGame(){
        gameRunning = false;
        Alien.removeAlienArmy(canvas);
        canvas.removeAll();
        GraphicsText loss = new GraphicsText("Game Over", CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2);
        loss.setFontSize(20);
        loss.setFillColor(Color.GREEN);
        canvas.add(loss);
        canvas.draw();
        canvas.pause(2000);
        Button restart = new Button("Restart");
        restart.setPosition(CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2 + 100);
        Button exit = new Button("Exit");
        exit.setPosition(CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2 + 150);
        // GraphicsText restart= new GraphicsText("Restart? (Y/N)", CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2 + 100);
        // restart.setFontSize(20);
        // restart.setFillColor(Color.red);
        canvas.add(restart);
        canvas.add(exit);
        canvas.draw();
        // String reset = sc.nextLine();
        // System.out.println(reset);
        restart.onClick(() -> {
            canvas.remove(loss);
            canvas.remove(restart);
            canvas.remove(exit);
            resetEnvironment();
        });
        exit.onClick(() -> {
            canvas.closeWindow();
        });
        // if (reset.toLowerCase().equals("y")){
        //     canvas.remove(loss);
        //     canvas.remove(restart);
        //     canvas.remove(exit);
        //     resetEnvironment();
        // }
        // else{
        //     System.out.println("close window");
        //     canvas.closeWindow();
        // }
        
    }

    /**
     * method to reset the game if someone dictates they want to play again
     */
    public void resetGame(){
        new GameSetUp();
    }

    /**
    * Print method for game winner
    */
    public void gameWinner(){
        GraphicsText win = new GraphicsText("You Win!!!!", CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        win.setFontSize(20);
        win.setFillColor(Color.YELLOW);
        canvas.add(win);
        canvas.draw();
        canvas.pause(2000);
        canvas.closeWindow();
    }
    
    /**
     * 
     */
    public void alienShootingHandler() {
        // ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            
            // for (int i = 0; i < Alien.getAlienArmyList().size(); i++) {
            //     if (Alien.getAlienArmyList().get(i).size() == 0) {
            //         Alien.getAlienArmyList().remove(i);
            //         i--;
            //         continue;
            //     }
            //     for (int j = 0; j < Alien.getAlienArmyList().get(i).size(); j++) {
            //         if (Alien.getAlienArmyList().get(i).get(j).getCanvas() == null) {
            //             Alien.getAlienArmyList().get(i).remove(j);
            //             j--;
            //         }
            //     }
            // }

            double distance = 1100;
            int closestColumnIndex = 0;
            for (int i = 0; i < Alien.getAlienArmyList().size(); i++) {
                if (Alien.getAlienArmyList().size() > 0) {
                    double currentDistance = Math.abs(
                        Alien.getAlienArmyList().get(i).get(0).getCenterX()
                         - player.getCenterX());
                    if (currentDistance < distance) {
                        distance = currentDistance;
                        closestColumnIndex = i;
                    }
                }
            }
            List<Alien> closestColumn = Alien.getAlienArmyList().get(closestColumnIndex);
            closestColumn.get(closestColumn.size()-1).shootLaser();
            System.out.println("The executor scheduler is working.");
            // System.out.println(Alien.getAlienArmyList());
        };
        executor.scheduleAtFixedRate(task, 3, 3, TimeUnit.SECONDS);
    }
   

    
    public static void main(String[] args){
        new GameSetUp();
    }

}
