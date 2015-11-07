/**
 * ̹����ϷV3.0
 * 1,��̹�˶�����
 * 2,��̹�˷����ӵ�
 * 
 */
package com.day4;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TankGame3 extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame3 tg = new TankGame3();

	}
	
	public TankGame3() {
		// ̹����Ϸ�Ĺ��캯��
		mp = new MyPanel();
		Thread t = new Thread(mp);
		t.start();
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class MyPanel extends JPanel implements KeyListener,Runnable{
//	̹����������panel�ڲ��ģ�����̹��Ҫ������mypanel�
	Hero hero = null;
//	��һ�����˵�̹�˼����࣬��ΪҪ�����̰߳�ȫ������ѡ��Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>(); 
	int enemySize = 3;
	
	public MyPanel() {
		// ���ҵ�panel�ﹹ��һ��̹��
		hero = new Hero(100, 100);
		for(int i=0;i<enemySize;i++){
//			�������˵�̹�ˣ������뵽����̹�˼����С�
			EnemyTank et = new EnemyTank((i+1)*50, 0);
			et.setColor(0);
			et.setDirection(2);
			ets.addElement(et);
			
		}
	}
//	��дpaint����
	public void paint(Graphics g){
		super.paint(g); 
//		float lineWidth = 3.0f;
//	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
//		��̹�˵Ļ�������ΪĬ�Ϻ�ɫ
		g.fillRect(0, 0, 400, 300);
		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirection(),1);
//�����ӵ�
		if(hero.bomb!=null&&hero.bomb.isLive==true){
//			float lineWidth = 2.0f;
//			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//��������Ϊ����
			g.draw3DRect(hero.bomb.x, hero.bomb.y, 2, 2, true);
		}
		
		//		�������˵�̹��
		for(int i=0;i<ets.size();i++){
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirection(), 0);
		}
		
	}
	
//	����̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int direction,int type){
//		�ж�̹�����ͣ��ǵ��˵Ļ����ҷ���̹�ˣ��ı�̹�˵���ɫ����
		float lineWidth = 3.0f;//��������Ϊ����
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
//			1.��������Ĵ�
			g.fill3DRect(x, y, 5, 30, true);
//			2.�����ұ��Ĵ�
			g.fill3DRect(x+20, y, 5, 30, true);
//			3.�����м����
			g.fill3DRect(x+5, y+5, 15, 20, false);
//			4.�����м�Բ��
			g.fillOval(x+5, y+7, 12, 12);
//			5.������Ͳ
		    ((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//��������Ϊ����
			g.drawLine(x+11, y-5, x+11, y+17);
			break;
		case 1:
//			1.��������Ĵ�
			g.fill3DRect(x, y, 30, 5, true);
//			2.�����ұ��Ĵ�
			g.fill3DRect(x, y+20, 30, 5, true);
//			3.�����м����
			g.fill3DRect(x+5, y+5, 20, 15, false);
//			4.�����м�Բ��
			g.fillOval(x+7, y+5, 12, 12);
//			5.������Ͳ
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//��������Ϊ����
			g.drawLine(x+17, y+11,x+32, y+11);
			break;
		case 2:
//			1.��������Ĵ�
			g.fill3DRect(x, y, 5, 30, true);
//			2.�����ұ��Ĵ�
			g.fill3DRect(x+20, y, 5, 30, true);
//			3.�����м����
			g.fill3DRect(x+5, y+5, 15, 20, false);
//			4.�����м�Բ��
			g.fillOval(x+5, y+7, 12, 12);
//			5.������Ͳ
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//��������Ϊ����
			g.drawLine(x+11, y+17,x+11, y+32 );
			break;
		case 3:
//			1.��������Ĵ�
			g.fill3DRect(x, y, 30, 5, true);
//			2.�����ұ��Ĵ�
			g.fill3DRect(x, y+20, 30, 5, true);
//			3.�����м����
			g.fill3DRect(x+5, y+5, 20, 15, false);
//			4.�����м�Բ��
			g.fillOval(x+7, y+5, 12, 12);
//			5.������Ͳ
			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//��������Ϊ����
			g.drawLine(x+17, y+11,x-5, y+11);
			break;
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
		//�ж�����Ƿ���J�� �򿪻�
		if(e.getKeyCode()==KeyEvent.VK_J){
			hero.fire();
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
			this.repaint();
		}
	}
	
}

