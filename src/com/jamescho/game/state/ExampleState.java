package com.jamescho.game.state;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by cortman on 3/21/17.
 */
public class ExampleState extends State {


    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(400, 200, 10, 15);
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
