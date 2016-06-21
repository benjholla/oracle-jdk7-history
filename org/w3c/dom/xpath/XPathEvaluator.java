



package org.w3c.dom.xpath;


import org.w3c.dom.Node;
import org.w3c.dom.DOMException;


public interface XPathEvaluator {
    
    public XPathExpression createExpression(String expression,
                                            XPathNSResolver resolver)
                                            throws XPathException, DOMException;

    
    public XPathNSResolver createNSResolver(Node nodeResolver);

    
    public Object evaluate(String expression,
                           Node contextNode,
                           XPathNSResolver resolver,
                           short type,
                           Object result)
                           throws XPathException, DOMException;

}
