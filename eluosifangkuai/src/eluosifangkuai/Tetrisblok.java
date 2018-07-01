package eluosifangkuai;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetrisblok extends JPanel implements KeyListener {

	private int blockType; //方块类型
	private int turnState;//旋转状态
	private int score;
	private int nextblockType =-1,nextturnState =-1;
	private int x,y;//当前位置
	private Timer timer;
	
	int[][] map =new int[12][21];//游戏地图
	
	private final int shapes[][][]=new int[][][] {
		//长条型
		{
			{0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0},
			{0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0}
		},
		//倒z
		{
			{0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0},
			{0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0}

		},
		//z
		{
			{1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,1,1,0,0,1,0,0,0,0,0,0,0},
			{1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,1,1,0,0,1,0,0,0,0,0,0,0}
		},
		//j型
		{
			{0,1,0,0,0,1,0,0,1,1,0,0,0,0,0,0},
			{1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
			{1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0}
		},
		//田字型
		{
			{1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0}
		},
		//l
		{
			{1,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},
			{1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0},
			{0,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0}
		},
		//倒T
		{
			{0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,0},
			{1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,1,1,0,0,1,0,0,0,0,0,0}
		}
	};
	
	//构造方法产生新的下落块，启动定时器,每0.5秒自动触发，屏幕重画，判断当前块是否可以下落。并消除满行。
	Tetrisblok() {
		newblock();
		newmap();
		drawall();
		timer = new Timer(500,new TimerListener());
		timer.start();
	}
	
	
	public class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(blow(x,y+1,blockType,turnState)==1) {
				y=y+1;
			}
			if(blow(x,y+1,blockType,turnState)==0) {
				add(x,y,blockType,turnState);
				delline();
				newblock();
			}
			repaint();

		}

		private void delline() {
			// TODO Auto-generated method stub
			
		}

		private void add(int x, int y, int blockType, int turnState) {
			// TODO Auto-generated method stub
			
		}

		private int blow(int x, int i, int blockType, int turnState) {
			// TODO Auto-generated method stub
			return 0;
		}

	}


	//判断是否有下一块，
	public void newblock() {
		//没有下一个方块
		if(nextblockType==-1 && nextturnState ==-1) {
			blockType=(int) (Math.random() *1000)%7;    //返回一个随机数
			turnState=(int) (Math.random() *1000)%4;
			nextblockType=(int) (Math.random() *1000)%7;
			nextturnState=(int) (Math.random() *1000)%4;
		}
		else {
			blockType = nextblockType;
			turnState = nextturnState;
			nextblockType=(int) (Math.random() *1000)%7;
			nextturnState=(int) (Math.random() *1000)%4;
		}
		x=4;y=0;
		if(gameover(x,y)==1) {
			newmap();
			drawall();
			score =0;
			JOptionPane.showMessageDialog(null, "game over");
			
		}
	}
	
	
	//完成在map保存围墙信息
	public void drawall() {
		// TODO Auto-generated method stub
		int i,j;
		for(i=0;i<12;i++) {
			map[i][20]=2;	
		}
		for(j=0;j<21;j++) {
			map[11][j]=2;
			map[0][j]=2;
		}
		
	}



	public void newmap() {
		// TODO Auto-generated method stub
		int i,j;
		for(i=0;i<12;i++) {
			for(j=0;j<21;j++) {
				map[i][j]=0;
			}
		}
	}



	private int gameover(int x2, int y2) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
