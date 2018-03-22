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
    private int endPositionX = 0;
    private int endPositionY = 0;
    private boolean moveRight = true;
    private int yVelocity;


    @Override
    public void init() {

    }

    @Override
    public void update() {
        yPos += yVelocity;
        yPos2 -= yVelocity;
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


        g.setColor(Color.white);
        g.fillRect(xPos,yPos,5,50);

        g.setColor(Color.ORANGE);
        g.fillRect(xPos2,yPos2,5,50);
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
        } if (pressedKey == 's') {
            yVelocity = 5;
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

        yVelocity = 0;
    }
}
