/**
 * ̹����ϷV6.0
 * 1,��̹�˶�����
 * 2,��̹�˷����ӵ�
 * 3,̹���ӵ�������������෢5�ţ�
 * 4,�����е�̹�˿�����ʧ
 * 5�������е�̹�˿��Ա�ը
 * 6,����̹�˿���������������˶�
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
		// ̹����Ϸ�Ĺ��캯��
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
//	̹����������panel�ڲ��ģ�����̹��Ҫ������mypanel�
	Hero hero = null;
//	��һ�����˵�̹�˼����࣬��ΪҪ�����̰߳�ȫ������ѡ��Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>(); 
	Vector<BaoZha> baozhas = new Vector<BaoZha>();
	int enemySize = 11;
//	���屬ըͼƬ
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	
	public MyPanel() {
		// ���ҵ�panel�ﹹ��һ��̹��
		hero = new Hero(100, 100);
		for(int i=0;i<enemySize;i++){
//			�������˵�̹�ˣ������뵽����̹�˼����С�
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
//	��дpaint����
	public void paint(Graphics g){
		super.paint(g); 
//		float lineWidth = 3.0f;
//	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
//		��̹�˵Ļ�������ΪĬ�Ϻ�ɫ
		g.fillRect(0, 0, 800, 600);
		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirection(),1);

		for(int i=0;i<hero.bombs.size();i++){
			Bomb myBomb = hero.bombs.get(i);
		//����һ���ӵ�
			if(myBomb!=null&&myBomb.isLive==true){
	//			float lineWidth = 2.0f;
	//			((Graphics2D) g).setStroke(new BasicStroke(lineWidth));//��������Ϊ����
				g.draw3DRect(myBomb.x, myBomb.y, 2, 2, true);
			}
			if(myBomb.isLive == false){
				hero.bombs.remove(myBomb);
			}
		}
//		������ը
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
		
		
		//		�������˵�̹��
		for(int i=0;i<ets.size();i++){
			EnemyTank et = ets.get(i);
			if(et.isLive){
				
				this.drawTank(et.getX(), et.getY(), g, et.getDirection(), 0);
			}
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
	
	//�ж�̹���Ƿ񱻻���
	public void isHit(Bomb bomb,EnemyTank et){
//		���˵�̹�����ĸ�����ÿ����������겻ͬ
		switch(et.getDirection()){
//		���Ϻͳ���Ч��һ��
		case 0:
		case 2:
			//�ӵ��������̹�˵������غϼ�Ϊ����
			if(bomb.x>et.getX()&&bomb.x<et.getX()+25&&bomb.y<et.getY()+30&&bomb.y>et.getY()){
				//�ӵ�����������̹������
				bomb.isLive = false;
				et.isLive = false;
				BaoZha bz = new BaoZha(et.getX(), et.getY());
				
				baozhas.add(bz);
			}
//		����̹�˳���������
		case 1:
		case 3:
			if(bomb.x>et.getX()&&bomb.x<et.getX()+30&&bomb.y>et.getY()&&bomb.y<et.getY()+25){
				//�ӵ�����������̹������
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
		//�ж�����Ƿ���J�� �򿪻�
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
//			�ж�ÿһ���ӵ���ÿһ�����˵�̹�˶��Ƿ����غϣ����У�
			for(int i=0;i<hero.bombs.size();i++){
//				ȡ��ÿ���ӵ�
				Bomb myBomb = hero.bombs.get(i);
//				�ӵ�����ô������жϵ�����
				if(myBomb.isLive){
					for(int j=0;j<ets.size();j++){
//						ȡ��ÿ��̹��
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

