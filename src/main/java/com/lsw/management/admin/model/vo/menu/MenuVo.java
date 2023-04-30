package com.lsw.management.admin.model.vo.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lsw.management.admin.model.po.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/30  8:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuVo {

    private Integer key;

    private Integer menuId;

    private Integer pid;

    private String title;

    private String icon;

    private String iconColor;

    private String path;

    private String name;

    private Integer hidden;

    private String permissions;

    private String component;

    @JsonIgnore
    transient final BeanCopier beanCopier = BeanCopier.create(Menu.class, MenuVo.class, false);

    public MenuVo(Menu menu) {
        beanCopier.copy(menu, this, null);
    }
}
