/**
 * 坦克游戏V1.0
 */
package com.day1;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.*;

public class TankGame extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame tg = new TankGame();

	}
	
	public TankGame() {
		// 坦克游戏的构造函数
		mp = new MyPanel();
		this.add(mp);
		this.setSize(450,350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class MyPanel extends JPanel{
//	坦克是生活在panel内部的，所以坦克要定义在mypanel里。
	Hero hero = null;
	public MyPanel() {
		// 在我的panel里构造一个坦克
		hero = new Hero(10, 10);
	}
//	重写paint方法
	public void paint(Graphics g){
		super.paint(g); 
//		将坦克的活动区域填充为默认黑色
		g.fillRect(0, 0, 400, 300);
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
	}
	
//	画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direction,int type){
//		判断坦克类型，是敌人的还是我方的坦克，改变坦克的颜色区分
		switch (type) {
		case 0:
			g.setColor(Color.YELLOW);
			break;

		case 1:
			g.setColor(Color.red);
			break;
		}
		
		switch (direction) {
		case 0:
//			1.画出左边履带
			g.fill3DRect(x, y, 5, 30, true);
//			2.画出右边履带
			g.fill3DRect(x+20, y, 5, 30, true);
//			3.画出中间机身
			g.fill3DRect(x+5, y+5, 15, 20, false);
//			4.画出中间圆形
			g.fillOval(x+5, y+7, 12, 12);
//			5.画出炮筒
			g.drawLine(x+11, y-5, x+11, y+17);
			break;
		}
	}
	
}

//定义一个坦克类
class Tank{
//	下面的x和y表示坦克出现的（位置）横坐标和纵坐标
	private int x = 0;
	private int y = 0;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
//	坦克的构造
	public Tank(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
}
//定义我的坦克类继承自坦克类
class Hero extends Tank{
//	Hero的构造函数
	public Hero(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
	}
	
}