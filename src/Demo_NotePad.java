
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;


public class Demo_NotePad extends JFrame implements ActionListener{

	JTextArea jta = null;
	
	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Demo_NotePad();

	}

	public Demo_NotePad(){

		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("文件");
		jm1.setMnemonic('F');
		
//		jmi1 = new JMenuItem("打开",new ImageIcon(""));
		jmi1 = new JMenuItem("打开");
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		
		jmi2 = new JMenuItem("保存");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		this.setJMenuBar(jmb);
		jmb.add(jm1);
		jm1.add(jmi1);
		
		jm1.add(jmi2);
		this.add(jta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("open")){
			System.out.println("open");
			JFileChooser jfc1 = new JFileChooser();
			jfc1.setDialogTitle("please select files...");
			jfc1.showOpenDialog(null);//默认方式
			jfc1.setVisible(true);
			
			//得到选择的文件路径
			String filename = jfc1.getSelectedFile().getAbsolutePath();
			System.out.println(filename);
			FileReader fr=null;
			try {
				fr = new FileReader(new File(filename));
				BufferedReader br = new BufferedReader(fr);
				//fw = 
				String temp = "";
				String allStr="";
				while((temp=br.readLine())!=null){
					//jta.setText(temp+"\r\n");
					allStr += temp+"\r\n";
				}
				jta.setText(allStr);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if(e.getActionCommand().equals("save")){
			JFileChooser jfc2 = new JFileChooser();
			jfc2.setDialogTitle("另存为...");
			//按默认方式显示
			jfc2.showSaveDialog(null);
			jfc2.setVisible(true);
			BufferedWriter bw = null;
			String filename = jfc2.getSelectedFile().getAbsolutePath();
			try {
				FileWriter fw = new FileWriter(new File(filename));
			
				bw = new BufferedWriter(fw);
				bw.write(this.jta.getText());//没有设置缓存，文件太大不好
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		}
		
		
	}

}
