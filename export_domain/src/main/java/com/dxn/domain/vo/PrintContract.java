package com.dxn.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dxn
 * @date 2021年11月17日9:08 下午
 */
public class PrintContract implements Serializable {


    /**
     * 客户名称
     */
    private String customName;
    /**
     * 合同号，订单号
     */
    private String contractNo;
    /**
     * 货号
     */
    private String productNo;
    /**
     * 数量
     */
    private Integer cNumber;
    /**
     * 工厂名称
     */
    private String factoryName;
    /**
     * 交货期限
     */
    private Date deliveryPeriod;
    /**
     * 船期
     */
    private Date shipTime;
    /**
     * 贸易条款
     */
    private String tradeTerms;

    @Override
    public String toString() {
        return "PrintContract{" +
                "customName='" + customName + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", productNo='" + productNo + '\'' +
                ", cNumber=" + cNumber +
                ", factoryName='" + factoryName + '\'' +
                ", deliveryPeriod=" + deliveryPeriod +
                ", shipTime=" + shipTime +
                ", tradeTerms='" + tradeTerms + '\'' +
                '}';
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getcNumber() {
        return cNumber;
    }

    public void setcNumber(Integer cNumber) {
        this.cNumber = cNumber;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Date getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(Date deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public PrintContract(String customName, String contractNo, String productNo, Integer cNumber, String factoryName, Date deliveryPeriod, Date shipTime, String tradeTerms) {
        this.customName = customName;
        this.contractNo = contractNo;
        this.productNo = productNo;
        this.cNumber = cNumber;
        this.factoryName = factoryName;
        this.deliveryPeriod = deliveryPeriod;
        this.shipTime = shipTime;
        this.tradeTerms = tradeTerms;
    }

    public PrintContract() {
    }
}
