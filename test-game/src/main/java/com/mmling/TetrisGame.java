package com.mmling;

/**
 * 俄罗斯方块游戏
 *
 * @author Mei.Mengling
 * @date 2024-11-22
 * @since 1.0.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TetrisGame extends JFrame {
    private final int ROWS = 20;  // 游戏面板的行数
    private final int COLS = 10; // 游戏面板的列数
    private final int BLOCK_SIZE = 30; // 每个方块的大小

    private int[][] board = new int[ROWS][COLS]; // 游戏面板
    private Tetromino currentTetromino;         // 当前活动的方块
    private Tetromino nextTetromino;            // 预览的下一块方块
    private Timer timer;                        // 游戏计时器
    private boolean isPaused = false;           // 游戏是否暂停
    private boolean isGameOver = false;         // 游戏是否结束

    public TetrisGame() {
        setTitle("俄罗斯方块");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // 使用自定义布局
        setResizable(false);

        // 计算窗口实际大小
        int panelWidth = COLS * BLOCK_SIZE + 150; // 右侧增加150像素用于显示预览和按钮
        int panelHeight = ROWS * BLOCK_SIZE;
        setSize(panelWidth, panelHeight);
        setVisible(true); // 必须先显示窗口才能获取 insets

        Insets insets = getInsets();
        setSize(panelWidth + insets.left + insets.right, panelHeight + insets.top + insets.bottom);

        initGame();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!isPaused && !isGameOver) {
                    handleKeyPress(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // 添加按钮
        addButtons();
    }

    private void addButtons() {
        JButton startButton = new JButton("开始");
        startButton.setBounds(COLS * BLOCK_SIZE + 20, 50, 100, 30);
        startButton.addActionListener(e -> startGame());
        add(startButton);

        JButton pauseButton = new JButton("暂停");
        pauseButton.setBounds(COLS * BLOCK_SIZE + 20, 100, 100, 30);
        pauseButton.addActionListener(e -> pauseGame());
        add(pauseButton);

        JButton endButton = new JButton("结束");
        endButton.setBounds(COLS * BLOCK_SIZE + 20, 150, 100, 30);
        endButton.addActionListener(e -> endGame());
        add(endButton);
    }

    private void initGame() {
        currentTetromino = Tetromino.randomTetromino(COLS / 2 - 1);
        nextTetromino = Tetromino.randomTetromino(COLS / 2 - 1);
        timer = new Timer(500, e -> {
            if (!isPaused && !isGameOver) {
                moveDown();
            }
        });
    }

    private void startGame() {
        if (isGameOver) {
            resetGame(); // 如果游戏已结束，重置游戏
        }
        isPaused = false;
        isGameOver = false;
        timer.start();
        this.requestFocus(); // 确保窗口获得焦点，激活按键监听
    }

    private void pauseGame() {
        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    private void endGame() {
        timer.stop(); // 停止计时器
        isGameOver = true; // 设置游戏结束状态
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = 0; // 清空棋盘
            }
        }
        repaint(); // 重绘界面清除方块
        JOptionPane.showMessageDialog(this, "游戏结束！");
    }

    private void resetGame() {
        board = new int[ROWS][COLS];
        currentTetromino = Tetromino.randomTetromino(COLS / 2 - 1);
        nextTetromino = Tetromino.randomTetromino(COLS / 2 - 1);
        isPaused = false;
        isGameOver = false;
        timer.start();
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_UP:
                rotate();
                break;
        }
        repaint();
    }

    private void moveLeft() {
        if (currentTetromino.canMove(board, -1, 0)) {
            currentTetromino.move(-1, 0);
        }
    }

    private void moveRight() {
        if (currentTetromino.canMove(board, 1, 0)) {
            currentTetromino.move(1, 0);
        }
    }

    private void moveDown() {
        if (currentTetromino.canMove(board, 0, 1)) {
            currentTetromino.move(0, 1);
        } else {
            currentTetromino.addToBoard(board);
            clearFullLines();
            currentTetromino = nextTetromino;
            nextTetromino = Tetromino.randomTetromino(COLS / 2 - 1);
            if (!currentTetromino.canMove(board, 0, 0)) {
                endGame();
            }
        }
        repaint();
    }

    private void rotate() {
        currentTetromino.rotate(board);
    }

    private void clearFullLines() {
        for (int row = ROWS - 1; row >= 0; row--) {
            boolean isFull = true;
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 0) {
                    isFull = false;
                    break;
                }
            }
            if (isFull) {
                for (int r = row; r > 0; r--) {
                    System.arraycopy(board[r - 1], 0, board[r], 0, COLS);
                }
                board[0] = new int[COLS];
                row++;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Insets insets = getInsets();
        g.translate(insets.left, insets.top);

        // 绘制游戏面板
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] != 0) {
                    g.setColor(Color.GRAY);
                    g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        // 绘制当前方块
        currentTetromino.draw(g, BLOCK_SIZE);

        // 绘制下一块方块
        drawNextTetromino(g);
    }

    private void drawNextTetromino(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("下一块：", COLS * BLOCK_SIZE + 20, 250);

        nextTetromino.drawAtPosition(g, COLS * BLOCK_SIZE + 30, 270, BLOCK_SIZE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TetrisGame game = new TetrisGame();
            game.setVisible(true);
        });
    }
}

class Tetromino {
    private int[][] shape;
    private int row, col;

    private static final int[][][] SHAPES = {
            {{1, 1, 1, 1}},        // I
            {{1, 1}, {1, 1}},      // O
            {{0, 1, 0}, {1, 1, 1}},// T
            {{1, 1, 0}, {0, 1, 1}},// S
            {{0, 1, 1}, {1, 1, 0}},// Z
            {{1, 1, 1}, {1, 0, 0}},// L
            {{1, 1, 1}, {0, 0, 1}} // J
    };

    public Tetromino(int[][] shape, int row, int col) {
        this.shape = shape;
        this.row = row;
        this.col = col;
    }

    public static Tetromino randomTetromino(int startCol) {
        Random rand = new Random();
        int[][] shape = SHAPES[rand.nextInt(SHAPES.length)];
        return new Tetromino(shape, 0, startCol);
    }

    public void move(int dx, int dy) {
        col += dx;
        row += dy;
    }

    public void rotate(int[][] board) {
        int[][] rotated = new int[shape[0].length][shape.length];
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                rotated[c][shape.length - r - 1] = shape[r][c];
            }
        }
        shape = rotated;
    }

    public boolean canMove(int[][] board, int dx, int dy) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 1) {
                    int newRow = row + r + dy;
                    int newCol = col + c + dx;
                    if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length
                            || board[newRow][newCol] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addToBoard(int[][] board) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 1) {
                    board[row + r][col + c] = 1;
                }
            }
        }
    }

    public void draw(Graphics g, int blockSize) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect((col + c) * blockSize, (row + r) * blockSize, blockSize, blockSize);
                    g.setColor(Color.BLACK);
                    g.drawRect((col + c) * blockSize, (row + r) * blockSize, blockSize, blockSize);
                }
            }
        }
    }

    public void drawAtPosition(Graphics g, int x, int y, int blockSize) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x + c * blockSize, y + r * blockSize, blockSize, blockSize);
                    g.setColor(Color.BLACK);
                    g.drawRect(x + c * blockSize, y + r * blockSize, blockSize, blockSize);
                }
            }
        }
    }
}
