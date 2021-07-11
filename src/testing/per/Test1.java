package testing.per;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test1 {
	private static File[] roots;
	private static PrintWriter writer;
	private static int target;
	private static int j = 0;
	public static void main(String[] args) {
		// TODO testing background process
		
		String st = "check d";
		System.out.println(st.charAt(6));
		
		
		
		
		
		
		
		/*
		try {
			PrintWriter writer = new PrintWriter("C:\\Users\\HP\\Desktop\\faltu.txt", "UTF-8");
			writer.println("The first line");
			writer.println("The second line");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer = new PrintWriter("C:\\Users\\HP\\Desktop\\Desire2.txt", "UTF-8");
			System.out.println(validateAndPrint("E:\\"));
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			
		}*/

	}
	private static String validateAndPrint(String dir) {
		String errorMsg = "";
		try {
			if(dir.contains("\\") && dir.indexOf(':') == 1 && new File(dir).isDirectory()) {
				print(dir, 0);
				System.out.println("j: "+j+" Target: "+target);
				if(j == target)System.out.println("All printed Successfully.");
			}else {
				System.out.println("Wrong directory.!!");
			}
		}catch(Exception e) {
			errorMsg = e.toString();
		}
		return (errorMsg.isEmpty()) ? "Successfully printed " : "It says problem on "+errorMsg;
	}


	private static void print(String dir, int lavel)  {
		try {
			String load = " |";
			for(int i = 0; i<lavel; i++)load+="--|";
			if(lavel == 1) ++j;
			if(new File(dir).isDirectory()) {
				if(lavel == 0) load = "";
				System.out.println(load+dir.substring(dir.lastIndexOf('\\')+1)+"{F}");
				writer.println(load+dir.substring(dir.lastIndexOf('\\')+1)+"{F}");
				try {
					Queue<String> qu  = new PriorityQueue<>(Arrays.asList(new File(dir).list()));
					while(!qu.isEmpty()) {
						try {
							print(dir+"\\"+qu.poll(), lavel+1);
						}catch(Exception e) {}
					}
				}catch(Exception e) {}
			}
			else {
				if(lavel == 0) load = "";
				System.out.println(load+dir.substring(dir.lastIndexOf('\\')+1));
				writer.println(load+dir.substring(dir.lastIndexOf('\\')+1));
			}			
		}catch(NullPointerException e) {
			System.out.println("Exception occurs on "+dir);
		}
	}

}
