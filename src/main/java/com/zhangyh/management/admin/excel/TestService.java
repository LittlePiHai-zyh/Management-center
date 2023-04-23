package com.zhangyh.management.admin.excel;

import com.zhangyh.management.common.http.response.ApiResponse;
import com.zhangyh.management.common.http.response.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyh
 * @Date 2023/4/21 11:15
 * @desc
 */
@Slf4j
@Service
public class TestService {
    /**
     * 下载
     *
     * @param response
     * @return
     */
    public ApiResponse downloadKolList(HttpServletResponse response) {
        List<TestExport> data = buildExportKolList();
        String fileName = "数据清单";
        String sheet = "数据清单";
        TestExcelUtils.writeKolWithSheet(fileName, sheet, data, TestExport.class, response);
        return ResponseHelper.success();
    }

    private List<TestExport> buildExportKolList() {
        List<TestExport> list = new ArrayList<>();
        TestExport export = new TestExport();
        export.setKey("A");
        export.setValue("1");


        TestExport export1 = new TestExport();
        export1.setKey("B");
        export1.setValue("2");

        list.add(export);
        list.add(export1);
        return list;
    }
}
