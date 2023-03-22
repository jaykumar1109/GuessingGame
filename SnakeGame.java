import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;
    private final static int DOT_SIZE = 10;
    private final static int ALL_DOTS = (WIDTH * HEIGHT) / (DOT_SIZE * DOT_SIZE);
    private final static int RAND_POS = (WIDTH / DOT_SIZE) - 1;

    private final static int DELAY = 140;
    private final static int RIGHT = 0;
    private final static int LEFT = 1;
    private final static int UP = 2;
    private final static int DOWN = 3;

    private Timer timer;
    private boolean gameOver;
    private boolean running;
    private Point food;
    private int direction;
    private int dots;
    private Point[] snake;

    private Board board;

    public SnakeGame() {
        initGame();
    }

    private void initGame() {
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        board = new Board();
        add(board);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_LEFT) && (direction != RIGHT)) {
                    direction = LEFT;
                } else if ((key == KeyEvent.VK_RIGHT) && (direction != LEFT)) {
                    direction = RIGHT;
                } else if ((key == KeyEvent.VK_UP) && (direction != DOWN)) {
                    direction = UP;
                } else if ((key == KeyEvent.VK_DOWN) && (direction != UP)) {
                    direction = DOWN;
                }
            }
        });

        initGameParams();
    }

    private void initGameParams() {
        snake = new Point[ALL_DOTS];
        dots = 3;
        for (int i = 0; i < dots; i++) {
            snake[i] = new Point(DOT_SIZE * (dots - i), DOT_SIZE);
        }
        direction = RIGHT;
        gameOver = false;
        running = true;
        placeFood();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void move() {
        for (int i = dots; i > 0; i--) {
            snake[i] = snake[i - 1];
        }
        if (direction == RIGHT) {
            snake[0].x += DOT_SIZE;
        } else if (direction == LEFT) {
            snake[0].x -= DOT_SIZE;
        } else if (direction == UP) {
            snake[0].y -= DOT_SIZE;
        } else if (direction == DOWN) {
            snake[0].y += DOT_SIZE;
        }
    }

    private void checkFood() {
        if ((snake[0].x == food.x) && (snake[0].y == food.y)) {
            dots++;
            placeFood();
        }
    }

    private void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if ((i == 0) && (snake[0].equals(snake[i]))) {
                gameOver = true;
                break;
            }
        }
        if ((snake[0].x < 0) || (snake[0].x > WIDTH - DOT_SIZE) || (snake[0].y < 0)
                || (snake[0].y > HEIGHT - DOT_SIZE)) {
            gameOver = true;
        }
        if (gameOver) {
            running = false;
            timer.stop();
        }
    }

    private void placeFood() {
        Random rand = new Random();
        int r = rand.nextInt(RAND_POS);
        food = new Point(r * DOT_SIZE, r * DOT_SIZE);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollisions();
        }
        board.repaint();
    }

    private class Board extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!gameOver) {
                g.setColor(Color.RED);
                g.fillRect(food.x, food.y, DOT_SIZE, DOT_SIZE);

                for (int i = 0; i < dots; i++) {
                    g.setColor(Color.GREEN);
                    g.fillRect(snake[i].x, snake[i].y, DOT_SIZE, DOT_SIZE);
                }
            } else {
                String msg = "Game Over";
                Font font = new Font("Helvetica", Font.BOLD, 30);
                FontMetrics metrics = getFontMetrics(font);

                g.setColor(Color.RED);
                g.setFont(font);
                g.drawString(msg, (WIDTH - metrics.stringWidth(msg)) / 2, HEIGHT / 2);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }
    }
}

