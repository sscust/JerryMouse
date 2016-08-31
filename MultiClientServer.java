import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiClientServer extends Thread {
	private Socket client;
	public MultiClientServer(Socket client){
			this.client=client;
	}
	@Override
	public void run() {
		System.out.println("a client connected");
		InputStream ips=null;
		OutputStream ops=null;
		Request request=null;
		Response response=null;
		try {
			request=new Request(client);
			
			ips=client.getInputStream();
			ops=client.getOutputStream();
			
	/*		ops.write("Welcome use JerryMouse".getBytes());
			ops.write("</BR>".getBytes());
			ops.write((request.getUrl()+"</BR>").getBytes());
			ops.write((request.getRequestMethod()+"</BR>").getBytes());
			
	*/		
			response=new Response(ops);
			response.sentStaticResource(request.getUrl());
			ops.flush();
			if(ops!=null) ops.close();
			if(client!=null) client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public Socket getClient(){
		return this.client;
	}
}
