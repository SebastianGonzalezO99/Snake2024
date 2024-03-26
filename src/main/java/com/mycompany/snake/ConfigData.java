/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Color;

/**
 *
 * @author alu13114532
 */
public class ConfigData {

    public static final Color PURPLE = new Color(102, 0, 102);//color cabeza
    public static final Color GREEN = new Color(0, 204, 102);// color cuerpo 
    public static final Color RED = new Color(255, 0, 0);// color food 
    public static final Color BLUE = new Color(0, 0, 255);//color special food
    public static final Color BG1 = new Color(173, 208, 90);
    public static final Color BG2 = new Color(87, 138, 52);
    public static int deltaTime;
    private static ConfigData instance;
    private static String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public static void setDeltaTime(int deltaTime) {
        ConfigData.deltaTime = deltaTime;
    }

    public static ConfigData getInstance() {
        if (instance == null) {
            instance = new ConfigData();
        }
        return instance;
    }

    public static int getDeltaTime() {
        return deltaTime;
    }
}
