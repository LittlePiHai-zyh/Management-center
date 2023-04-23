package com.zhangyh.management.admin.excel;

import com.zhangyh.management.common.http.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangyh
 * @Date 2023/4/21 11:15
 * @desc
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    /**
     * 下载
     * @return
     */
    @GetMapping("/download")
    public ApiResponse download(HttpServletResponse response){
        return testService.downloadKolList(response);
    }
}
