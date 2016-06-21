

package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import com.sun.org.apache.xerces.internal.impl.xs.XSGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelGroupImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import org.w3c.dom.Element;


class  XSDGroupTraverser extends XSDAbstractParticleTraverser {

    XSDGroupTraverser (XSDHandler handler,
            XSAttributeChecker gAttrCheck) {

        super(handler, gAttrCheck);
    }

    XSParticleDecl traverseLocal(Element elmNode,
            XSDocumentInfo schemaDoc,
            SchemaGrammar grammar) {

        
        Object[] attrValues = fAttrChecker.checkAttributes(elmNode, false,
                schemaDoc);
        QName refAttr = (QName) attrValues[XSAttributeChecker.ATTIDX_REF];
        XInt  minAttr = (XInt)  attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt  maxAttr = (XInt)  attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];

        XSGroupDecl group = null;

        
        if (refAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"group (local)", "ref"}, elmNode);
        } else {
            
            
            group = (XSGroupDecl)fSchemaHandler.getGlobalDecl(schemaDoc, XSDHandler.GROUP_TYPE, refAttr, elmNode);
        }

        XSAnnotationImpl annotation = null;
        
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        else {
            String text = DOMUtil.getSyntheticAnnotation(elmNode);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
            }
        }

        if (child != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{"group (local)", "(annotation?)", DOMUtil.getLocalName(elmNode)}, elmNode);
        }

        int minOccurs = minAttr.intValue();
        int maxOccurs = maxAttr.intValue();

        XSParticleDecl particle = null;

        
        if (group != null && group.fModelGroup != null &&
                !(minOccurs == 0 && maxOccurs == 0)) {
            
            if (fSchemaHandler.fDeclPool != null) {
                particle = fSchemaHandler.fDeclPool.getParticleDecl();
            } else {
                particle = new XSParticleDecl();
            }
            particle.fType = XSParticleDecl.PARTICLE_MODELGROUP;
            particle.fValue = group.fModelGroup;
            particle.fMinOccurs = minOccurs;
            particle.fMaxOccurs = maxOccurs;
            if (group.fModelGroup.fCompositor == XSModelGroupImpl.MODELGROUP_ALL) {
                Long defaultVals = (Long)attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT];
                particle = checkOccurrences(particle, SchemaSymbols.ELT_GROUP,
                        (Element)elmNode.getParentNode(), GROUP_REF_WITH_ALL,
                        defaultVals.longValue());
            }
            if (refAttr != null) {
                XSObjectList annotations;
                if (annotation != null) {
                    annotations = new XSObjectListImpl();
                    ((XSObjectListImpl) annotations).addXSObject(annotation);
                } else {
                    annotations = XSObjectListImpl.EMPTY_LIST;
                }
                particle.fAnnotations = annotations;
            } else {
                particle.fAnnotations = group.fAnnotations;
            }
        }

        fAttrChecker.returnAttrArray(attrValues, schemaDoc);

        return particle;

    } 

    XSGroupDecl traverseGlobal(Element elmNode,
            XSDocumentInfo schemaDoc,
            SchemaGrammar grammar) {

        
        Object[] attrValues = fAttrChecker.checkAttributes(elmNode, true,
                schemaDoc);
        String  strNameAttr = (String)  attrValues[XSAttributeChecker.ATTIDX_NAME];

        
        if (strNameAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"group (global)", "name"}, elmNode);
        }

        
        
        XSGroupDecl group = new XSGroupDecl();
        XSParticleDecl particle = null;

        
        Element l_elmChild = DOMUtil.getFirstChildElement(elmNode);
        XSAnnotationImpl annotation = null;
        if (l_elmChild == null) {
            reportSchemaError("s4s-elt-must-match.2",
                    new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"},
                    elmNode);
        } else {
            String childName = l_elmChild.getLocalName();
            if (childName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                annotation = traverseAnnotationDecl(l_elmChild, attrValues, true, schemaDoc);
                l_elmChild = DOMUtil.getNextSiblingElement(l_elmChild);
                if (l_elmChild != null)
                    childName = l_elmChild.getLocalName();
            }
            else {
                String text = DOMUtil.getSyntheticAnnotation(elmNode);
                if (text != null) {
                    annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
                }
            }

            if (l_elmChild == null) {
                reportSchemaError("s4s-elt-must-match.2",
                        new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"},
                        elmNode);
            } else if (childName.equals(SchemaSymbols.ELT_ALL)) {
                particle = traverseAll(l_elmChild, schemaDoc, grammar, CHILD_OF_GROUP, group);
            } else if (childName.equals(SchemaSymbols.ELT_CHOICE)) {
                particle = traverseChoice(l_elmChild, schemaDoc, grammar, CHILD_OF_GROUP, group);
            } else if (childName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                particle = traverseSequence(l_elmChild, schemaDoc, grammar, CHILD_OF_GROUP, group);
            } else {
                reportSchemaError("s4s-elt-must-match.1",
                        new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(l_elmChild)},
                        l_elmChild);
            }

            if (l_elmChild != null &&
                    DOMUtil.getNextSiblingElement(l_elmChild) != null) {
                reportSchemaError("s4s-elt-must-match.1",
                        new Object[]{"group (global)", "(annotation?, (all | choice | sequence))",
                        DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(l_elmChild))},
                        DOMUtil.getNextSiblingElement(l_elmChild));
            }
        }

        
        if (strNameAttr != null) {
            group.fName = strNameAttr;
            group.fTargetNamespace = schemaDoc.fTargetNamespace;
            if (particle == null) {
                particle = XSConstraints.getEmptySequence();
            }
            group.fModelGroup = (XSModelGroupImpl)particle.fValue;
            XSObjectList annotations;
            if (annotation != null) {
                annotations = new XSObjectListImpl();
                ((XSObjectListImpl) annotations).addXSObject(annotation);
            } else {
                annotations = XSObjectListImpl.EMPTY_LIST;
            }
            group.fAnnotations = annotations;
            
            if (grammar.getGlobalGroupDecl(group.fName) == null) {
                grammar.addGlobalGroupDecl(group);
            }

            
            final String loc = fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            final XSGroupDecl group2 = grammar.getGlobalGroupDecl(group.fName, loc);
            if (group2 == null) {
                grammar.addGlobalGroupDecl(group, loc);
            }

            
            if (fSchemaHandler.fTolerateDuplicates) {
                if (group2 != null) {
                    group = group2;
                }
                fSchemaHandler.addGlobalGroupDecl(group);
            }
        }
        else {
            
            group = null;
        }

        if (group != null) {
            
            
            Object redefinedGrp = fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(XSDHandler.GROUP_TYPE,
                    new QName(XMLSymbols.EMPTY_STRING, strNameAttr, strNameAttr, schemaDoc.fTargetNamespace),
                    schemaDoc, elmNode);
            if (redefinedGrp != null) {
                
                grammar.addRedefinedGroupDecl(group, (XSGroupDecl)redefinedGrp,
                        fSchemaHandler.element2Locator(elmNode));
            }
        }

        fAttrChecker.returnAttrArray(attrValues, schemaDoc);

        return group;

    } 
}
