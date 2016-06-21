

package javax.management.relation;


public interface RelationSupportMBean
    extends Relation {

    
    public Boolean isInRelationService();

    
    public void setRelationServiceManagementFlag(Boolean flag)
        throws IllegalArgumentException;
}
