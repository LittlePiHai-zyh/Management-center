package com.zhangyh.management.admin.controller;

import com.zhangyh.management.admin.service.PushMsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangyh
 * @Date 2023/4/12 16:38
 * @desc
 */
@RestController
@RequestMapping("/push")
public class PushMsgController {

    @Resource
    PushMsgService pushMsgService;



    @GetMapping("/one")
    public void pushMsgToOne(String userId, String msg) {
        pushMsgService.pushMsgToOne(userId,msg);
    }

    @GetMapping("/all")
    public void pushMsgToAll(@RequestParam String msg) {
        pushMsgService.pushMsgToAll(msg);
    }

}
