import java.io.File;
import java.net.URLEncoder;

import jodd.http.HttpProgressListener;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.upload.JoddUpload;

public class TestUpload {

	public static void main(String[] args) {
		System.out.println("1\n33");
		String exe="cd /home/server/1 \n"+
		"nohup java -Djava.ext.dirs=../commlib -Xms128m -Xmx1024m -XX:+UseParallelGC -XX:ParallelGCThreads=4 -server  org.tp.zb.server.EurekaserverApplication 1 > console.log & echo $! > login.pid &";
		exe=URLEncoder.encode(exe);
		String url="http://127.0.0.1:9901/gitexec?gitUrl=https://github.com/mger186/spring_cloud_zuul.git&branch=master&appName=app1&gitname=&gitpwd=&appPath=/home/server/1&execShell="+exe;
		HttpRequest q =HttpRequest
		        .get(url);
		 if(true){
	        	q.acceptEncoding("gzip");
	        }
		HttpResponse response =q.send();
		String s = response.bodyText();
		response.close();
		
		
//		 HttpResponse response = HttpRequest
//			        .post("http://127.0.0.1:9901/writeShellfile")
//			        .form("file", new File("e:/tv.txt"))
//			        .monitor(new HttpProgressListener() {
//			            
//						@Override
//						public void transferred(int len) {
//							System.out.println(""+len/size);
//						}
//			        })
//			        .send();

	}

}
