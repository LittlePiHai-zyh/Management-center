package com.zhangyh.management;

import com.zhangyh.management.common.util.table.sync.annotation.EnableTableSync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableTableSync("com.zhangyh.management")
public class ManagementCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementCenterApplication.class, args);
    }

}
