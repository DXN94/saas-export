package com.dxn.domain.cargo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Contract implements Serializable {
    private String id;

    private String offeror;

    private String contractNo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date signingDate;

    private String inputBy;

    private String checkBy;

    private String inspector;

    private BigDecimal totalAmount;

    private String crequest;

    private String customName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date shipTime;

    private Long importNum;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date deliveryPeriod;

    private Integer oldState;

    private Integer outState;

    private String tradeTerms;

    private String printStyle;

    private String remark;

    private Integer state;

    private Integer proNum;

    private Integer extNum;

    private String createBy;

    private String createDept;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createTime;

    private String updateBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updateTime;

    private String companyId;

    private String companyName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", offeror='" + offeror + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", signingDate=" + signingDate +
                ", inputBy='" + inputBy + '\'' +
                ", checkBy='" + checkBy + '\'' +
                ", inspector='" + inspector + '\'' +
                ", totalAmount=" + totalAmount +
                ", crequest='" + crequest + '\'' +
                ", customName='" + customName + '\'' +
                ", shipTime=" + shipTime +
                ", importNum=" + importNum +
                ", deliveryPeriod=" + deliveryPeriod +
                ", oldState=" + oldState +
                ", outState=" + outState +
                ", tradeTerms='" + tradeTerms + '\'' +
                ", printStyle='" + printStyle + '\'' +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", proNum=" + proNum +
                ", extNum=" + extNum +
                ", createBy='" + createBy + '\'' +
                ", createDept='" + createDept + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCrequest() {
        return crequest;
    }

    public void setCrequest(String crequest) {
        this.crequest = crequest;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Long getImportNum() {
        return importNum;
    }

    public void setImportNum(Long importNum) {
        this.importNum = importNum;
    }

    public Date getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(Date deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public Integer getOldState() {
        return oldState;
    }

    public void setOldState(Integer oldState) {
        this.oldState = oldState;
    }

    public Integer getOutState() {
        return outState;
    }

    public void setOutState(Integer outState) {
        this.outState = outState;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(String printStyle) {
        this.printStyle = printStyle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getProNum() {
        return proNum;
    }

    public void setProNum(Integer proNum) {
        this.proNum = proNum;
    }

    public Integer getExtNum() {
        return extNum;
    }

    public void setExtNum(Integer extNum) {
        this.extNum = extNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Contract(String id, String offeror, String contractNo, Date signingDate, String inputBy, String checkBy, String inspector, BigDecimal totalAmount, String crequest, String customName, Date shipTime, Long importNum, Date deliveryPeriod, Integer oldState, Integer outState, String tradeTerms, String printStyle, String remark, Integer state, Integer proNum, Integer extNum, String createBy, String createDept, Date createTime, String updateBy, Date updateTime, String companyId, String companyName) {
        this.id = id;
        this.offeror = offeror;
        this.contractNo = contractNo;
        this.signingDate = signingDate;
        this.inputBy = inputBy;
        this.checkBy = checkBy;
        this.inspector = inspector;
        this.totalAmount = totalAmount;
        this.crequest = crequest;
        this.customName = customName;
        this.shipTime = shipTime;
        this.importNum = importNum;
        this.deliveryPeriod = deliveryPeriod;
        this.oldState = oldState;
        this.outState = outState;
        this.tradeTerms = tradeTerms;
        this.printStyle = printStyle;
        this.remark = remark;
        this.state = state;
        this.proNum = proNum;
        this.extNum = extNum;
        this.createBy = createBy;
        this.createDept = createDept;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Contract() {
    }
}