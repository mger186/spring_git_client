package org.tp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.ArrayUtils;
 

public class RunShellUtil {

	 /**
     * 解决了 参数中包含 空格和脚本没有执行权限的问题
     * @param scriptPath 脚本路径
     * @param para 参数数组
     */
	 public static void execShell(String scriptPath, String ... para) {
        try {
            String[] cmd = new String[]{scriptPath};
            //为了解决参数中包含空格
            cmd=ArrayUtils.addAll(cmd,para);

            //解决脚本没有执行权限
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755",scriptPath);
            Process process = builder.start();
            process.waitFor();

            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            //执行结果
            String result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    
    /**
     * 解决了 参数中包含 空格和脚本没有执行权限的问题
     * @param scriptPath 脚本路径
     * @param para 参数数组
     */
    public static void execShell(String cmd) {
        try {
             

            //解决脚本没有执行权限
//            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755",scriptPath);
//            Process process = builder.start();
//            process.waitFor();

            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            //执行结果
            String result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
