package com.platform.utils.excel;

import com.platform.utils.RRException;
import com.platform.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 导出EXCEL功能包主类
 * 使用POI进行EXCEL导出的功能类。
 * 目前简单处理，未设置内存优化。 数据量偏大，出现性能问题时再处理。
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年10月28日 13:11:27
 */
public class ExcelExport {

    /**
     * EXCEL 2003 扩展名
     */
    public static final String EXCEL03_EXTENSION = ".xls";

    /**
     * EXCEL 2007 扩展名
     */
    public static final String EXCEL07_EXTENSION = ".xls";

    /**
     * 工作表
     */
    private Workbook workBook;

    /**
     * 导出文件名
     */
    private String exportFileName;

    /**
     * 日志
     */
    private Log logger = LogFactory.getLog(ExcelExport.class);

    /**
     * 默认构造方法。
     * 自动生成工作表。
     */
    public ExcelExport() {

        this("workbook.xlsx");

    }

    /**
     * 指定导出文件名的构造方法
     * 指定导出文件名。 同时根据指定文件名的扩展名确定导出格式是03或是07 。
     * 不带扩展名默认为07格式。（POI 07格式支持内存优化）
     *
     * @param exportFileName 要导出的文件名（带扩展名。不带扩展名默认为07）
     */
    public ExcelExport(String exportFileName) {

        if (StringUtils.isNullOrEmpty(exportFileName)) {
            exportFileName = "workbook.xlsx";
        }

        String fileName = exportFileName.toLowerCase();

        // 根据文件名扩展名生成EXCEL格式。
        if (fileName.endsWith(ExcelExport.EXCEL03_EXTENSION)) {
            workBook = new HSSFWorkbook();
        } else if (exportFileName.endsWith(ExcelExport.EXCEL07_EXTENSION)) {
            workBook = new XSSFWorkbook();
        } else {
            workBook = new XSSFWorkbook();
            fileName += ".xlsx";
            // 按正则替换？ 处理文件名最后一个字符为“.” 的问题
            // fileName = fileName.replaceAll("..", ".");
        }

        // 最后按小写文件名输出
        this.exportFileName = fileName;
    }

