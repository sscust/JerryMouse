import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class Request {
	private InputStream ResponseIps;
	private BufferedReader br;
	private String url;
	private String header;
	private String requestMethod;
	public Request(Socket client){
		try {
			this.ResponseIps=client.getInputStream();
			br=new BufferedReader(new InputStreamReader(this.ResponseIps));
			parseRequestMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getUrl(){		
		return url;
	}
	private void parseRequestMessage(){
		String firstLine=null;
		
		
		String temp=null;
		String append=null;
		try {
			firstLine=this.br.readLine();
			append=""+firstLine;
			/*while((temp=this.br.readLine())!=null){
				append=append+temp;
			}*/
			this.header=append;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(firstLine);
		String[] firstLineWords=firstLine.split(" ");
		this.requestMethod=firstLineWords[0];
		this.url=firstLineWords[1];
	}
	public String getHeader(){
		return header;
	}
	public String getRequestMethod(){
		return requestMethod;
	}
	
}
