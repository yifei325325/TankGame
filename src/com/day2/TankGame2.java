/**
 * ̹����ϷV2.0
 * 1,��̹�˶�����
 * 
 */
package com.day2;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankGame2 extends JFrame{

	MyPanel mp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame2 tg = new TankGame2();

	}
	
	public TankGame2() {
		// ̹����Ϸ�Ĺ��캯��
		mp = new MyPanel();
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class MyPanel extends JPanel implements KeyListener{
//	̹����������panel�ڲ��ģ�����̹��Ҫ������mypanel�
	Hero hero = null;
	public MyPanel() {
		// ���ҵ�panel�ﹹ��һ��̹��
		hero = new Hero(100, 100);
	}
//	��дpaint����
	public void paint(Graphics g){
		super.paint(g); 
//		float lineWidth = 3.0f;
//	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
//		��̹�˵Ļ�������ΪĬ�Ϻ�ɫ
		g.fillRect(0, 0, 400, 300);
		this.drawTank(hero.getX(), hero.getY(), g, hero.getDirection(),1);
		
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
			g.drawLine(x+11, y-5, x+11, y+17);
			break;
		case 3:
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
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

//����һ��̹����
class Tank{
//	�����x��y��ʾ̹�˳��ֵģ�λ�ã��������������
	private int x = 0;
	private int y = 0;
//	����̹�˵ķ���0��ʾ���ϣ�1��ʾ���ң�2��ʾ���£�3��ʾ����
	private int speed = 1;
	
	private int direction;
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
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
//	̹�˵Ĺ���
	public Tank(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	
	public void moveUp(){
		y=y-speed;
	}
	public void moveRight(){
		x=x+speed;
	}
	public void moveDown(){
		y=y+speed;
	}
	public void moveLeft(){
		x=x-speed;
	}
	
	
}
//�����ҵ�̹����̳���̹����
class Hero extends Tank{
//	Hero�Ĺ��캯��
	public Hero(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
	}
	
}