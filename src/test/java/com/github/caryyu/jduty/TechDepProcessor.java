package com.github.caryyu.jduty;

public class TechDepProcessor implements IProcessor {
    public void process(ProcessState state, ProcessChain chain) throws ProcessException {
        String role = state.getAttribute("role",String.class);

        if ("程序员".equals(role)){
            state.setAttribute("clazz",this.getClass());
        } else {
            chain.process(state);
        }
    }
}