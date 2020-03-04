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

	private Font font = new Font("����", Font.PLAIN, Size.FONT_SIZE);
	private Font labFont = new Font("����", Font.BOLD, Size.FONT_SIZE);
	private JFrame jFrame = new JFrame();
	private WinPanel winPanel = new WinPanel();

	public MainView() {
		jFrame.setSize(Size.WIN_WIDTH, Size.WIN_HEIGHT);
		jFrame.setTitle("̰����");
		jFrame.add(winPanel);
		jFrame.addKeyListener(winPanel);

		winPanel.setLayout(null);

		// ��ʼ��Ϸ��Ť����
		JButton button = new JButton("��ʼ��Ϸ");
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

		// ���¿�ʼ��Ť����
		JButton button2 = new JButton("���¿�ʼ");
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

		// ��ͣ��Ť����
		JButton button3 = new JButton("��ͣ/��ʼ");
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

		// �˳���Ť����
		JButton button4 = new JButton("�˳�");
		button4.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 180, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		button4.setFont(font);
		winPanel.add(button4);
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳�?");
				if (flag == 0) {
					System.exit(0);
				}
			}
		});
		
		JLabel label = new JLabel("����:��������");
		label.setFont(labFont);
		label.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 250, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		winPanel.add(label);
		
		JLabel label2 = new JLabel("��ʼ/��ͣ:�ո�");
		label2.setFont(labFont);
		label2.setBounds(Size.BTN_LEFT, Size.BTN_TOP + 300, Size.BTN_WIDTH, Size.BTN_HEIGHT);
		winPanel.add(label2);

		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
	}

}
