



package com.sun.org.apache.xerces.internal.impl;

import java.util.Enumeration;
import java.util.NoSuchElementException;


public final class Constants {
    
    
    
    
    
    public static final String NS_XMLSCHEMA = "http://www.w3.org/2001/XMLSchema".intern();
    public static final String NS_DTD = "http://www.w3.org/TR/REC-xml".intern();

    
    public static final String SUN_SCHEMA_FEATURE_PREFIX = "http://java.sun.com/xml/schema/features/";
    public static final String SUN_REPORT_IGNORED_ELEMENT_CONTENT_WHITESPACE = "report-ignored-element-content-whitespace";

    
    
    public static final String ZEPHYR_PROPERTY_PREFIX = "http://java.sun.com/xml/stream/properties/" ;    
    public static final String STAX_PROPERTIES = "stax-properties" ;
    public static final String STAX_ENTITY_RESOLVER_PROPERTY = "internal/stax-entity-resolver";
    public static final String STAX_REPORT_CDATA_EVENT = "report-cdata-event";
    public static final String READER_IN_DEFINED_STATE = ZEPHYR_PROPERTY_PREFIX + "reader-in-defined-state" ;
    public static final String ADD_NAMESPACE_DECL_AS_ATTRIBUTE = "add-namespacedecl-as-attrbiute";
    public static final String ESCAPE_CHARACTERS = "escapeCharacters";
    public static final String REUSE_INSTANCE = "reuse-instance" ;
    
    
    public static final String SUN_DOM_PROPERTY_PREFIX = "http://java.sun.com/xml/dom/properties/" ;
    public static final String SUN_DOM_ANCESTOR_CHECCK = "ancestor-check";

    
    public static final String IGNORE_EXTERNAL_DTD = "ignore-external-dtd";
    
    
    
    
    public static final String SAX_FEATURE_PREFIX = "http://xml.org/sax/features/";

    public static final String NAMESPACES_FEATURE = "namespaces";
    
    
    public static final String NAMESPACE_PREFIXES_FEATURE = "namespace-prefixes";
    
    
    public static final String STRING_INTERNING_FEATURE = "string-interning";
    
    
    public static final String VALIDATION_FEATURE = "validation";
    
    
    public static final String EXTERNAL_GENERAL_ENTITIES_FEATURE = "external-general-entities";
    
    
    public static final String EXTERNAL_PARAMETER_ENTITIES_FEATURE = "external-parameter-entities";
    
    
    public static final String LEXICAL_HANDLER_PARAMETER_ENTITIES_FEATURE = "lexical-handler/parameter-entities";
    
    
    public static final String IS_STANDALONE_FEATURE = "is-standalone";
    
    
    public static final String RESOLVE_DTD_URIS_FEATURE = "resolve-dtd-uris";
    
    
    public static final String USE_ATTRIBUTES2_FEATURE = "use-attributes2";
    
    
    public static final String USE_LOCATOR2_FEATURE = "use-locator2";
    
    
    public static final String USE_ENTITY_RESOLVER2_FEATURE = "use-entity-resolver2";
    
    
    public static final String UNICODE_NORMALIZATION_CHECKING_FEATURE = "unicode-normalization-checking";
    
    
    public static final String XMLNS_URIS_FEATURE = "xmlns-uris";
    
    
    public static final String XML_11_FEATURE = "xml-1.1";
    
    
    public static final String ALLOW_DTD_EVENTS_AFTER_ENDDTD_FEATURE = "allow-dtd-events-after-endDTD";
    
    
    
    
    public static final String SAX_PROPERTY_PREFIX = "http://xml.org/sax/properties/";
    
    
    public static final String DECLARATION_HANDLER_PROPERTY = "declaration-handler";
    
    
    public static final String LEXICAL_HANDLER_PROPERTY = "lexical-handler";
    
    
    public static final String DOM_NODE_PROPERTY = "dom-node";
    
    
    public static final String XML_STRING_PROPERTY = "xml-string";
    
	public static final String FEATURE_SECURE_PROCESSING = "http://javax.xml.XMLConstants/feature/secure-processing";
    
    
    public static final String DOCUMENT_XML_VERSION_PROPERTY = "document-xml-version";
    
    
    
    
    
    
    
    public static final String JAXP_PROPERTY_PREFIX =
        "http://java.sun.com/xml/jaxp/properties/";
    
    
    public static final String SCHEMA_SOURCE = "schemaSource";
    
    
    public static final String SCHEMA_LANGUAGE = "schemaLanguage";

