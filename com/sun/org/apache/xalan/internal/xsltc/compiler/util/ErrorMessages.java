



package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import java.util.ListResourceBundle;


public class ErrorMessages extends ListResourceBundle {



    
    
    public Object[][] getContents()
    {
      return new Object[][] {
        {ErrorMsg.MULTIPLE_STYLESHEET_ERR,
        "More than one stylesheet defined in the same file."},

        
        {ErrorMsg.TEMPLATE_REDEF_ERR,
        "Template ''{0}'' already defined in this stylesheet."},


        
        {ErrorMsg.TEMPLATE_UNDEF_ERR,
        "Template ''{0}'' not defined in this stylesheet."},

        
        {ErrorMsg.VARIABLE_REDEF_ERR,
        "Variable ''{0}'' is multiply defined in the same scope."},

        
        {ErrorMsg.VARIABLE_UNDEF_ERR,
        "Variable or parameter ''{0}'' is undefined."},

        
        {ErrorMsg.CLASS_NOT_FOUND_ERR,
        "Cannot find class ''{0}''."},

        
        {ErrorMsg.METHOD_NOT_FOUND_ERR,
        "Cannot find external method ''{0}'' (must be public)."},

        
        {ErrorMsg.ARGUMENT_CONVERSION_ERR,
        "Cannot convert argument/return type in call to method ''{0}''"},

        
        {ErrorMsg.FILE_NOT_FOUND_ERR,
        "File or URI ''{0}'' not found."},

        
        {ErrorMsg.INVALID_URI_ERR,
        "Invalid URI ''{0}''."},

        
        {ErrorMsg.FILE_ACCESS_ERR,
        "Cannot open file or URI ''{0}''."},

        
        {ErrorMsg.MISSING_ROOT_ERR,
        "<xsl:stylesheet> or <xsl:transform> element expected."},

        
        {ErrorMsg.NAMESPACE_UNDEF_ERR,
        "Namespace prefix ''{0}'' is undeclared."},

        
        {ErrorMsg.FUNCTION_RESOLVE_ERR,
        "Unable to resolve call to function ''{0}''."},

        
        {ErrorMsg.NEED_LITERAL_ERR,
        "Argument to ''{0}'' must be a literal string."},

        
        {ErrorMsg.XPATH_PARSER_ERR,
        "Error parsing XPath expression ''{0}''."},

        
        {ErrorMsg.REQUIRED_ATTR_ERR,
        "Required attribute ''{0}'' is missing."},

        
        {ErrorMsg.ILLEGAL_CHAR_ERR,
        "Illegal character ''{0}'' in XPath expression."},

        
        {ErrorMsg.ILLEGAL_PI_ERR,
        "Illegal name ''{0}'' for processing instruction."},

        
        {ErrorMsg.STRAY_ATTRIBUTE_ERR,
        "Attribute ''{0}'' outside of element."},

        
        {ErrorMsg.ILLEGAL_ATTRIBUTE_ERR,
        "Illegal attribute ''{0}''."},

        
        {ErrorMsg.CIRCULAR_INCLUDE_ERR,
        "Circular import/include. Stylesheet ''{0}'' already loaded."},

        
        {ErrorMsg.RESULT_TREE_SORT_ERR,
        "Result-tree fragments cannot be sorted (<xsl:sort> elements are " +
        "ignored). You must sort the nodes when creating the result tree."},

        
        {ErrorMsg.SYMBOLS_REDEF_ERR,
        "Decimal formatting ''{0}'' is already defined."},

        
        {ErrorMsg.XSL_VERSION_ERR,
        "XSL version ''{0}'' is not supported by XSLTC."},

        
        {ErrorMsg.CIRCULAR_VARIABLE_ERR,
        "Circular variable/parameter reference in ''{0}''."},

        
        {ErrorMsg.ILLEGAL_BINARY_OP_ERR,
        "Unknown operator for binary expression."},

        
        {ErrorMsg.ILLEGAL_ARG_ERR,
        "Illegal argument(s) for function call."},

        
        {ErrorMsg.DOCUMENT_ARG_ERR,
        "Second argument to document() function must be a node-set."},

        
        {ErrorMsg.MISSING_WHEN_ERR,
        "At least one <xsl:when> element required in <xsl:choose>."},

        
        {ErrorMsg.MULTIPLE_OTHERWISE_ERR,
        "Only one <xsl:otherwise> element allowed in <xsl:choose>."},

        
        {ErrorMsg.STRAY_OTHERWISE_ERR,
        "<xsl:otherwise> can only be used within <xsl:choose>."},

        
        {ErrorMsg.STRAY_WHEN_ERR,
        "<xsl:when> can only be used within <xsl:choose>."},

        
        {ErrorMsg.WHEN_ELEMENT_ERR,
        "Only <xsl:when> and <xsl:otherwise> elements allowed in <xsl:choose>."},

        
        {ErrorMsg.UNNAMED_ATTRIBSET_ERR,
        "<xsl:attribute-set> is missing the 'name' attribute."},

        
        {ErrorMsg.ILLEGAL_CHILD_ERR,
        "Illegal child element."},

        
        {ErrorMsg.ILLEGAL_ELEM_NAME_ERR,
        "You cannot call an element ''{0}''"},

        
        {ErrorMsg.ILLEGAL_ATTR_NAME_ERR,
        "You cannot call an attribute ''{0}''"},

        
        {ErrorMsg.ILLEGAL_TEXT_NODE_ERR,
        "Text data outside of top-level <xsl:stylesheet> element."},

        
        {ErrorMsg.SAX_PARSER_CONFIG_ERR,
        "JAXP parser not configured correctly"},

        
        {ErrorMsg.INTERNAL_ERR,
        "Unrecoverable XSLTC-internal error: ''{0}''"},

        
        {ErrorMsg.UNSUPPORTED_XSL_ERR,
        "Unsupported XSL element ''{0}''."},

        
        {ErrorMsg.UNSUPPORTED_EXT_ERR,
        "Unrecognised XSLTC extension ''{0}''."},

        
        {ErrorMsg.MISSING_XSLT_URI_ERR,
        "The input document is not a stylesheet (the XSL namespace is not "+
        "declared in the root element)."},

        
        {ErrorMsg.MISSING_XSLT_TARGET_ERR,
        "Could not find stylesheet target ''{0}''."},

        
        {ErrorMsg.ACCESSING_XSLT_TARGET_ERR,
        "Could not read stylesheet target ''{0}'', because ''{1}'' access is not allowed due to restriction set by the accessExternalStylesheet property."},

        
        {ErrorMsg.NOT_IMPLEMENTED_ERR,
        "Not implemented: ''{0}''."},

        
        {ErrorMsg.NOT_STYLESHEET_ERR,
        "The input document does not contain an XSL stylesheet."},

        
        {ErrorMsg.ELEMENT_PARSE_ERR,
        "Could not parse element ''{0}''"},

        
        {ErrorMsg.KEY_USE_ATTR_ERR,
        "The use attribute of <key> must be node, node-set, string or number."},

        
        {ErrorMsg.OUTPUT_VERSION_ERR,
        "Output XML document version should be 1.0"},

        
        {ErrorMsg.ILLEGAL_RELAT_OP_ERR,
        "Unknown operator for relational expression"},

        
        {ErrorMsg.ATTRIBSET_UNDEF_ERR,
        "Attempting to use non-existing attribute set ''{0}''."},

        
        {ErrorMsg.ATTR_VAL_TEMPLATE_ERR,
        "Cannot parse attribute value template ''{0}''."},

        
        {ErrorMsg.UNKNOWN_SIG_TYPE_ERR,
        "Unknown data-type in signature for class ''{0}''."},

        
        {ErrorMsg.DATA_CONVERSION_ERR,
        "Cannot convert data-type ''{0}'' to ''{1}''."},

        
        {ErrorMsg.NO_TRANSLET_CLASS_ERR,
        "This Templates does not contain a valid translet class definition."},

        
        {ErrorMsg.NO_MAIN_TRANSLET_ERR,
        "This Templates does not contain a class with the name ''{0}''."},

        
        {ErrorMsg.TRANSLET_CLASS_ERR,
        "Could not load the translet class ''{0}''."},

        {ErrorMsg.TRANSLET_OBJECT_ERR,
        "Translet class loaded, but unable to create translet instance."},

        
        {ErrorMsg.ERROR_LISTENER_NULL_ERR,
        "Attempting to set ErrorListener for ''{0}'' to null"},

        
        {ErrorMsg.JAXP_UNKNOWN_SOURCE_ERR,
        "Only StreamSource, SAXSource and DOMSource are supported by XSLTC"},

        
        {ErrorMsg.JAXP_NO_SOURCE_ERR,
        "Source object passed to ''{0}'' has no contents."},

        
        {ErrorMsg.JAXP_COMPILE_ERR,
        "Could not compile stylesheet"},

        
        {ErrorMsg.JAXP_INVALID_ATTR_ERR,
        "TransformerFactory does not recognise attribute ''{0}''."},

        
        {ErrorMsg.JAXP_SET_RESULT_ERR,
        "setResult() must be called prior to startDocument()."},

        
        {ErrorMsg.JAXP_NO_TRANSLET_ERR,
        "The Transformer has no encapsulated translet object."},

        
        {ErrorMsg.JAXP_NO_HANDLER_ERR,
        "No defined output handler for transformation result."},

        
        {ErrorMsg.JAXP_NO_RESULT_ERR,
        "Result object passed to ''{0}'' is invalid."},

        
        {ErrorMsg.JAXP_UNKNOWN_PROP_ERR,
        "Attempting to access invalid Transformer property ''{0}''."},

        
        {ErrorMsg.SAX2DOM_ADAPTER_ERR,
        "Could not create SAX2DOM adapter: ''{0}''."},

        
        {ErrorMsg.XSLTC_SOURCE_ERR,
        "XSLTCSource.build() called without systemId being set."},

        { ErrorMsg.ER_RESULT_NULL,
            "Result should not be null"},

        
        {ErrorMsg.JAXP_INVALID_SET_PARAM_VALUE,
        "The value of param {0} must be a valid Java Object"},


        {ErrorMsg.COMPILE_STDIN_ERR,
        "The -i option must be used with the -o option."},


        
        {ErrorMsg.COMPILE_USAGE_STR,
        "SYNOPSIS\n"+
        "   java com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile [-o <output>]\n"+
        "      [-d <directory>] [-j <jarfile>] [-p <package>]\n"+
        "      [-n] [-x] [-u] [-v] [-h] { <stylesheet> | -i }\n\n"+
        "OPTIONS\n"+
        "   -o <output>    assigns the name <output> to the generated\n"+
        "                  translet.  By default the translet name is\n"+
        "                  derived from the <stylesheet> name.  This option\n"+
        "                  is ignored if compiling multiple stylesheets.\n"+
        "   -d <directory> specifies a destination directory for translet\n"+
        "   -j <jarfile>   packages translet classes into a jar file of the\n"+
        "                  name specified as <jarfile>\n"+
        "   -p <package>   specifies a package name prefix for all generated\n"+
        "                  translet classes.\n"+
        "   -n             enables template inlining (default behavior better\n"+
        "                  on average).\n"+
        "   -x             turns on additional debugging message output\n"+
        "   -u             interprets <stylesheet> arguments as URLs\n"+
        "   -i             forces compiler to read stylesheet from stdin\n"+
        "   -v             prints the version of the compiler\n"+
        "   -h             prints this usage statement\n"},

        
        {ErrorMsg.TRANSFORM_USAGE_STR,
        "SYNOPSIS \n"+
        "   java com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform [-j <jarfile>]\n"+
        "      [-x] [-n <iterations>] {-u <document_url> | <document>}\n"+
        "      <class> [<param1>=<value1> ...]\n\n"+
        "   uses the translet <class> to transform an XML document \n"+
        "   specified as <document>. The translet <class> is either in\n"+
        "   the user's CLASSPATH or in the optionally specified <jarfile>.\n"+
        "OPTIONS\n"+
        "   -j <jarfile>    specifies a jarfile from which to load translet\n"+
        "   -x              turns on additional debugging message output\n"+
        "   -n <iterations> runs the transformation <iterations> times and\n"+
        "                   displays profiling information\n"+
        "   -u <document_url> specifies XML input document as a URL\n"},



        
        {ErrorMsg.STRAY_SORT_ERR,
        "<xsl:sort> can only be used within <xsl:for-each> or <xsl:apply-templates>."},

        
        {ErrorMsg.UNSUPPORTED_ENCODING,
        "Output encoding ''{0}'' is not supported on this JVM."},

        
        {ErrorMsg.SYNTAX_ERR,
        "Syntax error in ''{0}''."},

        
        {ErrorMsg.CONSTRUCTOR_NOT_FOUND,
        "Cannot find external constructor ''{0}''."},

        
        {ErrorMsg.NO_JAVA_FUNCT_THIS_REF,
        "The first argument to the non-static Java function ''{0}'' is not a "+
        "valid object reference."},

        
        {ErrorMsg.TYPE_CHECK_ERR,
        "Error checking type of the expression ''{0}''."},

        
        {ErrorMsg.TYPE_CHECK_UNK_LOC_ERR,
        "Error checking type of an expression at an unknown location."},

        
        {ErrorMsg.ILLEGAL_CMDLINE_OPTION_ERR,
        "The command-line option ''{0}'' is not valid."},

        
        {ErrorMsg.CMDLINE_OPT_MISSING_ARG_ERR,
        "The command-line option ''{0}'' is missing a required argument."},

        
        {ErrorMsg.WARNING_PLUS_WRAPPED_MSG,
        "WARNING:  ''{0}''\n       :{1}"},

        
        {ErrorMsg.WARNING_MSG,
        "WARNING:  ''{0}''"},

        
        {ErrorMsg.FATAL_ERR_PLUS_WRAPPED_MSG,
        "FATAL ERROR:  ''{0}''\n           :{1}"},

        
        {ErrorMsg.FATAL_ERR_MSG,
        "FATAL ERROR:  ''{0}''"},

        
        {ErrorMsg.ERROR_PLUS_WRAPPED_MSG,
        "ERROR:  ''{0}''\n     :{1}"},

        
        {ErrorMsg.ERROR_MSG,
        "ERROR:  ''{0}''"},

        
        {ErrorMsg.TRANSFORM_WITH_TRANSLET_STR,
        "Transform using translet ''{0}'' "},

        
        {ErrorMsg.TRANSFORM_WITH_JAR_STR,
        "Transform using translet ''{0}'' from jar file ''{1}''"},

        
        {ErrorMsg.COULD_NOT_CREATE_TRANS_FACT,
        "Could not create an instance of the TransformerFactory class ''{0}''."},

        
        {ErrorMsg.TRANSLET_NAME_JAVA_CONFLICT,
         "The name ''{0}'' could not be used as the name of the translet "+
         "class because it contains characters that are not permitted in the "+
         "name of Java class.  The name ''{1}'' was used instead."},

        
        {ErrorMsg.COMPILER_ERROR_KEY,
        "Compiler errors:"},

        
        {ErrorMsg.COMPILER_WARNING_KEY,
        "Compiler warnings:"},

        
        {ErrorMsg.RUNTIME_ERROR_KEY,
        "Translet errors:"},

        
        {ErrorMsg.INVALID_QNAME_ERR,
        "An attribute whose value must be a QName or whitespace-separated list of QNames had the value ''{0}''"},

        
        {ErrorMsg.INVALID_NCNAME_ERR,
        "An attribute whose value must be an NCName had the value ''{0}''"},

        
        {ErrorMsg.INVALID_METHOD_IN_OUTPUT,
        "The method attribute of an <xsl:output> element had the value ''{0}''.  The value must be one of ''xml'', ''html'', ''text'', or qname-but-not-ncname"},

        {ErrorMsg.JAXP_GET_FEATURE_NULL_NAME,
        "The feature name cannot be null in TransformerFactory.getFeature(String name)."},

        {ErrorMsg.JAXP_SET_FEATURE_NULL_NAME,
        "The feature name cannot be null in TransformerFactory.setFeature(String name, boolean value)."},

        {ErrorMsg.JAXP_UNSUPPORTED_FEATURE,
        "Cannot set the feature ''{0}'' on this TransformerFactory."},

        {ErrorMsg.JAXP_SECUREPROCESSING_FEATURE,
        "FEATURE_SECURE_PROCESSING: Cannot set the feature to false when security manager is present."},

        
        {ErrorMsg.OUTLINE_ERR_TRY_CATCH,
         "Internal XSLTC error:  the generated byte code contains a " +
         "try-catch-finally block and cannot be outlined."},

        
        {ErrorMsg.OUTLINE_ERR_UNBALANCED_MARKERS,
         "Internal XSLTC error:  OutlineableChunkStart and " +
         "OutlineableChunkEnd markers must be balanced and properly nested."},

        
        {ErrorMsg.OUTLINE_ERR_DELETED_TARGET,
         "Internal XSLTC error:  an instruction that was part of a block of " +
         "byte code that was outlined is still referred to in the original " +
         "method."
        },


        
        {ErrorMsg.OUTLINE_ERR_METHOD_TOO_BIG,
         "Internal XSLTC error:  a method in the translet exceeds the Java " +
         "Virtual Machine limitation on the length of a method of 64 " +
         "kilobytes.  This is usually caused by templates in a stylesheet " +
         "that are very large.  Try restructuring your stylesheet to use " +
         "smaller templates."
        },

         {ErrorMsg.DESERIALIZE_TRANSLET_ERR, "When Java security is enabled, " +
                        "support for deserializing TemplatesImpl is disabled." +
                        "This can be overridden by setting the jdk.xml.enableTemplatesImplDeserialization" +
                        " system property to true."}

    };

    }
}
