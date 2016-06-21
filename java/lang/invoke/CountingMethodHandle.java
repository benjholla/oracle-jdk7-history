

package java.lang.invoke;

import static java.lang.invoke.MethodHandleNatives.Constants.*;


class CountingMethodHandle extends AdapterMethodHandle {
    private int vmcount;

    private CountingMethodHandle(MethodHandle target) {
        super(target, target.type(), AdapterMethodHandle.makeConv(OP_RETYPE_ONLY));
    }

    
    static MethodHandle wrap(MethodHandle mh) {
        if (MethodHandleNatives.COUNT_GWT) {
            return new CountingMethodHandle(mh);
        }
        return mh;
    }
}