    public static final String SYSTEM_PROPERTY_ELEMENT_ATTRIBUTE_LIMIT = "elementAttributeLimit" ;

    
    
    
    
    
    public static final String INCLUDE_COMMENTS_FEATURE = "include-comments";
    
    
    public static final String CREATE_CDATA_NODES_FEATURE = "create-cdata-nodes";
    
    
    public static final String LOAD_AS_INFOSET = "load-as-infoset";
    
    
    
    
    
    
    public static final String DOM_CANONICAL_FORM = "canonical-form";
    public static final String DOM_CDATA_SECTIONS ="cdata-sections";

    public static final String DOM_COMMENTS = "comments";

    

    public static final String DOM_CHARSET_OVERRIDES_XML_ENCODING =
    "charset-overrides-xml-encoding";

    public static final String DOM_DATATYPE_NORMALIZATION = "datatype-normalization";

    public static final String DOM_ENTITIES = "entities";
    public static final String DOM_INFOSET = "infoset";
    public static final String DOM_NAMESPACES = "namespaces";
    public static final String DOM_NAMESPACE_DECLARATIONS = "namespace-declarations";
    public static final String DOM_SUPPORTED_MEDIATYPES_ONLY =
        "supported-media-types-only";
    
    public static final String DOM_VALIDATE_IF_SCHEMA = "validate-if-schema";
    public static final String DOM_VALIDATE = "validate";
    public static final String DOM_ELEMENT_CONTENT_WHITESPACE =
        "element-content-whitespace";
    
    
    public static final String DOM_DISCARD_DEFAULT_CONTENT = "discard-default-content";
    public static final String DOM_NORMALIZE_CHARACTERS    = "normalize-characters";
    public static final String DOM_CHECK_CHAR_NORMALIZATION  = "check-character-normalization";
    public static final String DOM_WELLFORMED  = "well-formed";
    public static final String DOM_SPLIT_CDATA = "split-cdata-sections";
    
    
    public static final String DOM_FORMAT_PRETTY_PRINT = "format-pretty-print";
    public static final String DOM_XMLDECL = "xml-declaration";
    public static final String DOM_UNKNOWNCHARS = "unknown-characters";
    public static final String DOM_CERTIFIED =  "certified";
    public static final String DOM_DISALLOW_DOCTYPE =  "disallow-doctype";
    public static final String DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS =  "ignore-unknown-character-denormalizations";
    
    
    public static final String DOM_RESOURCE_RESOLVER = "resource-resolver";
    public static final String DOM_ERROR_HANDLER = "error-handler";
    public static final String DOM_SCHEMA_TYPE = "schema-type";
    public static final String DOM_SCHEMA_LOCATION = "schema-location";
    public static final String DOM_ANCESTOR_CHECCK = "ancestor-check";
    
    public static final String DOM_PSVI = "psvi";
    
    
    
    
    
