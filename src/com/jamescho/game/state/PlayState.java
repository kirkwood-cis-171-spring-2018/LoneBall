package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;
import com.jamescho.game.model.Paddle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cortman on 3/21/17.
 */
public class PlayState extends State {

    private Paddle paddleLeft, paddleRight;
    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 60;

    @Override
    public void init() {
        paddleLeft = new Paddle(0,195,PADDLE_WIDTH, PADDLE_HEIGHT);
        paddleRight = new Paddle(785,195,PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void update() {

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

        //render
        g.setColor(Color.white);
        g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
        g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());


    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {

    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
