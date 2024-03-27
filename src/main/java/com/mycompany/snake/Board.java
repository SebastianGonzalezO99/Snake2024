/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author alu13114532
 */
public class Board extends javax.swing.JPanel {

    public static final int NUM_ROWS = 30;
    public static final int NUM_COL = 30;
    private Timer timer;
    private MyKeyAdapter keyAdapter;
    private SnakeBody snake;
    private Food food;
    private SpecialFood specialFood;
    private boolean turning;
    private int specialFood_timer = 0;
    private ScoreBoard scoreBoard;

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        snake = new SnakeBody();
        food = new Food(5, 5);
        food.generateFood(snake);
        specialFood = new SpecialFood(10, 10);
        specialFood.generateFood(snake);
        setFocusable(true);
        setPreferredSize(new Dimension(NUM_COL * 15, NUM_ROWS * 15));
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }
    
    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT && !turning) {
                        snake.setDirection(Direction.LEFT);
                        turning = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT && !turning) {
                        snake.setDirection(Direction.RIGHT);
                        turning = true;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN && !turning) {
                        snake.setDirection(Direction.UP);
                        turning = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP && !turning) {
                        snake.setDirection(Direction.DOWN);
                        turning = true;
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public void initGame() {
        specialFood_timer = 0;
        snake = new SnakeBody();
        keyAdapter = new MyKeyAdapter();
        addKeyListener(keyAdapter);
        int deltaTime = ConfigData.getInstance().getDeltaTime();
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });
        timer.start();
    }
    
    public void stop() {
        timer.stop();
    }

    public void tick() {
        if (snake.move()) {
            if (snake.checkFoodEaten(food)) {
                scoreBoard.incrementFood();
                food.generateFood(snake);
            }
            if (snake.checkSpecialFoodEaten(specialFood)) {
                    scoreBoard.incrementSpecialFood();
                    specialFood.generateFood(snake);
                    specialFood_timer = 0;
            }
            repaint();
            turning = false;
        } else {
            gameOver();
        }
        specialFood_timer++;
        System.out.println(specialFood);
    }

    public void paintMatrix(Graphics g) {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COL; col++) {
                if ((row + col) % 2 == 0) {
                    Util.drawSquare(g, row, col, ConfigData.BG1, NUM_ROWS / 2, NUM_COL / 2);
                } else {
                    Util.drawSquare(g, row, col, ConfigData.BG2, NUM_ROWS / 2, NUM_COL / 2);
                }
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBorder(g);
        paintMatrix(g);
        if (snake != null) {
            snake.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (food != null) {
            food.paint(g, getSquareWidth(), getSquareHeight());
        }
        if (specialFood != null && specialFood_timer >= 30) {
            specialFood.paint(g, getSquareWidth(), getSquareHeight());
            if(specialFood_timer == 70) {
              specialFood_timer = 0;
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private int getSquareWidth() {
        return getWidth() / NUM_COL;
    }

    private int getSquareHeight() {
        return getHeight() / NUM_ROWS;
    }

    public void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(null, "GAME OVER!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
