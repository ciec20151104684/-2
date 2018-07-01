package eluosifangkuai;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
