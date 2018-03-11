package org.tp.util;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.joda.time.DateTimeUtils;

import jodd.io.FileUtil;


public class  GitUtil {
	
	/**
	 * 
	 * @param workSavePath E:/workspace/Test_Git/TestRepo
	 * @param gitUrl https://github.com/xxx/xx.git
	 * @param branch master
	 * @param appName
	 * @throws GitAPIException
	 * @throws IOException 
	 */
	public static void gitClone(String workSavePath,String gitUrl,String branch,String appName,String gitname,String gitpwd,String appPath,String execShell) throws GitAPIException, IOException {
	    final File localPath = new File(workSavePath+"git/"+appName+ "/"+DateTimeUtil.getDateTime("yyyyMMddHHmmss"));
//	    deleteFolder(localPath);
	    Git.cloneRepository()
	        .setURI(gitUrl)
	        .setBranch(branch)
	        .setDirectory(localPath)
	        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitname, gitpwd))
	        .call();
	    deleteFolder(new File(appPath));
	    FileUtil.copy(localPath, new File(appPath));
	    RunShellUtil.execShell(execShell);
	}
	
	
	
	 public static void deleteFolder(File file){
		 try{
	        if(file.isFile() || file.list().length==0){
	            file.delete();
	        }else{
	            File[] files = file.listFiles();
	            for(int i=0;i<files.length;i++){
	                deleteFolder(files[i]);
	                files[i].delete();
	            }
	        }
		 }catch(Exception e){}
	    }
}
