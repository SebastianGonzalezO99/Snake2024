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
public class SpecialFood extends Food{
    
    
    public SpecialFood(int row, int col) {
        super(row, col);
    }

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        g.setColor(ConfigData.BLUE);
        g.fillRect(getCol() * squareWidth, getRow() * squareHeight, squareWidth, squareHeight);
    }
    
}
