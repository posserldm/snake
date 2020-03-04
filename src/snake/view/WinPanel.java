package snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import snake.size.Size;

public class WinPanel extends JPanel implements KeyListener,Runnable{

	private static final long serialVersionUID = 1386654890091704181L;
	private final int edge = 25;
	private Thread thread = null;
	private List<Integer> snakeX = new ArrayList<Integer>();
	private List<Integer> snakeY = new ArrayList<Integer>();
	private boolean started = false;
	private boolean stop = false;
	private int space = 25;
	private int delay = 150;
	private int foodX = -1;
	private int foodY = 0;
	private Random random = new Random();
	private int x = space;
	private int y = 0;

	public WinPanel() {
		init();
		makeFood();
	}

	public void init() {
		snakeX.clear();
		snakeY.clear();
		x = 25;
		y = 0;
		snakeX.add(220);
		snakeY.add(50);
		snakeX.add(195);
		snakeY.add(50);
		snakeX.add(170);
		snakeY.add(50);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// 渲染活动背景
		g.fillRect(Size.PAN_LEFT, Size.PAN_TOP, Size.PAN_WIDTH, Size.PAN_HEIGHT);
		g.setColor(Color.BLACK);
		// 渲染蛇
		g.setColor(Color.RED);
		g.fillRect(snakeX.get(0), snakeY.get(0), edge, edge);
		g.setColor(Color.WHITE);
		for (int i = 1; i < snakeX.size(); i++) {
			g.fillRect(snakeX.get(i), snakeY.get(i), edge, edge);
		}
		// 渲染食物
		g.setColor(Color.BLUE);
		g.fillRect(foodX, foodY, edge, edge);
	}

	/**
	 * 蛇移动
	 */
	private void move() {
		int tempX = snakeX.get(snakeX.size() - 1);
		int tempY = snakeY.get(snakeY.size() - 1);
		// 蛇身前进
		for (int i = snakeX.size() - 1; i > 0; i--) {
			snakeX.set(i, snakeX.get(i - 1));
			snakeY.set(i, snakeY.get(i - 1));
		}
		// 蛇头前进
		snakeX.set(0, snakeX.get(0) + x);
		snakeY.set(0, snakeY.get(0) + y);
		// 吃到食物
		if (snakeX.get(0) == foodX && snakeY.get(0) == foodY) {
			snakeX.add(tempX);
			snakeY.add(tempY);
			foodX = -1;
		}
	}

	/**
	 * 创建食物坐标
	 */
	private void makeFood() {
		if (foodX == -1) {
			do {
				foodX = random.nextInt(Size.PAN_WIDTH - edge);
				foodX = foodX - foodX % edge + Size.PAN_LEFT;
				foodY = random.nextInt(Size.PAN_HEIGHT - edge);
				foodY = foodY - foodY % edge + Size.PAN_TOP;
			} while (snakeX.indexOf(foodX) != -1 && snakeY.indexOf(foodY) != -1);
		}
	}

	/**
	 * 判定游戏是否结束
	 * 
	 * @return
	 *         <p>
	 *         结束true
	 *         </p>
	 *         <p>
	 *         不结束false
	 *         </p>
	 */
	private boolean isOver() {
		int tempX = snakeX.get(0);
		int tempY = snakeY.get(0);
		for (int i = 1; i < snakeX.size(); i++) {
			if (snakeX.get(i) == tempX && snakeY.get(i) == tempY) {
				return true;
			}
		}
		return snakeX.get(0) > 1145 || snakeX.get(0) < 170 || snakeY.get(0) < 25 || snakeY.get(0) > 700 || stop;
	}

	/**
	 * 开始游戏
	 */
	public void start() {
		if (!started) {
			thread = new Thread(this);
			stop = false;
			thread.start();
			started = true;
		}
	}

	/**
	 * 重新开始游戏
	 */
	public void restart() {
		stop = true;
		started = false;
		try {
			if (thread != null) { 
				thread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		init();
		start();
	}

	/**
	 * 暂停/开始游戏
	 */
	public void suspend() {
		stop = !stop;
		if (stop == false) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		while (!isOver()) {
			move();
			makeFood();
			repaint();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			suspend();
			break;
		case KeyEvent.VK_LEFT:
			if (x == 0) {
				x = -space;
				y = 0;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (x == 0) {
				x = space;
				y = 0;
			}
			break;
		case KeyEvent.VK_UP:
			if (y == 0) {
				x = 0;
				y = -space;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (y == 0) {
				x = 0;
				y = space;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
