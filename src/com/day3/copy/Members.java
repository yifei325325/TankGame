package com.day3.copy;

//定义一个坦克类
class Tank{
//	下面的x和y表示坦克出现的（位置）横坐标和纵坐标
	private int x = 0;
	private int y = 0;
//	定义坦克的方向，0表示向上，1表示向右，2表示向下，3表示向左
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
//	坦克的构造
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

//定义敌人的坦克
class EnemyTank extends Tank{
	public EnemyTank(int x,int y) {
		super(x, y);
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