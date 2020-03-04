package snake.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import snake.size.Size;

public class MainView {

	private Font font = new Font("楷体", Font.PLAIN, Size.FONT_SIZE);
	private Font labFont = new Font("楷体", Font.BOLD, Size.FONT_SIZE);
	private JFrame jFrame = new JFrame();
	private WinPanel winPanel = new WinPanel();

	public MainView() {
		jFrame.setSize(Size.WIN_WIDTH, Size.WIN_HEIGHT);
		jFrame.setTitle("贪吃蛇");
		jFrame.add(winPanel);
		jFrame.addKeyListener(winPanel);

		winPanel.setLayout(null);

		// 开始游戏按扭定义
		JButton button = new JButton("开始游戏");
		button.setFont(font);
		button.setBounds(Size.BTN_LEFT, Size.BTN_TOP, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				winPanel.start();
				jFrame.requestFocus();
			}
		});
		winPanel.add(button);

		// 重新开始按扭定义
		JButton button2 = new JButton("重新开始");
		button2.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 60, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		button2.setFont(font);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				winPanel.restart();
				jFrame.requestFocus();
			}
		});
		winPanel.add(button2);

		// 暂停按扭定义
		JButton button3 = new JButton("暂停/开始");
		button3.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 120, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		button3.setFont(font);
		winPanel.add(button3);
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				winPanel.suspend();
				jFrame.requestFocus();
			}
		});

		// 退出按扭定义
		JButton button4 = new JButton("退出");
		button4.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 180, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		button4.setFont(font);
		winPanel.add(button4);
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showConfirmDialog(null, "确定要退出?");
				if (flag == 0) {
					System.exit(0);
				}
			}
		});
		
		JLabel label = new JLabel("控制:←↑→↓");
		label.setFont(labFont);
		label.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 250, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		winPanel.add(label);
		
		JLabel label2 = new JLabel("开始/暂停:空格");
		label2.setFont(labFont);
		label2.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 300, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		winPanel.add(label2);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
	}

}
