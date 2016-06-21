

package com.sun.org.apache.xml.internal.security.transforms.implementations;

import java.io.OutputStream;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_WithComments;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.transforms.Transform;
import com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;


public class TransformC14N11_WithComments extends TransformSpi {

    protected String engineGetURI() {
        return Transforms.TRANSFORM_C14N11_WITH_COMMENTS;
    }

    protected XMLSignatureInput enginePerformTransform
        (XMLSignatureInput input, Transform transform)
        throws CanonicalizationException {
        return enginePerformTransform(input, null, transform);
    }

    protected XMLSignatureInput enginePerformTransform
        (XMLSignatureInput input, OutputStream os, Transform transform)
        throws CanonicalizationException {

        Canonicalizer11_WithComments c14n = new Canonicalizer11_WithComments();
        if (os != null) {
            c14n.setWriter(os);
        }

        byte[] result = null;
        result = c14n.engineCanonicalize(input);
        XMLSignatureInput output = new XMLSignatureInput(result);
        if (os != null) {
            output.setOutputStream(os);
        }
        return output;
    }
}
