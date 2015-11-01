/**
 * ̹����ϷV1.0
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
		// ̹����Ϸ�Ĺ��캯��
		mp = new MyPanel();
		this.add(mp);
		this.setSize(450,350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class MyPanel extends JPanel{
//	̹����������panel�ڲ��ģ�����̹��Ҫ������mypanel�
	Hero hero = null;
	public MyPanel() {
		// ���ҵ�panel�ﹹ��һ��̹��
		hero = new Hero(10, 10);
	}
//	��дpaint����
	public void paint(Graphics g){
		super.paint(g); 
//		��̹�˵Ļ�������ΪĬ�Ϻ�ɫ
		g.fillRect(0, 0, 400, 300);
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
	}
	
//	����̹�˵ĺ���
	public void drawTank(int x,int y,Graphics g,int direction,int type){
//		�ж�̹�����ͣ��ǵ��˵Ļ����ҷ���̹�ˣ��ı�̹�˵���ɫ����
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
			g.drawLine(x+11, y-5, x+11, y+17);
			break;
		}
	}
	
}

//����һ��̹����
class Tank{
//	�����x��y��ʾ̹�˳��ֵģ�λ�ã��������������
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
//	̹�˵Ĺ���
	public Tank(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
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