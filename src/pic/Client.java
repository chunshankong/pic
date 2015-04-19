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
	static ImageCut imgCut;//ͼƬ������
	static ImageCut imgCut2;//ͼƬ������
	boolean cutcross = false;//Ĭ��Ϊ����ָ�
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
			if(0 != Src.length()) //��ѡ����ͼƬ�����
			{
				try {
					//��ȡ����ʾͼƬ
					BufferedImage img = ImageIO.read(new File(Src));
					g.drawImage(img, 0, 0, IMAGE_WIDTH, IMAGE_HIGHT, null);
					g.setColor(Color.RED);
					
					if(!cutcross)//��������ָ���
					{
						
						int cutwidth = IMAGE_WIDTH/Index;
						int local = 0; 
						for(int i = 0 ;i<Index;i++)
						{
							g.drawRect(local, 0, cutwidth, IMAGE_HIGHT);
							local += cutwidth;
						} 
						
					}
					else //���ƺ���ָ���
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
		//panel_cpn�е����
 	 t1 = new TextField(100);//�����
 	 t2 = new TextField(10); 
 	 t1.setFont(new Font("����",Font.PLAIN,18));
 	 t2.setFont(new Font("����",Font.PLAIN,18));
 	 Label lb = new Label("ͼƬ·��"); //��̬�ı�
 	 Label lb2 = new Label("�ָ����"); //��̬�ı�
 	 lb.setFont(new Font("����",Font.PLAIN,18));
 	 lb2.setFont(new Font("����",Font.PLAIN,18));
 	 
	 Button bstart = new Button("��ʼ");
	 bstart.addActionListener(new Mymonitor_start(this));//��button��Ӷ�������ͬʱ���������Լ�������
 	 Button bchooser = new Button("ѡ��...");
 	 bchooser.addActionListener(new Mymonitor_chooser(this));//��button��Ӷ�������ͬʱ���������Լ������� 
 	 Button bcross = new Button("����");
 	 bcross.addActionListener(new Mymonitor_cross(this));
 	 Button bvertical = new Button("����");
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
	 setResizable(false);//���ı䴰�ڴ�С
	 this.addWindowListener(new WDmonitor());
		
	 try//���ó���Ϊϵͳ���
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
		Client tf; //����������TFrame��������
		Mymonitor_start(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)///!!!! 
		{   
			String Sdex = tf.t2.getText(); 
			if(0 == Sdex.length()||0 == Src.length()) //��û�������򷵻�
			{return;}
			else
			{
				Index = Integer.parseInt(Sdex); 
				 try {
						begin();
						imgCut.setX(0);//һ����в��������ü��п�ʼ��λ��
						imgCut.setY(0);//һ����в��������ü��п�ʼ��λ��
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
		Client tf; //����������TFrame��������
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
			
			//��ѡ�����ļ�
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
		Client tf; //����������TFrame��������
		Mymonitor_cross(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)//���ü��÷�ʽ������
		{   
			String Sdex = tf.t2.getText(); 
			if(0 == Sdex.length()||0 == Src.length()) //��û�������򷵻�
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
		Client tf; //����������TFrame��������
		Mymonitor_vertical(Client tf)
		{
			this.tf = tf;
		}
		public void actionPerformed(ActionEvent e)//���ü��÷�ʽ������
		{   
			String Sdex = tf.t2.getText(); 
			if(0 == Sdex.length()||0 == Src.length()) //��û�������򷵻�
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
	class WDmonitor extends WindowAdapter//����Window�¼����ڲ���
	{
		public void windowClosing(WindowEvent e) //�������Ĵ������ڱ��رջ���ô˷���
		{
			setVisible(false);
			System.exit(0);
		}
	}

}
