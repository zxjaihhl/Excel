package com.zxjiahhl.excel.utils;


import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 使用上传图片时先配置虚拟目录 /pic 物理路径 C:\img
 *
 * @author
 * @date 2016年8月17日 下午10:41:05
 */
public class FileUploadUtil {

    public static String type = "5";

    public static String url = "";


    /**
     * 初始化路径
     *
     * @throws Exception
     */
    public static void getProperties(HttpServletRequest request) throws Exception {
        Properties prop = new Properties();
        try {
            // 读取属性文件a.properties
            String requesturl = request.getSession().getServletContext().getRealPath("");
            String path = requesturl + "WEB-INF/classes/config/properties/img.properties";
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            prop.load(in); /// 加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            Map<String, String> map = new HashMap<String, String>();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key, prop.getProperty(key));
            }
            in.close();

            FileUploadUtil.type = map.get("imgType");
            FileUploadUtil.url = map.get("imgUrl");
        } catch (Exception e) {
            System.out.println("没有找到某配置文件->img.properties");
        }
    }

    public static String upload(MultipartFile file) throws Exception {
        // 原始名称
        String originalFilename = file.getOriginalFilename();
        // 上传文件
        if (file != null && originalFilename != null && originalFilename.length() > 0) {

            // 存储文件的物理路径
            String file_path = "c:\\img\\";

            // 新的文件名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新文件
            File newFile = new File(file_path + newFileName);
            System.out.println(file_path + newFileName);
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            return newFileName;
        }
        return null;
    }

    public static String upload(MultipartFile file, HttpServletRequest request, String str) throws Exception {

        String realPath = request.getSession().getServletContext().getRealPath("/upload/");

        // 原始名称
        String originalFilename = file.getOriginalFilename();
        // 上传文件
        if (file != null && originalFilename != null && originalFilename.length() > 0) {

            // 存储文件的物理路径
            String file_path = realPath;

            String path = file_path;

            File newFile = new File(path);

            FileExists(newFile);
            String fileName = UUID.randomUUID().toString() + "."
                    + FilenameUtils.getExtension(file.getOriginalFilename());

            file.transferTo(new File(newFile, fileName));

            String returnUrl = "upload/" + fileName;

            return returnUrl;
        }
        return null;
    }

    public static String upload(String url, MultipartFile file) throws Exception {
        // 原始名称
        String originalFilename = file.getOriginalFilename();
        // 上传文件
        if (file != null && originalFilename != null && originalFilename.length() > 0) {

            //String realPath = request.getSession().getServletContext().getRealPath("/upload/");
            String realPath = url;

            // String realPath = request.getContextPath()
            // 存储文件的物理路径
            String file_path = realPath;

            String path = file_path;
            File newFile = new File(path);

            FileExists(newFile);
            String fileName = UUID.randomUUID().toString() + "."
                    + FilenameUtils.getExtension(file.getOriginalFilename());

            file.transferTo(new File(newFile, fileName));

            return fileName;
        }
        return null;
    }

    private static void FileExists(File newFile) {
        if (!newFile.exists()) {
            if (!newFile.mkdirs()) {
                try {
                    throw new Exception("创建保存目录失败");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
