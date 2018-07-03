package eluosifangkuai;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TetrisFrame extends JFrame implements ActionListener {

	static JMenu game = new JMenu("游戏");
	JMenuItem newgame =game.add("新游戏");
	JMenuItem pause =game.add("暂停");
	JMenuItem goon =game.add("继续");
	JMenuItem exit =game.add("退出");
	static JMenu help =new JMenu("帮助");
	JMenuItem about =help.add("关于");
	Tetrisblok a=new Tetrisblok();
	public TetrisFrame() {
		addKeyListener(a);
		this.add(a);
		newgame.addActionListener(this);
		pause.addActionListener(this);
		goon.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newgame) {
			a.newGame();
		}else if(e.getSource()==pause) {
			a.pauseGame();
		}
		else if(e.getSource()==goon) {
			a.continueGame();
		}else if(e.getSource()==about) {
			DisplayToast("左，右键移动，向上旋转");
		}else if(e.getSource()==exit) {
			System.exit(0);
		}
	}
	
    private void DisplayToast(String str) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, str, "提示", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TetrisFrame frame =new TetrisFrame();
		JMenuBar menu =new JMenuBar();
		frame.setJMenuBar(menu);
		menu.add(game);
		menu.add(help);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(320, 375);
		frame.setTitle("高级俄罗斯方块");
		frame.setVisible(true);
		frame.setResizable(false);

	}
}
