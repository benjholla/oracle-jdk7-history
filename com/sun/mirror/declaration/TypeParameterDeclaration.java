

package com.sun.mirror.declaration;


import java.util.Collection;

import com.sun.mirror.type.*;



@Deprecated
@SuppressWarnings("deprecation")
public interface TypeParameterDeclaration extends Declaration {

    
    Collection<ReferenceType> getBounds();

    
    Declaration getOwner();
}
