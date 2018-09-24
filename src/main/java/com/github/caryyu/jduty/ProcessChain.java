package com.github.caryyu.jduty;

/**
 * 处理链
 * @author cary
 * @date 2018/07/24
 */
public interface ProcessChain {
    void process(ProcessState state) throws ProcessException ;
}