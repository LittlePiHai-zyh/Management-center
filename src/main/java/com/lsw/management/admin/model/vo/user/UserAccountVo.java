package com.lsw.management.admin.model.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsw.management.admin.model.po.user.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author lsw
 * @Date 2023/4/6 16:37
 * @desc
 */
@Data
@NoArgsConstructor
public class UserAccountVo {

    private Integer id;

    private String username;

    private Integer state;

    @JsonIgnore
    transient final BeanCopier beanCopier = BeanCopier.create(UserAccount.class, UserAccountVo.class, false);

    public UserAccountVo(UserAccount userAccount) {
        beanCopier.copy(userAccount, this, null);
    }
}
