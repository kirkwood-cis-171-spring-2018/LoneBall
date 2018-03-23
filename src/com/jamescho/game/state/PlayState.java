package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cortman on 3/21/17.
 */
public class PlayState extends State {
    private int xPos = 0;
    private int yPos = GameMain.GAME_HEIGHT / 2;
    private int xPos2 = GameMain.GAME_WIDTH - 5;
    private int yPos2 = GameMain.GAME_HEIGHT / 2;
    private int paddleHeight = 50;
    private int endPositionX = 0;
    private int endPositionY = 0;
    private boolean moveRight = true;
    private int yVelocity;


    @Override
    public void init() {

    }

    @Override
    public void update() {

        //calculate next position
        //if next position is at top then don't change
        //else if next position is at the bottom then don't change
        //else change
        int nextPosition = yPos + yVelocity;
        if (nextPosition <= 0) {
            //no change
        } else if(nextPosition >= (GameMain.GAME_HEIGHT - paddleHeight)) {
           //no change
        } else {
            //change
            yPos = nextPosition;
        }
        nextPosition = yPos2 - yVelocity;

        if (nextPosition <= 0) {
            //no change
        } else if(nextPosition >= (GameMain.GAME_HEIGHT - paddleHeight)) {
            //no change
        } else {
            //change
            yPos2 = nextPosition;
        }

    }

    @Override
    public void render(Graphics g) {
        //Background
        g.setColor(Resources.darkBlue);
        g.fillRect(0,0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.setColor(Resources.darkRed);
        g.fillRect(GameMain.GAME_WIDTH/2, 0,GameMain.GAME_WIDTH/2, GameMain.GAME_HEIGHT);

        //line
        g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);

        //left
        g.setColor(Color.white);
        g.fillRect(xPos,yPos,5,paddleHeight);

        //right
        g.setColor(Color.ORANGE);
        g.fillRect(xPos2,yPos2,5,paddleHeight);

        g.drawString(new Integer(yPos2).toString(), (GameMain.GAME_WIDTH/2) + 25, GameMain.GAME_HEIGHT/2);
    }

    @Override
    public void onClick(MouseEvent e) {
        boolean doubleClick = false;
        if (e.getClickCount() == 2) {
            doubleClick = true;
        }
        if (doubleClick) {
            setCurrentState(new PlayState());
        }


    }

    @Override
    public void onKeyPress(KeyEvent e) {
        char pressedKey = e.getKeyChar();
        pressedKey = Character.toLowerCase(pressedKey);
        System.out.println(pressedKey);
        if (pressedKey == 'w' && pressedKey == 's') {//program crashes after a while checking if this was the problem I doubt it
            System.out.println("for some reason the program read both w & s");
        }//end of useless info
        if (pressedKey == 'w') {
            yVelocity = -5;
        } else if (pressedKey == 's') {
            yVelocity = 5;
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

        yVelocity = 0;
    }
}
