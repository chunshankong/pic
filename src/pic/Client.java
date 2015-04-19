package pic;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Client extends Frame
{
	static final int IMAGE_WIDTH = 800 ;
	static final int IMAGE_HIGHT = 600;
	int Index = 1;
	String Src = "";
	String Sub = "";
	static ImageCut imgCut;//图片剪切器
	static ImageCut imgCut2;//图片剪切器
	boolean cutcross = false;//默认为纵向分割
	public static void main(String args[]) throws Exception
	{	
		imgCut = ImageCut.getInstance();
		new Client().launchFrame();
	}
	public void begin() throws Exception
	{
		imgCut2 = ImageCut.getInstance();
		System.out.println();
		 for(int i = 0;i<Index;i++)
		{
			imgCut2.start(Index,Src,Src,cutcross);
		}
		
	}
	class Panel_Img extends Panel
	{
		public void paint(Graphics g)
		{
			//super.paint(g); 
			if(0 != Src.length()) //若选择了图片则绘制
			{
				try {
					//读取并显示图片
					BufferedImage img = ImageIO.read(new File(Src));
					g.drawImage(img, 0, 0, IMAGE_WIDTH, IMAGE_HIGHT, null);
					g.setColor(Color.RED);
					
					if(!cutcross)//绘制纵向分割线
					{
						
						int cutwidth = IMAGE_WIDTH/Index;
						int local = 0; 
						for(int i = 0 ;i<Index;i++)
						{
							g.drawRect(local, 0, cutwidth, IMAGE_HIGHT);
							local += cutwidth;
						} 
						
					}
					else //绘制横向分割线
					{
						int cuthight = IMAGE_HIGHT/Index;
						int local = 0; 
						for(int i = 0 ;i<Index;i++)
						{
							g.drawRect(0, local, IMAGE_WIDTH, cuthight);
							local += cuthight;
						} 
						
					}
					
					 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//g.setColor(Color.YELLOW); 
				//g.fillRect(50, 50, 500, 500);
				
			} 
			
		}
		/*public void update(Graphics g)
		{
			System.out.println("ok");
			paint(g); 
			g.drawString("ddddddddddddddddddddddddd", 500, 500);
			g.fillRect(50, 50, 500, 500);
		}*/  
	} 
	Panel panel_cpn ;
	Panel_Img  panel_img ; 
	TextField t1,t2,t3;
	
	public void launchFrame()
	{
		//panel_cpn中的组件
 	 t1 = new TextField(100);//输入框
 	 t2 = new TextField(10); 
 	 t1.setFont(new Font("楷体",Font.PLAIN,18));
 	 t2.setFont(new Font("楷体",Font.PLAIN,18));
 	 Label lb = new Label("图片路径"); //静态文本
 	 Label lb2 = new Label("分割份数"); //静态文本
 	 lb.setFont(new Font("楷体",Font.PLAIN,18));
 	 lb2.setFont(new Font("楷体",Font.PLAIN,18));
 	 
	 Button bstart = new Button("开始");
	 bstart.addActionListener(new Mymonitor_start(this));//给button添加动作监听同时给监听器自己的引用
 	 Button bchooser = new Button("选择...");
 	 bchooser.addActionListener(new Mymonitor_chooser(this));//给button添加动作监听同时给监听器自己的引用 
 	 Button bcross = new Button("横向");
 	 bcross.addActionListener(new Mymonitor_cross(this));
 	 Button bvertical = new Button("纵向");
 	 bvertical.addActionListener(new Mymonitor_vertical(this));
 	 
 	 lb.setBounds(20, 30, 80, 30);
 	 t1.setBounds(100, 30, 600, 30);
 	 bchooser.setBounds(710, 30, 100, 30);
 	 lb2.setBounds(20, 100, 80, 30);
 	 t2.setBounds(100, 100, 100, 30); 
 	 bcross.setBounds(220, 100, 100, 30); 
 	 bvertical.setBounds(320, 100, 100, 30);
 	 bstart.setBounds(710, 100, 100, 30); 
 	 
	 //panel_cpn
 	 panel_cpn = new Panel(); 
	 panel_cpn.add(lb);panel_cpn.add(t1);panel_cpn.add(bchooser);	panel_cpn.add(lb2);panel_cpn.add(t2); panel_cpn.add(bstart);panel_cpn.add(bcross);panel_cpn.add(bvertical); 
		 
	 
	 panel_cpn.setBounds(0, 0, 800, 150);	 
	 panel_cpn.setLayout(null);
	 
	 //panel_img 
	 panel_img = new Panel_Img(); 
	// panel_img.setBackground(Color.black);
	 panel_img.setBounds(0, 150, IMAGE_WIDTH, IMAGE_HIGHT);	 
	 panel_img.setLayout(null);
	 
	 this.add(panel_cpn);this.add(panel_img);
	 this.setLayout(null);
	 this.setSize(800, 750);
	 setResizable(false);//不改变窗口大小
	 this.addWindowListener(new WDmonitor());
		
	 try//设置程序为系统风格
	{
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	}
	 
	catch(Exception e)
	{
		e.printStackTrace();
	} 
	 setVisible(true);
	}
	
	class Mymonitor_start implements ActionListener
	{
		Client tf; //构造器接受TFrame对象引用
		Mymonitor_start(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)///!!!! 
		{   
			String Sdex = tf.t2.getText(); 
			if(0 == Sdex.length()||0 == Src.length()) //若没有输入则返回
			{return;}
			else
			{
				Index = Integer.parseInt(Sdex); 
				 try {
						begin();
						imgCut.setX(0);//一组剪切操作后，重置剪切开始的位置
						imgCut.setY(0);//一组剪切操作后，重置剪切开始的位置
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				System.out.println(Src);
				System.out.println(Index);
				panel_img.repaint();
			}
			
		}
	}
	class Mymonitor_chooser implements ActionListener
	{
		Client tf; //构造器接受TFrame对象引用
		Mymonitor_chooser(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)///!!!! 
		{     
			JFileChooser chooser = new JFileChooser();
			chooser.getFileSystemView();
			chooser.setFileView(chooser.getFileView());
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(tf);
			
			//若选择了文件
			if(returnVal == JFileChooser.APPROVE_OPTION) {  
				Src = chooser.getSelectedFile().getPath();    
				System.out.println("You chose to open this file: " +Src); 
				tf.t1.setText(Src);
				panel_img.repaint();
				 
				
				
			}
		

		}
	}
	class Mymonitor_cross implements ActionListener
	{
		Client tf; //构造器接受TFrame对象引用
		Mymonitor_cross(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)//设置剪裁方式和数量
		{   
			String Sdex = tf.t2.getText(); 
			if(0 == Sdex.length()||0 == Src.length()) //若没有输入则返回
			{return;}
			else
			{
				cutcross = true;
				Index = Integer.parseInt(Sdex); 
				System.out.println(Index);
				panel_img.repaint();
			}
			
		}
	}
	class Mymonitor_vertical implements ActionListener
	{
		Client tf; //构造器接受TFrame对象引用
		Mymonitor_vertical(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)//设置剪裁方式和数量
		{   
			String Sdex = tf.t2.getText(); 
			if(0 == Sdex.length()||0 == Src.length()) //若没有输入则返回
			{return;}
			else
			{
				cutcross = false;
				Index = Integer.parseInt(Sdex);  
				System.out.println(Index);
				panel_img.repaint();
			}
			
		}
	}
	class WDmonitor extends WindowAdapter//监听Window事件的内部类
	{
		public void windowClosing(WindowEvent e) //当监听的窗口正在被关闭会调用此方法
		{
			setVisible(false);
			System.exit(0);
		}
	}

}
