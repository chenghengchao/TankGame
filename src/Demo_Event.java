import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 事件处理
 */
public class Demo_Event extends JFrame implements ActionListener{
	ThePanel tp = null;
	JButton jb1 = null;
	JButton jb2 = null;
	public Demo_Event(){
		tp = new ThePanel();
		jb1 = new JButton("黑色");
		jb2 = new JButton("红色");
		
		this.add(jb1,BorderLayout.NORTH);
		tp.setBackground(Color.black);
		this.add(tp);
		this.add(jb2,BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb1.setActionCommand("aa");
		jb2.setActionCommand("bb");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200,150);
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_Event dk = new Demo_Event();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("aa")){
			tp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("bb")){
			tp.setBackground(Color.RED);
		}
		
	}

}

class ThePanel extends JPanel{
	public void paint(Graphics g){
		super.paint(g);
		
//		g.drawOval(10, 10, 10, 10);
		
	}
	
}
