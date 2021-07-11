package tracker;
import java.io.File;

public class TreeTest {

	static File tempFile, rootFile;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//print("I:\\JavA\\TestPer\\Testper1", 0 );
		System.out.println(new File("D:\\System Volume Information").isDirectory());
       String s = validateAndPrint("D:\\System Volume Information");
        /*String []list = new File("D:\\").list();
		int i =  0;
		while(i<list.length) {
			System.out.println(list[i++]);
		}*/
       // System.out.println(s);
		

	}
	private static String validateAndPrint(String dir) {
		String errorMsg = "";
		try {
			if(dir.contains("\\") && dir.indexOf(':') == 1 && new File(dir).isDirectory()) {
				System.out.println("hmdg");
				print(dir, 0);
			}else {
				System.out.println("Wrong directory.!!");
			}
		}catch(Exception e) {
			errorMsg = e.getLocalizedMessage();
		}
		return (errorMsg.isEmpty()) ? "Successfully printed " : "It says problem on "+errorMsg;
	}
	
	
	private static void print(String dir, int lavel) throws Exception  {
		try {
				String load = " |";
				for(int i = 0; i<lavel; i++)load+="--|";
				if(new File(dir).isDirectory()) {
					if(lavel == 0)System.out.println(dir.substring(dir.lastIndexOf('\\')+1)+"{F}");
					else System.out.println(load+dir.substring(dir.lastIndexOf('\\')+1)+"{F}");
					String []list = new File(dir).list();
					int i =  0;
					while(i<list.length) {
						print(dir+"\\"+list[i++], lavel+1);
					}
				}
				else {
					if(lavel == 0)System.out.println(dir.substring(dir.lastIndexOf('\\')+1));
					else System.out.println(load+dir.substring(dir.lastIndexOf('\\')+1));
				}			
		}catch(Exception e) {
			throw new Exception(dir);
		}
	}

}
