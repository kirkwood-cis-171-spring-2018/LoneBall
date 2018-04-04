package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by cortman on 3/21/17.
 */
public class PlayState extends State {
    private final int BALL_SIZE = 30;
    private final int PADDLE_WIDTH = 5;
    private final int PADDLE_HEIGHT = 50;

    private int xPosition = 0;
    private int yPosition = GameMain.GAME_HEIGHT / 2;

    private int xPosition2 = GameMain.GAME_WIDTH - 5;
    private int yPosition2 = GameMain.GAME_HEIGHT / 2;

    private int yVelocity;

    private int ballXPosition = GameMain.GAME_WIDTH/2;
    private int ballYPosition = GameMain.GAME_HEIGHT / 2;

    private int ballXVelocity = 5;
    private int ballYVelocity = 0;
    private int playerScore = 0;


    @Override
    public void init() {

    }

    @Override
    public void update() {

        int nextPositionLeft = yPosition + yVelocity;
        int nextPositionRight = yPosition2 - yVelocity;

        if (!atTopOrBottom(nextPositionLeft, PADDLE_HEIGHT)) {
            yPosition = nextPositionLeft;
        }

        if (!atTopOrBottom(nextPositionRight, PADDLE_HEIGHT)) {
            yPosition2 = nextPositionRight;
        }

        updateBallPosition();

        Rectangle ballRectangle = new Rectangle(ballXPosition, ballYPosition, BALL_SIZE, BALL_SIZE);
        Rectangle paddleLeftRectangle = new Rectangle(xPosition, yPosition,PADDLE_WIDTH , PADDLE_HEIGHT);
        Rectangle paddleRightRectangle = new Rectangle(xPosition2, yPosition2, PADDLE_WIDTH, PADDLE_HEIGHT);

        if (ballRectangle.intersects(paddleLeftRectangle)) {
            playerScore += 1;
            ballXVelocity = 5;
            generateRandomYVelocityOfBall();
            updateBallPosition();

        } else if (ballRectangle.intersects(paddleRightRectangle)) {
            playerScore += 1;
            ballXVelocity = -5;
            generateRandomYVelocityOfBall();
            updateBallPosition();
            Resources.hit.play();

        } else if (atTopOrBottom(ballYPosition, BALL_SIZE)) {
            ballYVelocity = ballYVelocity * -1;
            Resources.bounce.play();

        } else if (atLeftOrRight(ballXPosition, BALL_SIZE)) {
            playerScore -= 3;
            ballXPosition = GameMain.GAME_WIDTH / 2;
            ballYPosition = GameMain.GAME_HEIGHT / 2;
            ballYVelocity = 0;
            ballXVelocity = 5;
        }
    }

    private boolean atLeftOrRight(int nextXPosition, int width) {
        boolean atLeft = nextXPosition <= 0;
        boolean atRight = nextXPosition >= (GameMain.GAME_WIDTH - width);
        return atLeft || atRight;
    }

    private boolean atTopOrBottom(int nextYPosition, int height) {
        boolean atTop = nextYPosition <= 0;
        boolean atBottom = nextYPosition >= (GameMain.GAME_HEIGHT - height);
        return atTop || atBottom;
    }

    private void updateBallPosition() {
        ballXPosition += ballXVelocity;
        ballYPosition += ballYVelocity;
    }

    private void generateRandomYVelocityOfBall() {
        Random rnd = new Random();
        final int UPPER = 5;
        final int LOWER = -4;

        int randomBound = UPPER - LOWER + 1;
        int randomYVelocityNumber = rnd.nextInt(randomBound);
        ballYVelocity = randomYVelocityNumber - 4;

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
        g.fillRect(xPosition, yPosition, PADDLE_WIDTH, PADDLE_HEIGHT);
        //g.drawString(new Integer(yPosition).toString(), GameMain.GAME_WIDTH/2 - 45,GameMain.GAME_HEIGHT/2);

        //right
        g.setColor(Color.white);
        g.fillRect(xPosition2, yPosition2, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.drawString(new Integer(playerScore).toString(), GameMain.GAME_WIDTH/2 + 25,GameMain.GAME_HEIGHT/2);

        //ball
        g.fillRect(ballXPosition, ballYPosition, BALL_SIZE,BALL_SIZE);

        //g.drawString(new Integer(yPosition2).toString(), (GameMain.GAME_WIDTH/2) + 25, GameMain.GAME_HEIGHT);
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
