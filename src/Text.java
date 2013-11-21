import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;





public class Text {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		File file = new File("D:/thrift");
//		FileFilterUtils.NameFileFilter("temp");
		Collection<File> collection2 = FileUtils.listFiles(file, 
				FileFilterUtils.nameFileFilter("ThriftTest.java"), TrueFileFilter.INSTANCE);
		System.out.println(collection2.size());
		for (File f : collection2) {
			System.out.println(f.getAbsolutePath());
		}
		
//		String[] strs = {"java"};
//		Collection<File> collection = FileUtils.listFiles(file, strs, true);
//		System.out.println(collection.size());
//		for (File f : collection) {
//			System.out.println(f.getAbsolutePath());
//		}
	}
	
	
	

	public static void main1(String[] args) {
		 boolean isOk = 1>2;
		 assert isOk : "程序错误";
		 System.out.println("程序正常");
	}
	
	public static void open() {
		boolean isOpen = false;  
		assert isOpen=true; //如果开启了断言，会将isOpen的值改为true 
		System.out.println(isOpen);//打印是否开启了断言   
	}
}
