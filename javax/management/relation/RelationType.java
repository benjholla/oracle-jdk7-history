

package javax.management.relation;

import java.util.ArrayList; 
import java.util.List;

import java.io.Serializable;


public interface RelationType extends Serializable {

    
    
    

    
    public String getRelationTypeName();

    
    public List<RoleInfo> getRoleInfos();

    
    public RoleInfo getRoleInfo(String roleInfoName)
        throws IllegalArgumentException,
               RoleInfoNotFoundException;
}