    /**
     * 主要功能: 导出EXCEL到response
     * 注意事项:无
     *
     * @param response HttpServletResponse
     */
    public void export(HttpServletResponse response) {

        // response.setHeader("Content-Disposition","attachment;filename=totalExcel.xls");
        response.reset();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        // response.setContentType("text/html;charset=UTF-8");
        // response.setContentType("APPLICATION/*");
        String fileName = null;

        // 处理编码
        try {

            //modify by ld 2017年4月10日19:04:37 根据浏览器编码文件名
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = URLEncoder.encode(exportFileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(exportFileName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (UnsupportedEncodingException e) {

            fileName = "export.xls";
        }

        response.setHeader("Content-Disposition", "attachment;filename="
                + fileName);

        ServletOutputStream output;

        try {
            output = response.getOutputStream();
            workBook.write(output);
            output.flush();
            output.close();
        } catch (IOException e) {
            logger.error("导出文件出错了", e);
            throw new RRException("文件导出错误");
        }

    }

    /**
     * 主要功能:简单测试
     * 注意事项:无
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ExcelExport ee1 = new ExcelExport();

        List<Object[]> list1 = new ArrayList<Object[]>();

        List<Object> obj = new ArrayList<Object>();

        obj.add("370681198002042214");
        obj.add(new Date());
        obj.add(new Timestamp(System.currentTimeMillis()));
        obj.add(1);
        obj.add(11111111111111L);
        obj.add(123456.123456f);
        obj.add(123456789.12345678);
        obj.add(new BigDecimal("123456789123456789.1234"));
        obj.add(true);

        list1.add(obj.toArray());

        String[] header = new String[]{"身份证号", "日期", "时间", "整型", "长整", "浮点",
                "双精度", "大数", "布尔"};
        ee1.addSheetByArray("测试1", list1, header);

        OutputStream fis;
        try {
            fis = new FileOutputStream("D:\\test1.xlsx");

            ee1.getWorkbook().write(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 主要功能: 在EXCEL中添加一个Sheet
     * 注意事项:添加Sheet 并将List<Object[]> 中的数据填充
     *
     * @param sheetName  表单名字
     * @param list       要填充的数据  以Object[] 表示单条记录
     * @param colCaption 要生成的表头
     */
    public void addSheetByArray(String sheetName, List<Object[]> list,
                                String[] colCaption) {

        // 创建表单
        Sheet sheet;

        if (StringUtils.isNullOrEmpty(sheetName)) {
            sheet = workBook.createSheet();
        } else {
            sheet = workBook.createSheet(sheetName);
        }

        // 生成标题行 （表头）
        createCaptionRow(colCaption, sheet);
        // 记录列数
        int colNum = 0;
        // 从第二行开始
        int startRow = 1;
        for (Object[] obj : list) {

            Row row = sheet.createRow(startRow++);
            int cols = createRowData(row, Arrays.asList(obj));
            row.setHeight((short) 400);
            colNum = colNum > cols ? colNum : cols;
        }

        adjustCellWidth(colNum, startRow, sheet);

    }

    /**
     * 主要功能:在EXCEL中添加一个Sheet
     * 注意事项:添加Sheet 并将List<Object[]> 中的数据填充
     *
     * @param sheetName  表单名字
     * @param list       要填充的数据  以Map<String,Object> 表示单条记录
     * @param colCaption 要生成的表头
     */
    public void addSheetByMap(String sheetName, List<Map<String, Object>> list,
                              String[] colCaption) {

        // 创建表单
        Sheet sheet;

        if (StringUtils.isNullOrEmpty(sheetName)) {
            sheet = workBook.createSheet();
        } else {
            sheet = workBook.createSheet(sheetName);
        }

        // 生成标题行 （表头）
        createCaptionRow(colCaption, sheet);

        int colNum = 0;
        // 转换数据
        // 从第二行开始
        int startRow = 1;
        for (Map<String, Object> map : list) {
            Row row = sheet.createRow(startRow++);
            int cols = createRowData(row, map.values());
            row.setHeight((short) 400);
            colNum = colNum > cols ? colNum : cols;
        }

        adjustCellWidth(colNum, startRow, sheet);

        // 处理宽度

    }

    /**
     * 主要功能: 判断fiel
     * 注意事项:无
     *
     * @param name    要判断的字符
     * @param ignores 要忽略的数组
     * @return 存在返回true
     */
    private boolean isInIgnors(String name, String[] ignores) {

        for (String ignore : ignores) {
            if (name.equals(ignore)) {
                return true;
            }
        }

        return false;

    }

    /**
     * 主要功能: 生成每一行的数据
     * 注意事项:无
     *
     * @param row  要处理的行
     * @param coll 集合类型（List Map.values) 要处理的数据
     * @return int  列数
     */
    private int createRowData(Row row, Collection<Object> coll) {

        int cellNum = 0;
        for (Object obj : coll) {

            // 空值 按照 "" 字符串处理
            if (obj == null) {
                obj = "";
            }
            // 转换一个字符串值， 以方便判断
            String strValue = obj.toString();

            // 创建单元格
            Cell cell = row.createCell(cellNum);

            // 根据单元格的类型设置值
            // 公式型(普通BEAN和MAP中暂时并不存在)
            if (strValue.startsWith("FORMULA:")) {

            } else {
                // 处理宽度

                // 设置单元格的值
                setCellValue(cell, obj);

            }

            // 处理下一个单元格
            cellNum++;

        }

        return cellNum;

    }

    /**
     * 主要功能: 设置某单元格的值
     * 注意事项: 同时根据值类型设置样式
     * TODO: 对数字类型做进一步解析和判断。 对需要按%显示的进行处理。 %可以从表头中获取？
     * 对于CellType 和CellValue 的设置还需要详细参考文档，避免产生不必要的错误转换
     * 日期类型格式以通用格式。 待替换为全局变量
     * 数字保留小数和显示百分比格式暂时未设置
     *
     * @param cell 单元格
     * @param obj  原始值（ null已转换为"" 空字符串)
     */
    private void setCellValue(Cell cell, Object obj) {

        CreationHelper createHelper = workBook.getCreationHelper();
        String strValue = obj.toString();

        // 时间戳类型 要先判断时间戳类型再判断时期类型
        if (obj instanceof Timestamp) {
            // 转换为Date后赋值
            Date dt = (Timestamp) obj;
            // String value = DateUtil.format(dt, "yyyy-MM-dd HH:mm:ss");
            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
                    "yyyy-MM-dd HH:mm:ss"));

            cell.setCellValue(dt);
            cell.setCellStyle(cellStyle);

        }
        // 日期型
        else if (obj instanceof Date) {

            // String value = DateUtil.format((Date)obj, "yyyy-MM-dd");
            // cell.setCellValue(value);

            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
                    "yyyy-MM-dd"));

            cell.setCellValue((Date) obj);
            cell.setCellStyle(cellStyle);

        }

        /*
         * 由于数字类型有int、long、double、BigDecimal 多种类型 输出格式可能要求数字、百分比、保留几位小数等多种格式. 目前仅按照数字格式输出
         */
        else if (obj instanceof Integer) {
            cell.setCellType(CellType.NUMERIC);
            DecimalFormat df = new DecimalFormat("0");
            String value = df.format(obj);
            cell.setCellValue(value);

        } else if (obj instanceof Long) {
            cell.setCellType(CellType.NUMERIC);
            DecimalFormat df = new DecimalFormat("0");
            String value = df.format(obj);
            cell.setCellValue(value);
        } else if (obj instanceof Float) {
            cell.setCellType(CellType.NUMERIC);
            DecimalFormat df = new DecimalFormat("0.0000");
            String value = df.format(obj);
            cell.setCellValue(value);
        } else if (obj instanceof Double) {
            cell.setCellType(CellType.NUMERIC);
            DecimalFormat df = new DecimalFormat("0.0000");
            String value = df.format(obj);
            cell.setCellValue(value);

        }
        // BigDecimal
        else if (obj instanceof BigDecimal) {
            cell.setCellType(CellType.STRING);

            cell.setCellValue(strValue);

        }

