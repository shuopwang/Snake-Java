import java.awt.Frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Yard extends Frame{
	

	public static final int ROWS=50,COLS=50,BLOCK_SIZE=10;
	Snake s=new Snake();
	Egg e=new Egg();
	Image offScreenImage=null;//防止刷新过快，建立双缓冲
	
	public void launch ()
	{
		this.setLocation(200,200);//将图像出现在（200，200）的位置上
		this.setSize(ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);//规定窗口大小
		this.addWindowListener(new WindowAdapter()//增加窗口关闭功能

				{
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}});
		this.setTitle("Snake Game 1.0   By Shuopeng WANG");
		this.setVisible(true);//令窗口可视
		this.addKeyListener(new keymonitor());//监听键盘事件
		new Thread(new PaintThread()).start();//在窗口里启动线程
	}
	
	public static void main(String[] args) {
		new Yard().launch();
	}
	@Override
	public void update(Graphics g) {
		
			if(offScreenImage==null)
				offScreenImage=this.createImage( ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
			Graphics goff=offScreenImage.getGraphics();
			paint(goff);
			g.drawImage(offScreenImage,0,0,null);
		
	}
	
	@Override
	public void paint(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.BLACK);//设置窗口环境颜色
		g.fillRect(0,0,ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);//填充自左上角（0，0）至（w、h,是矩形区域的宽和高）的矩阵区域
		g.setColor(c);
		e.draw(g);
		s.draw(g);
		s.eat(e);
	}
	
	private class PaintThread implements Runnable
	{

		@Override
		public void run() {
			while(s.flag){
			repaint();
			try{
				Thread.sleep(100);
			}
			catch(InterruptedException e){
				e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
				}
			}
		}
		
	}

	public class keymonitor extends KeyAdapter
	{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keypressed(e);
			
		}
		
	}
	
	
}
