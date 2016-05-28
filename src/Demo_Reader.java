import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Demo_Reader {
	public static void main(String[] args) {
		FileReader fr = null;//输入
		FileWriter fw = null;//输出
		
		try {
			//创建fr对象
			fr = new FileReader("d:\\test.txt");
		
			fw = new FileWriter("d:\\vvv.txt");
			//读入内存
			char[] cbuf = new char[1024];
			//fr.read(cbuf);
			int n =0;//记录读取到的字符数
			while((n=fr.read(cbuf))!=-1){
/*				String s =new String(cbuf,0,n);
				fw.write(s);
				System.out.println(s);*///三行，输出的时候没有乱码
				//fw.write(cbuf);//写文件的时候可以直接调用writer,直接用记事本打开没有问题
			
				fw.write(cbuf, 0, n);//注意此方法的使用，参数含义
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//一定要关闭！！
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
