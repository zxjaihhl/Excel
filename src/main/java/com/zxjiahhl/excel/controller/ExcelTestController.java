package com.zxjiahhl.excel.controller;

import com.zxjiahhl.excel.utils.FileUploadUtil;
import com.zxjiahhl.excel.utils.R;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;


/**
 * Created by zxjaihhl on 2018/11/22.
 */
@RestController
@RequestMapping(value = "excel")
public class ExcelTestController {


    @Value("${physics_url}")
    private String physics_url;

    /**
     * excel文件导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/load")
    public R loadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        String uploadUrl = null;
        if (file == null) {
            return R.error();
        }
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                if (fileName != null) {
                    uploadUrl = FileUploadUtil.upload(physics_url, file);
                    System.out.println(uploadUrl);
                } else {
                    return R.error();
                }
            } else {
                return R.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

        //读取03或07的版本
        String filePath = "D:\\upload/" + uploadUrl;
        if (filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            FileInputStream fis = new FileInputStream(filePath);
            boolean is03Excell = filePath.matches("^.+\\.(?i)(xls)$");
            Workbook workbook = null;
            workbook = is03Excell ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(2);
            if (cell == null || cell.getStringCellValue() == null) {
                System.out.println("null");
            } else {
                System.out.println("第一行第一列的数据是:" + cell.getStringCellValue());
                System.out.println("03 + 07");
            }
        } else {
            return R.error();
        }
        return R.ok();
    }

    @GetMapping("/get")
    public R getExcel() {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //新建工作表
        XSSFSheet sheet = workbook.createSheet("hello");
        //创建行,0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格行号由row确定,列号作为参数传递给createCell;第一列从0开始计算
        XSSFCell cell = row.createCell(2);
        //给单元格赋值
        cell.setCellValue("hello sheet");
        //创建输出流
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("D:\\upload/hello1.xlsx"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return R.error();
        }
        try {
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }
        System.out.println("07写入");
        return R.ok();

    }
}
