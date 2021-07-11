package tracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Generate {
	private static File[] roots;
	private static PrintWriter writer;
	static Scanner in = new Scanner(System.in);
	private static int target;
	private static int j = 0;
	private String generatedFileName; 
	private String errorMsg ;
	Generate(String fileName){
		generatedFileName = fileName;
	}
	public boolean generate(String st) {
		st = st.toUpperCase()+":\\";
		System.out.println(st);
		if(isValid(st)) {
			System.out.println("Mached");
			File  rootFile = new File(st);			
			target  = new PriorityQueue<>(Arrays.asList(rootFile.list())).size();
			System.out.println("Size is: "+target);
			try {
				writer = new PrintWriter(generatedFileName, "UTF-8");
				boolean b = validateAndPrint(st);
				writer.close();
				if(b) return true;
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.out.println("Problem on file generation");
			}
		}
		else errorMsg = "Invalid drive latter.";
		return false;
	}
	
	public static void printRoot() {
		roots = File.listRoots();
		for(int i = 0; i < roots.length ; i++)
			System.out.println("Root["+i+"]:" + roots[i]);
	}

	public static boolean isValid(String in) {
		for(int i = 0; i<roots.length; i++ ) {
			if(in.equals(roots[i].toString()))return true;
		}
		return false;
	}

	private  boolean validateAndPrint(String dir) {
		try {
			if(dir.contains("\\") && dir.indexOf(':') == 1 && new File(dir).isDirectory()) {
				java.util.Date d= new java.util.Date();
				//System.out.println("Directory ->>"+dir+" Time :"+d.toString());
				writer.println("Directory ->>"+dir+" Time :"+d.toString());
				print(dir, 0);
				if(j == target)return true;
			}else {
				errorMsg = "Wrong directory.!!";
				return false;
			}
		}catch(Exception e) {
			errorMsg = e.toString();
		}
		return false;
	}


	String getErrorMsg() {
		return errorMsg;
	}
	private void print(String dir, int lavel)  {
		try {
			String load = " |";
			for(int i = 0; i<lavel; i++)load+="--|";
			if(lavel == 1) ++j;
			if(new File(dir).isDirectory()) {
				if(lavel == 0) load = "";
				//System.out.println(load+dir.substring(dir.lastIndexOf('\\')+1)+"{F}");
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
				//System.out.println(load+dir.substring(dir.lastIndexOf('\\')+1));
				writer.println(load+dir.substring(dir.lastIndexOf('\\')+1));
			}			
		}catch(NullPointerException e) {
			System.out.println("Exception occurs on "+dir);
		}
	}

}
