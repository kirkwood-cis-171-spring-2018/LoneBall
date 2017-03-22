package com.jamescho.game.model;

import java.awt.*;

/**
 * Created by cortman on 3/21/17.
 */
public class Paddle {
    private int x, y, width, height, velY;
    private Rectangle rect;
    private final static int MOVE_SPEED_Y = 4;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x,y,width,height);
        velY = 0;
    }

    public void update() {
        y += velY;
        updateRect();
    }

    private void updateRect() {
        rect.setBounds(x,y,width,height);
    }

    public void accelUp() {
        velY = -MOVE_SPEED_Y;
    }

    public void accelDown() {
        velY = MOVE_SPEED_Y;
    }

    public void stop() {
        velY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRect() {
        return rect;
    }
}