    public static final String XERCES_FEATURE_PREFIX = "http://apache.org/xml/features/";
    
    
    public static final String SCHEMA_VALIDATION_FEATURE = "validation/schema";
    
    
    public static final String SCHEMA_NORMALIZED_VALUE = "validation/schema/normalized-value";
    
    
    public static final String SCHEMA_ELEMENT_DEFAULT = "validation/schema/element-default";
    
    
    public static final String SCHEMA_FULL_CHECKING = "validation/schema-full-checking";
    
    
    public static final String SCHEMA_AUGMENT_PSVI = "validation/schema/augment-psvi";
    
    
    public static final String DYNAMIC_VALIDATION_FEATURE = "validation/dynamic";
    
    
    public static final String WARN_ON_DUPLICATE_ATTDEF_FEATURE = "validation/warn-on-duplicate-attdef";
    
    
    public static final String WARN_ON_UNDECLARED_ELEMDEF_FEATURE = "validation/warn-on-undeclared-elemdef";
    
    
    public static final String WARN_ON_DUPLICATE_ENTITYDEF_FEATURE = "warn-on-duplicate-entitydef";
    
    
    public static final String ALLOW_JAVA_ENCODINGS_FEATURE = "allow-java-encodings";
    
    
    public static final String DISALLOW_DOCTYPE_DECL_FEATURE = "disallow-doctype-decl";
    
    
    public static final String CONTINUE_AFTER_FATAL_ERROR_FEATURE = "continue-after-fatal-error";
    
    
    public static final String LOAD_DTD_GRAMMAR_FEATURE = "nonvalidating/load-dtd-grammar";
    
    
    public static final String LOAD_EXTERNAL_DTD_FEATURE = "nonvalidating/load-external-dtd";
    
    
    public static final String DEFER_NODE_EXPANSION_FEATURE = "dom/defer-node-expansion";
    
    
    public static final String CREATE_ENTITY_REF_NODES_FEATURE = "dom/create-entity-ref-nodes";
    
    
    public static final String INCLUDE_IGNORABLE_WHITESPACE = "dom/include-ignorable-whitespace";
    
    
    public static final String DEFAULT_ATTRIBUTE_VALUES_FEATURE = "validation/default-attribute-values";
    
    
    public static final String VALIDATE_CONTENT_MODELS_FEATURE = "validation/validate-content-models";
    
    
    public static final String VALIDATE_DATATYPES_FEATURE = "validation/validate-datatypes";

    
    public static final String BALANCE_SYNTAX_TREES = "validation/balance-syntax-trees";

    
    public static final String NOTIFY_CHAR_REFS_FEATURE = "scanner/notify-char-refs";
    
    
    public static final String NOTIFY_BUILTIN_REFS_FEATURE = "scanner/notify-builtin-refs";
    
    
    public static final String STANDARD_URI_CONFORMANT_FEATURE = "standard-uri-conformant";

    
    public static final String GENERATE_SYNTHETIC_ANNOTATIONS_FEATURE = "generate-synthetic-annotations";

    
    public static final String VALIDATE_ANNOTATIONS_FEATURE = "validate-annotations";
    

        
    public static final String HONOUR_ALL_SCHEMALOCATIONS_FEATURE = "honour-all-schemaLocations";

    
    public static final String NAMESPACE_GROWTH_FEATURE = "namespace-growth";

    
    public static final String TOLERATE_DUPLICATES_FEATURE = "internal/tolerate-duplicates";
    
    
    public static final String XINCLUDE_FEATURE = "xinclude";
    
    
    public static final String XINCLUDE_FIXUP_BASE_URIS_FEATURE = "xinclude/fixup-base-uris";
    
    
    public static final String XINCLUDE_FIXUP_LANGUAGE_FEATURE = "xinclude/fixup-language";
    
    
    public static final String USE_GRAMMAR_POOL_ONLY_FEATURE = "internal/validation/schema/use-grammar-pool-only";
   
    
    public static final String PARSER_SETTINGS = "internal/parser-settings";


    
    public static final String XINCLUDE_AWARE = "xinclude-aware";

    
    public static final String IGNORE_SCHEMA_LOCATION_HINTS = "validation/schema/ignore-schema-location-hints";
    
    
    public static final String CHANGE_IGNORABLE_CHARACTERS_INTO_IGNORABLE_WHITESPACES =
        "validation/change-ignorable-characters-into-ignorable-whitespaces";
    
    
    
    
    public static final String XERCES_PROPERTY_PREFIX = "http://apache.org/xml/properties/";
    
    
    public static final String CURRENT_ELEMENT_NODE_PROPERTY = "dom/current-element-node";
    
    
    public static final String DOCUMENT_CLASS_NAME_PROPERTY = "dom/document-class-name";
    
    
    public static final String SYMBOL_TABLE_PROPERTY = "internal/symbol-table";
    
    
    public static final String ERROR_REPORTER_PROPERTY = "internal/error-reporter";
    
    
    public static final String ERROR_HANDLER_PROPERTY = "internal/error-handler";
    
    
    public static final String XINCLUDE_HANDLER_PROPERTY = "internal/xinclude-handler";
    
    
    public static final String XPOINTER_HANDLER_PROPERTY = "internal/xpointer-handler";
    
    
    public static final String ENTITY_MANAGER_PROPERTY = "internal/entity-manager";
    
    public static final String BUFFER_SIZE_PROPERTY = "input-buffer-size";
    
    
    public static final String SECURITY_MANAGER_PROPERTY = "security-manager";

    
    public static final String LOCALE_PROPERTY = "locale";

    
    protected static final String SECURITY_MANAGER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.SECURITY_MANAGER_PROPERTY;
    

