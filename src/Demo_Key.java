import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//JFrame ���¼�Դ Panel �Ǽ�����
public class Demo_Key extends JFrame {

	ThePanel1 tp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Demo_Key();
	}
	public Demo_Key(){
		tp = new ThePanel1();
		this.add(tp);
		
		this.addKeyListener(tp);//ʵ���˽ӿڲ���Ϊ�߱���������
		this.setVisible(true);
		this.setSize(400,300);
	}

}

class ThePanel1 extends JPanel implements KeyListener{
	int x=10;
	int y=10;
	public void paint(Graphics g){
		super.paint(g);
		g.drawOval(x, y, 10, 10);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//���£���ֵ��
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			y+=5;
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			y--;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			x--;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			x++;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}