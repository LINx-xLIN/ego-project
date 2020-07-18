package com.lin.ego.base.pojo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("tb_item_param")
public class ItemParam {

  @TableId(value = "id",type= IdType.AUTO)
  private Long id;
  @TableField("item_cat_id")
  private Long itemCatId;
  @TableField("param_data")
  private String paramData;
  private Date created;
  private Date updated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getItemCatId() {
    return itemCatId;
  }

  public void setItemCatId(Long itemCatId) {
    this.itemCatId = itemCatId;
  }

  public String getParamData() {
    return paramData;
  }

  public void setParamData(String paramData) {
    this.paramData = paramData;
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
