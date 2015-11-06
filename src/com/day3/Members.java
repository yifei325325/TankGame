package com.day3;

//����һ��̹����
class Tank{
//	�����x��y��ʾ̹�˳��ֵģ�λ�ã��������������
	private int x = 0;
	private int y = 0;
//	����̹�˵ķ���0��ʾ���ϣ�1��ʾ���ң�2��ʾ���£�3��ʾ����
	private int direction;
	private int speed = 2;
	private int color;
	
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
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

//������˵�̹��
class EnemyTank extends Tank{
	public EnemyTank(int x,int y) {
		super(x, y);
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