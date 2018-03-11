package org.tp.zb.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tp.util.GitUtil;
 

 
@SpringBootApplication
@RestController 
public class ServiceApplication extends SpringBootServletInitializer{
	
	private final Logger logger = Logger.getLogger(getClass());
    
	public static String APP_ROOT_PATH;
	 
	public static void loadMainParm()  {
		APP_ROOT_PATH = ServiceApplication.class.getProtectionDomain()
				.getCodeSource().getLocation().getFile();
		if (APP_ROOT_PATH.startsWith("/"))
			APP_ROOT_PATH = "/" + APP_ROOT_PATH;
	}
	
	 
	/**
	 * 写入需要执行的shell文件内容并返回执行文件名
	 * @param body
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/writeShellfile")
	public String writeShellfile(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception   {
//		FileOutputStream fos=new FileOutputStream(new File("e:/test/"+multiReq.getFile("file").getOriginalFilename())); 
		
		file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File tempFile = new File(APP_ROOT_PATH+file.getOriginalFilename());
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tempFile));
                // "d:/"+file.getOriginalFilename() 指定目录
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
              
            }
        }
		return "";
		
	}
	
	
	@RequestMapping(value = "/gitexec")
	public void gitexec(@RequestParam("gitUrl")String gitUrl,@RequestParam("branch")String branch,
			@RequestParam("appName")String appName,@RequestParam("gitname")String gitname,
			@RequestParam("gitpwd")String gitpwd,@RequestParam("appPath")String appPath,@RequestParam("execShell")String execShell,HttpServletRequest request) throws Exception {
		
//		Map p=(Map)JSONUtils.parse(body);
		
		//String workSavePath,String gitUrl,String branch,String appName,String gitname,String gitpwd
		
		GitUtil.gitClone(APP_ROOT_PATH, gitUrl, branch, appName, gitname, gitpwd,appPath,execShell);
	}

	
	
	public static void main(String[] args) throws Exception {
		loadMainParm();
		SpringApplication.run(ServiceApplication.class, args);
	}
    
    
    @Bean
    public EmbeddedServletContainerFactory servletContainer(){
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.setPort(port);
//        factory.setContextPath("/eureka");
        return factory;
    }
    
     
    

    
}
