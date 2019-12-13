package com.hh.wx.xcx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.fdfs.util.FastdfsClientUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hh
 * 
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {
	
	@Autowired
	private FastdfsClientUtil fastdfsClientUtil;
	
	@RequestMapping(value="/upload/uploadBase64",method=RequestMethod.POST)
    public ResultVo<String> base64UpLoad(@RequestParam("file") String base64Data, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException{  	
		String path = null;
        try{  	           
            String dataPrix = "";
            String data = "";	       
            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = "jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = "ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = "gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = "png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            //使用apache提供的工具类操作流
            	
            path = fastdfsClientUtil.upload(bs,suffix);
            //FileUtils.writeByteArrayToFile(new File(request.getServletContext().getRealPath("/upload"), tempFileName), bs);  
        }catch (Exception e) { 
        	log.error(e.getMessage(),e);
            return ResultUtils.fail(e.getMessage());
        }  	   
        
        return ResultUtils.secusses(path);
    }
	
	@RequestMapping(value="/upload/MultipartFile",method=RequestMethod.POST)
    public ResultVo<String> upload(@RequestParam("file") MultipartFile file) {
		
        if (file.isEmpty()) {
            return ResultUtils.fail("上传失败，请选择文件");
        }
        String path = null;
        try {
        	path = fastdfsClientUtil.upload(file);
        } catch (Exception e) {
            log.error(e.toString(), e);
            return ResultUtils.fail(e.getMessage());
        }
        return ResultUtils.secusses(path);
    }
	
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
    public ResultVo<String> delete(String path) {
		fastdfsClientUtil.deleteFile(path);
        return ResultUtils.secusses("");
    }
	
	
	public static void main(String[] args) {
		String data = GetImageStr();
		System.out.println(data);
		GenerateImage(data);
	}
	
	//图片转化成base64字符串
	public static String GetImageStr() {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	    String imgFile = "E:/wxxcx/icon/icon-jiantou-r.png";//待处理的图片
	    InputStream in = null;
	    byte[] data = null;
	    //读取图片字节数组
	    try {
	        in = new FileInputStream(imgFile);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    //对字节数组Base64编码
	    return Base64Utils.encodeToString(data);
	    //BASE64Encoder encoder = new BASE64Encoder();
	    //return encoder.encode(data);//返回Base64编码过的字节数组字符串
	}
	
	
	//base64字符串转化成图片
	public static String GenerateImage(String imgStr) {//对字节数组字符串进行Base64解码并生成图片
	    if (imgStr == null) {//图像数据为空
	        log.error("图像数据为空");
	        return "error";
	    }
	    try {
	        //Base64解码
	        byte[] b = Base64Utils.decodeFromString(imgStr);
	        for (int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {//调整异常数据
	                b[i] += 256;
	            }
	        }
	        //生成jpeg图片
	        System.out.println("生成jpeg图");
	        String path = "E:/wxxcx/icon/";
	        String file = "1.png";
	 
	        String filePath = path +file;
	        //新生成的图片
	        OutputStream out = new FileOutputStream(filePath);
	        out.write(b);
	        out.flush();
	        out.close();
	        File file2 = new File(filePath);
	        //StorePath storePath = fastFileStorageClient.uploadFile(null, new FileInputStream(file2), file2.length(), "png");
	        System.out.println("成功");
	        return  "";
	    } catch (Exception e) {
	        log.error("读取头像失败");
	        return "error";
	    }
	}
}
