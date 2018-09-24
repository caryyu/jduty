package com.github.caryyu.jduty;

/**
 * 逻辑处理器
 * @author cary
 * @date 2018/07/24
 */
public interface IProcessor {
    void process(ProcessState state, ProcessChain chain) throws ProcessException;
}