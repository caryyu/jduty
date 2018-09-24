package com.github.caryyu.jduty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 默认处理链，注意线程安全等问题，推荐的做法是不同的线程用 new 进行隔离。
 * @author cary
 */
public class DefaultProcessChain implements ProcessChain {
    private static Logger logger = LoggerFactory.getLogger(DefaultProcessChain.class);

    private List<IProcessor> processors;

    private int pos;

    public DefaultProcessChain(){
        processors = new ArrayList<IProcessor>();
    }

    public void process(ProcessState state) throws ProcessException {
        int len = processors.size();

        if (pos == len) {
            reuse();
            return;
        }

        if (pos > len) {
            throw new ProcessException("Processors index out of bound, pos: " + pos + ", length: " + len);
        }

        IProcessor processor = processors.get(pos++);

        logger.debug("Start with " + processor);

        processor.process(state, this);
    }

    /**
     * reset position to zero as needs to be used again
     */
    private void reuse() {
        this.pos = 0;
    }

    public void register(IProcessor ...processors) {
        this.processors.addAll(Arrays.asList(processors));
    }
}