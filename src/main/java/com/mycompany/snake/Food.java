/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author alu13114532
 */
public class Food extends Node {

    private Random random;

    public Food(int row, int col) {
        super(row, col);
        random = new Random();
    }

    public void generateFood(SnakeBody snakeBody) {
        int numRows = Board.NUM_ROWS;
        int numCols = Board.NUM_COL;
        do {
            setRow(random.nextInt(numRows));
            setCol(random.nextInt(numCols));
        } while (snakeBody.containsNode(getRow(), getCol())); 
    }

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        g.setColor(ConfigData.RED);
        g.fillRect(getCol() * squareWidth, getRow() * squareHeight, squareWidth, squareHeight);
    }

}
