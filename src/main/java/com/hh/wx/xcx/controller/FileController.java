package com.hh.wx.xcx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	private FastdfsClientUtil astdfsClientUtil;
	
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
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = UUID.randomUUID().toString() + suffix;	            
 
            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            //使用apache提供的工具类操作流
            	
            path = astdfsClientUtil.upload(bs,"jpg");
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
        	path = astdfsClientUtil.upload(file);
        } catch (Exception e) {
            log.error(e.toString(), e);
            return ResultUtils.fail(e.getMessage());
        }
        return ResultUtils.secusses(path);
    }
	
	@RequestMapping(value="/upload/MultipartFile",method=RequestMethod.GET)
    public ResultVo<String> upload1() {
		
        return ResultUtils.secusses("");
    }
}
