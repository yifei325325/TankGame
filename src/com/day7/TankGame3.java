/**
 * 坦克游戏V6.0
 * 1,让坦克动起来
 * 2,让坦克发射子弹
 * 3,坦克子弹可以连发（最多发5颗）
 * 4,被击中的坦克可以消失
 * 5，被击中的坦克可以爆炸
 * 6,敌人坦克可以在区域内随机运动
 */
package com.day7;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.time.Year;
import java.util.*;

public class TankGame3 extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame3 tg = new TankGame3();

	}
	
	public TankGame3() {
		// 坦克游戏的构造函数
		mp = new MyPanel();
		Thread t = new Thread(mp);
		t.start();
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class MyPanel extends JPanel implements KeyListener,Runnable{
//	坦克是生活在panel内部的，所以坦克要定义在mypanel里。
	Hero hero = null;
//	定一个敌人的坦克集合类，因为要考虑线程安全，所以选用Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>(); 
	Vector<BaoZha> baozhas = new Vector<BaoZha>();
	int enemySize = 11;
//	定义爆炸图片
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	
	public MyPanel() {
		// 在我的panel里构造一个坦克
		hero = new Hero(100, 100);
		for(int i=0;i<enemySize;i++){
//			创建敌人的坦克，并加入到敌人坦克集合中。
			EnemyTank et = new EnemyTank((i+1)*50, 0);
			et.setColor(0);
			et.setDirection(2);
			Thread t = new Thread(et);
			t.start();
			ets.addElement(et);
			
		}
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
}
//	重写paint方法
	public void paint(Graphics g){
		super.paint(g); 
//		float lineWidth = 3.0f;
//	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
//		将坦克的活动区域填充为默认黑色
		g.fillRect(0, 0, 800, 600);
		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirection(),1);

		for(int i=0;i<hero.bombs.size();i++){
			Bomb myBomb = hero.bombs.get(i);
		//画出一颗子弹
			if(myBomb!=null&&myBomb.isLive==true){
	//			float lineWidth = 2.0f;
	//			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//设置线条为粗线
				g.draw3DRect(myBomb.x, myBomb.y, 2, 2, true);
			}
			if(myBomb.isLive == false){
				hero.bombs.remove(myBomb);
			}
		}
//		画出爆炸
		for(int i=0;i<baozhas.size();i++){
			BaoZha bz = baozhas.get(i);
			System.out.println("baozhas.size()= "+baozhas.size());
			if(bz.life>5){
				g.drawImage(image3, bz.x, bz.y, 30,30,this);
			}else if(bz.life>3){
				g.drawImage(image2, bz.x, bz.y, 30,30,this);
			}else {
				g.drawImage(image1, bz.x, bz.y, 30,30,this);
			}
			bz.liftDown();
			if(bz.life==0){
				baozhas.remove(bz);
			}
			
		}
		
		
		//		画出敌人的坦克
		for(int i=0;i<ets.size();i++){
			EnemyTank et = ets.get(i);
			if(et.isLive){
				
				this.drawTank(et.getX(), et.getY(), g, et.getDirection(), 0);
			}
		}
		
	}
	
//	画出坦克的函数
	public void drawTank(int x,int y,Graphics g,int direction,int type){
//		判断坦克类型，是敌人的还是我方的坦克，改变坦克的颜色区分
		float lineWidth = 3.0f;//设置线条为粗线
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
		    ((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//设置线条为粗线
			g.drawLine(x+11, y-5, x+11, y+17);
			break;
		case 1:
//			1.画出左边履带
			g.fill3DRect(x, y, 30, 5, true);
//			2.画出右边履带
			g.fill3DRect(x, y+20, 30, 5, true);
//			3.画出中间机身
			g.fill3DRect(x+5, y+5, 20, 15, false);
//			4.画出中间圆形
			g.fillOval(x+7, y+5, 12, 12);
//			5.画出炮筒
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//设置线条为粗线
			g.drawLine(x+17, y+11,x+32, y+11);
			break;
		case 2:
//			1.画出左边履带
			g.fill3DRect(x, y, 5, 30, true);
//			2.画出右边履带
			g.fill3DRect(x+20, y, 5, 30, true);
//			3.画出中间机身
			g.fill3DRect(x+5, y+5, 15, 20, false);
//			4.画出中间圆形
			g.fillOval(x+5, y+7, 12, 12);
//			5.画出炮筒
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//设置线条为粗线
			g.drawLine(x+11, y+17,x+11, y+32 );
			break;
		case 3:
//			1.画出左边履带
			g.fill3DRect(x, y, 30, 5, true);
//			2.画出右边履带
			g.fill3DRect(x, y+20, 30, 5, true);
//			3.画出中间机身
			g.fill3DRect(x+5, y+5, 20, 15, false);
//			4.画出中间圆形
			g.fillOval(x+7, y+5, 12, 12);
//			5.画出炮筒
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//设置线条为粗线
			g.drawLine(x+17, y+11,x-5, y+11);
			break;
		}
	}
	
	//判断坦克是否被击中
	public void isHit(Bomb bomb,EnemyTank et){
//		敌人的坦克有四个方向，每个方向的坐标不同
		switch(et.getDirection()){
//		朝上和朝下效果一样
		case 0:
		case 2:
			//子弹的坐标和坦克的坐标重合即为击中
			if(bomb.x>et.getX()&&bomb.x<et.getX()+25&&bomb.y<et.getY()+30&&bomb.y>et.getY()){
				//子弹死亡，敌人坦克死亡
				bomb.isLive = false;
				et.isLive = false;
				BaoZha bz = new BaoZha(et.getX(), et.getY());
				
				baozhas.add(bz);
			}
//		敌人坦克朝左右两边
		case 1:
		case 3:
			if(bomb.x>et.getX()&&bomb.x<et.getX()+30&&bomb.y>et.getY()&&bomb.y<et.getY()+25){
				//子弹死亡，敌人坦克死亡
				bomb.isLive = false;
				et.isLive = false;
				BaoZha bz = new BaoZha(et.getX(), et.getY());
				baozhas.add(bz);
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W){
			this.hero.setDirection(0);
			hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			this.hero.setDirection(1);
			hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			this.hero.setDirection(2);
			hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			this.hero.setDirection(3);
			hero.moveLeft();
		}
		//判断玩家是否按下J键 则开火
		if(e.getKeyCode()==KeyEvent.VK_J){
			if(this.hero.bombs.size()<=40){
				
				hero.fire();
			}
		}
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			判断每一粒子弹和每一辆敌人的坦克都是否有重合（击中）
			for(int i=0;i<hero.bombs.size();i++){
//				取出每个子弹
				Bomb myBomb = hero.bombs.get(i);
//				子弹必须得存活才有判断的意义
				if(myBomb.isLive){
					for(int j=0;j<ets.size();j++){
//						取出每辆坦克
						EnemyTank et = ets.get(j);
						if(et.isLive){
							this.isHit(myBomb, et);
						}
						
					}
				}
			}
			
			this.repaint();
		}
	}
	
}

