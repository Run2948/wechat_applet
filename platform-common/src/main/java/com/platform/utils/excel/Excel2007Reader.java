
package com.platform.utils.excel;

import com.platform.utils.RRException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel2007解析器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年10月28日 13:11:27
 */
public class Excel2007Reader extends DefaultHandler {

    /**
     * Table with unique strings
     */
    private ReadOnlySharedStringsTable sharedStringsTable;

    /**
     * 总共解析多少列
     */
    private final int minColumnCount = 0;

    /**
     * 判断是否值标签‘V’
     */
    private boolean vIsOpen;

    /**
     * 单元数据类型，startElement中确认赋值，endElement中使用判断
     */
    private XssfDataType nextDataType;

    /**
     * 当前解析列下标
     */
    private int thisColumn = -1;

    /**
     * 已完成解析列最近下标
     */
    private int lastColumnNumber = 0;

    /**
     * 单元格值内容临时存储
     */
    private StringBuffer value = new StringBuffer();

    /**
     * 单行数据集合
     */
    private List<String> rowlist = new ArrayList<String>();

    /**
     * 当前sheet
     */
    private int sheetIndex = -1;

    /**
     * 单Sheet数据
     */
    private List<String[]> sheetData = new ArrayList<String[]>();

    /**
     * 多Sheet数据
     */
    private Map<Integer, List<String[]>> workData = new HashMap<Integer, List<String[]>>();

    private DataFormatter formatter;

    /**
     * 格式索引暂时不用
     */
    private short formatIndex;

    private String formatString;

    /**
     * 样式表暂时不用
     */
    private StylesTable stylesTable;

    /**
     * 解析单Sheet内容
     *
     * @param strings          字符串
     * @param sheetInputStream 输入流
     * @throws IOException                  IO异常
     * @throws ParserConfigurationException 解析异常
     * @throws SAXException                 SAX异常
     */
    private void processSheet(ReadOnlySharedStringsTable strings,
                              InputStream sheetInputStream)
            throws IOException, ParserConfigurationException, SAXException {
        sheetIndex++;
        this.sharedStringsTable = strings;
        InputSource sheetSource = new InputSource(sheetInputStream);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader sheetParser = saxParser.getXMLReader();
        sheetParser.setContentHandler(this);
        sheetParser.parse(sheetSource);
    }

