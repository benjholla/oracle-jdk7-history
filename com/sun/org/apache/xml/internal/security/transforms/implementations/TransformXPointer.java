

package com.sun.org.apache.xml.internal.security.transforms.implementations;




import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.transforms.Transform;
import com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import com.sun.org.apache.xml.internal.security.transforms.TransformationException;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;




public class TransformXPointer extends TransformSpi {

   
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_XPOINTER;


   
   protected String engineGetURI() {
      return implementedTransformURI;
   }

   
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input, Transform _transformObject)
           throws  TransformationException {

      Object exArgs[] = { implementedTransformURI };

      throw new TransformationException(
         "signature.Transform.NotYetImplemented", exArgs);
   }
}
