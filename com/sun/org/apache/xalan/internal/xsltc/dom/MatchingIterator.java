



package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIteratorBase;


public final class MatchingIterator extends DTMAxisIteratorBase {

    
    private DTMAxisIterator _source;

    
    private final int _match;

    public MatchingIterator(int match, DTMAxisIterator source) {
        _source = source;
        _match = match;
    }


    public void setRestartable(boolean isRestartable) {
        _isRestartable = isRestartable;
        _source.setRestartable(isRestartable);
    }

    public DTMAxisIterator cloneIterator() {

        try {
            final MatchingIterator clone = (MatchingIterator) super.clone();
            clone._source = _source.cloneIterator();
            clone._isRestartable = false;
            return clone.reset();
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError(BasisLibrary.ITERATOR_CLONE_ERR,
                                      e.toString());
            return null;
        }
    }

    public DTMAxisIterator setStartNode(int node) {
        if (_isRestartable) {
            
            _source.setStartNode(node);

            
            _position = 1;
            while ((node = _source.next()) != END && node != _match) {
                _position++;
            }
        }
        return this;
    }

    public DTMAxisIterator reset() {
        _source.reset();
        return resetPosition();
    }

    public int next() {
        return _source.next();
    }

    public int getLast() {
        if (_last == -1) {
            _last = _source.getLast();
        }
        return _last;
    }

    public int getPosition() {
        return _position;
    }

    public void setMark() {
        _source.setMark();
    }

    public void gotoMark() {
        _source.gotoMark();
    }
}