    /**
     * 解析多Sheet内容
     * 异常处理暂时不完整
     *
     * @param fileStream 文件流
     * @param isMuti     是否多个sheet
     */
    public void process(InputStream fileStream, boolean isMuti) {
        OPCPackage xlsxPackage;
        try {
            xlsxPackage = OPCPackage.open(fileStream);

            ReadOnlySharedStringsTable sharedStringsTable = new ReadOnlySharedStringsTable(
                    xlsxPackage);
            XSSFReader xssfReader = new XSSFReader(xlsxPackage);
            this.stylesTable = xssfReader.getStylesTable();
            this.formatter = new DataFormatter();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                processSheet(sharedStringsTable, stream);
                stream.close();
                List<String[]> data = new ArrayList<String[]>();
                data.addAll(sheetData);
                workData.put(sheetIndex, data);
                sheetData.clear();
                if (!isMuti) {// 如果只加载一个sheet内容，直接跳出循环
                    break;
                }
            }
        } catch (InvalidFormatException e) {
            throw new RRException("解析EXCEL出错");
        } catch (IOException e) {

            e.printStackTrace();
            throw new RRException("解析EXCEL出错");
        } catch (SAXException e) {

            e.printStackTrace();
            throw new RRException("解析EXCEL出错");
        } catch (OpenXML4JException e) {

            e.printStackTrace();
            throw new RRException("解析EXCEL出错");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new RRException("解析EXCEL出错");
        }

    }

    /**
     * Receive notification of the start of an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param name       The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes)
            throws SAXException {
        if ("inlineStr".equals(name) || "v".equals(name)) {// v=> value
            vIsOpen = true;
            value.setLength(0);
        } else if ("c".equals(name)) {// c => cell
            vIsOpen = false;
            String r = attributes.getValue("r");
            int firstDigit = -1;
            for (int c = 0; c < r.length(); ++c) {
                if (Character.isDigit(r.charAt(c))) {
                    firstDigit = c;
                    break;
                }
            }
            thisColumn = nameToColumn(r.substring(0, firstDigit));
            this.nextDataType = XssfDataType.NUMBER;
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType)) {
                nextDataType = XssfDataType.BOOL;
            } else if ("e".equals(cellType)) {
                nextDataType = XssfDataType.ERROR;
            } else if ("inlineStr".equals(cellType)) {
                nextDataType = XssfDataType.INLINESTR;
            } else if ("s".equals(cellType)) {
                nextDataType = XssfDataType.SSTINDEX;
            } else if ("str".equals(cellType)) {
                nextDataType = XssfDataType.FORMULA;
            } else if (cellStyleStr != null) {
                // if (cellStyleStr.equals("2")) {// 判断是否是日期
                // nextDataType = XssfDataType.DATE;
                // }
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (formatIndex == 14 || formatIndex == 15 || formatIndex == 16
                        || formatIndex == 17) {
                    nextDataType = XssfDataType.DATE;
                } else if (formatIndex == 18 || formatIndex == 19 || formatIndex == 20
                        || formatIndex == 21 || formatIndex == 45 || formatIndex == 46
                        || formatIndex == 47) {
                    nextDataType = XssfDataType.TIME;
                } else if (formatIndex == 22) {
                    nextDataType = XssfDataType.DATETIME;
                }
            }
        }

    }

    /**
     * Receive notification of the end of an element.
     * <p>
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param name      The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see org.xml.sax.ContentHandler#endElement
     */
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        String thisStr = null;
        // v标识单元格值标签
        if ("v".equals(name)) {
            // Process the value contents as required.
            // Do now, as characters() may be called more than once
            switch (nextDataType) {
                case BOOL:
                    char first = value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;

                case ERROR:
                    thisStr = "\"ERROR:" + value.toString() + '"';
                    break;

                case FORMULA:
                    // A formula could result in a string value,
                    // so always add double-quote characters.
                    thisStr = '"' + value.toString() + '"';
                    break;

                case INLINESTR:
                    // TODO: have seen an example of this, so it's untested.
                    XSSFRichTextString rtsi = new XSSFRichTextString(
                            value.toString());
                    thisStr = rtsi.toString();
                    break;

                case SSTINDEX:
                    String sstIndex = value.toString();
                    try {
                        int idx = Integer.parseInt(sstIndex);
                        XSSFRichTextString rtss = new XSSFRichTextString(
                                sharedStringsTable.getEntryAt(idx));
                        thisStr = rtss.toString();
                    } catch (NumberFormatException ex) {
                        // @TODO
                    }
                    break;

                case NUMBER:
                    String n = value.toString();
                    BigDecimal bd = new BigDecimal(n);

                    // 未移除最后的0，按默认string处理
                    thisStr = bd.toString();
                    break;
                case DATE:
                    Date date = HSSFDateUtil.getJavaDate(Double.valueOf(value.toString()));
                    thisStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    break;
                case DATETIME:
                    Date dateTime = HSSFDateUtil.getJavaDate(Double.valueOf(value.toString()));
                    thisStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
                    break;
                case TIME:
                    Date time = HSSFDateUtil.getJavaDate(Double.valueOf(value.toString()));
                    thisStr = new SimpleDateFormat("HH:mm:ss").format(time);
                    break;
                default:
                    thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
                    break;
            }
            // 判定已解析列下标与当前下标差异，超过1则表示前面有空单元格，自动填入空串
            for (int i = lastColumnNumber; i < thisColumn - 1; ++i) {
                // 使用"" 作为空串
                rowlist.add("");
            }
            rowlist.add(thisStr);
            // 更新已解析列下标
            if (thisColumn > -1) {
                lastColumnNumber = thisColumn;
            }

        } else if ("row".equals(name)) {// 标识当前行结束，行数据写入结果
            for (int i = lastColumnNumber; i < (this.minColumnCount); i++) {
                rowlist.add("");
            }
            lastColumnNumber = -1;
            sheetData.add(rowlist.toArray(new String[]{}));
            rowlist.clear();
        }

    }

    /**
     * 添加字符
     *
     * @param ch     char[] 添加字符串
     * @param start  开始位置
     * @param length 长度
     * @throws SAXException SAX异常
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (vIsOpen) {
            value.append(ch, start, length);
        }
    }

    /**
     * 获取当前列索引下标
     *
     * @param name 名字
     * @return 整型
     */
    private int nameToColumn(String name) {
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    /**
     * 获取数据(单Sheet)
     *
     * @param sheetIndex sheet下标
     * @return List<String[]>
     */
    public List<String[]> getSheetData(Integer sheetIndex) {
        return workData.get(sheetIndex);
    }

    /**
     * 获取数据(多Sheet)
     *
     * @return Map<Integer,List<String[]>>  对应列的Map
     */
    public Map<Integer, List<String[]>> getSheetData() {
        return workData;
    }

    /**
     * 取得sharedStringsTable的值
     *
     * @return sharedStringsTable值.
     */
    public ReadOnlySharedStringsTable getSharedStringsTable() {
        return sharedStringsTable;
    }

    /**
     * 设定sharedStringsTable的值
     *
     * @param sharedStringsTable 设定值
     */
    public void setSharedStringsTable(ReadOnlySharedStringsTable sharedStringsTable) {
        this.sharedStringsTable = sharedStringsTable;
    }

    /**
     * 取得vIsOpen的值
     *
     * @return vIsOpen值.
     */
    public boolean isvIsOpen() {
        return vIsOpen;
    }

    /**
     * 设定vIsOpen的值
     *
     * @param vIsOpen 设定值
     */
    public void setvIsOpen(boolean vIsOpen) {
        this.vIsOpen = vIsOpen;
    }

    /**
     * 取得nextDataType的值
     *
     * @return nextDataType值.
     */
    public XssfDataType getNextDataType() {
        return nextDataType;
    }

    /**
     * 设定nextDataType的值
     *
     * @param nextDataType 设定值
     */
    public void setNextDataType(XssfDataType nextDataType) {
        this.nextDataType = nextDataType;
    }

    /**
     * 取得thisColumn的值
     *
     * @return thisColumn值.
     */
    public int getThisColumn() {
        return thisColumn;
    }

    /**
     * 设定thisColumn的值
     *
     * @param thisColumn 设定值
     */
    public void setThisColumn(int thisColumn) {
        this.thisColumn = thisColumn;
    }

    /**
     * 取得lastColumnNumber的值
     *
     * @return lastColumnNumber值.
     */
    public int getLastColumnNumber() {
        return lastColumnNumber;
    }

    /**
     * 设定lastColumnNumber的值
     *
     * @param lastColumnNumber 设定值
     */
    public void setLastColumnNumber(int lastColumnNumber) {
        this.lastColumnNumber = lastColumnNumber;
    }

    /**
     * 取得value的值
     *
     * @return value值.
     */
    public StringBuffer getValue() {
        return value;
    }

    /**
     * 设定value的值
     *
     * @param value 设定值
     */
    public void setValue(StringBuffer value) {
        this.value = value;
    }

    /**
     * 取得rowlist的值
     *
     * @return rowlist值.
     */
    public List<String> getRowlist() {
        return rowlist;
    }

    /**
     * 设定rowlist的值
     *
     * @param rowlist 设定值
     */
    public void setRowlist(List<String> rowlist) {
        this.rowlist = rowlist;
    }

    /**
     * 取得sheetIndex的值
     *
     * @return sheetIndex值.
     */
    public int getSheetIndex() {
        return sheetIndex;
    }

    /**
     * 设定sheetIndex的值
     *
     * @param sheetIndex 设定值
     */
    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    /**
     * 取得workData的值
     *
     * @return workData值.
     */
    public Map<Integer, List<String[]>> getWorkData() {
        return workData;
    }

    /**
     * 设定workData的值
     *
     * @param workData 设定值
     */
    public void setWorkData(Map<Integer, List<String[]>> workData) {
        this.workData = workData;
    }

    /**
     * 取得formatter的值
     *
     * @return formatter值.
     */
    public DataFormatter getFormatter() {
        return formatter;
    }

    /**
     * 设定formatter的值
     *
     * @param formatter 设定值
     */
    public void setFormatter(DataFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * 取得formatIndex的值
     *
     * @return formatIndex值.
     */
    public short getFormatIndex() {
        return formatIndex;
    }

    /**
     * 设定formatIndex的值
     *
     * @param formatIndex 设定值
     */
    public void setFormatIndex(short formatIndex) {
        this.formatIndex = formatIndex;
    }

    /**
     * 取得formatString的值
     *
     * @return formatString值.
     */
    public String getFormatString() {
        return formatString;
    }

    /**
     * 设定formatString的值
     *
     * @param formatString 设定值
     */
    public void setFormatString(String formatString) {
        this.formatString = formatString;
    }

    /**
     * 取得stylesTable的值
     *
     * @return stylesTable值.
     */
    public StylesTable getStylesTable() {
        return stylesTable;
    }

    /**
     * 设定stylesTable的值
     *
     * @param stylesTable 设定值
     */
    public void setStylesTable(StylesTable stylesTable) {
        this.stylesTable = stylesTable;
    }

    /**
     * 取得minColumnCount的值
     *
     * @return minColumnCount值.
     */
    public int getMinColumnCount() {
        return minColumnCount;
    }

    /**
     * 设定sheetData的值
     *
     * @param sheetData 设定值
     */
    public void setSheetData(List<String[]> sheetData) {
        this.sheetData = sheetData;
    }

}