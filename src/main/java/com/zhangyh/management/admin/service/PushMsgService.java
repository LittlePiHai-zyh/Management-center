package com.zhangyh.management.admin.service;

/**
 * @author zhangyh
 * @Date 2023/4/12 16:35
 * @desc
 */
public interface PushMsgService {
    /**
     * 推送给指定用户
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送给所有用户
     */
    void pushMsgToAll(String msg);

}
