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
    private int xPosition = 0;
    private int yPosition = GameMain.GAME_HEIGHT / 2;
    private int xPosition2 = GameMain.GAME_WIDTH - 5;
    private int yPosition2 = GameMain.GAME_HEIGHT / 2;
    private int endPositionX = 0;
    private int endPositionY = 0;
    private boolean moveRight = true;
    private int yVelocity;
    private int paddleHeight = 50;


    @Override
    public void init() {

    }

    @Override
    public void update() {

        int nextPositionLeft = yPosition + yVelocity;
        int nextPositionRight = yPosition2 - yVelocity;

        if (inBounds(nextPositionLeft)) {
            yPosition = nextPositionLeft;
        }

        if (inBounds(nextPositionRight)) {
            yPosition2 = nextPositionRight;
        }

    }

    private boolean inBounds(int nextPosition) {
        boolean atTop = nextPosition <= 0;
        boolean atBottom = nextPosition >= (GameMain.GAME_HEIGHT - paddleHeight);
        return !(atTop || atBottom);
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
        g.fillRect(xPosition,yPosition,5,paddleHeight);
        g.drawString(new Integer(yPosition).toString(), GameMain.GAME_WIDTH/2 - 45,GameMain.GAME_HEIGHT/2);

        //right
        g.setColor(Color.white);
        g.fillRect(xPosition2,yPosition2,10,paddleHeight);
        g.drawString(new Integer(yPosition2).toString(), GameMain.GAME_WIDTH/2 + 25,GameMain.GAME_HEIGHT/2);
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
