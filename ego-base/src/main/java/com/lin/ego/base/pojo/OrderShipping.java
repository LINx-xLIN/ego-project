package com.lin.ego.base.pojo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("tb_order_shipping")
public class OrderShipping {

  @TableId(value = "order_id", type = IdType.INPUT)
  private String orderId;
  @TableField(value = "receiver_name")
  private String receiverName;
  @TableField(value = "receiver_phone")
  private String receiverPhone;
  @TableField(value = "receiver_mobile")
  private String receiverMobile;
  @TableField(value = "receiver_state")
  private String receiverState;
  @TableField(value = "receiver_city")
  private String receiverCity;
  @TableField(value = "receiver_district")
  private String receiverDistrict;
  @TableField(value = "receiver_address")
  private String receiverAddress;
  @TableField(value = "receiver_zip")
  private String receiverZip;
  private Date created;
  private Date updated;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public void setReceiverPhone(String receiverPhone) {
    this.receiverPhone = receiverPhone;
  }

  public String getReceiverMobile() {
    return receiverMobile;
  }

  public void setReceiverMobile(String receiverMobile) {
    this.receiverMobile = receiverMobile;
  }

  public String getReceiverState() {
    return receiverState;
  }

  public void setReceiverState(String receiverState) {
    this.receiverState = receiverState;
  }

  public String getReceiverCity() {
    return receiverCity;
  }

  public void setReceiverCity(String receiverCity) {
    this.receiverCity = receiverCity;
  }

  public String getReceiverDistrict() {
    return receiverDistrict;
  }

  public void setReceiverDistrict(String receiverDistrict) {
    this.receiverDistrict = receiverDistrict;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public String getReceiverZip() {
    return receiverZip;
  }

  public void setReceiverZip(String receiverZip) {
    this.receiverZip = receiverZip;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
