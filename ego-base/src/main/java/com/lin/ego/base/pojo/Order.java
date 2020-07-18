package com.lin.ego.base.pojo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("tb_order")
public class Order {

  @TableId(value = "order_id", type = IdType.INPUT)
  private String orderId;

  private String payment;
  @TableField(value = "payment_type")
  private int Date;
  @TableField(value = "post_fee")
  private String postFee;
  private int status;
  @TableField(value = "create_time")
  private Date createTime;
  @TableField(value = "update_time")
  private Date updateTime;
  @TableField(value = "payment_time")
  private Date paymentTime;
  @TableField(value = "consign_time")
  private Date consignTime;
  @TableField(value = "end_time")
  private Date endTime;
  @TableField(value = "close_time")
  private Date closeTime;
  @TableField(value = "shipping_name")
  private String shippingName;
  @TableField(value = "shipping_code")
  private String shippingCode;
  @TableField(value = "user_id")
  private long userId;
  @TableField(value = "buyer_message")
  private String buyerMessage;
  @TableField(value = "buyer_nick")
  private String buyerNick;
  @TableField(value = "buyer_rate")
  private int buyerRate;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public int getDate() {
    return Date;
  }

  public void setDate(int date) {
    Date = date;
  }

  public String getPostFee() {
    return postFee;
  }

  public void setPostFee(String postFee) {
    this.postFee = postFee;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

  public java.util.Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.util.Date updateTime) {
    this.updateTime = updateTime;
  }

  public java.util.Date getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(java.util.Date paymentTime) {
    this.paymentTime = paymentTime;
  }

  public java.util.Date getConsignTime() {
    return consignTime;
  }

  public void setConsignTime(java.util.Date consignTime) {
    this.consignTime = consignTime;
  }

  public java.util.Date getEndTime() {
    return endTime;
  }

  public void setEndTime(java.util.Date endTime) {
    this.endTime = endTime;
  }

  public java.util.Date getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(java.util.Date closeTime) {
    this.closeTime = closeTime;
  }

  public String getShippingName() {
    return shippingName;
  }

  public void setShippingName(String shippingName) {
    this.shippingName = shippingName;
  }

  public String getShippingCode() {
    return shippingCode;
  }

  public void setShippingCode(String shippingCode) {
    this.shippingCode = shippingCode;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getBuyerMessage() {
    return buyerMessage;
  }

  public void setBuyerMessage(String buyerMessage) {
    this.buyerMessage = buyerMessage;
  }

  public String getBuyerNick() {
    return buyerNick;
  }

  public void setBuyerNick(String buyerNick) {
    this.buyerNick = buyerNick;
  }

  public int getBuyerRate() {
    return buyerRate;
  }

  public void setBuyerRate(int buyerRate) {
    this.buyerRate = buyerRate;
  }
}
