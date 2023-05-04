package com.lsw.management.admin.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  8:23
 */
@RequestMapping("/fileManage")
@RestController
@Api(tags = "文件管理模块")
public class FileController {
    // 文件上传
    @ApiOperation(value = "文件上传", httpMethod = "POST")
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        try {
            // 上传的文件名
            String originalFilename = file.getOriginalFilename();
            // 上传到的目录路径
            String uploadPath = "/upload/path/";
            // 生成新的文件名
            String newFilename = UUID.randomUUID()+ getFileExtension(originalFilename);
            // 构建上传文件的完整路径
            String filePath = uploadPath + newFilename;
            // 上传文件
            Files.write(Paths.get(filePath), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @ApiOperation(value = "文件下载", httpMethod = "GET")
    // 文件下载
    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) {
        try {
            // 下载文件的完整路径
            String filePath = "/upload/path/" + filename;
            // 读取文件数据
            byte[] data = Files.readAllBytes(Paths.get(filePath));

            // 设置文件响应类型
            response.setContentType("application/octet-stream");
            // 设置文件名称
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            // 将文件数据写入响应输出流中
            response.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取文件后缀名
    private String getFileExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index >= 0) {
            return filename.substring(index);
        }
        return "";
    }
}

