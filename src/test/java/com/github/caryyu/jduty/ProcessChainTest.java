package com.github.caryyu.jduty;

import org.junit.Before;
import org.junit.Test;

/**
 * # 例子一
 * 这段例子主要针对请假进行演示。
 * ## 前提说明
 * 公司的行政审批层级依次为：
 * > 开发小组 < 信息部门 < 人力资源 < CEO
 *
 * ## 角色说明
 * * 测试员 - 当此角色申请假时需要开发小组批准即可；
 * * 程序员 - 当此角色申请假时需要信息部门批准即可；
 * * 开发组长 - 当此角色申请假时需要人力资源批准即可；
 * * 开发总监 - 当此角色申请假时需要得到 CEO 批准
 */
public class ProcessChainTest {
    private DefaultProcessChain chain;

    @Before
    public void context() {
        IProcessor[] processors = {
                new CodeGroupProcessor(),
                new TechDepProcessor(),
                new HRDepProcessor(),
                new CEOProcessor()
        };

        chain = new DefaultProcessChain();
        chain.register(processors);
    }

    @Test
    public void testCase1() throws ProcessException {
        ProcessState state = new ProcessState();
        state.setAttribute("role","测试员");

        chain.process(state);

        Class clz = state.getAttribute("clazz",Class.class);

        assert CodeGroupProcessor.class.isAssignableFrom(clz);
    }

    @Test
    public void testCase2() throws ProcessException {
        ProcessState state = new ProcessState();
        state.setAttribute("role","程序员");

        chain.process(state);

        Class clz = state.getAttribute("clazz",Class.class);

        assert TechDepProcessor.class.isAssignableFrom(clz);
    }

    @Test
    public void testCase3() throws ProcessException {
        ProcessState state = new ProcessState();
        state.setAttribute("role","开发组长");

        chain.process(state);

        Class clz = state.getAttribute("clazz",Class.class);

        assert HRDepProcessor.class.isAssignableFrom(clz);
    }

    @Test
    public void testCase4() throws ProcessException {
        ProcessState state = new ProcessState();
        state.setAttribute("role","开发总监");

        chain.process(state);

        Class clz = state.getAttribute("clazz",Class.class);

        assert CEOProcessor.class.isAssignableFrom(clz);
    }
}
