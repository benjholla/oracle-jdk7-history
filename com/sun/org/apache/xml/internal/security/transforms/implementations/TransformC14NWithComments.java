

package com.sun.org.apache.xml.internal.security.transforms.implementations;

import java.io.OutputStream;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315WithComments;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.transforms.Transform;
import com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;


public class TransformC14NWithComments extends TransformSpi {

   
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_C14N_WITH_COMMENTS;

   
   protected String engineGetURI() {
      return implementedTransformURI;
   }

   
   protected XMLSignatureInput enginePerformTransform
        (XMLSignatureInput input, Transform _transformObject)
        throws CanonicalizationException {
            return enginePerformTransform(input, null, _transformObject);
   }

   
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input,OutputStream os, Transform _transformObject)
           throws CanonicalizationException {

        Canonicalizer20010315WithComments c14n = new Canonicalizer20010315WithComments();
        if (os!=null) {
                c14n.setWriter( os);
        }

         byte[] result = null;
         result=c14n.engineCanonicalize(input);
         XMLSignatureInput output=new XMLSignatureInput(result);
         if (os!=null) {
                output.setOutputStream(os);
         }
         return output;
   }
}
