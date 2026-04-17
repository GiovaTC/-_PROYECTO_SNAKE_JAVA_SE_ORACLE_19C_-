// =========================================
// PROYECTO: SNAKE JAVA SE + ORACLE 19c
// =========================================
// ✔ Interfaz gráfica (Swing)
// ✔ Colores modernos
// ✔ Persistencia en Oracle (puntajes)
// ✔ Arquitectura simple (DAO)
// =========================================

// =============================
// 1. CLASE MAIN
// =============================

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game - Oracle Edition");
        GamePanel gamePanel = new GamePanel();

    //    frame.add(gamePanel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}