package com.lsw.management;

import com.lsw.management.common.util.table.sync.annotation.EnableTableSync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.lsw.management.admin.mapper")
@EnableAsync
@SpringBootApplication
@EnableTableSync("com.lsw.management.admin.model.po")
public class GDMCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GDMCenterApplication.class, args);
    }

}
