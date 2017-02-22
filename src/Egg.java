import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int row,col;
	int w=Yard.BLOCK_SIZE,h=Yard.BLOCK_SIZE;
	private static Random r=new Random();
	
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public void reshow()
	{
		this.row=r.nextInt(Yard.ROWS);
		this.col=r.nextInt(Yard.COLS);
	}
	
	public Egg()
	{
		this(r.nextInt(Yard.ROWS),r.nextInt(Yard.COLS));
	}
	
	public Rectangle getRect(){
		return new Rectangle(Yard.BLOCK_SIZE*col, Yard.BLOCK_SIZE*row,h,w);
	}
	
	void draw (Graphics g){
		Color c=g.getColor();
		g.setColor(Color.GRAY);
		g.fillOval(Yard.BLOCK_SIZE*col, Yard.BLOCK_SIZE*row, w, h);//画出一个圆形的东西
		
		
		g.setColor(c);
	}
}
