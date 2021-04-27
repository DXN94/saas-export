package com.dxn.domain.cargo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContractProduct implements Serializable {
    private String id;

    private String contractId;

    private String factoryId;

    private String factoryName;

    private String productNo;

    private String productImage;

    private String productDesc;

    private String loadingRate;

    private Long boxNum;

    private String packingUnit;

    private Long cnumber;

    private Long outNumber;

    private Long finished;

    private String productRequest;

    private BigDecimal price;

    private BigDecimal amount;

    private Long orderNo;

    private String companyId;

    private String companyName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName == null ? null : factoryName.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage == null ? null : productImage.trim();
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    public String getLoadingRate() {
        return loadingRate;
    }

    public void setLoadingRate(String loadingRate) {
        this.loadingRate = loadingRate == null ? null : loadingRate.trim();
    }

    public Long getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Long boxNum) {
        this.boxNum = boxNum;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit == null ? null : packingUnit.trim();
    }

    public Long getCnumber() {
        return cnumber;
    }

    public void setCnumber(Long cnumber) {
        this.cnumber = cnumber;
    }

    public Long getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Long outNumber) {
        this.outNumber = outNumber;
    }

    public Long getFinished() {
        return finished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }

    public String getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(String productRequest) {
        this.productRequest = productRequest == null ? null : productRequest.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    @Override
    public String toString() {
        return "ContractProduct{" +
                "id='" + id + '\'' +
                ", contractId='" + contractId + '\'' +
                ", factoryId='" + factoryId + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", productNo='" + productNo + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", loadingRate='" + loadingRate + '\'' +
                ", boxNum=" + boxNum +
                ", packingUnit='" + packingUnit + '\'' +
                ", cnumber=" + cnumber +
                ", outNumber=" + outNumber +
                ", finished=" + finished +
                ", productRequest='" + productRequest + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", orderNo=" + orderNo +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public ContractProduct() {
    }

    public ContractProduct(String id, String contractId, String factoryId, String factoryName, String productNo, String productImage, String productDesc, String loadingRate, Long boxNum, String packingUnit, Long cnumber, Long outNumber, Long finished, String productRequest, BigDecimal price, BigDecimal amount, Long orderNo, String companyId, String companyName) {
        this.id = id;
        this.contractId = contractId;
        this.factoryId = factoryId;
        this.factoryName = factoryName;
        this.productNo = productNo;
        this.productImage = productImage;
        this.productDesc = productDesc;
        this.loadingRate = loadingRate;
        this.boxNum = boxNum;
        this.packingUnit = packingUnit;
        this.cnumber = cnumber;
        this.outNumber = outNumber;
        this.finished = finished;
        this.productRequest = productRequest;
        this.price = price;
        this.amount = amount;
        this.orderNo = orderNo;
        this.companyId = companyId;
        this.companyName = companyName;
    }
}