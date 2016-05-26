package com.chc.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

//记录；类，同时保存玩家的一些设置
public class Recoder {
	//记录每一关有多少敌人
	private static int enNum = 20;
	//设置有几条命
	private static int myLife = 3;
	
	private static int allEnNum = 0;
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static FileReader fr = null;
	private static BufferedReader br = null;
	private  Vector<Enemy> ets = new Vector<Enemy>();
	
	//恢复时候的记录点
	static Vector<Node> nodes = new Vector<Node>();

	//完成读取任务
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
	//把玩家机会坦克数量保存到文件中
	public static void keepRec(){
		try {
			fw = new FileWriter("D:\\recorder.txt");
			bw = new BufferedWriter(fw);
			bw.write(allEnNum+"\r\n");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			先开的后关闭
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//从文件中读取游戏记录
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
	//保存击毁敌人的数量和敌人坦克坐标，方向
	public  void keepRecAndEnemy(){
		try {
			fw = new FileWriter("D:\\recorder.txt");
			bw = new BufferedWriter(fw);
			//第一行，敌人的坦克被击毁了多少
			bw.write(allEnNum+"\r\n");
			//保存当前活着的敌人的坐标和方向
			for(Enemy e:ets){
				if(e.isLive){
					String record = e.getX()+" "+e.getY()+" "+e.getDerect()+"";
				
					bw.write(record+"\r\n");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			先开的后关闭
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
	//减少人数
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
