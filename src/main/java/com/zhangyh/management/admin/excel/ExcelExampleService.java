package com.zhangyh.management.admin.excel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyh
 * @Date 2023/4/21 11:06
 * @desc
 */
public class ExcelExampleService {

    public static List<String> queryProvinceList() {
        return Arrays.asList("浙江省", "湖南省", "贵州省");
    }

    public static Map<String, List<String>> queryMunicipalityList() {
        Map<String, List<String>> map = new HashMap<>(4);
        map.put("浙江省", Arrays.asList("杭州市", "温州市", "宁波市"));
        map.put("湖南省", Arrays.asList("长沙市", "邵阳市", "常德市"));
        map.put("贵州省", Arrays.asList("贵阳市", "遵义市", "安顺市"));
        return map;
    }

    public static Map<String, List<String>> queryDistrictList() {
        Map<String, List<String>> map = new HashMap<>(8);
        map.put("杭州市", Arrays.asList("上城区", "下城区", "萧山区"));
        map.put("温州市", Arrays.asList("鹿城区", "龙湾区", "瓯海区", "洞头区"));
        map.put("长沙市", Arrays.asList("芙蓉区", "天心区", "岳麓区"));
        map.put("邵阳市", Arrays.asList("双清区", "大祥区", "北塔区"));
        map.put("贵阳市", Arrays.asList("南明区", "云岩区", "花溪区"));
        return map;
    }
}
