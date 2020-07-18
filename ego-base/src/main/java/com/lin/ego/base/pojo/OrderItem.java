package com.lin.ego.base.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("tb_order_item")
public class OrderItem {
  @TableId(value = "id", type = IdType.INPUT)
  private String id;
  @TableField(value = "item_id")
  private String itemId;
  @TableField(value = "order_id")
  private String orderId;
  private long num;
  private String title;
  private long price;
  @TableField(value = "total_fee")
  private long totalFee;
  @TableField(value = "pic_path")
  private String picPath;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(long totalFee) {
    this.totalFee = totalFee;
  }

  public String getPicPath() {
    return picPath;
  }

  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }
}