    public static final String ENTITY_RESOLVER_PROPERTY = "internal/entity-resolver";
    
    
    public static final String XMLGRAMMAR_POOL_PROPERTY = "internal/grammar-pool";
    
    
    public static final String DATATYPE_VALIDATOR_FACTORY_PROPERTY = "internal/datatype-validator-factory";
    
    
    public static final String DOCUMENT_SCANNER_PROPERTY = "internal/document-scanner";
    
    
    public static final String DTD_SCANNER_PROPERTY = "internal/dtd-scanner";
    
    
    public static final String DTD_PROCESSOR_PROPERTY = "internal/dtd-processor";
    
    
    public static final String VALIDATOR_PROPERTY = "internal/validator";
    
    
    public static final String DTD_VALIDATOR_PROPERTY = "internal/validator/dtd";
    
    
    public static final String SCHEMA_VALIDATOR_PROPERTY = "internal/validator/schema";
    
    
    public static final String SCHEMA_LOCATION = "schema/external-schemaLocation";
    
    
    public static final String SCHEMA_NONS_LOCATION = "schema/external-noNamespaceSchemaLocation";

    
    public static final String NAMESPACE_BINDER_PROPERTY = "internal/namespace-binder";
    
    
    public static final String NAMESPACE_CONTEXT_PROPERTY = "internal/namespace-context";
    
    
    public static final String VALIDATION_MANAGER_PROPERTY = "internal/validation-manager";


    
    public static final String XPOINTER_SCHEMA_PROPERTY = "xpointer-schema";

    
    public static final String SCHEMA_DV_FACTORY_PROPERTY = "internal/validation/schema/dv-factory";


    
    
    
    public final static String ELEMENT_PSVI = "ELEMENT_PSVI";
    
    
    public final static String ATTRIBUTE_PSVI = "ATTRIBUTE_PSVI";
        
    
    public final static String ATTRIBUTE_DECLARED = "ATTRIBUTE_DECLARED";
    
	public final static String ENTITY_EXPANSION_LIMIT = "entityExpansionLimit";
	
	public final static String MAX_OCCUR_LIMIT = "maxOccurLimit";

    
    public final static String TYPEINFO = "org.w3c.dom.TypeInfo";
    
    
    public final static String ID_ATTRIBUTE = "ID_ATTRIBUTE";
    
    
    
    
    public final static String ENTITY_SKIPPED = "ENTITY_SKIPPED";
    
    
    public final static String CHAR_REF_PROBABLE_WS = "CHAR_REF_PROBABLE_WS";
    
    
    public final static String LAST_ENTITY = "LAST_ENTITY";
    
    
    public final static short XML_VERSION_ERROR = -1;
    public final static short XML_VERSION_1_0 = 1;
    public final static short XML_VERSION_1_1 = 2;


    
    
    public final static String ANONYMOUS_TYPE_NAMESPACE =
        "http://apache.org/xml/xmlschema/1.0/anonymousTypes";
    

    
    
