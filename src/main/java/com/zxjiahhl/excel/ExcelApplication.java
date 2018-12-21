package com.zxjiahhl.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@MapperScan("com.zxjiahhl.excel.mapper")
@Controller
public class ExcelApplication {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
    }
}
