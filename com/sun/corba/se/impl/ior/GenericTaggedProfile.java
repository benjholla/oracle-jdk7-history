

package com.sun.corba.se.impl.ior;

import org.omg.CORBA_2_3.portable.InputStream ;

import com.sun.corba.se.spi.ior.TaggedProfile ;
import com.sun.corba.se.spi.ior.TaggedProfileTemplate ;
import com.sun.corba.se.spi.ior.ObjectId ;
import com.sun.corba.se.spi.ior.ObjectKeyTemplate ;
import com.sun.corba.se.spi.ior.ObjectKey ;

import com.sun.corba.se.spi.orb.ORB ;

import com.sun.corba.se.spi.ior.iiop.GIOPVersion ;

import com.sun.corba.se.impl.encoding.EncapsOutputStream ;


public class GenericTaggedProfile extends GenericIdentifiable implements TaggedProfile
{
    private ORB orb ;

    public GenericTaggedProfile( int id, InputStream is )
    {
        super( id, is ) ;
        this.orb = (ORB)(is.orb()) ;
    }

    public GenericTaggedProfile( ORB orb, int id, byte[] data )
    {
        super( id, data ) ;
        this.orb = orb ;
    }

    public TaggedProfileTemplate getTaggedProfileTemplate()
    {
        return null ;
    }

    public ObjectId getObjectId()
    {
        return null ;
    }

    public ObjectKeyTemplate getObjectKeyTemplate()
    {
        return null ;
    }

    public ObjectKey getObjectKey()
    {
        return null ;
    }

    public boolean isEquivalent( TaggedProfile prof )
    {
        return equals( prof ) ;
    }

    public void makeImmutable()
    {
        
    }

    public boolean isLocal()
    {
        return false ;
    }

    public org.omg.IOP.TaggedProfile getIOPProfile()
    {
        EncapsOutputStream os = new EncapsOutputStream( orb ) ;
        write( os ) ;
        InputStream is = (InputStream)(os.create_input_stream()) ;
        return org.omg.IOP.TaggedProfileHelper.read( is ) ;
    }
}