    public final static boolean SCHEMA_1_1_SUPPORT = false;
    public final static short SCHEMA_VERSION_1_0          = 1;
    public final static short SCHEMA_VERSION_1_0_EXTENDED = 2;

    
    
    
    private static final String[] fgSAXFeatures = {
            NAMESPACES_FEATURE,
            NAMESPACE_PREFIXES_FEATURE,
            STRING_INTERNING_FEATURE,
            VALIDATION_FEATURE,
            EXTERNAL_GENERAL_ENTITIES_FEATURE,
            EXTERNAL_PARAMETER_ENTITIES_FEATURE,
    };
    
    
    private static final String[] fgSAXProperties = {
            DECLARATION_HANDLER_PROPERTY,
            LEXICAL_HANDLER_PROPERTY,
            DOM_NODE_PROPERTY,
            XML_STRING_PROPERTY,
    };
    
    
    private static final String[] fgXercesFeatures = {

        SCHEMA_VALIDATION_FEATURE,
        SCHEMA_FULL_CHECKING,
        DYNAMIC_VALIDATION_FEATURE,
        WARN_ON_DUPLICATE_ATTDEF_FEATURE,
        WARN_ON_UNDECLARED_ELEMDEF_FEATURE,
        ALLOW_JAVA_ENCODINGS_FEATURE,
        CONTINUE_AFTER_FATAL_ERROR_FEATURE,
        LOAD_DTD_GRAMMAR_FEATURE,
        LOAD_EXTERNAL_DTD_FEATURE,
        
        CREATE_ENTITY_REF_NODES_FEATURE,
        XINCLUDE_AWARE,
        INCLUDE_IGNORABLE_WHITESPACE,
        
        DEFAULT_ATTRIBUTE_VALUES_FEATURE,
        VALIDATE_CONTENT_MODELS_FEATURE,
        VALIDATE_DATATYPES_FEATURE,
        BALANCE_SYNTAX_TREES,
        NOTIFY_CHAR_REFS_FEATURE,
        NOTIFY_BUILTIN_REFS_FEATURE,
        DISALLOW_DOCTYPE_DECL_FEATURE,
        STANDARD_URI_CONFORMANT_FEATURE,
        GENERATE_SYNTHETIC_ANNOTATIONS_FEATURE,
        VALIDATE_ANNOTATIONS_FEATURE,
        HONOUR_ALL_SCHEMALOCATIONS_FEATURE,
        XINCLUDE_FEATURE,
        XINCLUDE_FIXUP_BASE_URIS_FEATURE,
        XINCLUDE_FIXUP_LANGUAGE_FEATURE,
        NAMESPACE_GROWTH_FEATURE,
        TOLERATE_DUPLICATES_FEATURE,
    };
    
    
    private static final String[] fgXercesProperties = {
            CURRENT_ELEMENT_NODE_PROPERTY,
            DOCUMENT_CLASS_NAME_PROPERTY,
            SYMBOL_TABLE_PROPERTY,
            ERROR_HANDLER_PROPERTY,
            ERROR_REPORTER_PROPERTY,
            ENTITY_MANAGER_PROPERTY,
            ENTITY_RESOLVER_PROPERTY,
            XMLGRAMMAR_POOL_PROPERTY,
            DATATYPE_VALIDATOR_FACTORY_PROPERTY,
            DOCUMENT_SCANNER_PROPERTY,
            DTD_SCANNER_PROPERTY,
            VALIDATOR_PROPERTY,
            SCHEMA_LOCATION,
            SCHEMA_NONS_LOCATION,
            VALIDATION_MANAGER_PROPERTY,
            BUFFER_SIZE_PROPERTY,
            SECURITY_MANAGER_PROPERTY,
            LOCALE_PROPERTY,
            SCHEMA_DV_FACTORY_PROPERTY,
    };
    
    
    private static final Enumeration fgEmptyEnumeration = new ArrayEnumeration(new Object[] {});
    
    
    
    
    
    
    private Constants() {}
    
    
    
    
    
    
    
    
    public static Enumeration getSAXFeatures() {
        return fgSAXFeatures.length > 0
        ? new ArrayEnumeration(fgSAXFeatures) : fgEmptyEnumeration;
    } 
    
    
    public static Enumeration getSAXProperties() {
        return fgSAXProperties.length > 0
        ? new ArrayEnumeration(fgSAXProperties) : fgEmptyEnumeration;
    } 
    
    
    
    
    public static Enumeration getXercesFeatures() {
        return fgXercesFeatures.length > 0
        ? new ArrayEnumeration(fgXercesFeatures) : fgEmptyEnumeration;
    } 
    
    
    public static Enumeration getXercesProperties() {
        return fgXercesProperties.length > 0
        ? new ArrayEnumeration(fgXercesProperties) : fgEmptyEnumeration;
    } 
    
    
    
    
    
    
    static class ArrayEnumeration
    implements Enumeration {
        
        
        
        
        
        
        private Object[] array;
        
        
        private int index;
        
        
        
        
        
        
        public ArrayEnumeration(Object[] array) {
            this.array = array;
        } 
        
        
        
        
        
        
        public boolean hasMoreElements() {
            return index < array.length;
        } 
        
        
        public Object nextElement() {
            if (index < array.length) {
                return array[index++];
            }
            throw new NoSuchElementException();
        } 
        
    } 
    
    
    
    
    
    
    public static void main(String[] argv) {
        
        print("SAX features:", SAX_FEATURE_PREFIX, fgSAXFeatures);
        print("SAX properties:", SAX_PROPERTY_PREFIX, fgSAXProperties);
        print("Xerces features:", XERCES_FEATURE_PREFIX, fgXercesFeatures);
        print("Xerces properties:", XERCES_PROPERTY_PREFIX, fgXercesProperties);
        
    } 
    
    
    private static void print(String header, String prefix, Object[] array) {
        System.out.print(header);
        if (array.length > 0) {
            System.out.println();
            for (int i = 0; i < array.length; i++) {
                System.out.print("  ");
                System.out.print(prefix);
                System.out.println(array[i]);
            }
        }
        else {
            System.out.println(" none.");
        }
    } 
    
} 
