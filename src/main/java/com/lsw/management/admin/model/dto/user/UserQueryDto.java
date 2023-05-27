package com.lsw.management.admin.model.dto.user;

import com.lsw.management.admin.model.dto.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lsw
 * @Date 2023/4/7 14:03
 * @desc
 */
@EqualsAndHashCode
@Data
public class UserQueryDto extends PageRequest implements Serializable {

    private static final long serialVersionUID = 543654641L;

    private String username;

    private String name;

    private String type;

    private String professional;
}
