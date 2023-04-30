package com.lsw.management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.io.IOException;
import java.util.Locale;

@SpringBootTest
class GDMCenterApplicationTests {

    @Test
    void contextLoads() throws IOException {


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
