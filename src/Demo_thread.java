
public class Demo_thread {
	public static void main(String[] args) {
		Bird b = new Bird(10);
		Pig p = new Pig(10);
		Thread t1 = new Thread(b);
		Thread t2 = new Thread(p);
		t1.start();
		t2.start();
	}

}
class Pig implements Runnable{

	int n;
	int times;
	int res;
	public Pig (int n){
		this.n=n;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			res+=(++times);
			System.out.println("当前结果"+res);
			System.out.println("正在输出"+times);
			if(times==n){
				break;
			}
		}
	}
	
}
class Bird implements Runnable{
	int n;
	int res;
	int times=0;
	public Bird(int n){
		this.n =n;
	}

	@Override
	public void run() {

		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res+=(++times);
			
			if(times==n){
				System.out.println("最后结果"+res);
			
				break;
			}
		}
	}
	
}
