package com.chc.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//��¼���࣬ͬʱ������ҵ�һЩ����
public class Recoder {
	//��¼ÿһ���ж��ٵ���
	private static int enNum = 20;
	//�����м�����
	private static int myLife = 3;
	
	private static int allEnNum = 0;
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static FileReader fr = null;
	private static BufferedReader br = null;
	private  Vector<Enemy> ets = new Vector<Enemy>();
	
	//�ָ�ʱ��ļ�¼��
	static Vector<Node> nodes = new Vector<Node>();

	//��ɶ�ȡ����
	public void getNodeAndEnNums(){
		try {
			fr = new FileReader("D:\\recorder.txt");
			br = new BufferedReader(fr);
			String n = br.readLine();
			allEnNum = Integer.parseInt(n);
		
//			String n = "";
			while((n=br.readLine())!=null){
				String []point = n.split(" ");
				for(int i =0;i<point.length;i++){
					Node node = new Node(Integer.parseInt(point[0]),Integer.parseInt(point[1]),Integer.parseInt(point[2]));			
					nodes.add(node);
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//����һ���̹���������浽�ļ���
	public static void keepRec(){
		try {
			fw = new FileWriter("D:\\recorder.txt");
			bw = new BufferedWriter(fw);
			bw.write(allEnNum+"\r\n");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			�ȿ��ĺ�ر�
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//���ļ��ж�ȡ��Ϸ��¼
	public static void getRec(){
		try {
			fr = new FileReader("D:\\recorder.txt");
			br = new BufferedReader(fr);
			String n = br.readLine();
			allEnNum = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//������ٵ��˵������͵���̹�����꣬����
	public  void keepRecAndEnemy(){
		try {
			fw = new FileWriter("D:\\recorder.txt");
			bw = new BufferedWriter(fw);
			//��һ�У����˵�̹�˱������˶���
			bw.write(allEnNum+"\r\n");
			//���浱ǰ���ŵĵ��˵�����ͷ���
			for(Enemy e:ets){
				if(e.isLive){
					String record = e.getX()+" "+e.getY()+" "+e.getDerect()+"";
				
					bw.write(record+"\r\n");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			�ȿ��ĺ�ر�
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recoder.allEnNum = allEnNum;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recoder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recoder.myLife = myLife;
	}
	//��������
	public static void reduceEnNum(){
		enNum--;
	}
	public static void addEnNumRec(){
		allEnNum++;
	}
	public static void reduceMyNum(){
		myLife--;
	}
	public  Vector<Enemy> getEts() {
		return ets;
	}
	public  void setEts(Vector<Enemy> ets) {
		this.ets = ets;
	}
}
