package com.github.caryyu.jduty;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理链中的状态
 * @author cary
 * @date 2017/07/24
 */
public class ProcessState {
    private Map<String,Object> attributes;

    private Map<String, Object> getAttributes() {
        if (attributes == null) {
            setAttributes(new HashMap<String, Object>());
        }
        return attributes;
    }

    private void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setAttribute(String key, Object value){
        getAttributes().put(key, value);
    }

    public Object getAttribute(String key) {
        return  getAttributes().get(key);
    }

    public <T> T getAttribute(String key, Class<T> clazz) {
        return (T)getAttributes().get(key);
    }
}