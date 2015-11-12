package com.day7;

import java.util.Vector;
class BaoZha{
	int x;
	int y;
	int life = 6;
	boolean isLive = true;
	public BaoZha(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	public void liftDown(){
		if(life>0){
			life--;
		}else{
			this.isLive = false;
		}
	}
	
}

//定义子弹类
class Bomb implements Runnable{
	int x;
	int y;
	int direct;
	int speed = 9;
	boolean isLive=true;
	public Bomb(int x,int y,int direct) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	public void run(){
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (this.direct) {
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;
			}
			
			System.out.println("x="+x+",y="+y);
			//子弹何时死亡？
			if(x<0||y<0||x>800||y>600){
				this.isLive = false;
				break;
			}
		}
		
	}
}



//定义一个坦克类
class Tank{
//	下面的x和y表示坦克出现的（位置）横坐标和纵坐标
	private int x = 0;
	private int y = 0;
//	定义坦克的方向，0表示向上，1表示向右，2表示向下，3表示向左
	private int direction;
	private int speed = 5;
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
	
public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
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
class EnemyTank extends Tank implements Runnable{
	boolean isLive = true;
	public EnemyTank(int x,int y) {
		super(x, y);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			switch (this.getDirection()) {
//			向上
			case 0:
				for(int i=0;i<30;i++){
					if(this.getY()>0){
						this.setY(this.getY()-this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
//			向右
			case 1:
				
				for(int i=0;i<30;i++){
					if(this.getX()<800-40){
						this.setX(this.getX()+this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
//			向下
			case 2:
				for(int i=0;i<30;i++){
					if(this.getY()<600-60){
						this.setY(this.getY()+this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
//			向左
			case 3:
				for(int i=0;i<30;i++){
					if(this.getX()>0){
						this.setX(this.getX()-this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			
		}
			this.setDirection((int)(Math.random()*4));
			if(this.isLive == false){
				break;
			}

		
		}
		
	}
}


//定义我的坦克类继承自坦克类
class Hero extends Tank{
	//子弹是从Hero里发出的，所以子弹应该是Hero的一个成员变量
	Vector<Bomb> bombs = new Vector<Bomb>();
	Bomb bomb = null;
	
//	Hero的构造函数
	public Hero(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
	}
	
	public void fire(){
		switch (this.getDirection()) {
		case 0:
			bomb = new Bomb(this.getX()+10, this.getY()-10,0);
			bombs.add(bomb);
			break;
		case 1:
			bomb = new Bomb(this.getX()+35, this.getY()+10,1);
			bombs.add(bomb);
			break;
		case 2:
			bomb = new Bomb(this.getX()+10, this.getY()+35,2);
			bombs.add(bomb);
			break;
		case 3:
			bomb = new Bomb(this.getX()-10, this.getY()+10,3);
			bombs.add(bomb);
			break;
		}
		Thread t = new Thread(bomb);
		t.start();
	}
	
}