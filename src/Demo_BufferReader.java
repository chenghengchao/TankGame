import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * 字节流：byte
 * 字符流：char
 * 缓冲字符流：String，读取一行，效率越高了
 * bufferedreader 不读取还行
 * @author chc
 *
 */
public class Demo_BufferReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			//需要先构建fileReader，转成bufferedreader
			FileReader fr = new FileReader("D:\\test.txt");
			br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter("D:\\中文.txt");
			bw = new BufferedWriter(fw);
			//循环读取，按字符串读取
			String s = "";

			while((s=br.readLine())!=null){
				System.out.println(s);
//				输出到磁盘
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
