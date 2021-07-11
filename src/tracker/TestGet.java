package tracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


import javafx.scene.shape.Path;

public class TestGet {
	private static File[] roots;
	private static PrintWriter writer;
	static Scanner in = new Scanner(System.in);
	private static int target;
	private static int j = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		printRoot();
		System.out.print("Enter Drive latter: ");
		String st = in.next();
		st = st.toUpperCase()+":\\";
		System.out.println(st);
		if(isValid(st)) {
			System.out.println("Mached");
			File  rootFile = new File(st);			
			target  = new PriorityQueue<>(Arrays.asList(rootFile.list())).size();
			System.out.println("Size is: "+target);
			try {
				writer = new PrintWriter("C:\\Users\\HP\\Desktop\\Desire2.txt", "UTF-8");
				validateAndPrint(st);
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				
			}finally {
				writer.close();
			}
		}
		else System.out.println("Invalid drive latter.");		    
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
	
	private static String validateAndPrint(String dir) {
		String errorMsg = "";
		try {
			if(dir.contains("\\") && dir.indexOf(':') == 1 && new File(dir).isDirectory()) {
				java.util.Date d= new java.util.Date();
				System.out.println("Directory ->>"+dir+" Time :"+d.toString());
				writer.println("Directory ->>"+dir+" Time :"+d.toString());
				print(dir, 0);
				if(j == target)System.out.println("All printed Successfully.");
				else errorMsg = "Something left";
			}else {
				System.out.println("Wrong directory.!!");
			}
		}catch(Exception e) {
			errorMsg = e.toString();
		}
		return (!errorMsg.isEmpty()) ? "Successfully printed " : "It says problem on "+errorMsg;
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
