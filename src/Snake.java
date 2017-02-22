import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Snake {
	private Node head=null,tail=null;
	public int size=0;
	private Node n=new Node(20,20,Dir.L);
	public Boolean flag=true;
	public Snake(){
		this.head=n;
		this.tail=n;
		this.size=1;
	
	}
	public void addtail(){
		Node node =null;
		switch(tail.dir){
		case L:
			node=new Node(tail.row,tail.col+1,tail.dir);break;
		case U:
			node=new Node(tail.row+1,tail.col,tail.dir);break;
		case R:
			node=new Node(tail.row,tail.col-1,tail.dir);break;
		case D:
			node=new Node(tail.row-1,tail.col,tail.dir);break;
		}
		tail.next=node;node.prev=tail;
		tail=node;
		size++;
	}
	public void addtohead()
	{
		Node node=null;
		switch(head.dir){
		case L:
			node=new Node(head.row,head.col-1,head.dir);break;
		case U:
			node=new Node(head.row-1,head.col,head.dir);break;
		case R:
			node=new Node(head.row,head.col+1,head.dir);break;
		case D:
			node=new Node(head.row+1,head.col,head.dir);break;
		}
		node.next=head;head.prev=node;
		head=node;
		size++;
	}
	public void draw(Graphics g)
	{	
		if (size<=0) return;
		for(Node n=head;n!=null;n=n.next)
		{
			n.draw(g);
		}
		move();
	}	
	private void move() {
		addtohead();
		deltail();
		checkdead();
	}
	
	private void checkdead() {
		if(head.row<2||head.col<0||head.row>Yard.ROWS||head.col>Yard.COLS)
		{
			flag=false;
			end e=new end(this);
		}
		/*Node n=head.next;
		for(;n!=null;n=n.next)
		{
			if (head.row==n.row||head.col==n.col)
			{flag=false;
			end e=new end(this);}
		}*/
	
	}
	public void eat(Egg e)
	{
		if (this.getRect().intersects(e.getRect()))
		{
			e.reshow();
			this.addtohead();
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(Yard.BLOCK_SIZE*head.col, Yard.BLOCK_SIZE*head.row,head.h,head.w);
	}
	
	
	
	
	private void deltail() {
		if(size==0) return;
		tail=tail.prev;
		tail.next=null;
		size--;
	}
	public void keypressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			if(head.dir !=Dir.R)
			head.dir=Dir.L;break;
		case KeyEvent.VK_UP:
			if(head.dir !=Dir.D)
			head.dir=Dir.U;break;
		case KeyEvent.VK_RIGHT:
			if(head.dir !=Dir.L)
			head.dir=Dir.R;break;
		case KeyEvent.VK_DOWN:
			if(head.dir !=Dir.U)
			head.dir=Dir.D;break;
		}
		
		
	}
	
 	private class Node{
		
		int w=Yard.BLOCK_SIZE,h=Yard.BLOCK_SIZE;//每个node的宽度和高度
		int row,col;//node所处的位置
		Node next=null;
		Node prev=null;
		Dir dir=Dir.L;
		public Node(int row, int col,Dir dir) {
			this.row = row;
			this.col = col;
			this.dir=dir;
		}
		
		
		
		public boolean col(int i) {
			// TODO Auto-generated method stub
			return false;
		}



		void draw (Graphics g){
			Color c=g.getColor();
			g.setColor(Color.WHITE);
			g.fillRect(Yard.BLOCK_SIZE*col, Yard.BLOCK_SIZE*row, w, h);//画出一个正方形的node
			
			
			g.setColor(c);
		}

		
	}
	
	
}
