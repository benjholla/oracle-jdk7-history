



package com.sun.org.apache.xml.internal.serialize;


import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;



public class IndentPrinter
    extends Printer
{


    
    private StringBuffer    _line;


    
    private StringBuffer    _text;


    
    private int             _spaces;


    
    private int             _thisIndent;


    
    private int             _nextIndent;


    public IndentPrinter( Writer writer, OutputFormat format)
    {
        super( writer, format );
        
        _line = new StringBuffer( 80 );
        _text = new StringBuffer( 20 );
        _spaces = 0;
        _thisIndent = _nextIndent = 0;
    }


    
    public void enterDTD()
    {
        
        
        if ( _dtdWriter == null ) {
            _line.append( _text );
            _text = new StringBuffer( 20 );
            flushLine( false );
            _dtdWriter = new StringWriter();
            _docWriter = _writer;
            _writer = _dtdWriter;
        }
    }


    
    public String leaveDTD()
    {
        
        if ( _writer == _dtdWriter ) {
            _line.append( _text );
            _text = new StringBuffer( 20 );
            flushLine( false );
            _writer = _docWriter;
            return _dtdWriter.toString();
        } else
            return null;
    }


    
    public void printText( String text )
    {
        _text.append( text );
    }


    public void printText( StringBuffer text )
    {
        _text.append( text.toString() );
    }


    public void printText( char ch )
    {
        _text.append( ch );
    }


    public void printText( char[] chars, int start, int length )
    {
        _text.append( chars, start, length );
    }


    
    public void printSpace()
    {
        
        
        
        
        
        
        
        
        
        

        
        
        
        if ( _text.length() > 0 ) {
            
            
            
            
            if ( _format.getLineWidth() > 0 &&
                 _thisIndent + _line.length() + _spaces + _text.length() > _format.getLineWidth() ) {
                flushLine( false );
                try {
                    
                    _writer.write( _format.getLineSeparator() );
                } catch ( IOException except ) {
                    
                    
                    if ( _exception == null )
                        _exception = except;
                }
            }

            
            
            while ( _spaces > 0 ) {
                _line.append( ' ' );
                --_spaces;
            }
            _line.append( _text );
            _text = new StringBuffer( 20 );
        }
        
        
        ++_spaces;
    }


    
    public void breakLine()
    {
        breakLine( false );
    }


    public void breakLine( boolean preserveSpace )
    {
        
        if ( _text.length() > 0 ) {
            while ( _spaces > 0 ) {
                _line.append( ' ' );
                --_spaces;
            }
            _line.append( _text );
            _text = new StringBuffer( 20 );
        }
        flushLine( preserveSpace );
        try {
            
            _writer.write( _format.getLineSeparator() );
        } catch ( IOException except ) {
            
            
            if ( _exception == null )
                _exception = except;
        }
    }


    
    public void flushLine( boolean preserveSpace )
    {
        int     indent;

        if ( _line.length() > 0 ) {
            try {

                if ( _format.getIndenting() && ! preserveSpace ) {
                    
                    indent = _thisIndent;
                    if ( ( 2 * indent ) > _format.getLineWidth() && _format.getLineWidth() > 0 )
                        indent = _format.getLineWidth() / 2;
                    
                    
                    while ( indent > 0 ) {
                        _writer.write( ' ' );
                        --indent;
                    }
                }
                _thisIndent = _nextIndent;

                
                
                
                _spaces = 0;
                _writer.write( _line.toString() );

                _line = new StringBuffer( 40 );
            } catch ( IOException except ) {
                
                
                if ( _exception == null )
                    _exception = except;
            }
        }
    }


    
    public void flush()
    {
        if ( _line.length() > 0 || _text.length() > 0 )
            breakLine();
        try {
            _writer.flush();
        } catch ( IOException except ) {
            
            
            if ( _exception == null )
                _exception = except;
        }
    }


    
    public void indent()
    {
        _nextIndent += _format.getIndent();
    }


    
    public void unindent()
    {
        _nextIndent -= _format.getIndent();
        if ( _nextIndent < 0 )
            _nextIndent = 0;
        
        
        if ( ( _line.length() + _spaces + _text.length() ) == 0 )
            _thisIndent = _nextIndent;
    }


    public int getNextIndent()
    {
        return _nextIndent;
    }


    public void setNextIndent( int indent )
    {
        _nextIndent = indent;
    }


    public void setThisIndent( int indent )
    {
        _thisIndent = indent;
    }


}
