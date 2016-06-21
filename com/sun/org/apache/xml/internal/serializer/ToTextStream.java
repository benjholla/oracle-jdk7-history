


package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;

import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public final class ToTextStream extends ToStream
{


  
  public ToTextStream()
  {
    super();
  }



  
  protected void startDocumentInternal() throws org.xml.sax.SAXException
  {
    super.startDocumentInternal();

    m_needToCallStartDocument = false;

    
  }

  
  public void endDocument() throws org.xml.sax.SAXException
  {
    flushPending();
    flushWriter();
    if (m_tracer != null)
        super.fireEndDoc();
  }

  
  public void startElement(
          String namespaceURI, String localName, String name, Attributes atts)
            throws org.xml.sax.SAXException
  {
    
    if (m_tracer != null) {
        super.fireStartElem(name);
        this.firePseudoAttributes();
    }
    return;
  }

  
  public void endElement(String namespaceURI, String localName, String name)
          throws org.xml.sax.SAXException
  {
        if (m_tracer != null)
            super.fireEndElem(name);
  }

  
  public void characters(char ch[], int start, int length)
          throws org.xml.sax.SAXException
  {

    flushPending();

    try
    {
        if (inTemporaryOutputState()) {
            
            m_writer.write(ch, start, length);
        }
        else {
            
            writeNormalizedChars(ch, start, length, m_lineSepUse);
        }

        if (m_tracer != null)
            super.fireCharEvent(ch, start, length);
    }
    catch(IOException ioe)
    {
      throw new SAXException(ioe);
    }
  }

  
  public void charactersRaw(char ch[], int start, int length)
          throws org.xml.sax.SAXException
  {

    try
    {
      writeNormalizedChars(ch, start, length, m_lineSepUse);
    }
    catch(IOException ioe)
    {
      throw new SAXException(ioe);
    }
  }

    
    void writeNormalizedChars(
        final char ch[],
            final int start,
            final int length,
            final boolean useLineSep)
            throws IOException, org.xml.sax.SAXException
    {
        final String encoding = getEncoding();
        final java.io.Writer writer = m_writer;
        final int end = start + length;

        
        final char S_LINEFEED = CharInfo.S_LINEFEED;

        
        
        
        
        for (int i = start; i < end; i++) {
            final char c = ch[i];

            if (S_LINEFEED == c && useLineSep) {
                writer.write(m_lineSep, 0, m_lineSepLen);
                
            } else if (m_encodingInfo.isInEncoding(c)) {
                writer.write(c);
                
            } else if (Encodings.isHighUTF16Surrogate(c)) {
                final int codePoint = writeUTF16Surrogate(c, ch, i, end);
                if (codePoint != 0) {
                    
                    
                    final String integralValue = Integer.toString(codePoint);
                    final String msg = Utils.messages.createMessage(
                        MsgKey.ER_ILLEGAL_CHARACTER,
                        new Object[] { integralValue, encoding });

                    
                    
                    
                    System.err.println(msg);

                }
                i++; 
            } else {
                
                
                
                if (encoding != null) {
                    

                    
                    writer.write('&');
                    writer.write('#');
                    writer.write(Integer.toString(c));
                    writer.write(';');

                    
                    
                    final String integralValue = Integer.toString(c);
                    final String msg = Utils.messages.createMessage(
                        MsgKey.ER_ILLEGAL_CHARACTER,
                        new Object[] { integralValue, encoding });

                    
                    
                    
                    System.err.println(msg);
                } else {
                    
                    writer.write(c);
                }

                
            }
        }
    }

  
  public void cdata(char ch[], int start, int length)
          throws org.xml.sax.SAXException
  {
    try
    {
        writeNormalizedChars(ch, start, length, m_lineSepUse);
        if (m_tracer != null)
            super.fireCDATAEvent(ch, start, length);
    }
    catch(IOException ioe)
    {
      throw new SAXException(ioe);
    }
  }

  
  public void ignorableWhitespace(char ch[], int start, int length)
          throws org.xml.sax.SAXException
  {

    try
    {
      writeNormalizedChars(ch, start, length, m_lineSepUse);
    }
    catch(IOException ioe)
    {
      throw new SAXException(ioe);
    }
  }

  
  public void processingInstruction(String target, String data)
          throws org.xml.sax.SAXException
  {
    
    flushPending();

    if (m_tracer != null)
        super.fireEscapingEvent(target, data);
  }

  
  public void comment(String data) throws org.xml.sax.SAXException
  {
      final int length = data.length();
      if (length > m_charsBuff.length)
      {
          m_charsBuff = new char[length*2 + 1];
      }
      data.getChars(0, length, m_charsBuff, 0);
      comment(m_charsBuff, 0, length);
  }

  
  public void comment(char ch[], int start, int length)
          throws org.xml.sax.SAXException
  {

    flushPending();
    if (m_tracer != null)
        super.fireCommentEvent(ch, start, length);
  }

  
  public void entityReference(String name) throws org.xml.sax.SAXException
  {
        if (m_tracer != null)
            super.fireEntityReference(name);
  }

    
    public void addAttribute(
        String uri,
        String localName,
        String rawName,
        String type,
        String value,
        boolean XSLAttribute)
    {
        
    }

    
    public void endCDATA() throws SAXException
    {
        
    }

    
    public void endElement(String elemName) throws SAXException
    {
        if (m_tracer != null)
            super.fireEndElem(elemName);
    }

    
    public void startElement(
    String elementNamespaceURI,
    String elementLocalName,
    String elementName)
    throws SAXException
    {
        if (m_needToCallStartDocument)
            startDocumentInternal();
        
        if (m_tracer != null) {
            super.fireStartElem(elementName);
            this.firePseudoAttributes();
        }

        return;
    }


    
    public void characters(String characters)
    throws SAXException
    {
        final int length = characters.length();
        if (length > m_charsBuff.length)
        {
            m_charsBuff = new char[length*2 + 1];
        }
        characters.getChars(0, length, m_charsBuff, 0);
        characters(m_charsBuff, 0, length);
    }


    
    public void addAttribute(String name, String value)
    {
        
    }

    
    public void addUniqueAttribute(String qName, String value, int flags)
        throws SAXException
    {
        
    }

    public boolean startPrefixMapping(
        String prefix,
        String uri,
        boolean shouldFlush)
        throws SAXException
    {
        
        return false;
    }


    public void startPrefixMapping(String prefix, String uri)
        throws org.xml.sax.SAXException
    {
        
    }


    public void namespaceAfterStartElement(
        final String prefix,
        final String uri)
        throws SAXException
    {
        
    }

    public void flushPending() throws org.xml.sax.SAXException
    {
            if (m_needToCallStartDocument)
            {
                startDocumentInternal();
                m_needToCallStartDocument = false;
            }
    }
}
