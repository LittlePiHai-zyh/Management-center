package com.zhangyh.management;

import com.alibaba.excel.EasyExcel;
import com.zhangyh.management.admin.excel.CustomSheetWriteHandler;
import com.zhangyh.management.admin.excel.DemoData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class ManagementCenterApplicationTests {

    @Test
    void contextLoads() throws IOException {
       String fileName ="simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        DemoData demoData = new DemoData();
        demoData.setDate(new Date());
        demoData.setString("hahaha");

        EasyExcel.write(fileName, DemoData.class).registerWriteHandler(new CustomSheetWriteHandler())
                .sheet("模板").doWrite(Collections.singletonList(demoData));


    }
    @Autowired
    private MessageSource messageSource;

    @Test
    public void testI18n(){
        //得到我们需要的Local对象
        Locale locale =Locale.CHINA;
//        ResourceBundle bundle=ResourceBundle.getBundle("i18n/message",locale);
//        System.out.println("username"+bundle.getString("username"));
//        System.out.println("password"+bundle.getString("password"));
//        System.out.println("sex"+bundle.getString("sex"));
//        System.out.println("age"+bundle.getString("age"));
        String username = messageSource.getMessage("username", null, locale);
        String password = messageSource.getMessage("password", null, locale);
        String sex = messageSource.getMessage("sex", null, locale);
        System.out.println(username+"==="+password+"==="+sex);
    }




}
