// =============================
// 2. GAME PANEL
// =============================
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GamePanel extends JPanel implements ActionListener {

    private final int SIZE = 20;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    private java.util.List<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean running = true;
    private Timer timer;
    private int score = 0;

    public GamePanel() {
        setBackground(new Color(30, 30, 40));
        setFocusable(true);

        snake = new ArrayList<>();
        snake.add(new Point(5, 5));

        spawnFood();

        timer = new Timer(120, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: if (direction != 'D') direction = 'U'; break;
                    case KeyEvent.VK_DOWN: if (direction != 'U') direction = 'D'; break;
                    case KeyEvent.VK_LEFT: if (direction != 'R') direction = 'L'; break;
                    case KeyEvent.VK_RIGHT: if (direction != 'L') direction = 'R'; break;
                }
            }
        });
    }

    private void spawnFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(WIDTH / SIZE), rand.nextInt(HEIGHT / SIZE));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo degradado simple
        g.setColor(new Color(40, 40, 60));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Comida
        g.setColor(new Color(255, 80, 80));
        g.fillOval(food.x * SIZE, food.y * SIZE, SIZE, SIZE);

        // Snake
        for (int i = 0; i < snake.size(); i++) {
            if (i == 0)
                g.setColor(new Color(0, 255, 150));
            else
                g.setColor(new Color(0, 180, 120));

            Point p = snake.get(i);
            g.fillRoundRect(p.x * SIZE, p.y * SIZE, SIZE, SIZE, 10, 10);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 20);

        if (!running) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 180, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
        }
        repaint();
    }

    private void move() {
        Point head = new Point(snake.get(0));

        switch (direction) {
            case 'U': head.y--; break;
            case 'D': head.y++; break;
            case 'L': head.x--; break;
            case 'R': head.x++; break;
        }

        snake.add(0, head);

        if (head.equals(food)) {
            score += 10;
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);

        if (head.x < 0 || head.y < 0 || head.x >= WIDTH / SIZE || head.y >= HEIGHT / SIZE) {
            endGame();
        }

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                endGame();
            }
        }
    }

    private void endGame() {
        running = false;
        timer.stop();

        String player = JOptionPane.showInputDialog("Nombre del jugador:");
        ScoreDAO.saveScore(player, score);
    }
}
