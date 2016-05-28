import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Demo_FileCopy {
	public static void main(String[] args) {
		File f = new File("d:\\spring.jpg");
		FileInputStream fis = null; 
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(f);
		
		    fos =new FileOutputStream("d:\\b.jpg");
			int n = 0;
			byte[] br = new byte[1024];
			while((n=fis.read(br))!=-1){
				fos.write(br);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
