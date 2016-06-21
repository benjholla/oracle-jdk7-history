
package org.omg.CORBA;




public abstract class FloatSeqHelper
{
    private static String  _id = "IDL:omg.org/CORBA/FloatSeq:1.0";

    public static void insert (org.omg.CORBA.Any a, float[] that)
    {
        org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
        a.type (type ());
        write (out, that);
        a.read_value (out.create_input_stream (), type ());
    }

    public static float[] extract (org.omg.CORBA.Any a)
    {
        return read (a.create_input_stream ());
    }

    private static org.omg.CORBA.TypeCode __typeCode = null;
    synchronized public static org.omg.CORBA.TypeCode type ()
    {
        if (__typeCode == null)
            {
                __typeCode = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
                __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
                __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (org.omg.CORBA.FloatSeqHelper.id (), "FloatSeq", __typeCode);
            }
        return __typeCode;
    }

    public static String id ()
    {
        return _id;
    }

    public static float[] read (org.omg.CORBA.portable.InputStream istream)
    {
        float value[] = null;
        int _len0 = istream.read_long ();
        value = new float[_len0];
        istream.read_float_array (value, 0, _len0);
        return value;
    }

    public static void write (org.omg.CORBA.portable.OutputStream ostream, float[] value)
    {
        ostream.write_long (value.length);
        ostream.write_float_array (value, 0, value.length);
    }

}
