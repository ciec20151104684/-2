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
				newblock();   //页面重画
			}
			repaint();

		}
		
		//新游戏
		public void newGame() {
			
			newblock();
			newmap();
			drawall();
		}
		
		//暂停游戏
		public void pauseGame() {
			
			timer.stop();
		}
		//继续游戏
		public void continueGame() {
			timer.start();
		}
		

		//旋转当前方块的方法
		public void turn() {
			int tempturnState = turnState;
			turnState = (turnState + 1) % 4;
			if(blow(x,y,blockType,turnState)==1) {
				//可以旋转
			}
			if(blow(x,y,blockType,turnState)==0) {
				//不可以旋转
				turnState = tempturnState;
			}
			repaint();
		}
		
		public void left() {
			if(blow(x-1,y,blockType,turnState)==1) {
				x=x-1;
			}
			repaint();
		}
		
		public void right() {
			if(blow(x+1,y,blockType,turnState)==1) {
				x=x+1;
			}
			repaint();
		}
		
		public void down() {
			if(blow(x,y+1,blockType,turnState)==1) {
				y=y+1;
			}
			if(blow(x,y+1,blockType,turnState)==0) {
				add(x,y,blockType,turnState);
				newblock();
				delline();	
			}
			repaint();
		}
		
		
		//满行消去
		public void delline() {
			// TODO Auto-generated method stub
			int c=0;
			for(int b=0;b<21;b++) {
				for(int a=0;a<12;a++) {
					if(map[a][b]==1) {
						c+=1;
						if(c==10) {
							score +=10;
							for(int d=b;d>0;d--) {
								for(int e=0;e<12;e++) {
									map[e][d]=map[e][d-1];
								}
							}
						}
					}
				}
				
			c=0;
			}
		}

		public void add(int x, int y, int blockType, int turnState) {
			// TODO Auto-generated method stub
			
		}
		
		
        //当前方块位置 ?????
		public int blow(int x, int i, int blockType, int turnState) {
			// TODO Auto-generated method stub
			for(int a=0;a<4;a++) {
				for(int b=0;b<4;b++) {
					if(((shapes[blockType][turnState][a*4+b]==1)&&(map[x+b+1][y+a]==1))
							||((shapes[blockType][turnState][a*4+b]==1)&&(map[x+b+1][y+a]==2))) {
						return 0;
					}
				}
			}
			
			return 1;
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



	public int gameover(int x, int y ) {
		// TODO Auto-generated method stub
		
		if(blow(x,y,blockType,turnState)==0) {
			return 1;
		}
		return 0;
	}



	


	private int blow(int x2, int y2, int blockType2, int turnState2) {
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
