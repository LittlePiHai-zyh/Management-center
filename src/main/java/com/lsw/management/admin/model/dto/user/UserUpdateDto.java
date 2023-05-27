package com.lsw.management.admin.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/5/27  16:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDto {

    private Integer id;

    private String roles;

}
