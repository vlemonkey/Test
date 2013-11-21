import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriteThread {
	
	private static final String PATH = "D:/#1/#2.txt";
	
	private static final String STRDAY = "20130811";         // 修改这个时间

	public static void main(String[] args) {
		
		int count =1;
		
		String[] strs = new String[count];
		for(int i=0; i<count; i++) {
			strs[i] = PATH.replace("#1", STRDAY).replace("#2", String.valueOf(i));
		}
		
		for (String filePath : strs) {
			new Thread(new FThread(filePath)).start();
		}
		
	}
}

class FThread implements Runnable{
	
	private static final String STRDAY = "20130811";           // 修改这个时间
	BufferedWriter bWriter; 
	
	public FThread(String fileName) {
		File file = new File(fileName);
		
		File fileParent = new File(file.getParent());
		if (!fileParent.exists()) {
			fileParent.mkdir();
		}
		
		try {
			bWriter = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		int count = 100000000;       // 修改这里 1亿条数据
		
		try {
			for (int i=0; i<count; i++) {
				bWriter.write(MakeDataGn.getRandomData(STRDAY));
				bWriter.newLine();
			}
			bWriter.flush();
			System.out.printf("%s finished.\n", Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
