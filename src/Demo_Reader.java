import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Demo_Reader {
	public static void main(String[] args) {
		FileReader fr = null;//����
		FileWriter fw = null;//���
		
		try {
			//����fr����
			fr = new FileReader("d:\\test.txt");
		
			fw = new FileWriter("d:\\vvv.txt");
			//�����ڴ�
			char[] cbuf = new char[1024];
			//fr.read(cbuf);
			int n =0;//��¼��ȡ�����ַ���
			while((n=fr.read(cbuf))!=-1){
/*				String s =new String(cbuf,0,n);
				fw.write(s);
				System.out.println(s);*///���У������ʱ��û������
				//fw.write(cbuf);//д�ļ���ʱ�����ֱ�ӵ���writer,ֱ���ü��±���û������
			
				fw.write(cbuf, 0, n);//ע��˷�����ʹ�ã���������
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//һ��Ҫ�رգ���
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
