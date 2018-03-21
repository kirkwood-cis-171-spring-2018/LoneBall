package com.jamescho.game.state;

import com.jamescho.game.main.GameMain;
import com.jamescho.game.main.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cortman on 3/21/17.
 */
public class PlayState extends State {
    private int endPosition = 0;
    private boolean moveRight = true;
    private int yposition = 0;
    private int yvelocity = 5;
    @Override
    public void init() {

    }

    @Override
    public void update() {
       yposition += yvelocity;
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

        g.setColor(Color.WHITE);
        g.fillRect(GameMain.GAME_WIDTH/40,yposition,20,50);
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {

        String x = Character.toString(e.getKeyChar());
        endPosition = yposition + 3;
        if (KeyEvent.VK_UP == e.getKeyCode()) {
            yvelocity = -5;
        } else {
            yvelocity = 5;
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {

        endPosition = yposition + 3;
        if (endPosition < yposition) {
            yvelocity = 0;
        } else {
            yvelocity = 0;
        }
    }
}
