package com.zhangyh.management.admin.excel.t;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author zhangyh
 * @Date 2023/4/21 12:27
 * @desc
 */
public class ExcelUtils {
    private static final Integer ENDROW = 3000;

    /**
     * @param filePath   文件路径
     * @param mapOneList 一级数据
     * @param map        N级数据（需递归获取每层数据，统一封装为map）
     * @param startRow   开始插入行
     * @param startCol   开始插入列
     * @param levels     N级联动
     */
    public static void export(String filePath, List<String> mapOneList, Map<String, List<String>> map, Integer startRow, Integer startCol, Integer levels) throws Exception {

        FileInputStream fis = new FileInputStream(filePath);
        // 1、创建Excel
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet mainSheet = workbook.getSheetAt(0);
        //2、N级联动 sheet
        Sheet mapSheet = workbook.createSheet("SHEET_MAP");
        //联动关系写入mapSheet
        workbook.setSheetHidden(workbook.getSheetIndex(mapSheet), true);// 设置sheet是否隐藏
        // 3、将数据写入隐藏的sheet中并做好关联关系
        writeData(workbook, mapSheet, mapOneList, map);//
        // 4、设置数据有效性----CharStatusEnum.A从第几列开始插入，A从第1列，B第二列
        for (CharStatusEnum charStatusEnum : CharStatusEnum.values()) {
            if (charStatusEnum.getValue() == startCol) {
                setDataValid(workbook, mainSheet, mapOneList, charStatusEnum, startRow, levels);
            }
        }
        FileOutputStream os = null;
        try {
            String exisname = filePath.substring(0, filePath.lastIndexOf("\\"));
            File f = new File(exisname);
            if (!f.exists()) {
                f.mkdirs();
            }
            // 创建可写入的Excel工作簿
            File file = new File(filePath);
            if (!file.exists()) {
                boolean bool = file.createNewFile();
                System.out.println(bool);
            } else {
                file.delete();
                file.createNewFile();
            }
            os = new FileOutputStream(filePath);
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    private static void writeData(Workbook hssfWorkBook, Sheet mapSheet, List<String> provinceList, Map<String, List<String>> siteMap) {

        //循环将父数据写入siteSheet的第1行中
        int siteRowId = 0;
        Row provinceRow = mapSheet.createRow(siteRowId);
        provinceRow.createCell(0).setCellValue("父列表");
        for (int i = 0; i < provinceList.size(); i++) {
            //有多少个省，创建多少个下拉框
            provinceRow.createCell(i + 1).setCellValue(provinceList.get(i));
        }
        // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
        Iterator<String> keyIterator = siteMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            List<String> son = siteMap.get(key);
            Row siteRow = mapSheet.createRow(siteRowId++);

            siteRow.createCell(0).setCellValue(key);
            for (int i = 0; i < son.size(); i++) {
                siteRow.createCell(i + 1).setCellValue(son.get(i));
            }

            // 添加名称管理器: 偏移量、第几行、一共多少列
            String range = getRange(1, siteRowId, son.size());
            Name name = hssfWorkBook.createName();
            name.setNameName(key);
            String formula = mapSheet.getSheetName() + "!" + range;
            name.setRefersToFormula(formula);
        }
    }

    /**
     * 计算formula
     *
     * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId    第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     */
    private static String getRange(int offset, int rowId, int colCount) {
        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char) ('A' + 25);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }



    private static void setDataValid(Workbook workbook, Sheet mainSheet, List<String> provinceList, CharStatusEnum charEnum, Integer startRow, Integer levels) {
        //设置省份下拉
        DataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) mainSheet);
        String[] dataArray = provinceList.toArray(new String[0]);
        Sheet hidden = workbook.createSheet("hidden");
        Cell cell = null;
        for (int i = 0, length = dataArray.length; i < length; i++) {
            String name = dataArray[i];
            Row row = hidden.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(name);
        }

        Name namedCell = workbook.createName();
        namedCell.setNameName("hidden");
        namedCell.setRefersToFormula("hidden!$A$1:$A$" + dataArray.length);
        //加载数据,将名称为hidden的
        XSSFDataValidationConstraint constraint = new XSSFDataValidationConstraint(DataValidationConstraint.ValidationType.LIST, "hidden");
        // 四个参数分别是：起始行、终止行、起始列、终止列.
        CellRangeAddressList provinceRangeAddressList = new CellRangeAddressList(startRow - 1, ENDROW, charEnum.getValue() - 1, charEnum.getValue() - 1);
        DataValidation provinceDataValidation = dvHelper.createValidation(constraint, provinceRangeAddressList);
        provinceDataValidation.createErrorBox("error", "请选择正确");
        provinceDataValidation.setShowErrorBox(true);
        // 设置sheet是否隐藏
        workbook.setSheetHidden(workbook.getSheetIndex(hidden), true);
        mainSheet.addValidationData(provinceDataValidation);
        // 设置非第一层级的数据下拉
        for (int i = 0; i <= 10; i++) {
            // setDataValidation('B', mainSheet, i + 1, 2);// "B"是指父类所在的列，i+1初始值为1代表从第2行开始
            // 2要与“B”对应，为B的列号加1，假如第一个参数为“C”，那么对应的第四个参数就为3
            setDataValidation(charEnum.getCharacter(), mainSheet, i + 1, charEnum.getValue(), levels);

        }

    }

    /**
     * 设置有效性
     *
     * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
     * @param sheet
     * @param rowNum 行数
     * @param colNum 列数
     */
    private static void setDataValidation(char offset, Sheet sheet, int rowNum, int colNum, Integer levels) {
        DataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        for (Integer i = 0; i < levels - 1; i++) {
            sheet.addValidationData(getDataValidationByFormula("INDIRECT($" + (char) (offset + i) + (rowNum) + ")", rowNum - 1, colNum + i, dvHelper));
        }
    }

    private static DataValidation getDataValidationByFormula(String formulaString, int naturalRowIndex, int naturalColumnIndex, DataValidationHelper dvHelper) {
        DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(formulaString);
        CellRangeAddressList regions = new CellRangeAddressList(naturalRowIndex, 65535, naturalColumnIndex, naturalColumnIndex);
        DataValidation data_validation_list = dvHelper.createValidation(dvConstraint, regions);
        data_validation_list.setEmptyCellAllowed(false);
        if (data_validation_list instanceof XSSFDataValidationHelper) {
            data_validation_list.setShowErrorBox(true);
        } else {
            //  data_validation_list.setSuppressDropDownArrow(false);
        }
        // 设置输入信息提示信息
        data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
        return data_validation_list;
    }

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\Users\\Desktop\\t.xlsx";
        //所有一级
        List<String> mapOneList = new ArrayList<String>();
        mapOneList.add("广东省");
        mapOneList.add("湖北省");
        mapOneList.add("安徽省");
        //一二级关系map
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("广东省", Arrays.asList("广州市", "佛山市"));
        map.put("湖北省", Arrays.asList("武汉市", "荆州市"));
        map.put("广州市", Arrays.asList("白云区", "越秀区"));
        map.put("佛山市", Arrays.asList("顺德区", "南海区"));
        map.put("安徽省", Arrays.asList("合肥市", "芜湖市"));
        map.put("合肥市", Arrays.asList("肥东县", "肥西县"));
        map.put("肥东县", Arrays.asList("西山镇","店埠"));
        map.put("西山镇", Arrays.asList("西山镇123","店埠123"));
        //第2行第3列插入5级联动
        ExcelUtils.export(filePath,mapOneList,map,2,3,5);
    }
}