        // 数字 //数字型要判断 对于百分比显示 、单位是万元等的显示细节要处理
        // else if (StringUtil.isFloatNumeric(strValue)) {

        // cell.setCellType(CellType.NUMERIC);

        // 暂时未处理BigDecimal 类型的数据，如果有问题，只能使用String
        // cell.setCellValue(Double.valueOf(strValue));

        // modi by zhang @2016年11月14日14:13:11 处理浮点小数格式 暂时无法统一位数
        // DecimalFormat df = new DecimalFormat("0.0000");
        // String whatYourWant = df.format();
        // 按String 方式输出数字
        // BigDecimal bd = new BigDecimal(strValue);
        // cell.setCellValue(bd.toPlainString());

        // 主要处理小数的位数格式，暂时不好判断，输出原值
        // cell.setCellValue(strValue);

        // CellStyle style = workBook.createCellStyle();
        // style.setDataFormat(workBook.createDataFormat().getFormat("0.00%"));

        // }
        // 字符串类型
        else if (obj instanceof String) {
            // modi at 2016年11月14日14:27:51 by zhang
            // 补充设置单元格类型，避免编码类被当作数字类型
            cell.setCellType(CellType.STRING);
            // end modi

            cell.setCellValue(strValue);

        } else {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(strValue);
        }

    }

    /**
     * 主要功能: 生成EXCEL的第一行表头
     * 注意事项: 默认按第一行生成表头。
     *
     * @param colCaption 表头字符数组
     * @param sheet      表单
     */
    private void createCaptionRow(String[] colCaption, Sheet sheet) {
        // 默认第一行
        Row row = sheet.createRow(0);
        row.setHeight((short) 400);

        if (colCaption == null) {
            return;
        }
        // 按列生成表头
        for (int i = 0; i < colCaption.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(getStyle("title"));
            cell.setCellType(CellType.STRING);
            cell.setCellValue(colCaption[i]);

        }

    }

    /**
     * 主要功能: 调整列宽
     * 注意事项:无
     *
     * @param cols  列数
     * @param rows  行数
     * @param sheet sheet
     */
    private void adjustCellWidth(int cols, int rows, Sheet sheet) {

        int[] cellWidth = new int[cols];

        // 取列中最长的行
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                Cell cell = sheet.getRow(row).getCell(col);
                String value = getCellValue(cell);
                int length = value.getBytes().length;
                if (length > cellWidth[col]) {
                    cellWidth[col] = length;
                }
            }
        }

        for (int j = 0; j < cellWidth.length; j++) {
            if (cellWidth[j] > 254) {
                cellWidth[j] = 254;
            }

            // 设置列宽度 单位为 1/256 个字符 设置加10%
            sheet.setColumnWidth(j,
                    cellWidth[j] * 12 > 255 ? (255 * 256 / 10) : (cellWidth[j] * 12 * 256 / 10));
        }

    }

    /**
     * 主要功能: 取单元格的值
     * 注意事项:按数字和字符两种类型先处理。 BOOLEAN FORMULA 先不处理。 日期格式如何判断？
     *
     * @param cell 单元格
     * @return 单元格的值
     */
    @SuppressWarnings("deprecation")
    private String getCellValue(Cell cell) {

        String value = "";

        try {
            // 按数字和字符两种类型先处理。 BOOLEAN FORMULA 先不处理。 日期格式如何判断？
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    value = cell.getNumericCellValue() + "1111"; // 增加4位的长度
                    // 数字格式包含日期格式，但暂时无法判断日期格式
                    // 除非使用固定宽度。。。。。。。

                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                default:
                    break;

            }
        } catch (Exception e) {
            // 不对异常做处理。仅打印到控制台以供调试 
            // 占不打印
            // e.printStackTrace();
        }

        return value;

    }

    /**
     * 主要功能: 生成表头样式（默认样式）
     * 注意事项:仅生成默认样式
     *
     * @param type 类型 title 或  data
     * @return CellStyle
     */
    private CellStyle getStyle(String type) {
        CellStyle cs = workBook.createCellStyle();// 创建一个style
        if ("title".equals(type)) {// 表头样式
            Font font = workBook.createFont();// 创建一个字体
            // font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
            font.setBold(true);
            // cs.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
            cs.setAlignment(HorizontalAlignment.CENTER);
            cs.setFillBackgroundColor(HSSFColor.SKY_BLUE.index);// 设置背景色
            cs.setFont(font);
        }
        return cs;
    }

    /**
     * 取得wb的值
     *
     * @return wb值.
     */
    public Workbook getWorkbook() {
        return workBook;
    }

    /**
     * 设定wb的值
     *
     * @param workBook 设定值
     */
    public void setWorkbook(Workbook workBook) {
        this.workBook = workBook;
    }

    /**
     * 取得exportFileName的值
     *
     * @return exportFileName值.
     */
    public String getExportFileName() {
        return exportFileName;
    }

    /**
     * 设定exportFileName的值
     *
     * @param exportFileName 设定值
     */
    public void setExportFileName(String exportFileName) {
        this.exportFileName = exportFileName;
    }

}
