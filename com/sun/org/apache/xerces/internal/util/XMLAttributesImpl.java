


package com.sun.org.apache.xerces.internal.util;

import com.sun.xml.internal.stream.XMLBufferListener;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLString;

public class XMLAttributesImpl
implements XMLAttributes, XMLBufferListener {

    
    
    
    
    
    protected static final int TABLE_SIZE = 101;
    
    
    protected static final int SIZE_LIMIT = 20;
    
    
    
    

    

    
    protected boolean fNamespaces = true;

    

    
    protected int fLargeCount = 1;
    
    
    protected int fLength;

    
    protected Attribute[] fAttributes = new Attribute[4];

    
    protected Attribute[] fAttributeTableView;
    
    
    protected int[] fAttributeTableViewChainState;
    
    
    protected int fTableViewBuckets;
    
    
    protected boolean fIsTableViewConsistent;

    
    
    

    
    public XMLAttributesImpl() {
        this(TABLE_SIZE);
    }
    
    
    public XMLAttributesImpl(int tableSize) {
        fTableViewBuckets = tableSize;
        for (int i = 0; i < fAttributes.length; i++) {
            fAttributes[i] = new Attribute();
        }
    } 

    
    
    

    
    public void setNamespaces(boolean namespaces) {
        fNamespaces = namespaces;
    } 

    
    
    

    
    public int addAttribute(QName name, String type, String value) {
      return addAttribute(name,type,value,null);
    }
    public int addAttribute(QName name, String type, String value,XMLString valueCache) {

        int index;
        if (fLength < SIZE_LIMIT) {
            index = name.uri != null && !name.uri.equals("") 
                ? getIndexFast(name.uri, name.localpart)
                : getIndexFast(name.rawname);
    
            if (index == -1) {
                index = fLength;
                if (fLength++ == fAttributes.length) {
                    Attribute[] attributes = new Attribute[fAttributes.length + 4];
                    System.arraycopy(fAttributes, 0, attributes, 0, fAttributes.length);
                    for (int i = fAttributes.length; i < attributes.length; i++) {
                        attributes[i] = new Attribute();
                    }
                    fAttributes = attributes;
                }
            }
        }
        else if (name.uri == null || 
            name.uri.length() == 0 || 
            (index = getIndexFast(name.uri, name.localpart)) == -1) {
            
            
            if (!fIsTableViewConsistent || fLength == SIZE_LIMIT) {
                prepareAndPopulateTableView();
                fIsTableViewConsistent = true;
            }

            int bucket = getTableViewBucket(name.rawname); 
        
            
            
            if (fAttributeTableViewChainState[bucket] != fLargeCount) {
                index = fLength;
                if (fLength++ == fAttributes.length) {
                    Attribute[] attributes = new Attribute[fAttributes.length << 1];
                    System.arraycopy(fAttributes, 0, attributes, 0, fAttributes.length);
                    for (int i = fAttributes.length; i < attributes.length; i++) {
                        attributes[i] = new Attribute();
                    }
                    fAttributes = attributes;
                }
            
                
                fAttributeTableViewChainState[bucket] = fLargeCount;
                fAttributes[index].next = null;
                fAttributeTableView[bucket] = fAttributes[index];
            }
            
            
            else {
                
                Attribute found = fAttributeTableView[bucket];
                while (found != null) {
                    if (found.name.rawname == name.rawname) {
                        break;
                    }
                    found = found.next;
                }
                
                if (found == null) {
                    index = fLength;
                    if (fLength++ == fAttributes.length) {
                        Attribute[] attributes = new Attribute[fAttributes.length << 1];
                        System.arraycopy(fAttributes, 0, attributes, 0, fAttributes.length);
                        for (int i = fAttributes.length; i < attributes.length; i++) {
                            attributes[i] = new Attribute();
                        }
                        fAttributes = attributes;
                    }
                 
                    
                    fAttributes[index].next = fAttributeTableView[bucket];
                    fAttributeTableView[bucket] = fAttributes[index];
                }
                
                else {
                    index = getIndexFast(name.rawname);
                }
            }
        }          

        
        Attribute attribute = fAttributes[index];
        attribute.name.setValues(name);
        attribute.type = type;
        attribute.value = value;
        attribute.xmlValue = valueCache;
        attribute.nonNormalizedValue = value;
        attribute.specified = false;

        
        if(attribute.augs != null)
            attribute.augs.removeAllItems();
        
        return index;

    } 

    
    public void removeAllAttributes() {
        fLength = 0;
    } 

    
    public void removeAttributeAt(int attrIndex) {
        fIsTableViewConsistent = false;
        if (attrIndex < fLength - 1) {
            Attribute removedAttr = fAttributes[attrIndex];
            System.arraycopy(fAttributes, attrIndex + 1,
                             fAttributes, attrIndex, fLength - attrIndex - 1);
            
            
            fAttributes[fLength-1] = removedAttr;
        }
        fLength--;
    } 

    
    public void setName(int attrIndex, QName attrName) {
        fAttributes[attrIndex].name.setValues(attrName);
    } 

    
    public void getName(int attrIndex, QName attrName) {
        attrName.setValues(fAttributes[attrIndex].name);
    } 

    
    public void setType(int attrIndex, String attrType) {
        fAttributes[attrIndex].type = attrType;
    } 

    
    public void setValue(int attrIndex, String attrValue) {
        setValue(attrIndex,attrValue,null);
    }
    
    public void setValue(int attrIndex, String attrValue,XMLString value) {
        Attribute attribute = fAttributes[attrIndex];
        attribute.value = attrValue;
        attribute.nonNormalizedValue = attrValue;
        attribute.xmlValue = value;
    } 

    
    public void setNonNormalizedValue(int attrIndex, String attrValue) {
        if (attrValue == null) {
            attrValue = fAttributes[attrIndex].value;
        }
        fAttributes[attrIndex].nonNormalizedValue = attrValue;
    } 

    
    public String getNonNormalizedValue(int attrIndex) {
        String value = fAttributes[attrIndex].nonNormalizedValue;
        return value;
    } 

    
    public void setSpecified(int attrIndex, boolean specified) {
        fAttributes[attrIndex].specified = specified;
    } 

    
    public boolean isSpecified(int attrIndex) {
        return fAttributes[attrIndex].specified;
    } 

    
    
    

    
    public int getLength() {
        return fLength;
    } 

    
    public String getType(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        return getReportableType(fAttributes[index].type);
    } 

    
    public String getType(String qname) {
        int index = getIndex(qname);
        return index != -1 ? getReportableType(fAttributes[index].type) : null;
    } 

    
    public String getValue(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        if(fAttributes[index].value == null && fAttributes[index].xmlValue != null)
            fAttributes[index].value = fAttributes[index].xmlValue.toString();
        return fAttributes[index].value;
    } 

    
    public String getValue(String qname) {
        int index = getIndex(qname);
        if(index == -1 )
            return null;
        if(fAttributes[index].value == null)
            fAttributes[index].value = fAttributes[index].xmlValue.toString();
        return fAttributes[index].value;
    } 

    
    
    

    
    public String getName(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fAttributes[index].name.rawname;
    } 

    
    
    

    
    public int getIndex(String qName) {
        for (int i = 0; i < fLength; i++) {
            Attribute attribute = fAttributes[i];
            if (attribute.name.rawname != null &&
                attribute.name.rawname.equals(qName)) {
                return i;
            }
        }
        return -1;
    } 
    
    
    public int getIndex(String uri, String localPart) {
        for (int i = 0; i < fLength; i++) {
            Attribute attribute = fAttributes[i];
            if (attribute.name.localpart != null &&
                attribute.name.localpart.equals(localPart) &&
                ((uri==attribute.name.uri) ||
            (uri!=null && attribute.name.uri!=null && attribute.name.uri.equals(uri)))) {
                return i;
            }
        }
        return -1;
    } 

    
    public int getIndexByLocalName(String localPart) {
        for (int i = 0; i < fLength; i++) {
            Attribute attribute = fAttributes[i];
            if (attribute.name.localpart != null &&
                attribute.name.localpart.equals(localPart)) {
                return i;
            }
        }
        return -1;
    } 

    
    public String getLocalName(int index) {
        if (!fNamespaces) {
            return "";
        }
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fAttributes[index].name.localpart;
    } 

    
    public String getQName(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        String rawname = fAttributes[index].name.rawname;
        return rawname != null ? rawname : "";
    } 
    
    public QName getQualifiedName(int index){
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fAttributes[index].name;
    }

    
    public String getType(String uri, String localName) {
        if (!fNamespaces) {
            return null;
        }
        int index = getIndex(uri, localName);
        return index != -1 ? getType(index) : null;
    } 
    
    public int getIndexFast(String qName) {
        for (int i = 0; i < fLength; ++i) {
            Attribute attribute = fAttributes[i];
            if (attribute.name.rawname == qName) {
                return i;
            }
        }
        return -1;
    } 
    
    
    public void addAttributeNS(QName name, String type, String value) {
        int index = fLength;
        if (fLength++ == fAttributes.length) {
            Attribute[] attributes;
            if (fLength < SIZE_LIMIT) {
                attributes = new Attribute[fAttributes.length + 4];
            }
            else {
                attributes = new Attribute[fAttributes.length << 1];
            }
            System.arraycopy(fAttributes, 0, attributes, 0, fAttributes.length);
            for (int i = fAttributes.length; i < attributes.length; i++) {
                attributes[i] = new Attribute();
            }
            fAttributes = attributes;
        }
        
        
        Attribute attribute = fAttributes[index];
        attribute.name.setValues(name);
        attribute.type = type;
        attribute.value = value;
        attribute.nonNormalizedValue = value;
        attribute.specified = false;
            
        
        attribute.augs.removeAllItems();
    }
    
    
    public QName checkDuplicatesNS() {
        
        if (fLength <= SIZE_LIMIT) {
            for (int i = 0; i < fLength - 1; ++i) {
                Attribute att1 = fAttributes[i];
                for (int j = i + 1; j < fLength; ++j) {
                    Attribute att2 = fAttributes[j];
                    if (att1.name.localpart == att2.name.localpart &&
                        att1.name.uri == att2.name.uri) {
                        return att2.name;       
                    }
                }
            }
        }
        
        else {
            
            
            fIsTableViewConsistent = false;

            prepareTableView();

            Attribute attr;
            int bucket;

            for (int i = fLength - 1; i >= 0; --i) {
                attr = fAttributes[i];
                bucket = getTableViewBucket(attr.name.localpart, attr.name.uri);
                
                
                
                if (fAttributeTableViewChainState[bucket] != fLargeCount) {
                    fAttributeTableViewChainState[bucket] = fLargeCount;
                    attr.next = null;
                    fAttributeTableView[bucket] = attr;
                } 
                
                
                else {
                    
                    Attribute found = fAttributeTableView[bucket];
                    while (found != null) {
                        if (found.name.localpart == attr.name.localpart &&
                            found.name.uri == attr.name.uri) {
                            return attr.name;
                        }
                        found = found.next;
                    }
                    
                    
                    attr.next = fAttributeTableView[bucket];
                    fAttributeTableView[bucket] = attr;
                }
            }
        }
        return null;
    }
    
    
    public int getIndexFast(String uri, String localPart) {
        for (int i = 0; i < fLength; ++i) {
            Attribute attribute = fAttributes[i];
            if (attribute.name.localpart == localPart && 
                attribute.name.uri == uri) {
                return i;
            }
        }
        return -1;
    } 

    
    private String getReportableType(String type) {

        if (type.charAt(0) == '(') {
            return "NMTOKEN";
        }
        return type;
    }
    
    
    protected int getTableViewBucket(String qname) {
        return (qname.hashCode() & 0x7FFFFFFF) % fTableViewBuckets;
    }
    
    
    protected int getTableViewBucket(String localpart, String uri) {
        if (uri == null) {
            return (localpart.hashCode() & 0x7FFFFFFF) % fTableViewBuckets;
        }
        else {
            return ((localpart.hashCode() + uri.hashCode()) 
               & 0x7FFFFFFF) % fTableViewBuckets;
        }
    }
    
    
    protected void cleanTableView() {
        if (++fLargeCount < 0) {
            
            if (fAttributeTableViewChainState != null) {
                for (int i = fTableViewBuckets - 1; i >= 0; --i) {
                    fAttributeTableViewChainState[i] = 0;
                } 
            }
            fLargeCount = 1;
        }
    }
    
    
    protected void prepareTableView() {
        if (fAttributeTableView == null) {
            fAttributeTableView = new Attribute[fTableViewBuckets];
            fAttributeTableViewChainState = new int[fTableViewBuckets];
        }
        else {
            cleanTableView();
        }
    }
    
    
    protected void prepareAndPopulateTableView() {
        prepareTableView();
        
        Attribute attr;
        int bucket;
        for (int i = 0; i < fLength; ++i) {
            attr = fAttributes[i];
            bucket = getTableViewBucket(attr.name.rawname);
            if (fAttributeTableViewChainState[bucket] != fLargeCount) {
                fAttributeTableViewChainState[bucket] = fLargeCount;
                attr.next = null;
                fAttributeTableView[bucket] = attr;
            } 
            else {
                
                attr.next = fAttributeTableView[bucket];
                fAttributeTableView[bucket] = attr;
            }
        }
    }


    
    public String getPrefix(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        String prefix = fAttributes[index].name.prefix;
        
        return prefix != null ? prefix : "";
    } 

    
    public String getURI(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        String uri = fAttributes[index].name.uri;
        return uri;                        
    } 

    
    public String getValue(String uri, String localName) {
        int index = getIndex(uri, localName);
        return index != -1 ? getValue(index) : null;
    } 

    
    public Augmentations getAugmentations (String uri, String localName) {
        int index = getIndex(uri, localName);
        return index != -1 ? fAttributes[index].augs : null;
    }

    
    public Augmentations getAugmentations(String qName){
        int index = getIndex(qName);
        return index != -1 ? fAttributes[index].augs : null;
    }



    
    public Augmentations getAugmentations (int attributeIndex){
        if (attributeIndex < 0 || attributeIndex >= fLength) {
            return null;
        }
        return fAttributes[attributeIndex].augs;
    }

    
    public void setAugmentations(int attrIndex, Augmentations augs) {
        fAttributes[attrIndex].augs = augs;
    }

    
    public void setURI(int attrIndex, String uri) {
        fAttributes[attrIndex].name.uri = uri;
    } 
    
    
    public void setSchemaId(int attrIndex, boolean schemaId) {
        fAttributes[attrIndex].schemaId = schemaId;
    }
    
    public boolean getSchemaId(int index) {
        if (index < 0 || index >= fLength) {
            return false;
        }
        return fAttributes[index].schemaId;
    }
    
    public boolean getSchemaId(String qname) {
        int index = getIndex(qname);
        return index != -1 ? fAttributes[index].schemaId : false; 
    } 
    
    public boolean getSchemaId(String uri, String localName) {
        if (!fNamespaces) {
            return false;
        }
        int index = getIndex(uri, localName);
        return index != -1 ? fAttributes[index].schemaId : false;
    } 
    
    
    
    public void refresh() {
        if(fLength > 0){
            for(int i = 0 ; i < fLength ; i++){
                getValue(i);
            }
        }
    }  
    public void refresh(int pos) {
	}

    
    
    

    
    static class Attribute {
        
        
        
        

        

        
        public QName name = new QName();

        
        public String type;

        
        public String value;

        
        public XMLString xmlValue;
        
        
        public String nonNormalizedValue;

        
        public boolean specified;
        
        
        public boolean schemaId;
        
        
        public Augmentations augs = new AugmentationsImpl();
        
        
        
        
        public Attribute next;
        
    } 

} 
