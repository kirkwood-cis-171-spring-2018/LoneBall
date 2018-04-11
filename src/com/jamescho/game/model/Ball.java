package com.jamescho.game.model;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.state.PlayState;

import java.awt.*;
import java.util.Random;

public class Ball {

    public static final int BALL_SPEED = 5;

    private int velocityX;
    private int velocityY;
    private int positionX;
    private int positionY;
    private int size;
    private final Rectangle ballRectangle;

    public Ball(int positionX, int positionY, int size) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;

        this.velocityX = BALL_SPEED;
        this.velocityY = generateRandomYVelocityOfBall();
        ballRectangle = new Rectangle(positionX, positionY, size,size);
    }

    public void update() {
       //updateballposition
        positionX += velocityX;
        positionY += velocityY;

       //rectangle for ball
        ballRectangle.setBounds(
                positionX,
                positionY,
                size,
                size
        );
       //collide with paddle --> still do in playstate

       //check at top or bottom
       if (atTopOrBottom()) {
           velocityY = velocityY *-1;
           Resources.bounce.play();
       }

        //check left or right
    }

    private boolean atTopOrBottom() {
        boolean atTop = positionY <= 0;
        boolean atBottom = positionY >= (GameMain.GAME_HEIGHT - size);
        return atTop || atBottom;
    }

    public boolean atLeftOrRight(PlayState playState) {
        boolean atLeft = positionX <= 0;
        boolean atRight = positionX >= (GameMain.GAME_WIDTH - size);
        if (atLeft || atRight) {
            playState.addScore(-3);
            positionX = 0;
            positionY = 0;
            velocityX = BALL_SPEED;
            velocityY = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean collidesWith(Rectangle paddleRect) {
        return ballRectangle.intersects(paddleRect);
    }

    public void onPaddleCollision() {
        this.velocityX = this.velocityX * -1;
        this.velocityY = generateRandomYVelocityOfBall();
    }

    private int generateRandomYVelocityOfBall() {
        Random rnd = new Random();
        final int UPPER = 5;
        final int LOWER = -4;

        int randomBound = UPPER - LOWER + 1;
        int randomYVelocityNumber = rnd.nextInt(randomBound);
        return randomYVelocityNumber - 4;

    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
