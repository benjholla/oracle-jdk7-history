

package java.lang.invoke;

import static com.sun.xml.internal.ws.org.objectweb.asm.Opcodes.*;
import static java.lang.invoke.LambdaForm.basicTypes;
import static java.lang.invoke.MethodHandleNatives.Constants.REF_invokeStatic;
import static java.lang.invoke.MethodHandleStatics.*;

import java.lang.invoke.LambdaForm.Name;
import java.lang.invoke.LambdaForm.NamedFunction;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

import sun.invoke.util.ValueConversions;
import sun.invoke.util.Wrapper;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;


 abstract class BoundMethodHandle extends MethodHandle {

     BoundMethodHandle(MethodType type, LambdaForm form) {
        super(type, form);
    }

    
    
    

    static MethodHandle bindSingle(MethodType type, LambdaForm form, char xtype, Object x) {
        
        try {
            switch (xtype) {
            case 'L':
                if (true)  return bindSingle(type, form, x);  
                return (BoundMethodHandle) SpeciesData.EMPTY.extendWithType('L').constructor[0].invokeBasic(type, form, x);
            case 'I':
                return (BoundMethodHandle) SpeciesData.EMPTY.extendWithType('I').constructor[0].invokeBasic(type, form, ValueConversions.widenSubword(x));
            case 'J':
                return (BoundMethodHandle) SpeciesData.EMPTY.extendWithType('J').constructor[0].invokeBasic(type, form, (long) x);
            case 'F':
                return (BoundMethodHandle) SpeciesData.EMPTY.extendWithType('F').constructor[0].invokeBasic(type, form, (float) x);
            case 'D':
                return (BoundMethodHandle) SpeciesData.EMPTY.extendWithType('D').constructor[0].invokeBasic(type, form, (double) x);
            default : throw new InternalError("unexpected xtype: " + xtype);
            }
        } catch (Throwable t) {
            throw newInternalError(t);
        }
    }

    static MethodHandle bindSingle(MethodType type, LambdaForm form, Object x) {
            return new Species_L(type, form, x);
    }

    MethodHandle cloneExtend(MethodType type, LambdaForm form, char xtype, Object x) {
        try {
            switch (xtype) {
            case 'L': return cloneExtendL(type, form, x);
            case 'I': return cloneExtendI(type, form, ValueConversions.widenSubword(x));
            case 'J': return cloneExtendJ(type, form, (long) x);
            case 'F': return cloneExtendF(type, form, (float) x);
            case 'D': return cloneExtendD(type, form, (double) x);
            }
        } catch (Throwable t) {
            throw newInternalError(t);
        }
        throw new InternalError("unexpected type: " + xtype);
    }

    @Override
    MethodHandle bindArgument(int pos, char basicType, Object value) {
        MethodType type = type().dropParameterTypes(pos, pos+1);
        LambdaForm form = internalForm().bind(1+pos, speciesData());
        return cloneExtend(type, form, basicType, value);
    }

    @Override
    MethodHandle dropArguments(MethodType srcType, int pos, int drops) {
        LambdaForm form = internalForm().addArguments(pos, srcType.parameterList().subList(pos, pos+drops));
        try {
             return clone(srcType, form);
         } catch (Throwable t) {
             throw newInternalError(t);
         }
    }

    @Override
    MethodHandle permuteArguments(MethodType newType, int[] reorder) {
        try {
             return clone(newType, form.permuteArguments(1, reorder, basicTypes(newType.parameterList())));
         } catch (Throwable t) {
             throw newInternalError(t);
         }
    }

    static final String EXTENSION_TYPES = "LIJFD";
    static final byte INDEX_L = 0, INDEX_I = 1, INDEX_J = 2, INDEX_F = 3, INDEX_D = 4;
    static byte extensionIndex(char type) {
        int i = EXTENSION_TYPES.indexOf(type);
        if (i < 0)  throw new InternalError();
        return (byte) i;
    }

    
    protected abstract SpeciesData speciesData();

    @Override
    final Object internalProperties() {
        return "/BMH="+internalValues();
    }

    @Override
    final Object internalValues() {
        Object[] boundValues = new Object[speciesData().fieldCount()];
        for (int i = 0; i < boundValues.length; ++i) {
            boundValues[i] = arg(i);
        }
        return Arrays.asList(boundValues);
    }

    public final Object arg(int i) {
        try {
            switch (speciesData().fieldType(i)) {
            case 'L': return argL(i);
            case 'I': return argI(i);
            case 'F': return argF(i);
            case 'D': return argD(i);
            case 'J': return argJ(i);
            }
        } catch (Throwable ex) {
            throw newInternalError(ex);
        }
        throw new InternalError("unexpected type: " + speciesData().types+"."+i);
    }
    public final Object argL(int i) throws Throwable { return          speciesData().getters[i].invokeBasic(this); }
    public final int    argI(int i) throws Throwable { return (int)    speciesData().getters[i].invokeBasic(this); }
    public final float  argF(int i) throws Throwable { return (float)  speciesData().getters[i].invokeBasic(this); }
    public final double argD(int i) throws Throwable { return (double) speciesData().getters[i].invokeBasic(this); }
    public final long   argJ(int i) throws Throwable { return (long)   speciesData().getters[i].invokeBasic(this); }

    
    
    

    public abstract BoundMethodHandle clone(MethodType mt, LambdaForm lf) throws Throwable;
    public abstract BoundMethodHandle cloneExtendL(MethodType mt, LambdaForm lf, Object narg) throws Throwable;
    public abstract BoundMethodHandle cloneExtendI(MethodType mt, LambdaForm lf, int    narg) throws Throwable;
    public abstract BoundMethodHandle cloneExtendJ(MethodType mt, LambdaForm lf, long   narg) throws Throwable;
    public abstract BoundMethodHandle cloneExtendF(MethodType mt, LambdaForm lf, float  narg) throws Throwable;
    public abstract BoundMethodHandle cloneExtendD(MethodType mt, LambdaForm lf, double narg) throws Throwable;

    
    @Override MethodHandle reinvokerTarget() {
        try {
            return (MethodHandle) argL(0);
        } catch (Throwable ex) {
            throw newInternalError(ex);
        }
    }

    
    
    

    private  
    static final class Species_L extends BoundMethodHandle {
        final Object argL0;
        public Species_L(MethodType mt, LambdaForm lf, Object argL0) {
            super(mt, lf);
            this.argL0 = argL0;
        }
        
        @Override MethodHandle reinvokerTarget() { return (MethodHandle) argL0; }
        @Override
        public SpeciesData speciesData() {
            return SPECIES_DATA;
        }
        public static final SpeciesData SPECIES_DATA = SpeciesData.getForClass("L", Species_L.class);
        @Override
        public final BoundMethodHandle clone(MethodType mt, LambdaForm lf) throws Throwable {
            return new Species_L(mt, lf, argL0);
        }
        @Override
        public final BoundMethodHandle cloneExtendL(MethodType mt, LambdaForm lf, Object narg) throws Throwable {
            return (BoundMethodHandle) SPECIES_DATA.extendWithIndex(INDEX_L).constructor[0].invokeBasic(mt, lf, argL0, narg);
        }
        @Override
        public final BoundMethodHandle cloneExtendI(MethodType mt, LambdaForm lf, int narg) throws Throwable {
            return (BoundMethodHandle) SPECIES_DATA.extendWithIndex(INDEX_I).constructor[0].invokeBasic(mt, lf, argL0, narg);
        }
        @Override
        public final BoundMethodHandle cloneExtendJ(MethodType mt, LambdaForm lf, long narg) throws Throwable {
            return (BoundMethodHandle) SPECIES_DATA.extendWithIndex(INDEX_J).constructor[0].invokeBasic(mt, lf, argL0, narg);
        }
        @Override
        public final BoundMethodHandle cloneExtendF(MethodType mt, LambdaForm lf, float narg) throws Throwable {
            return (BoundMethodHandle) SPECIES_DATA.extendWithIndex(INDEX_F).constructor[0].invokeBasic(mt, lf, argL0, narg);
        }
        @Override
        public final BoundMethodHandle cloneExtendD(MethodType mt, LambdaForm lf, double narg) throws Throwable {
            return (BoundMethodHandle) SPECIES_DATA.extendWithIndex(INDEX_D).constructor[0].invokeBasic(mt, lf, argL0, narg);
        }
    }



    
    
    

    
    static class SpeciesData {
        final String                             types;
        final Class<? extends BoundMethodHandle> clazz;
        
        
        final MethodHandle[]                     constructor;
        final MethodHandle[]                     getters;
        final SpeciesData[]                      extensions;

        public int fieldCount() {
            return types.length();
        }
        public char fieldType(int i) {
            return types.charAt(i);
        }

        public String toString() {
            return "SpeciesData["+(isPlaceholder() ? "<placeholder>" : clazz.getSimpleName())+":"+types+"]";
        }

        
        Name getterName(Name mhName, int i) {
            MethodHandle mh = getters[i];
            assert(mh != null) : this+"."+i;
            return new Name(mh, mhName);
        }

        static final SpeciesData EMPTY = new SpeciesData("", BoundMethodHandle.class);

        private SpeciesData(String types, Class<? extends BoundMethodHandle> clazz) {
            this.types = types;
            this.clazz = clazz;
            if (!INIT_DONE) {
                this.constructor = new MethodHandle[1];
                this.getters = new MethodHandle[types.length()];
            } else {
                this.constructor = Factory.makeCtors(clazz, types, null);
                this.getters = Factory.makeGetters(clazz, types, null);
            }
            this.extensions = new SpeciesData[EXTENSION_TYPES.length()];
        }

        private void initForBootstrap() {
            assert(!INIT_DONE);
            if (constructor[0] == null) {
                Factory.makeCtors(clazz, types, this.constructor);
                Factory.makeGetters(clazz, types, this.getters);
            }
        }

        private SpeciesData(String types) {
            
            this.types = types;
            this.clazz = null;
            this.constructor = null;
            this.getters = null;
            this.extensions = null;
        }
        private boolean isPlaceholder() { return clazz == null; }

        private static final HashMap<String, SpeciesData> CACHE = new HashMap<>();
        private static final boolean INIT_DONE;  

        SpeciesData extendWithType(char type) {
            int i = extensionIndex(type);
            SpeciesData d = extensions[i];
            if (d != null)  return d;
            extensions[i] = d = get(types+type);
            return d;
        }

        SpeciesData extendWithIndex(byte index) {
            SpeciesData d = extensions[index];
            if (d != null)  return d;
            extensions[index] = d = get(types+EXTENSION_TYPES.charAt(index));
            return d;
        }

        private static SpeciesData get(String types) {
            
            SpeciesData d = lookupCache(types);
            if (!d.isPlaceholder())
                return d;
            synchronized (d) {
                
                
                if (lookupCache(types).isPlaceholder())
                    Factory.generateConcreteBMHClass(types);
            }
            
            d = lookupCache(types);
            
            assert(d != null && !d.isPlaceholder());
            return d;
        }
        static SpeciesData getForClass(String types, Class<? extends BoundMethodHandle> clazz) {
            
            return updateCache(types, new SpeciesData(types, clazz));
        }
        private static synchronized SpeciesData lookupCache(String types) {
            SpeciesData d = CACHE.get(types);
            if (d != null)  return d;
            d = new SpeciesData(types);
            assert(d.isPlaceholder());
            CACHE.put(types, d);
            return d;
        }
        private static synchronized SpeciesData updateCache(String types, SpeciesData d) {
            SpeciesData d2;
            assert((d2 = CACHE.get(types)) == null || d2.isPlaceholder());
            assert(!d.isPlaceholder());
            CACHE.put(types, d);
            return d;
        }

        static {
            
            final Class<BoundMethodHandle> rootCls = BoundMethodHandle.class;
            SpeciesData d0 = BoundMethodHandle.SPECIES_DATA;  
            assert(d0 == null || d0 == lookupCache("")) : d0;
            try {
                for (Class<?> c : rootCls.getDeclaredClasses()) {
                    if (rootCls.isAssignableFrom(c)) {
                        final Class<? extends BoundMethodHandle> cbmh = c.asSubclass(BoundMethodHandle.class);
                        SpeciesData d = Factory.speciesDataFromConcreteBMHClass(cbmh);
                        assert(d != null) : cbmh.getName();
                        assert(d.clazz == cbmh);
                        assert(d == lookupCache(d.types));
                    }
                }
            } catch (Throwable e) {
                throw newInternalError(e);
            }

            for (SpeciesData d : CACHE.values()) {
                d.initForBootstrap();
            }
            
            
            INIT_DONE = Boolean.TRUE;
        }
    }

    static SpeciesData getSpeciesData(String types) {
        return SpeciesData.get(types);
    }

    
    static class Factory {

        static final String JLO_SIG  = "Ljava/lang/Object;";
        static final String JLS_SIG  = "Ljava/lang/String;";
        static final String JLC_SIG  = "Ljava/lang/Class;";
        static final String MH       = "java/lang/invoke/MethodHandle";
        static final String MH_SIG   = "L"+MH+";";
        static final String BMH      = "java/lang/invoke/BoundMethodHandle";
        static final String BMH_SIG  = "L"+BMH+";";
        static final String SPECIES_DATA     = "java/lang/invoke/BoundMethodHandle$SpeciesData";
        static final String SPECIES_DATA_SIG = "L"+SPECIES_DATA+";";

        static final String SPECIES_PREFIX_NAME = "Species_";
        static final String SPECIES_PREFIX_PATH = BMH + "$" + SPECIES_PREFIX_NAME;

        static final String BMHSPECIES_DATA_EWI_SIG = "(B)" + SPECIES_DATA_SIG;
        static final String BMHSPECIES_DATA_GFC_SIG = "(" + JLS_SIG + JLC_SIG + ")" + SPECIES_DATA_SIG;
        static final String MYSPECIES_DATA_SIG = "()" + SPECIES_DATA_SIG;
        static final String VOID_SIG   = "()V";

        static final String SIG_INCIPIT = "(Ljava/lang/invoke/MethodType;Ljava/lang/invoke/LambdaForm;";

        static final Class<?>[] TYPES = new Class<?>[] { Object.class, int.class, long.class, float.class, double.class };

        static final String[] E_THROWABLE = new String[] { "java/lang/Throwable" };

        
        static Class<? extends BoundMethodHandle> generateConcreteBMHClass(String types) {
            final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);

            final String className  = SPECIES_PREFIX_PATH + types;
            final String sourceFile = SPECIES_PREFIX_NAME + types;
            cw.visit(V1_6, ACC_PUBLIC + ACC_FINAL + ACC_SUPER, className, null, BMH, null);
            cw.visitSource(sourceFile, null);

            
            cw.visitField(ACC_PUBLIC + ACC_STATIC, "SPECIES_DATA", SPECIES_DATA_SIG, null, null).visitEnd();

            
            for (int i = 0; i < types.length(); ++i) {
                final char t = types.charAt(i);
                final String fieldName = makeFieldName(types, i);
                final String fieldDesc = t == 'L' ? JLO_SIG : String.valueOf(t);
                cw.visitField(ACC_FINAL, fieldName, fieldDesc, null, null).visitEnd();
            }

            MethodVisitor mv;

            
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", makeSignature(types, true), null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);

            mv.visitMethodInsn(INVOKESPECIAL, BMH, "<init>", makeSignature("", true));

            for (int i = 0, j = 0; i < types.length(); ++i, ++j) {
                
                char t = types.charAt(i);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitVarInsn(typeLoadOp(t), j + 3); 
                mv.visitFieldInsn(PUTFIELD, className, makeFieldName(types, i), typeSig(t));
                if (t == 'J' || t == 'D') {
                    ++j; 
                }
            }

            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();

            
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "reinvokerTarget", "()" + MH_SIG, null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, className, "argL0", JLO_SIG);
            mv.visitTypeInsn(CHECKCAST, MH);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();

            
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "speciesData", MYSPECIES_DATA_SIG, null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, className, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitInsn(ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();

            
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "clone", makeSignature("", false), null, E_THROWABLE);
            mv.visitCode();
            
            
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETSTATIC, className, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitFieldInsn(GETFIELD, SPECIES_DATA, "constructor", "[" + MH_SIG);
            mv.visitInsn(ICONST_0);
            mv.visitInsn(AALOAD);
            
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            
            emitPushFields(types, className, mv);
            
            mv.visitMethodInsn(INVOKEVIRTUAL, MH, "invokeBasic", makeSignature(types, false));
            mv.visitInsn(ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();

            
            for (Class<?> c : TYPES) {
                char t = Wrapper.basicTypeChar(c);
                mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "cloneExtend" + t, makeSignature(String.valueOf(t), false), null, E_THROWABLE);
                mv.visitCode();
                
                
                mv.visitFieldInsn(GETSTATIC, className, "SPECIES_DATA", SPECIES_DATA_SIG);
                int iconstInsn = ICONST_0 + extensionIndex(t);
                assert(iconstInsn <= ICONST_5);
                mv.visitInsn(iconstInsn);
                mv.visitMethodInsn(INVOKEVIRTUAL, SPECIES_DATA, "extendWithIndex", BMHSPECIES_DATA_EWI_SIG);
                mv.visitFieldInsn(GETFIELD, SPECIES_DATA, "constructor", "[" + MH_SIG);
                mv.visitInsn(ICONST_0);
                mv.visitInsn(AALOAD);
                
                mv.visitVarInsn(ALOAD, 1);
                mv.visitVarInsn(ALOAD, 2);
                
                emitPushFields(types, className, mv);
                
                mv.visitVarInsn(typeLoadOp(t), 3);
                
                mv.visitMethodInsn(INVOKEVIRTUAL, MH, "invokeBasic", makeSignature(types + t, false));
                mv.visitInsn(ARETURN);
                mv.visitMaxs(0, 0);
                mv.visitEnd();
            }

            
            mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "<clinit>", VOID_SIG, null, null);
            mv.visitCode();
            mv.visitLdcInsn(types);
            mv.visitLdcInsn(Type.getObjectType(className));
            mv.visitMethodInsn(INVOKESTATIC, SPECIES_DATA, "getForClass", BMHSPECIES_DATA_GFC_SIG);
            mv.visitFieldInsn(PUTSTATIC, className, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();

            cw.visitEnd();

            
            final byte[] classFile = cw.toByteArray();
            InvokerBytecodeGenerator.maybeDump(className, classFile);
            Class<? extends BoundMethodHandle> bmhClass =
                
                UNSAFE.defineClass(className, classFile, 0, classFile.length).asSubclass(BoundMethodHandle.class);
            UNSAFE.ensureClassInitialized(bmhClass);

            return bmhClass;
        }

        private static int typeLoadOp(char t) {
            switch (t) {
            case 'L': return ALOAD;
            case 'I': return ILOAD;
            case 'J': return LLOAD;
            case 'F': return FLOAD;
            case 'D': return DLOAD;
            default : throw new InternalError("unrecognized type " + t);
            }
        }

        private static void emitPushFields(String types, String className, MethodVisitor mv) {
            for (int i = 0; i < types.length(); ++i) {
                char tc = types.charAt(i);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETFIELD, className, makeFieldName(types, i), typeSig(tc));
            }
        }

        static String typeSig(char t) {
            return t == 'L' ? JLO_SIG : String.valueOf(t);
        }

        
        
        

        private static MethodHandle makeGetter(Class<?> cbmhClass, String types, int index) {
            String fieldName = makeFieldName(types, index);
            Class<?> fieldType = Wrapper.forBasicType(types.charAt(index)).primitiveType();
            try {
                return LOOKUP.findGetter(cbmhClass, fieldName, fieldType);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw newInternalError(e);
            }
        }

        static MethodHandle[] makeGetters(Class<?> cbmhClass, String types, MethodHandle[] mhs) {
            if (mhs == null)  mhs = new MethodHandle[types.length()];
            for (int i = 0; i < mhs.length; ++i) {
                mhs[i] = makeGetter(cbmhClass, types, i);
                assert(mhs[i].internalMemberName().getDeclaringClass() == cbmhClass);
            }
            return mhs;
        }

        static MethodHandle[] makeCtors(Class<? extends BoundMethodHandle> cbmh, String types, MethodHandle mhs[]) {
            if (mhs == null)  mhs = new MethodHandle[1];
            mhs[0] = makeCbmhCtor(cbmh, types);
            return mhs;
        }

        
        
        

        static SpeciesData speciesDataFromConcreteBMHClass(Class<? extends BoundMethodHandle> cbmh) {
            try {
                Field F_SPECIES_DATA = cbmh.getDeclaredField("SPECIES_DATA");
                return (SpeciesData) F_SPECIES_DATA.get(null);
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
        }

        
        private static String makeFieldName(String types, int index) {
            assert index >= 0 && index < types.length();
            return "arg" + types.charAt(index) + index;
        }

        private static String makeSignature(String types, boolean ctor) {
            StringBuilder buf = new StringBuilder(SIG_INCIPIT);
            for (char c : types.toCharArray()) {
                buf.append(typeSig(c));
            }
            return buf.append(')').append(ctor ? "V" : BMH_SIG).toString();
        }

        static MethodHandle makeCbmhCtor(Class<? extends BoundMethodHandle> cbmh, String types) {
            try {
                return linkConstructor(LOOKUP.findConstructor(cbmh, MethodType.fromMethodDescriptorString(makeSignature(types, true), null)));
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | TypeNotPresentException e) {
                throw newInternalError(e);
            }
        }

        
        private static MethodHandle linkConstructor(MethodHandle cmh) {
            final LambdaForm lf = cmh.form;
            final int initNameIndex = lf.names.length - 1;
            final Name initName = lf.names[initNameIndex];
            final MemberName ctorMN = initName.function.member;
            final MethodType ctorMT = ctorMN.getInvocationType();

            
            
            final MethodType linkerMT = ctorMT.changeParameterType(0, BoundMethodHandle.class).appendParameterTypes(MemberName.class);
            MemberName linkerMN = new MemberName(MethodHandle.class, "linkToSpecial", linkerMT, REF_invokeStatic);
            try {
                linkerMN = MemberName.getFactory().resolveOrFail(REF_invokeStatic, linkerMN, null, NoSuchMethodException.class);
                assert(linkerMN.isStatic());
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
            
            Object[] newArgs = Arrays.copyOf(initName.arguments, initName.arguments.length + 1);
            newArgs[newArgs.length - 1] = ctorMN;
            
            final NamedFunction nf = new NamedFunction(linkerMN);
            final Name linkedCtor = new Name(nf, newArgs);
            linkedCtor.initIndex(initNameIndex);
            lf.names[initNameIndex] = linkedCtor;
            return cmh;
        }

    }

    private static final Lookup LOOKUP = Lookup.IMPL_LOOKUP;

    
    static final SpeciesData SPECIES_DATA = SpeciesData.EMPTY;
}
