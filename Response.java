import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;



public class Response{
	private OutputStream ops;
	private int stateCode;//响应的状态码  200，404,500。。。
	private String responseHeader;
	public Response(OutputStream ops){
		this.ops=ops;
	}
	public void sentStaticResource(String url) throws IOException{
		/**
		 * 1、如果URL="/"，返回的是JerryMouse/webapps/ROOT/index.html
		 * 2、如果URL="test/index.html",查找JerryMouse/webapps/test/index.html
		 * 当前的文件的路径为JerryMouse/bin
		 */
		File file=null;
		byte[] buffer=new byte[1024];
		FileInputStream fis=null;
		if(url.equals("/")){
			file=new File("../webapps/ROOT/index.html");
		}else{
			file=new File("../webapps"+url);
		}
		if(file.exists()){
			this.stateCode=200;
			fis=new FileInputStream(file);
			int len=-1;
			while((len=fis.read(buffer, 0, 1024))!=-1){
				ops.write(buffer, 0, len);
			}
		}else{
			this.stateCode=404;
			ops.write("<html><head><title>Jerry Mouse/v1.0 - Error report</title><style><!--H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} H2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}A {color : black;}A.name {color : black;}HR {color : #525D76;}--></style> </head><body><h1>HTTP Status 404 - /test/a.html</h1><HR size=\"1\" noshade=\"noshade\"><p><b>type</b> Status report</p><p><b>message</b> <u>/test/a.html</u></p><p><b>description</b> <u>The requested resource is not available.</u></p><HR size=\"1\" noshade=\"noshade\"><h3>Jerry Mouse/v1.0</h3></body></html>".getBytes());
		}
		if(fis != null)  
            fis.close(); 
	}
}
