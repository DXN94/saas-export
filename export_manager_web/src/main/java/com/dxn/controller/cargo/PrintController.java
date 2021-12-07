package com.dxn.controller.cargo;

import com.dxn.common.utils.DownloadUtil;
import com.dxn.controller.BaseController;
import com.dxn.controller.cargo.print.PrintUtil;
import com.dxn.domain.vo.PrintContract;
import com.dxn.service.cargo.ContractService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author dxn
 * @date 2021年11月17日9:01 下午
 */
@Controller
@RequestMapping("/cargo/contract/")
public class PrintController extends BaseController {


    @Resource
    private ContractService contractService;

    @RequestMapping(name = "print", value="转发到打印页面")
    public String toPrint(){
        //转发到打印页面
        return "/cargo/print/contract-print";
    }

    @RequestMapping(value = "printExcel",name = "打印出货表")
    public void printContract(String inputDate){
        //1.根据条件查出要打印的数据（根据日期和公司id）
        List<PrintContract> printContracts = contractService.findPrintContract(inputDate,companyId);

        //2.合同打印
        XSSFWorkbook workbook = null;
        try {
            workbook = PrintUtil.contractPrint2(printContracts, inputDate,session);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.下载
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            workbook.write(bas);
            new DownloadUtil().download(bas,response,inputDate.replaceAll("-0", "-").replaceAll("-", "年")+"月出货表.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
