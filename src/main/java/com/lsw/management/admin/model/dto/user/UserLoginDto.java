package com.lsw.management.admin.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author lsw
 * @Date 2023/4/6 17:19
 * @desc
 */
@Getter
@Setter
public class UserLoginDto {

    @NotBlank(message = "USER_ACCOUNT_CAN_NOT_EMPTY")
    private String username;

    @NotBlank(message = "PASSWORD_CAN_NOT_EMPTY")
    private String password;

    @NotBlank(message = "VERIFY_CODE_CAN_NOT_EMPTY")
    private String verifyCode;

    @NotBlank(message = "RANDOM_KEY_CAN_NOT_EMPTY")
    private String randomKey;
}
