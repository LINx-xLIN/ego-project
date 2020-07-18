package com.lin.ego.base.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class EgoResult {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private int status;
    private Object data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public EgoResult() {
    }

    public EgoResult(int status, String msg, Object data) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static EgoResult build(Integer status, String msg, Object data){
        return new EgoResult(status,msg,data);
    }

    public EgoResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static EgoResult ok(Object data){
        return new EgoResult(data);
    }

    public static EgoResult ok() {
        return new EgoResult(null);
    }

    public static EgoResult build(Integer status, String msg) {
        return new EgoResult(status, msg, null);
    }


    /**
     * 将json结果集转化为EgoResult对象
     *
     * @param jsonData json数据
     * @param clazz EgoResult中的object类型
     * @return
     */
    public static EgoResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, EgoResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static EgoResult format(String json) {
        try {
            return MAPPER.readValue(json, EgoResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static EgoResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
