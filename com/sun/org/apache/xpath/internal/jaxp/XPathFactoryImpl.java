



package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xalan.internal.XalanConstants;
import com.sun.org.apache.xpath.internal.res.XPATHErrorResources;
import com.sun.org.apache.xalan.internal.res.XSLMessages;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import com.sun.org.apache.xalan.internal.utils.FeaturePropertyBase;

import javax.xml.XMLConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;


public  class XPathFactoryImpl extends XPathFactory {

        
        private static final String CLASS_NAME = "XPathFactoryImpl";

        
        private XPathFunctionResolver xPathFunctionResolver = null;

        
        private XPathVariableResolver xPathVariableResolver = null;

        
        private boolean _isNotSecureProcessing = true;
        
        private boolean _isSecureMode = false;
        

        private boolean _useServicesMechanism = true;

        private final FeatureManager _featureManager;

        public XPathFactoryImpl() {
            this(true);
        }

        public static XPathFactory newXPathFactoryNoServiceLoader() {
            return new XPathFactoryImpl(false);
        }

        public XPathFactoryImpl(boolean useServicesMechanism) {
            _featureManager = new FeatureManager();
            if (System.getSecurityManager() != null) {
                _isSecureMode = true;
                _isNotSecureProcessing = false;
                _featureManager.setValue(FeatureManager.Feature.ORACLE_ENABLE_EXTENSION_FUNCTION,
                        FeaturePropertyBase.State.FSP, XalanConstants.FEATURE_FALSE);
            }
            this._useServicesMechanism = useServicesMechanism;
        }
        
        public boolean isObjectModelSupported(String objectModel) {

            if (objectModel == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_OBJECT_MODEL_NULL,
                        new Object[] { this.getClass().getName() } );

                throw new NullPointerException( fmsg );
            }

            if (objectModel.length() == 0) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_OBJECT_MODEL_EMPTY,
                        new Object[] { this.getClass().getName() } );
                throw new IllegalArgumentException( fmsg );
            }

            
            if (objectModel.equals(XPathFactory.DEFAULT_OBJECT_MODEL_URI)) {
                return true;
            }

            
            return false;
        }

        
        public javax.xml.xpath.XPath newXPath() {
            return new com.sun.org.apache.xpath.internal.jaxp.XPathImpl(
                    xPathVariableResolver, xPathFunctionResolver,
                    !_isNotSecureProcessing, _useServicesMechanism,
                    _featureManager );
        }

        
        public void setFeature(String name, boolean value)
                throws XPathFactoryConfigurationException {

            
            if (name == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_FEATURE_NAME_NULL,
                        new Object[] { CLASS_NAME, new Boolean( value) } );
                throw new NullPointerException( fmsg );
             }

            
            if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                if ((_isSecureMode) && (!value)) {
                    String fmsg = XSLMessages.createXPATHMessage(
                            XPATHErrorResources.ER_SECUREPROCESSING_FEATURE,
                            new Object[] { name, CLASS_NAME, new Boolean(value) } );
                    throw new XPathFactoryConfigurationException( fmsg );
                }

                _isNotSecureProcessing = !value;
                if (value && _featureManager != null) {
                    _featureManager.setValue(FeatureManager.Feature.ORACLE_ENABLE_EXTENSION_FUNCTION,
                            FeaturePropertyBase.State.FSP, XalanConstants.FEATURE_FALSE);
                }

                
                return;
            }
            if (name.equals(XalanConstants.ORACLE_FEATURE_SERVICE_MECHANISM)) {
                
                if (!_isSecureMode)
                    _useServicesMechanism = value;
                return;
            }

            if (_featureManager != null &&
                    _featureManager.setValue(name, FeaturePropertyBase.State.APIPROPERTY, value)) {
                return;
            }

            
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_FEATURE_UNKNOWN,
                    new Object[] { name, CLASS_NAME, new Boolean(value) } );
            throw new XPathFactoryConfigurationException( fmsg );
        }

        
        public boolean getFeature(String name)
                throws XPathFactoryConfigurationException {

            
            if (name == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_GETTING_NULL_FEATURE,
                        new Object[] { CLASS_NAME } );
                throw new NullPointerException( fmsg );
            }

            
            if (name.equals(XMLConstants.FEATURE_SECURE_PROCESSING)) {
                return !_isNotSecureProcessing;
            }
            if (name.equals(XalanConstants.ORACLE_FEATURE_SERVICE_MECHANISM)) {
                return _useServicesMechanism;
            }

            
            String propertyValue = (_featureManager != null) ?
                    _featureManager.getValueAsString(name) : null;
            if (propertyValue != null) {
                return _featureManager.isFeatureEnabled(name);
            }

            
            String fmsg = XSLMessages.createXPATHMessage(
                    XPATHErrorResources.ER_GETTING_UNKNOWN_FEATURE,
                    new Object[] { name, CLASS_NAME } );

            throw new XPathFactoryConfigurationException( fmsg );
        }

        
        public void setXPathFunctionResolver(XPathFunctionResolver resolver) {

            
            if (resolver == null) {
                String fmsg = XSLMessages.createXPATHMessage(
                        XPATHErrorResources.ER_NULL_XPATH_FUNCTION_RESOLVER,
                        new Object[] {  CLASS_NAME } );
                throw new NullPointerException( fmsg );
            }

            xPathFunctionResolver = resolver;
        }

        
        public void setXPathVariableResolver(XPathVariableResolver resolver) {

                
                if (resolver == null) {
                    String fmsg = XSLMessages.createXPATHMessage(
                            XPATHErrorResources.ER_NULL_XPATH_VARIABLE_RESOLVER,
                            new Object[] {  CLASS_NAME } );
                    throw new NullPointerException( fmsg );
                }

                xPathVariableResolver = resolver;
        }
}
