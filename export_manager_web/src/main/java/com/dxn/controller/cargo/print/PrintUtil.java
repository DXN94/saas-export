package com.dxn.controller.cargo.print;

import com.dxn.domain.vo.PrintContract;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author dxn
 * @date 2021年11月22日9:48 下午
 */
public class PrintUtil {

    public static XSSFWorkbook contractPrint(List<PrintContract> printContracts, String inputDate) {
        //1.创建excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        //2.创建sheet页
        XSSFSheet sheet = workbook.createSheet();
        sheet.setColumnWidth(1, 26 * 256);
        sheet.setColumnWidth(2, 12 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 8 * 256);
        //3.大标题 第一行
        int rowIndex = 0;
        XSSFRow row1 = sheet.createRow(rowIndex++);
        //3.1.创建单元格 从第二个单元格  2012年8月份出货表
        XSSFCell cell = row1.createCell(2);
        cell.setCellValue(inputDate.replaceAll("-0", "-").replaceAll("-", "年") + "月份出货表");
        //3.2单元格样式
        cell.setCellStyle(getBigCellStyle(workbook));
        //4.小标题 第二行  客户	订单号	货号	数量	工厂	工厂交期	船期	贸易条款
        String[] title = new String[]{"", "客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款"};
        XSSFRow row2 = sheet.createRow(rowIndex++);
        for (int i = 0; i < title.length; i++) {
            XSSFCell cell1 = row2.createCell(i);
            cell1.setCellValue(title[i]);
            cell1.setCellStyle(getSmallTitle(workbook));
        }
        //5.打印数据库数据
        for (PrintContract printContract : printContracts) {
            XSSFRow row = sheet.createRow(rowIndex++);
            // 客户
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(printContract.getCustomName());
            cell1.setCellStyle(getTextStyle(workbook));
            // 订单号
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(printContract.getContractNo());
            cell2.setCellStyle(getTextStyle(workbook));
            // 货号
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(printContract.getContractNo());
            cell3.setCellStyle(getTextStyle(workbook));
            // 数量
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(printContract.getcNumber());
            cell4.setCellStyle(getTextStyle(workbook));
            // 工厂
            XSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(printContract.getFactoryName());
            cell5.setCellStyle(getTextStyle(workbook));
            // 工厂交期
            XSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(printContract.getDeliveryPeriod());
            cell6.setCellStyle(getTextStyle(workbook));
            // 船期
            XSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(printContract.getShipTime());
            cell7.setCellStyle(getTextStyle(workbook));
            // 贸易条款
            XSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(printContract.getTradeTerms());
            cell8.setCellStyle(getTextStyle(workbook));
        }

        return workbook;
    }


    public static XSSFWorkbook contractPrint2(List<PrintContract> printContracts, String inputDate, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath("/make/xlsprint/tOUTPRODUCT.xlsx");
        //1.获得文件
        XSSFWorkbook workbook = new XSSFWorkbook(realPath);
        //2.获得sheet页
        XSSFSheet sheet = workbook.getSheetAt(0);
        //3.创建行
        int rowIndex = 0;
        Cell cell = null;
        XSSFRow row = null;
        row = sheet.getRow(rowIndex++);
        //4.给单元格赋值
        cell = row.getCell(1);
        cell.setCellValue(inputDate.replaceAll("-0", "-").replaceAll("-", "年") + "月份出货表");

        //5.小标题行
        rowIndex++;

        //6.得到内容行的所有样式
        row = sheet.getRow(rowIndex);
        CellStyle[] cellStyles = new CellStyle[9];
        for (int i = 1; i < cellStyles.length; i++) {
            cellStyles[i] = row.getCell(i).getCellStyle();
        }

        //7.打印数据库数据
        for (PrintContract printContract : printContracts) {
            row = sheet.createRow(rowIndex++);
            // 客户
            XSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(printContract.getCustomName());
            cell1.setCellStyle(cellStyles[1]);
            // 订单号
            XSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(printContract.getContractNo());
            cell2.setCellStyle(cellStyles[2]);
            // 货号
            XSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(printContract.getContractNo());
            cell3.setCellStyle(cellStyles[3]);
            // 数量
            XSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(printContract.getcNumber());
            cell4.setCellStyle(cellStyles[4]);
            // 工厂
            XSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(printContract.getFactoryName());
            cell5.setCellStyle(cellStyles[5]);
            // 工厂交期
            XSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(printContract.getDeliveryPeriod());
            cell6.setCellStyle(cellStyles[6]);
            // 船期
            XSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(printContract.getShipTime());
            cell7.setCellStyle(cellStyles[7]);
            // 贸易条款
            XSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(printContract.getTradeTerms());
            cell8.setCellStyle(cellStyles[8]);
        }

        return workbook;
    }

    private static CellStyle getTextStyle(XSSFWorkbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 10);

        style.setFont(font);

        //横向居左
        style.setAlignment(HorizontalAlignment.LEFT);
        //纵向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //上细线
        style.setBorderTop(BorderStyle.THIN);
        //下细线
        style.setBorderBottom(BorderStyle.THIN);
        //左细线
        style.setBorderLeft(BorderStyle.THIN);
        //右细线
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private static CellStyle getSmallTitle(XSSFWorkbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        //横向居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //纵向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //上细线
        style.setBorderTop(BorderStyle.THIN);
        //下细线
        style.setBorderBottom(BorderStyle.THIN);
        //左细线
        style.setBorderLeft(BorderStyle.THIN);
        //右细线
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private static CellStyle getBigCellStyle(XSSFWorkbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        //字体加粗
        font.setBold(true);
        style.setFont(font);
        //横向居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //纵向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }


}
