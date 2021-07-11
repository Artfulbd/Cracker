package tracker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Splitter {
	private String path;
	private ArrayList<String> list = new ArrayList<>();
	Splitter(String path){
		this.path = path;
	}
	
	public boolean split() {
		boolean doNothing = false;
		try{
			byte b[] = new byte[1000000];
			int x=1, j=0;
			String s;
			FileInputStream fis = new FileInputStream(path);
			FileInputStream fis2 = new FileInputStream(path);
			if(fis2.readAllBytes().length <= 1100000) doNothing = true;
			fis2.close();
			FileOutputStream fos;
			int read_bytes;
			while(fis.available() !=0 && !doNothing){
				j = 0;
				s = path;
				 if(x<=9)  s = s.replace("Desire2", "0"+x+"Desire2");
				 else  s = s.replace("Desire2", +x+"Desire2");
				fos = new FileOutputStream(s);
				while(j<=1000000  && fis.available()!=0){
					read_bytes = fis.read(b, 0,100000);
					j = j+read_bytes;
					fos.write(b,0,read_bytes);
				}				
				list.add(s);
				x++;
			}
			fis.close();
		}catch(Exception e){}
		if(doNothing)return false;
		return true;
	}

	ArrayList<String> getSplitedFileList() {
		return list;
	}
	

}
