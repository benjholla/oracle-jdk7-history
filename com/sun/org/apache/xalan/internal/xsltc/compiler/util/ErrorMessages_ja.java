



package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import java.util.ListResourceBundle;


public class ErrorMessages_ja extends ListResourceBundle {



    
    private static final Object[][] _contents =  new Object[][] {
        {ErrorMsg.MULTIPLE_STYLESHEET_ERR,
        "\u540C\u3058\u30D5\u30A1\u30A4\u30EB\u306B\u8907\u6570\u306E\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u304C\u5B9A\u7FA9\u3055\u308C\u3066\u3044\u307E\u3059\u3002"},

        
        {ErrorMsg.TEMPLATE_REDEF_ERR,
        "\u30C6\u30F3\u30D7\u30EC\u30FC\u30C8''{0}''\u306F\u3053\u306E\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u5185\u3067\u3059\u3067\u306B\u5B9A\u7FA9\u3055\u308C\u3066\u3044\u307E\u3059\u3002"},


        
        {ErrorMsg.TEMPLATE_UNDEF_ERR,
        "\u30C6\u30F3\u30D7\u30EC\u30FC\u30C8''{0}''\u306F\u3053\u306E\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u5185\u3067\u5B9A\u7FA9\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.VARIABLE_REDEF_ERR,
        "\u5909\u6570''{0}''\u306F\u540C\u3058\u30B9\u30B3\u30FC\u30D7\u5185\u3067\u8907\u6570\u5B9A\u7FA9\u3055\u308C\u3066\u3044\u307E\u3059\u3002"},

        
        {ErrorMsg.VARIABLE_UNDEF_ERR,
        "\u5909\u6570\u307E\u305F\u306F\u30D1\u30E9\u30E1\u30FC\u30BF''{0}''\u304C\u672A\u5B9A\u7FA9\u3067\u3059\u3002"},

        
        {ErrorMsg.CLASS_NOT_FOUND_ERR,
        "\u30AF\u30E9\u30B9''{0}''\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.METHOD_NOT_FOUND_ERR,
        "\u5916\u90E8\u30E1\u30BD\u30C3\u30C9''{0}''\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093(public\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059)\u3002"},

        
        {ErrorMsg.ARGUMENT_CONVERSION_ERR,
        "\u30E1\u30BD\u30C3\u30C9''{0}''\u306E\u547C\u51FA\u3057\u306E\u5F15\u6570\u30BF\u30A4\u30D7\u307E\u305F\u306F\u623B\u308A\u578B\u3092\u5909\u63DB\u3067\u304D\u307E\u305B\u3093"},

        
        {ErrorMsg.FILE_NOT_FOUND_ERR,
        "\u30D5\u30A1\u30A4\u30EB\u307E\u305F\u306FURI ''{0}''\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.INVALID_URI_ERR,
        "URI ''{0}''\u304C\u7121\u52B9\u3067\u3059\u3002"},

        
        {ErrorMsg.FILE_ACCESS_ERR,
        "\u30D5\u30A1\u30A4\u30EB\u307E\u305F\u306FURI ''{0}''\u3092\u958B\u304F\u3053\u3068\u304C\u3067\u304D\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.MISSING_ROOT_ERR,
        "<xsl:stylesheet>\u307E\u305F\u306F<xsl:transform>\u306E\u8981\u7D20\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.NAMESPACE_UNDEF_ERR,
        "\u30CD\u30FC\u30E0\u30B9\u30DA\u30FC\u30B9\u306E\u63A5\u982D\u8F9E''{0}''\u306F\u5BA3\u8A00\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.FUNCTION_RESOLVE_ERR,
        "\u95A2\u6570''{0}''\u306E\u547C\u51FA\u3057\u3092\u89E3\u6C7A\u3067\u304D\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.NEED_LITERAL_ERR,
        "''{0}''\u3078\u306E\u5F15\u6570\u306F\u30EA\u30C6\u30E9\u30EB\u6587\u5B57\u5217\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.XPATH_PARSER_ERR,
        "XPath\u5F0F''{0}''\u306E\u89E3\u6790\u4E2D\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F\u3002"},

        
        {ErrorMsg.REQUIRED_ATTR_ERR,
        "\u5FC5\u9808\u5C5E\u6027''{0}''\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.ILLEGAL_CHAR_ERR,
        "XPath\u5F0F\u306E\u6587\u5B57''{0}''\u306F\u7121\u52B9\u3067\u3059\u3002"},

        
        {ErrorMsg.ILLEGAL_PI_ERR,
        "\u51E6\u7406\u547D\u4EE4\u306E\u540D\u524D''{0}''\u306F\u7121\u52B9\u3067\u3059\u3002"},

        
        {ErrorMsg.STRAY_ATTRIBUTE_ERR,
        "\u5C5E\u6027''{0}''\u304C\u8981\u7D20\u306E\u5916\u5074\u306B\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.ILLEGAL_ATTRIBUTE_ERR,
        "\u4E0D\u6B63\u306A\u5C5E\u6027''{0}''\u3067\u3059\u3002"},

        
        {ErrorMsg.CIRCULAR_INCLUDE_ERR,
        "\u30A4\u30F3\u30DD\u30FC\u30C8\u307E\u305F\u306F\u30A4\u30F3\u30AF\u30EB\u30FC\u30C9\u304C\u5FAA\u74B0\u3057\u3066\u3044\u307E\u3059\u3002\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8''{0}''\u306F\u3059\u3067\u306B\u30ED\u30FC\u30C9\u3055\u308C\u3066\u3044\u307E\u3059\u3002"},

        
        {ErrorMsg.RESULT_TREE_SORT_ERR,
        "\u7D50\u679C\u30C4\u30EA\u30FC\u30FB\u30D5\u30E9\u30B0\u30E1\u30F3\u30C8\u306F\u30BD\u30FC\u30C8\u3067\u304D\u307E\u305B\u3093(<xsl:sort>\u8981\u7D20\u306F\u7121\u8996\u3055\u308C\u307E\u3059)\u3002\u7D50\u679C\u30C4\u30EA\u30FC\u3092\u4F5C\u6210\u3059\u308B\u3068\u304D\u306B\u30CE\u30FC\u30C9\u3092\u30BD\u30FC\u30C8\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.SYMBOLS_REDEF_ERR,
        "10\u9032\u6570\u30D5\u30A9\u30FC\u30DE\u30C3\u30C8''{0}''\u306F\u3059\u3067\u306B\u5B9A\u7FA9\u3055\u308C\u3066\u3044\u307E\u3059\u3002"},

        
        {ErrorMsg.XSL_VERSION_ERR,
        "XSL\u30D0\u30FC\u30B8\u30E7\u30F3''{0}''\u306FXSLTC\u306B\u3088\u3063\u3066\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.CIRCULAR_VARIABLE_ERR,
        "''{0}''\u5185\u306E\u5909\u6570\u53C2\u7167\u307E\u305F\u306F\u30D1\u30E9\u30E1\u30FC\u30BF\u53C2\u7167\u304C\u5FAA\u74B0\u3057\u3066\u3044\u307E\u3059\u3002"},

        
        {ErrorMsg.ILLEGAL_BINARY_OP_ERR,
        "2\u9032\u6570\u306E\u5F0F\u306B\u5BFE\u3059\u308B\u4E0D\u660E\u306A\u6F14\u7B97\u5B50\u3067\u3059\u3002"},

        
        {ErrorMsg.ILLEGAL_ARG_ERR,
        "\u95A2\u6570\u547C\u51FA\u3057\u306E\u5F15\u6570\u304C\u4E0D\u6B63\u3067\u3059\u3002"},

        
        {ErrorMsg.DOCUMENT_ARG_ERR,
        "document()\u95A2\u6570\u306E2\u756A\u76EE\u306E\u5F15\u6570\u306F\u30CE\u30FC\u30C9\u30BB\u30C3\u30C8\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.MISSING_WHEN_ERR,
        "<xsl:choose>\u5185\u306B\u306F\u5C11\u306A\u304F\u3068\u30821\u3064\u306E<xsl:when>\u8981\u7D20\u304C\u5FC5\u8981\u3067\u3059\u3002"},

        
        {ErrorMsg.MULTIPLE_OTHERWISE_ERR,
        "<xsl:choose>\u5185\u3067\u306F1\u3064\u306E<xsl:otherwise>\u8981\u7D20\u306E\u307F\u304C\u8A31\u53EF\u3055\u308C\u3066\u3044\u307E\u3059\u3002"},

        
        {ErrorMsg.STRAY_OTHERWISE_ERR,
        "<xsl:otherwise>\u306F<xsl:choose>\u5185\u3067\u306E\u307F\u4F7F\u7528\u3067\u304D\u307E\u3059\u3002"},

        
        {ErrorMsg.STRAY_WHEN_ERR,
        "<xsl:when>\u306F<xsl:choose>\u5185\u3067\u306E\u307F\u4F7F\u7528\u3067\u304D\u307E\u3059\u3002"},

        
        {ErrorMsg.WHEN_ELEMENT_ERR,
        "<xsl:choose>\u5185\u3067\u306F<xsl:when>\u3068<xsl:otherwise>\u306E\u8981\u7D20\u306E\u307F\u304C\u8A31\u53EF\u3055\u308C\u307E\u3059\u3002"},

        
        {ErrorMsg.UNNAMED_ATTRIBSET_ERR,
        "<xsl:attribute-set>\u306B'name'\u5C5E\u6027\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.ILLEGAL_CHILD_ERR,
        "\u5B50\u8981\u7D20\u304C\u4E0D\u6B63\u3067\u3059\u3002"},

        
        {ErrorMsg.ILLEGAL_ELEM_NAME_ERR,
        "\u8981\u7D20''{0}''\u3092\u547C\u3073\u51FA\u3059\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093"},

        
        {ErrorMsg.ILLEGAL_ATTR_NAME_ERR,
        "\u5C5E\u6027''{0}''\u3092\u547C\u3073\u51FA\u3059\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093"},

        
        {ErrorMsg.ILLEGAL_TEXT_NODE_ERR,
        "\u30C6\u30AD\u30B9\u30C8\u30FB\u30C7\u30FC\u30BF\u306F\u30C8\u30C3\u30D7\u30EC\u30D9\u30EB\u306E<xsl:stylesheet>\u8981\u7D20\u306E\u5916\u5074\u306B\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.SAX_PARSER_CONFIG_ERR,
        "JAXP\u30D1\u30FC\u30B5\u30FC\u304C\u6B63\u3057\u304F\u69CB\u6210\u3055\u308C\u3066\u3044\u307E\u305B\u3093"},

        
        {ErrorMsg.INTERNAL_ERR,
        "\u30EA\u30AB\u30D0\u30EA\u4E0D\u80FD\u306AXSLTC\u5185\u90E8\u30A8\u30E9\u30FC: ''{0}''"},

        
        {ErrorMsg.UNSUPPORTED_XSL_ERR,
        "XSL\u8981\u7D20''{0}''\u306F\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.UNSUPPORTED_EXT_ERR,
        "XSLTC\u62E1\u5F35''{0}''\u306F\u8A8D\u8B58\u3055\u308C\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.MISSING_XSLT_URI_ERR,
        "\u5165\u529B\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u306F\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u3067\u306F\u3042\u308A\u307E\u305B\u3093(XSL\u306E\u30CD\u30FC\u30E0\u30B9\u30DA\u30FC\u30B9\u306F\u30EB\u30FC\u30C8\u8981\u7D20\u5185\u3067\u5BA3\u8A00\u3055\u308C\u3066\u3044\u307E\u305B\u3093)\u3002"},

        
        {ErrorMsg.MISSING_XSLT_TARGET_ERR,
        "\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u30FB\u30BF\u30FC\u30B2\u30C3\u30C8''{0}''\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093\u3067\u3057\u305F\u3002"},

        
        {ErrorMsg.NOT_IMPLEMENTED_ERR,
        "''{0}''\u304C\u5B9F\u88C5\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.NOT_STYLESHEET_ERR,
        "\u5165\u529B\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u306BXSL\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.ELEMENT_PARSE_ERR,
        "\u8981\u7D20''{0}''\u3092\u89E3\u6790\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F"},

        
        {ErrorMsg.KEY_USE_ATTR_ERR,
        "<key>\u306Euse\u5C5E\u6027\u306F\u3001\u30CE\u30FC\u30C9\u3001\u30CE\u30FC\u30C9\u30BB\u30C3\u30C8\u3001\u6587\u5B57\u5217\u307E\u305F\u306F\u6570\u5024\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.OUTPUT_VERSION_ERR,
        "\u51FA\u529BXML\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u306E\u30D0\u30FC\u30B8\u30E7\u30F3\u306F1.0\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},

        
        {ErrorMsg.ILLEGAL_RELAT_OP_ERR,
        "\u95A2\u4FC2\u5F0F\u306E\u4E0D\u660E\u306A\u6F14\u7B97\u5B50\u3067\u3059"},

        
        {ErrorMsg.ATTRIBSET_UNDEF_ERR,
        "\u5B58\u5728\u3057\u306A\u3044\u5C5E\u6027\u30BB\u30C3\u30C8''{0}''\u3092\u4F7F\u7528\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F\u3002"},

        
        {ErrorMsg.ATTR_VAL_TEMPLATE_ERR,
        "\u5C5E\u6027\u5024\u30C6\u30F3\u30D7\u30EC\u30FC\u30C8''{0}''\u3092\u89E3\u6790\u3067\u304D\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.UNKNOWN_SIG_TYPE_ERR,
        "\u30AF\u30E9\u30B9''{0}''\u306E\u7F72\u540D\u306B\u4E0D\u660E\u306A\u30C7\u30FC\u30BF\u578B\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.DATA_CONVERSION_ERR,
        "\u30C7\u30FC\u30BF\u578B''{0}''\u3092''{1}''\u306B\u5909\u63DB\u3067\u304D\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.NO_TRANSLET_CLASS_ERR,
        "\u3053\u306E\u30C6\u30F3\u30D7\u30EC\u30FC\u30C8\u306B\u306F\u6709\u52B9\u306Atranslet\u30AF\u30E9\u30B9\u5B9A\u7FA9\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.NO_MAIN_TRANSLET_ERR,
        "\u3053\u306E\u30C6\u30F3\u30D7\u30EC\u30FC\u30C8\u306B\u306F\u540D\u524D''{0}''\u3092\u6301\u3064\u30AF\u30E9\u30B9\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.TRANSLET_CLASS_ERR,
        "translet\u30AF\u30E9\u30B9''{0}''\u3092\u30ED\u30FC\u30C9\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F\u3002"},

        {ErrorMsg.TRANSLET_OBJECT_ERR,
        "Translet\u30AF\u30E9\u30B9\u304C\u30ED\u30FC\u30C9\u3055\u308C\u307E\u3057\u305F\u304C\u3001translet\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u4F5C\u6210\u3067\u304D\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.ERROR_LISTENER_NULL_ERR,
        "''{0}''\u306EErrorListener\u3092null\u306B\u8A2D\u5B9A\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F"},

        
        {ErrorMsg.JAXP_UNKNOWN_SOURCE_ERR,
        "StreamSource\u3001SAXSource\u304A\u3088\u3073DOMSource\u306E\u307F\u304CXSLTC\u306B\u3088\u3063\u3066\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u3066\u3044\u307E\u3059"},

        
        {ErrorMsg.JAXP_NO_SOURCE_ERR,
        "''{0}''\u306B\u6E21\u3055\u308C\u305F\u30BD\u30FC\u30B9\u30FB\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u306B\u30B3\u30F3\u30C6\u30F3\u30C4\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.JAXP_COMPILE_ERR,
        "\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u3092\u30B3\u30F3\u30D1\u30A4\u30EB\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F"},

        
        {ErrorMsg.JAXP_INVALID_ATTR_ERR,
        "TransformerFactory\u306F\u5C5E\u6027''{0}''\u3092\u8A8D\u8B58\u3057\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.JAXP_SET_RESULT_ERR,
        "setResult()\u306FstartDocument()\u3088\u308A\u3082\u524D\u306B\u547C\u3073\u51FA\u3059\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.JAXP_NO_TRANSLET_ERR,
        "\u30C8\u30E9\u30F3\u30B9\u30D5\u30A9\u30FC\u30DE\u306B\u306F\u30AB\u30D7\u30BB\u30EB\u5316\u3055\u308C\u305Ftranslet\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.JAXP_NO_HANDLER_ERR,
        "\u5909\u63DB\u7D50\u679C\u306B\u5BFE\u3057\u3066\u5B9A\u7FA9\u6E08\u306E\u51FA\u529B\u30CF\u30F3\u30C9\u30E9\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.JAXP_NO_RESULT_ERR,
        "''{0}''\u306B\u6E21\u3055\u308C\u305F\u7D50\u679C\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u306F\u7121\u52B9\u3067\u3059\u3002"},

        
        {ErrorMsg.JAXP_UNKNOWN_PROP_ERR,
        "\u7121\u52B9\u306A\u30C8\u30E9\u30F3\u30B9\u30D5\u30A9\u30FC\u30DE\u30FB\u30D7\u30ED\u30D1\u30C6\u30A3''{0}''\u306B\u30A2\u30AF\u30BB\u30B9\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F\u3002"},

        
        {ErrorMsg.SAX2DOM_ADAPTER_ERR,
        "SAX2DOM\u30A2\u30C0\u30D7\u30BF''{0}''\u3092\u4F5C\u6210\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F\u3002"},

        
        {ErrorMsg.XSLTC_SOURCE_ERR,
        "systemId\u3092\u8A2D\u5B9A\u305B\u305A\u306BXSLTCSource.build()\u304C\u547C\u3073\u51FA\u3055\u308C\u307E\u3057\u305F\u3002"},

        { ErrorMsg.ER_RESULT_NULL,
            "\u7D50\u679C\u306Fnull\u306B\u3067\u304D\u307E\u305B\u3093"},

        
        {ErrorMsg.JAXP_INVALID_SET_PARAM_VALUE,
        "\u30D1\u30E9\u30E1\u30FC\u30BF{0}\u306F\u6709\u52B9\u306AJava\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},


        {ErrorMsg.COMPILE_STDIN_ERR,
        "-i\u30AA\u30D7\u30B7\u30E7\u30F3\u306F-o\u30AA\u30D7\u30B7\u30E7\u30F3\u3068\u3068\u3082\u306B\u4F7F\u7528\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},


        
        {ErrorMsg.COMPILE_USAGE_STR,
        "SYNOPSIS\n   java com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile [-o <output>]\n      [-d <directory>] [-j <jarfile>] [-p <package>]\n      [-n] [-x] [-u] [-v] [-h] { <stylesheet> | -i }\n\nOPTIONS\n   -o <output>    \u540D\u524D<output>\u3092\u751F\u6210\u6E08translet\u306B\n                  \u5272\u308A\u5F53\u3066\u308B\u3002\u30C7\u30D5\u30A9\u30EB\u30C8\u3067\u306F\u3001translet\u540D\u306F\n                  <stylesheet>\u540D\u306B\u7531\u6765\u3057\u307E\u3059\u3002\u3053\u306E\u30AA\u30D7\u30B7\u30E7\u30F3\u306F\n                  \u8907\u6570\u306E\u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u3092\u30B3\u30F3\u30D1\u30A4\u30EB\u3059\u308B\u5834\u5408\u306F\u7121\u8996\u3055\u308C\u307E\u3059\u3002\n   -d <directory> translet\u306E\u5B9B\u5148\u30C7\u30A3\u30EC\u30AF\u30C8\u30EA\u3092\u6307\u5B9A\u3059\u308B\n   -j <jarfile>   <jarfile>\u3067\u6307\u5B9A\u3055\u308C\u308B\u540D\u524D\u306Ejar\u30D5\u30A1\u30A4\u30EB\u306Btranslet\u30AF\u30E9\u30B9\u3092\n                  \u30D1\u30C3\u30B1\u30FC\u30B8\u3059\u308B\n   -p <package>   \u751F\u6210\u3055\u308C\u308B\u3059\u3079\u3066\u306Etranslet\u30AF\u30E9\u30B9\u306E\u30D1\u30C3\u30B1\u30FC\u30B8\u540D\n                  \u63A5\u982D\u8F9E\u3092\u6307\u5B9A\u3059\u308B\u3002\n   -n             \u30C6\u30F3\u30D7\u30EC\u30FC\u30C8\u306E\u30A4\u30F3\u30E9\u30A4\u30F3\u5316\u3092\u6709\u52B9\u306B\u3059\u308B(\u5E73\u5747\u3057\u3066\u30C7\u30D5\u30A9\u30EB\u30C8\u52D5\u4F5C\u306E\u65B9\u304C\n                  \u512A\u308C\u3066\u3044\u307E\u3059)\u3002\n   -x             \u8FFD\u52A0\u306E\u30C7\u30D0\u30C3\u30B0\u30FB\u30E1\u30C3\u30BB\u30FC\u30B8\u51FA\u529B\u3092\u30AA\u30F3\u306B\u3059\u308B\n   -u             <stylesheet>\u5F15\u6570\u3092URL\u3068\u3057\u3066\u89E3\u91C8\u3059\u308B\n   -i             \u30B9\u30BF\u30A4\u30EB\u30B7\u30FC\u30C8\u3092stdin\u304B\u3089\u8AAD\u307F\u8FBC\u3080\u3053\u3068\u3092\u30B3\u30F3\u30D1\u30A4\u30E9\u306B\u5F37\u5236\u3059\u308B\n   -v             \u30B3\u30F3\u30D1\u30A4\u30E9\u306E\u30D0\u30FC\u30B8\u30E7\u30F3\u3092\u51FA\u529B\u3059\u308B\n   -h             \u3053\u306E\u4F7F\u7528\u65B9\u6CD5\u306E\u6587\u3092\u51FA\u529B\u3059\u308B\n"},

        
        {ErrorMsg.TRANSFORM_USAGE_STR,
        "SYNOPSIS \n   java com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform [-j <jarfile>]\n      [-x] [-n <iterations>] {-u <document_url> | <document>}\n      <class> [<param1>=<value1> ...]\n\n   translet <class>\u3092\u4F7F\u7528\u3057\u3066\u3001<document>\u3067\u6307\u5B9A\u3055\u308C\u308B\n   XML\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u3092\u5909\u63DB\u3059\u308B\u3002translet <class>\u306F\n   \u30E6\u30FC\u30B6\u30FC\u306ECLASSPATH\u5185\u304B\u3001\u30AA\u30D7\u30B7\u30E7\u30F3\u3067\u6307\u5B9A\u3055\u308C\u305F<jarfile>\u5185\u306B\u3042\u308A\u307E\u3059\u3002\nOPTIONS\n   -j <jarfile>    translet\u3092\u30ED\u30FC\u30C9\u3059\u308Bjarfile\u3092\u6307\u5B9A\u3059\u308B\n   -x              \u8FFD\u52A0\u306E\u30C7\u30D0\u30C3\u30B0\u30FB\u30E1\u30C3\u30BB\u30FC\u30B8\u51FA\u529B\u3092\u30AA\u30F3\u306B\u3059\u308B\n   -n <iterations> \u5909\u63DB\u3092<iterations>\u56DE\u5B9F\u884C\u3057\u3001\n                   \u30D7\u30ED\u30D5\u30A1\u30A4\u30EB\u60C5\u5831\u3092\u8868\u793A\u3059\u308B\n   -u <document_url> XML\u5165\u529B\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u3092URL\u3068\u3057\u3066\u6307\u5B9A\u3059\u308B\n"},



        
        {ErrorMsg.STRAY_SORT_ERR,
        "<xsl:sort>\u306F<xsl:for-each>\u307E\u305F\u306F<xsl:apply-templates>\u306E\u5185\u90E8\u3067\u306E\u307F\u4F7F\u7528\u3067\u304D\u307E\u3059\u3002"},

        
        {ErrorMsg.UNSUPPORTED_ENCODING,
        "\u51FA\u529B\u30A8\u30F3\u30B3\u30FC\u30C7\u30A3\u30F3\u30B0''{0}''\u306F\u3053\u306EJVM\u3067\u306F\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.SYNTAX_ERR,
        "''{0}''\u306B\u69CB\u6587\u30A8\u30E9\u30FC\u304C\u3042\u308A\u307E\u3059\u3002"},

        
        {ErrorMsg.CONSTRUCTOR_NOT_FOUND,
        "\u5916\u90E8\u30B3\u30F3\u30B9\u30C8\u30E9\u30AF\u30BF''{0}''\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.NO_JAVA_FUNCT_THIS_REF,
        "static\u3067\u306A\u3044Java\u95A2\u6570''{0}''\u306E\u6700\u521D\u306E\u5F15\u6570\u306F\u7121\u52B9\u306A\u30AA\u30D6\u30B8\u30A7\u30AF\u30C8\u53C2\u7167\u3067\u3059\u3002"},

        
        {ErrorMsg.TYPE_CHECK_ERR,
        "\u5F0F''{0}''\u306E\u30BF\u30A4\u30D7\u306E\u78BA\u8A8D\u4E2D\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F\u3002"},

        
        {ErrorMsg.TYPE_CHECK_UNK_LOC_ERR,
        "\u4E0D\u660E\u306A\u5834\u6240\u3067\u306E\u5F0F\u306E\u30BF\u30A4\u30D7\u306E\u78BA\u8A8D\u4E2D\u306B\u30A8\u30E9\u30FC\u304C\u767A\u751F\u3057\u307E\u3057\u305F\u3002"},

        
        {ErrorMsg.ILLEGAL_CMDLINE_OPTION_ERR,
        "\u30B3\u30DE\u30F3\u30C9\u30E9\u30A4\u30F3\u30FB\u30AA\u30D7\u30B7\u30E7\u30F3''{0}''\u306F\u7121\u52B9\u3067\u3059\u3002"},

        
        {ErrorMsg.CMDLINE_OPT_MISSING_ARG_ERR,
        "\u30B3\u30DE\u30F3\u30C9\u30E9\u30A4\u30F3\u30FB\u30AA\u30D7\u30B7\u30E7\u30F3''{0}''\u306B\u5FC5\u9808\u306E\u5F15\u6570\u304C\u3042\u308A\u307E\u305B\u3093\u3002"},

        
        {ErrorMsg.WARNING_PLUS_WRAPPED_MSG,
        "\u8B66\u544A:  ''{0}''\n       :{1}"},

        
        {ErrorMsg.WARNING_MSG,
        "WARNING:  ''{0}''"},

        
        {ErrorMsg.FATAL_ERR_PLUS_WRAPPED_MSG,
        "\u81F4\u547D\u7684\u30A8\u30E9\u30FC:  ''{0}''\n           :{1}"},

        
        {ErrorMsg.FATAL_ERR_MSG,
        "FATAL ERROR:  ''{0}''"},

        
        {ErrorMsg.ERROR_PLUS_WRAPPED_MSG,
        "\u30A8\u30E9\u30FC:  ''{0}''\n     :{1}"},

        
        {ErrorMsg.ERROR_MSG,
        "ERROR:  ''{0}''"},

        
        {ErrorMsg.TRANSFORM_WITH_TRANSLET_STR,
        "translet ''{0}''\u3092\u4F7F\u7528\u3057\u3066\u5909\u63DB\u3057\u307E\u3059 "},

        
        {ErrorMsg.TRANSFORM_WITH_JAR_STR,
        "translet ''{0}''\u3092\u4F7F\u7528\u3057\u3066jar\u30D5\u30A1\u30A4\u30EB''{1}''\u304B\u3089\u5909\u63DB\u3057\u307E\u3059"},

        
        {ErrorMsg.COULD_NOT_CREATE_TRANS_FACT,
        "TransformerFactory\u30AF\u30E9\u30B9''{0}''\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u4F5C\u6210\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F\u3002"},

        
        {ErrorMsg.TRANSLET_NAME_JAVA_CONFLICT,
         "\u540D\u524D''{0}''\u306B\u306FJava\u30AF\u30E9\u30B9\u306E\u540D\u524D\u306B\u8A31\u53EF\u3055\u308C\u3066\u3044\u306A\u3044\u6587\u5B57\u304C\u542B\u307E\u308C\u3066\u3044\u308B\u305F\u3081\u3001translet\u30AF\u30E9\u30B9\u306E\u540D\u524D\u3068\u3057\u3066\u4F7F\u7528\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F\u3002\u540D\u524D''{1}''\u304C\u304B\u308F\u308A\u306B\u4F7F\u7528\u3055\u308C\u307E\u3059\u3002"},

        
        {ErrorMsg.COMPILER_ERROR_KEY,
        "\u30B3\u30F3\u30D1\u30A4\u30E9\u30FB\u30A8\u30E9\u30FC:"},

        
        {ErrorMsg.COMPILER_WARNING_KEY,
        "\u30B3\u30F3\u30D1\u30A4\u30E9\u306E\u8B66\u544A:"},

        
        {ErrorMsg.RUNTIME_ERROR_KEY,
        "Translet\u30A8\u30E9\u30FC:"},

        
        {ErrorMsg.INVALID_QNAME_ERR,
        "\u5024\u304C1\u3064\u306EQName\u307E\u305F\u306FQName\u306E\u7A7A\u767D\u6587\u5B57\u533A\u5207\u308A\u30EA\u30B9\u30C8\u3067\u3042\u308B\u3053\u3068\u304C\u5FC5\u8981\u306A\u5C5E\u6027\u306E\u5024\u304C''{0}''\u3067\u3057\u305F"},

        
        {ErrorMsg.INVALID_NCNAME_ERR,
        "\u5024\u304CNCName\u3067\u3042\u308B\u3053\u3068\u304C\u5FC5\u8981\u306A\u5C5E\u6027\u306E\u5024\u304C''{0}''\u3067\u3057\u305F"},

        
        {ErrorMsg.INVALID_METHOD_IN_OUTPUT,
        "<xsl:output>\u8981\u7D20\u306E\u30E1\u30BD\u30C3\u30C9\u5C5E\u6027\u306E\u5024\u304C''{0}''\u3067\u3057\u305F\u3002\u5024\u306F''xml''\u3001''html''\u3001''text''\u307E\u305F\u306Fqname-but-not-ncname\u306E\u3044\u305A\u308C\u304B\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},

        {ErrorMsg.JAXP_GET_FEATURE_NULL_NAME,
        "\u6A5F\u80FD\u540D\u306FTransformerFactory.getFeature(String name)\u5185\u3067null\u306B\u3067\u304D\u307E\u305B\u3093\u3002"},

        {ErrorMsg.JAXP_SET_FEATURE_NULL_NAME,
        "\u6A5F\u80FD\u540D\u306FTransformerFactory.setFeature(String name, boolean value)\u5185\u3067null\u306B\u3067\u304D\u307E\u305B\u3093\u3002"},

        {ErrorMsg.JAXP_UNSUPPORTED_FEATURE,
        "\u6A5F\u80FD''{0}''\u3092\u3053\u306ETransformerFactory\u306B\u8A2D\u5B9A\u3067\u304D\u307E\u305B\u3093\u3002"},

        {ErrorMsg.JAXP_SECUREPROCESSING_FEATURE,
        "FEATURE_SECURE_PROCESSING: \u30BB\u30AD\u30E5\u30EA\u30C6\u30A3\u30FB\u30DE\u30CD\u30FC\u30B8\u30E3\u304C\u5B58\u5728\u3059\u308B\u3068\u304D\u3001\u6A5F\u80FD\u3092false\u306B\u8A2D\u5B9A\u3067\u304D\u307E\u305B\u3093\u3002"}
    };

    
    public Object[][] getContents()
    {
        return _contents;
    }
}
