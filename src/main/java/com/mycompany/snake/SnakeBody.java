/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import static com.mycompany.snake.Direction.UP;
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author alu13114532
 */
public class SnakeBody {

    private List<Node> body;
    private Direction direction;
    private int nodesToGrow;

    public SnakeBody() {
        body = new ArrayList<Node>();
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2 - 1));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2 - 2));
        body.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COL / 2 - 3));
        direction = Direction.RIGHT;
        nodesToGrow = 0;
    }

    public void paint(Graphics g, int squareWidth, int squareHeight) {
        boolean firstNode = true;
        Color color;
        for (Node node : body) {
            if (firstNode) {
                color = ConfigData.PURPLE;
                firstNode = false;
            } else {
                color = ConfigData.GREEN;
            }
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeight);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Node getHead() {
        return body.get(0);
    }

    public boolean move() {
        Node head = getHead();
        int row = head.getRow();
        int col = head.getCol();
        switch (direction) {
            case UP:
                if (canMove(row - 1, col)) {
                    body.add(0, new Node(row - 1, col));
                } else {
                    return false;
                }
                break;
            case DOWN:
                if (canMove(row + 1, col)) {
                    body.add(0, new Node(row + 1, col));
                } else {
                    return false;
                }
                break;
            case LEFT:
                if (canMove(row, col - 1)) {
                    body.add(0, new Node(row, col - 1));
                } else {
                    return false;
                }
                break;
            case RIGHT:
                if (canMove(row, col + 1)) {
                    body.add(0, new Node(row, col + 1));
                } else {
                    return false;
                }
                break;
        }
        if (nodesToGrow == 0) {
            body.remove(body.size() - 1);
        } else {
            nodesToGrow--;
        }
        return true;
    }

    private boolean canMove(int row, int col) {
        if (row < 0 || row >= Board.NUM_ROWS || col < 0 || col >= Board.NUM_COL) {
            return false;
        }

        if (snakeHitsMatrix(row, col)) {
            return false;
        }

        return true;
    }

    public boolean snakeHitsMatrix(int row, int col) {
        for (Node nodes : body) {
            if (row == nodes.getRow() && col == nodes.getCol()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkFoodEaten(Food food) {
        Node head = getHead();
        if (head.getRow() == food.getRow() && head.getCol() == food.getCol()) {
            nodesToGrow++;
            return true;
        }
        return false;
    }

    public boolean checkSpecialFoodEaten(SpecialFood food) {
        Node head = getHead();
        if (head.getRow() == food.getRow() && head.getCol() == food.getCol()) {
            nodesToGrow = nodesToGrow + 3;
            return true;
        }
        return false;
    }

}
