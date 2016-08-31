import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class JerryMouse {
	public static void main(String[] args) {
		ServerSocket ss=null;
		Socket client=null;
		try {
			ss = new ServerSocket(9100);
			while(true){
				client=ss.accept();
				MultiClientServer server=new MultiClientServer(client);
				server.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
