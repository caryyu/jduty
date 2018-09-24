package com.github.caryyu.jduty;

public class HRDepProcessor implements IProcessor {
    public void process(ProcessState state, ProcessChain chain) throws ProcessException {
        String role = state.getAttribute("role",String.class);

        if ("开发组长".equals(role)){
            state.setAttribute("clazz",this.getClass());
        } else {
            chain.process(state);
        }
    }
}
