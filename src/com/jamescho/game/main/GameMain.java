package com.jamescho.game.main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cortman on 2/14/17.
 */
public class GameMain {
    private static final String GAME_TITLE = "Java Game Development Framework";
    public static final int GAME_WIDTH= 800;
    public static final int GAME_HEIGHT = 450;
    public static Game sGame;

    public static void main(String[] args) {
        JFrame frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GAME_WIDTH,GAME_HEIGHT);
        frame.setResizable(false);

        sGame = new Game(GAME_WIDTH,GAME_HEIGHT);
        frame.add(sGame);
        frame.pack();

        frame.setIconImage(Resources.iconimage);
        frame.setVisible(true);

    }
}



