package com.jamescho.game.model;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.state.PlayState;

import java.awt.*;
import java.util.Random;

public class Ball {

    public static final int BALL_SPEED = 5;

    private int xVelocity;
    private int yVelocity;
    private int xPosition;
    private int yPosition;
    private int size;
    private final Rectangle ballRectangle;

    public Ball(int xPosition, int yPosition, int size) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;

        this.xVelocity = BALL_SPEED;
        this.yVelocity = generateRandomYVelocityOfBall();
        ballRectangle = new Rectangle(xPosition, yPosition, size, size);
    }

    public void update() {
        //updateballposition
        xPosition += xVelocity;
        yPosition += yVelocity;

        //rectangle for ball
        ballRectangle.setBounds(
                xPosition,
                yPosition,
                size,
                size
        );
        //collide with paddle --> still do in playstate
        //check at top or bottom
        if (atTopOrBottom()) {
            yVelocity = yVelocity * -1;
            Resources.bounce.play();
        }
        //check left or right
    }

    private boolean atTopOrBottom() {
        boolean atTop = yPosition <= 0;
        boolean atBottom = yPosition >= (GameMain.GAME_HEIGHT - size);
        return atTop || atBottom;
    }

    public boolean atLeftOrRight(PlayState playState) {
        boolean atLeft = xPosition <= 0;
        boolean atRight = xPosition >= (GameMain.GAME_WIDTH - size);
        if (atLeft || atRight) {
            playState.addScore(-3);
            xPosition = 0;
            yPosition = 0;
            xVelocity = BALL_SPEED;
            yVelocity = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean collidesWith(Rectangle paddleRect) {
        return ballRectangle.intersects(paddleRect);
    }

    public void onPaddleCollision() {
        this.xVelocity = this.xVelocity *-1;
        this.yVelocity = generateRandomYVelocityOfBall();
    }

    private int generateRandomYVelocityOfBall() {
        Random rnd = new Random();
        final int UPPER = 5;
        final int LOWER = -4;

        int randomBound = UPPER - LOWER + 1;
        int randomYVelocityNumber = rnd.nextInt(randomBound);
        return randomYVelocityNumber - 4;

    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}