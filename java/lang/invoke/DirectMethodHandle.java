

package java.lang.invoke;

import static java.lang.invoke.MethodHandleNatives.Constants.*;


class DirectMethodHandle extends MethodHandle {
    
    private final int  vmindex;     
    { vmindex = VM_INDEX_UNINITIALIZED; }  

    
    DirectMethodHandle(MethodType mtype, MemberName m, boolean doDispatch, Class<?> lookupClass) {
        super(mtype);

        assert(m.isMethod() || !doDispatch && m.isConstructor());
        if (!m.isResolved())
            throw new InternalError();

        if (m.getDeclaringClass().isInterface() && !m.isAbstract()) {
            
            MemberName m2 = new MemberName(Object.class, m.getName(), m.getMethodType(), m.getModifiers());
            m2 = MemberName.getFactory().resolveOrNull(m2, false, null);
            if (m2 != null && m2.isPublic()) {
                m = m2;
            }
        }

        MethodHandleNatives.init(this, (Object) m, doDispatch, lookupClass);
    }

    boolean isValid() {
        return (vmindex != VM_INDEX_UNINITIALIZED);
    }
}
