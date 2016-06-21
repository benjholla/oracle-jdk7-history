



package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;


public final class FilteredStepIterator extends StepIterator {

    private Filter _filter;

    public FilteredStepIterator(DTMAxisIterator source,
                                DTMAxisIterator iterator,
                                Filter filter) {
        super(source, iterator);
        _filter = filter;
    }

    public int next() {
        int node;
        while ((node = super.next()) != END) {
            if (_filter.test(node)) {
                return returnNode(node);
            }
        }
        return node;
    }

}
