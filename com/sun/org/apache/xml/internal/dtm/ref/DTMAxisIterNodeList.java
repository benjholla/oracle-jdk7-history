


package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.utils.IntVector;

import org.w3c.dom.Node;


public class DTMAxisIterNodeList extends DTMNodeListBase {
    private DTM m_dtm;
    private DTMAxisIterator m_iter;
    private IntVector m_cachedNodes;
    private int m_last = -1;
    
    
    private DTMAxisIterNodeList() {
    }

    
    public DTMAxisIterNodeList(DTM dtm, DTMAxisIterator dtmAxisIterator) {
        if (dtmAxisIterator == null) {
            m_last = 0;
        } else {
            m_cachedNodes = new IntVector();
            m_dtm = dtm;
        }
        m_iter = dtmAxisIterator;
    }

    
    public DTMAxisIterator getDTMAxisIterator() {
        return m_iter;
    }


    
    

    
    public Node item(int index) {
        if (m_iter != null) {
            int node = 0;
            int count = m_cachedNodes.size();

            if (count > index) {
                node = m_cachedNodes.elementAt(index);
                return m_dtm.getNode(node);
            } else if (m_last == -1) {
                while (count <= index
                        && ((node = m_iter.next()) != DTMAxisIterator.END)) {
                    m_cachedNodes.addElement(node);
                    count++;
                }
                if (node == DTMAxisIterator.END) {
                    m_last = count;
                } else {
                    return m_dtm.getNode(node);
                }
            }
        }
        return null;
    }

    
    public int getLength() {
        if (m_last == -1) {
            int node;
            while ((node = m_iter.next()) != DTMAxisIterator.END) {
                m_cachedNodes.addElement(node);
            }
            m_last = m_cachedNodes.size();
        }
        return m_last;
    }
}
