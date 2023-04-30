package com.lsw.management.common.constants;

import lombok.Getter;

/**
 * @Author: lsw
 * @desc
 * @date: 2023/4/20  21:55
 */
@Getter
public enum MajorEnum {
    ELECTRICAL_ENGINEERING(1, "电力工程"),
    ELECTRONIC_INFORMATION_ENGINEERING(2, "电子信息工程"),
    COMPUTER_SCIENCE_AND_TECHNOLOGY(3, "计算机科学与技术"),
    MECHANICAL_ENGINEERING(4, "机械工程"),
    MATERIALS_SCIENCE_AND_ENGINEERING(5, "材料科学与工程"),
    AUTOMATION(6, "自动化"),
    MEASUREMENT_AND_CONTROL_TECHNOLOGY_AND_INSTRUMENT(7, "测控技术与仪器"),
    COMMUNICATION_ENGINEERING(8, "通信工程"),
    CHEMICAL_ENGINEERING_AND_TECHNOLOGY(9, "化学工程与工艺"),
    ENERGY_AND_POWER_ENGINEERING(10, "能源动力工程"),
    ARCHITECTURE(11, "建筑学"),
    CIVIL_ENGINEERING(12, "土木工程"),
    SOFTWARE_ENGINEERING(13, "软件工程"),
    LOGISTICS_MANAGEMENT(14, "物流管理"),
    ENGINEERING_MANAGEMENT(15, "工程管理"),
    FINANCE(16, "金融学"),
    ACCOUNTING(17, "会计学"),
    INTERNATIONAL_ECONOMIC_AND_TRADE(18, "国际经济与贸易"),
    LAW(19, "法学"),
    CHINESE_LANGUAGE_AND_LITERATURE(20, "汉语言文学"),
    ENGLISH_LANGUAGE_AND_LITERATURE(21, "英语语言文学"),
    HISTORY(22, "历史学"),
    MATHEMATICS_AND_APPLIED_MATHEMATICS(23, "数学与应用数学"),
    STATISTICS(24, "统计学"),
    PHYSICS(25, "物理学"),
    BIOLOGICAL_SCIENCE(26, "生物科学"),
    GEOGRAPHIC_INFORMATION_SCIENCE(27, "地理信息科学"),
    ENVIRONMENTAL_SCIENCE_AND_ENGINEERING(28, "环境科学与工程"),
    POLITICAL_SCIENCE_AND_PUBLIC_ADMINISTRATION(29, "政治学与行政学"),
    SOCIAL_WORK(30, "社会工作"),
    TOURISM_MANAGEMENT(31, "旅游管理"),
    ADVERTISING(32, "广告学"),
    JOURNALISM_AND_COMMUNICATIONS(33, "新闻传播学"),
    FINE_ARTS(34, "美术学"),
    MUSIC(35, "音乐学"),
    FILM_AND_TELEVISION_STUDIES(36, "影视学"),
    VISUAL_COMMUNICATION_DESIGN(37, "视觉传达设计"),
    INDUSTRIAL_DESIGN(38, "工业设计"),
    FASHION_DESIGN(39, "服装设计"),
    MEDICINE(40, "医学"),
    AGRICULTURE(41, "农学"),
    FORESTRY(42, "林学"),
    HYDRAULIC_ENGINEERING(43, "水利工程"),
    DIGITAL_MEDIA_TECHNOLOGY(44, "数字媒体技术"),
    PHYSICAL_EDUCATION(45, "体育教育"),
    SPORTS_TRAINING(46, "体育训练"),
    SPORTS_REHABILITATION(47, "体育运动康复"),
    IOT_ENGINEERING(48, "物联网工程"),
    ;

    private Integer code;

    private String name;

    MajorEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
