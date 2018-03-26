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

    private final int BALL_SIZE = 30;
    private final int PADDLE_WIDTH = 5;
    private final int PADDLE_HEIGHT = 50;

    private int xPos = 0;
    private int yPos = GameMain.GAME_HEIGHT / 2;

    private int xPos2 = GameMain.GAME_WIDTH - 5;
    private int yPos2 = GameMain.GAME_HEIGHT / 2;

    private int yVelocity;

    private int ballXPosition = GameMain.GAME_WIDTH/2;
    private int ballYPosition = GameMain.GAME_HEIGHT / 2;

    private int ballXVelocity = 5;
    private int ballYVelocity = 0;

    @Override
    public void init() {

    }

    @Override
    public void update() {

        int nextPositionLeft = yPos + yVelocity;
        int nextPositionRight = yPos2 - yVelocity;

        if( inBounds(nextPositionLeft)) {
            yPos = nextPositionLeft;
        }

        if (inBounds(nextPositionRight)) {
            yPos2 = nextPositionRight;
        }

        ballXPosition += ballXVelocity;
        ballYPosition += ballYVelocity;

        Rectangle ballRectangle =
                new Rectangle(ballXPosition, ballYPosition, BALL_SIZE,BALL_SIZE );
        Rectangle paddleLeftRectangle =
                new Rectangle(xPos, yPos, PADDLE_WIDTH, PADDLE_HEIGHT);
        Rectangle paddleRightRectangle =
                new Rectangle(xPos2, yPos2, PADDLE_WIDTH, PADDLE_HEIGHT);

        if (ballRectangle.intersects(paddleLeftRectangle)) {
            // hit left paddle
        } else if (ballRectangle.intersects(paddleRightRectangle)) {
            ballXVelocity = -5;
            ballYVelocity = 5;

            ballXPosition += ballXVelocity;
            ballYPosition += ballYVelocity;

        } else {
            //hit a wall?
        }
    }

    private boolean inBounds(int nextPosition) {
        boolean atTop = nextPosition <= 0;
        boolean atBottom = nextPosition >= (GameMain.GAME_HEIGHT - PADDLE_HEIGHT);
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
        g.fillRect(xPos,yPos,PADDLE_WIDTH, PADDLE_HEIGHT);

        //right
        g.setColor(Color.ORANGE);
        g.fillRect(xPos2,yPos2,PADDLE_WIDTH, PADDLE_HEIGHT);

        //ball
        g.fillRect(ballXPosition, ballYPosition, BALL_SIZE,BALL_SIZE );

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
