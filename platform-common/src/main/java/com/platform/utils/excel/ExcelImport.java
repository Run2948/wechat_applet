package com.platform.utils.excel;


import com.platform.utils.RRException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Excel文件导入的基本功能类
 * 可导入EXCEL2003 和 EXCEL2007格式。
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年10月28日 13:11:27
 */
public class ExcelImport {
    /**
     * excel2003扩展名
     */
    public static final String EXCEL03_EXTENSION = ".xls";

    /**
     * excel2007扩展名
     */
    public static final String EXCEL07_EXTENSION = ".xlsx";

    private ExcelImport() {
    }

    /**
     * 解析EXCEL数据为 List<String[]>
     *
     * @param excelFile 要解析的上传EXCEL文件
     * @return List<String[]) 行（列）
     */
    public static List<String[]> getExcelData07(MultipartFile excelFile) {
        List<String[]> resultList = null;

        if (null == excelFile || excelFile.isEmpty()) {
            throw new RRException("文件内容为空！");

        }

        Excel2007Reader excel07 = new Excel2007Reader();
        try {
            excel07.process(excelFile.getInputStream(), false);
        } catch (Exception e) {
            throw new RRException("excel解析失败！");
        }
        resultList = excel07.getSheetData(0);

        return resultList;
    }

    /**
     * 解析EXCEL数据为 List<String[]>
     *
     * @param excelFile 要解析的上传EXCEL文件
     * @return List<String[]) 行（列）
     */
    public static List<String[]> getExcelData03(MultipartFile excelFile) {
        List<String[]> resultList = null;

        if (null == excelFile || excelFile.isEmpty()) {
            throw new RRException("文件内容为空！");

        }

        Excel2003Reader excel03 = new Excel2003Reader();// 实例化excel处理对象
        try {
            excel03.process(excelFile.getInputStream());
        } catch (IOException e) {
            throw new RRException("excel解析失败！");
        }
        resultList = excel03.getSheetData(0);

        return resultList;
    }

    /**
     * 通过解析MultipartFile对象获取excel内容，并且将其拼装为List<String[]>对象返回
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public static List<String[]> getExcelData(MultipartFile excelFile)
            throws RRException {
        List<String[]> resultList = null;

        if (!excelFile.isEmpty()) {// 上传的文件不能为空
            String excelFileName = excelFile.getOriginalFilename();// 文件名（带后缀）
            if (excelFileName.toLowerCase().endsWith(EXCEL03_EXTENSION)) {// 如果文件是以.xls为后缀
                Excel2003Reader excel03 = new Excel2003Reader();// 实例化excel处理对象
                try {
                    excel03.process(excelFile.getInputStream());
                } catch (IOException e) {
                    throw new RRException("excel解析失败！");
                }
                resultList = excel03.getSheetData(0);
            } else if (excelFileName.toLowerCase().endsWith(EXCEL07_EXTENSION)) {// 如果文件是以.xlsx为后缀
                Excel2007Reader excel07 = new Excel2007Reader();
                try {
                    excel07.process(excelFile.getInputStream(), false);
                } catch (Exception e) {
                    throw new RRException("excel解析失败！");
                }
                resultList = excel07.getSheetData(0);
            }
        }

        return resultList;
    }

    /**
     * 通过解析MultipartFile对象获取excel内容，并且将其拼装为Map<Integer, List<String[]>>对象返回
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public static Map<Integer, List<String[]>> getExcelDataAll(MultipartFile excelFile)
            throws RRException {
        Map<Integer, List<String[]>> result = null;

        if (!excelFile.isEmpty()) {// 上传的文件不能为空
            String excelFileName = excelFile.getOriginalFilename();// 文件名（带后缀）
            if (excelFileName.toLowerCase().endsWith(EXCEL03_EXTENSION)) {// 如果文件是以.xls为后缀
                Excel2003Reader excel03 = new Excel2003Reader();// 实例化excel处理对象
                try {
                    excel03.process(excelFile.getInputStream());
                } catch (IOException e) {
                    throw new RRException("excel解析失败！");
                }
                result = excel03.getSheetData();
            } else if (excelFileName.toLowerCase().endsWith(EXCEL07_EXTENSION)) {// 如果文件是以.xlsx为后缀
                Excel2007Reader excel07 = new Excel2007Reader();
                try {
                    excel07.process(excelFile.getInputStream(), true);
                } catch (Exception e) {
                    throw new RRException("excel解析失败！");
                }
                result = excel07.getSheetData();
            }
        }

        return result;
    }
}
