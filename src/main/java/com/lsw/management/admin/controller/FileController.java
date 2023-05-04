package com.lsw.management.admin.controller;

import com.lsw.management.admin.mapper.FileMapper;
import com.lsw.management.admin.model.po.file.File;
import com.lsw.management.admin.model.po.user.UserAccount;
import com.lsw.management.admin.service.UserAccountService;
import com.lsw.management.common.constants.ErrorCode;
import com.lsw.management.common.exception.BusinessException;
import com.lsw.management.common.http.response.ApiResponse;
import com.lsw.management.common.http.response.ResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/4  8:23
 */
@Slf4j
@RequestMapping("/fileManage")
@RestController
@Api(tags = "文件管理模块")
public class FileController {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    FileMapper fileMapper;

    // 文件上传
    @ApiOperation(value = "文件上传", httpMethod = "POST")
    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {
        if (files.isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_PARAMS, "文件不能为空");
        }
        UserAccount currentUser = userAccountService.getCurrentUser(request);
        if(currentUser==null){
            throw new BusinessException(ErrorCode.UNLOGIN);
        }
        try {
            // 上传到的目录路径
            String uploadPath = "/upload/path/";
            // 遍历上传文件
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    throw new BusinessException(ErrorCode.INVALID_PARAMS, "文件不能为空");
                }
                // 上传的文件名
                String originalFilename = file.getOriginalFilename();
                // 生成新的文件名
                String newFilename = UUID.randomUUID() + getFileExtension(originalFilename);
                // 构建上传文件的完整路径
                File myFile = File.builder()
                        .fileName(newFilename)
                        .createTime(new Date())
                        .deleted((byte) 0)
                        .accountId(currentUser.getId())
                        .build();
                fileMapper.insert(myFile);
                String filePath = uploadPath + newFilename;
                // 创建目录路径
                Files.createDirectories(Paths.get(uploadPath));
                // 上传文件
                Files.write(Paths.get(filePath), file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传失败");
            return ResponseHelper.failed(ErrorCode.SYSTEM_ERROR);
        }
        log.info("上传成功");
        return ResponseHelper.success("上传成功");
    }

    @ApiOperation(value = "文件下载", httpMethod = "GET")
    @GetMapping("/download")
    public void download(@RequestParam String accountId, HttpServletResponse response) {
        try {
            // 下载文件的完整路径
            String filePath = "/upload/path/";
            // 读取文件数据

            Example example = new Example(File.class);
            example.createCriteria().andEqualTo("deleted", 0)
                    .andEqualTo("accountId", accountId);
            File file = fileMapper.selectOneByExample(example);
            if(file == null) {
                throw new BusinessException(ErrorCode.FILE_NOT_EXIT);
            }
            Path path = Paths.get(filePath + file.getFileName());
            byte[] data = Files.readAllBytes(path);
            // 设置文件响应类型
//            response.setContentType("application/octet-stream");
            response.setContentType("application/force-download");
            // 设置文件大小
            response.setContentLength(data.length);
            response.setCharacterEncoding("UTF-8");
            // 设置文件名称
            response.setHeader("Content-disposition", "attachment;filename=" + file.getFileName());
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

