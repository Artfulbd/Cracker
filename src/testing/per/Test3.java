package testing.per;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
//split file
public class Test3 {
	public static void main(String[] args){
		try{
			byte b[] = new byte[1000000];
			int x=1, j=0;
			String s = "";
			InputStreamReader ins = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(ins);
			System.out.println("Enter file path");
			String path = "C:\\Users\\HP\\Desktop\\test\\Desire2.txt";

			FileInputStream fis = new FileInputStream(path);
			FileInputStream fis2 = new FileInputStream(path);
			byte [] by = fis2.readAllBytes();
			fis2.close();
			System.out.println("Length: "+by.length);
			int read_bytes;
			while(fis.available() != 0){
				j = 0;
				s = path;
				 if(x<=9)  s = s.replace("Desire2", "0"+x+"Desire2");
				 else  s = s.replace("Desire2", +x+"Desire2");
				System.out.println(s);
				FileOutputStream fos = new FileOutputStream(s);
				while(j<=1000000  && fis.available()!=0){
					read_bytes = fis.read(b, 0,100000);
					j = j+read_bytes;
					fos.write(b,0,read_bytes);
				}				
				System.out.println("Part "+x+" Created.");
				x++;
			}
			System.out.println("Successfull");
			fis.close();
		}catch(Exception e){}
	}
}
