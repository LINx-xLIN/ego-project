package com.lin.ego.base.pojo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
@TableName("tb_content_category")
public class ContentCategory {

  @TableId(value = "id",type = IdType.AUTO)
  private Long id;
  @TableField("parent_id")
  private Long parentId;
  private String name;
  private Long status;
  @TableField("sort_order")
  private Long sortOrder;
  @TableField("is_parent")
  private Long isParent;
  private Date created;
  private Date updated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Long getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Long sortOrder) {
    this.sortOrder = sortOrder;
  }

  public Long getIsParent() {
    return isParent;
  }

  public void setIsParent(Long isParent) {
    this.isParent = isParent;
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
