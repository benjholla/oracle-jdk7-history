



package com.sun.org.apache.xalan.internal.xsltc.dom;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;

import com.sun.org.apache.xalan.internal.xsltc.CollatorFactory;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.utils.StringComparable;


public abstract class NodeSortRecord {
    public static final int COMPARE_STRING     = 0;
    public static final int COMPARE_NUMERIC    = 1;

    public static final int COMPARE_ASCENDING  = 0;
    public static final int COMPARE_DESCENDING = 1;

    
    private static final Collator DEFAULT_COLLATOR = Collator.getInstance();

    
    protected Collator _collator = DEFAULT_COLLATOR;
    protected Collator[] _collators;

    
    protected Locale _locale;

    protected CollatorFactory _collatorFactory;

    protected SortSettings _settings;

    private DOM    _dom = null;
    private int    _node;           
    private int    _last = 0;       
    private int    _scanned = 0;    

    private Object[] _values; 

    
    public NodeSortRecord(int node) {
        _node = node;
    }

    public NodeSortRecord() {
        this(0);
    }

    
    public final void initialize(int node, int last, DOM dom,
         SortSettings settings)
        throws TransletException
    {
        _dom = dom;
        _node = node;
        _last = last;
        _settings = settings;

        int levels = settings.getSortOrders().length;
        _values = new Object[levels];

        String colFactClassname = null;
        try {
            
            colFactClassname =
                System.getProperty("com.sun.org.apache.xalan.internal.xsltc.COLLATOR_FACTORY");
        }
        catch (SecurityException e) {
            
        }

        if (colFactClassname != null) {
            try {
                Object candObj = ObjectFactory.findProviderClass(
                    colFactClassname, ObjectFactory.findClassLoader(), true);
                _collatorFactory = (CollatorFactory)candObj;
            } catch (ClassNotFoundException e) {
                throw new TransletException(e);
            }
            Locale[] locales = settings.getLocales();
            _collators = new Collator[levels];
            for (int i = 0; i < levels; i++){
                _collators[i] = _collatorFactory.getCollator(locales[i]);
            }
            _collator = _collators[0];
        } else {
            _collators = settings.getCollators();
            _collator = _collators[0];
        }
    }

    
    public final int getNode() {
        return _node;
    }

    
    public final int compareDocOrder(NodeSortRecord other) {
        return _node - other._node;
    }

    
    private final Comparable stringValue(int level) {
        
        if (_scanned <= level) {
            AbstractTranslet translet = _settings.getTranslet();
            Locale[] locales = _settings.getLocales();
            String[] caseOrder = _settings.getCaseOrders();

            
            final String str = extractValueFromDOM(_dom, _node, level,
                                                   translet, _last);
            final Comparable key =
                StringComparable.getComparator(str, locales[level],
                                               _collators[level],
                                               caseOrder[level]);
            _values[_scanned++] = key;
            return(key);
        }
        return((Comparable)_values[level]);
  }

    private final Double numericValue(int level) {
        
        if (_scanned <= level) {
            AbstractTranslet translet = _settings.getTranslet();

            
            final String str = extractValueFromDOM(_dom, _node, level,
                                                   translet, _last);
            Double num;
            try {
                num = new Double(str);
            }
            
            catch (NumberFormatException e) {
                num = new Double(Double.NEGATIVE_INFINITY);
            }
            _values[_scanned++] = num;
            return(num);
        }
        return((Double)_values[level]);
    }

    
    public int compareTo(NodeSortRecord other) {
        int cmp, level;
        int[] sortOrder = _settings.getSortOrders();
        int levels = _settings.getSortOrders().length;
        int[] compareTypes = _settings.getTypes();

        for (level = 0; level < levels; level++) {
            
            if (compareTypes[level] == COMPARE_NUMERIC) {
                final Double our = numericValue(level);
                final Double their = other.numericValue(level);
                cmp = our.compareTo(their);
            }
            else {
                final Comparable our = stringValue(level);
                final Comparable their = other.stringValue(level);
                cmp = our.compareTo(their);
            }

            
            if (cmp != 0) {
                return sortOrder[level] == COMPARE_DESCENDING ? 0 - cmp : cmp;
            }
        }
        
        return(_node - other._node);
    }

    
    public Collator[] getCollator() {
        return _collators;
    }

    
    public abstract String extractValueFromDOM(DOM dom, int current, int level,
                                               AbstractTranslet translet,
                                               int last);

}
