import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * �ֽ�����byte
 * �ַ�����char
 * �����ַ�����String����ȡһ�У�Ч��Խ����
 * bufferedreader ����ȡ����
 * @author chc
 *
 */
public class Demo_BufferReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			//��Ҫ�ȹ���fileReader��ת��bufferedreader
			FileReader fr = new FileReader("D:\\test.txt");
			br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter("D:\\����.txt");
			bw = new BufferedWriter(fw);
			//ѭ����ȡ�����ַ�����ȡ
			String s = "";

			while((s=br.readLine())!=null){
				System.out.println(s);
//				���������
				bw.write(s+"\r\n");
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
