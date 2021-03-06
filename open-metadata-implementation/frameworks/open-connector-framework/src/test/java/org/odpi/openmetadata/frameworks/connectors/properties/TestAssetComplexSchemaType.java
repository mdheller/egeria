/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.frameworks.connectors.properties;

import org.odpi.openmetadata.frameworks.connectors.properties.beans.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Validate that the ComplexSchemaType can function as a facade for its bean.
 */
public class TestAssetComplexSchemaType
{
    private ElementType          type                 = new ElementType();
    private List<Classification> classifications      = new ArrayList<>();
    private Map<String, Object>  additionalProperties = new HashMap<>();

    private AssetMeanings        assetMeanings        = new MockAssetMeanings(null,
                                                                              23,
                                                                              50);

    private AssetSchemaAttributes schemaAttributes    = new MockAssetSchemaAttributes(null,
                                                                                      12,
                                                                                      56);
    private AssetSchemaLinks      schemaLinks         = new MockAssetSchemaLinks(null,
                                                                                 4,
                                                                                 28);


    /**
     * Default constructor
     */
    public TestAssetComplexSchemaType()
    {
        type.setElementTypeName("TestType");
    }


    /**
     * Set up an example object to test.
     *
     * @return filled in object
     */
    private AssetComplexSchemaType getTestObject()
    {
        Schema testBean = new Schema();

        testBean.setType(type);
        testBean.setGUID("TestGUID");
        testBean.setURL("TestURL");
        testBean.setClassifications(classifications);

        testBean.setQualifiedName("TestQualifiedName");
        testBean.setAdditionalProperties(additionalProperties);

        testBean.setAuthor("TestAuthor");
        testBean.setEncodingStandard("TestEncodingStandard");
        testBean.setUsage("TestUsage");
        testBean.setVersionNumber("TestVersionNumber");

        testBean.setMaximumElements(9);
        testBean.setSchemaType(SchemaType.SET);

        return new AssetComplexSchemaType(testBean, assetMeanings, schemaAttributes, schemaLinks);
    }


    /**
     * Set up an example object to test.  This has a different field in the superclass (setGUID).
     *
     * @return filled in object
     */
    private AssetComplexSchemaType getDifferentObject()
    {
        Schema testBean = new Schema();

        testBean.setType(type);
        testBean.setGUID("TestDifferentGUID");
        testBean.setURL("TestURL");
        testBean.setClassifications(classifications);

        testBean.setQualifiedName("TestQualifiedName");
        testBean.setAdditionalProperties(additionalProperties);

        testBean.setAuthor("TestAuthor");
        testBean.setEncodingStandard("TestEncodingStandard");
        testBean.setUsage("TestUsage");
        testBean.setVersionNumber("TestVersionNumber");

        testBean.setMaximumElements(9);
        testBean.setSchemaType(SchemaType.SET);

        return new AssetComplexSchemaType(testBean, assetMeanings, schemaAttributes, schemaLinks);
    }



    /**
     * Set up an example object to test.  This has a different field in the subclass.
     *
     * @return filled in object
     */
    private AssetComplexSchemaType getAnotherDifferentObject()
    {
        Schema testBean = new Schema();

        testBean.setType(type);
        testBean.setGUID("TestGUID");
        testBean.setURL("TestURL");
        testBean.setClassifications(classifications);

        testBean.setQualifiedName("TestQualifiedName");
        testBean.setAdditionalProperties(additionalProperties);

        testBean.setAuthor("TestAuthor");
        testBean.setEncodingStandard("TestEncodingStandard");
        testBean.setUsage("TestUsage");
        testBean.setVersionNumber("TestVersionNumber");

        testBean.setMaximumElements(10);
        testBean.setSchemaType(SchemaType.SET);

        return new AssetComplexSchemaType(testBean, assetMeanings, schemaAttributes, schemaLinks);
    }


    /**
     * Set up an example object to test.  This has a different field in the subclass.
     *
     * @return filled in object
     */
    private AssetComplexSchemaType getYetAnotherDifferentObject()
    {
        Schema testBean = new Schema();

        testBean.setType(type);
        testBean.setGUID("TestGUID");
        testBean.setURL("TestURL");
        testBean.setClassifications(classifications);

        testBean.setQualifiedName("TestQualifiedName");
        testBean.setAdditionalProperties(additionalProperties);

        testBean.setAuthor("TestAuthor");
        testBean.setEncodingStandard("TestEncodingStandard");
        testBean.setUsage("TestUsage");
        testBean.setVersionNumber("TestVersionNumber");

        testBean.setMaximumElements(9);
        testBean.setSchemaType(SchemaType.SET);

        return new AssetComplexSchemaType(new AssetSummary(new Asset()), testBean, assetMeanings, schemaAttributes, schemaLinks);
    }


    /**
     * Set up an example object to test.  This has a different field in the subclass.
     *
     * @return filled in object
     */
    private AssetComplexSchemaType getAndYetAnotherDifferentObject()
    {
        Schema testBean = new Schema();

        testBean.setType(type);
        testBean.setGUID("TestGUID");
        testBean.setURL("TestURL");
        testBean.setClassifications(classifications);

        testBean.setQualifiedName("TestQualifiedName");
        testBean.setAdditionalProperties(additionalProperties);

        testBean.setAuthor("TestAuthor");
        testBean.setEncodingStandard("TestEncodingStandard");
        testBean.setUsage("TestUsage");
        testBean.setVersionNumber("TestVersionNumber");

        testBean.setMaximumElements(9);
        testBean.setSchemaType(SchemaType.SET);

        return new AssetComplexSchemaType(testBean, assetMeanings, schemaAttributes, null);
    }


    /**
     * Validate that the object that comes out of the test has the same content as the original test object.
     *
     * @param resultObject object returned by the test
     */
    private void validateResultObject(AssetComplexSchemaType resultObject)
    {
        assertTrue(resultObject.getType().getElementTypeBean().equals(type));
        assertTrue(resultObject.getGUID().equals("TestGUID"));
        assertTrue(resultObject.getURL().equals("TestURL"));
        assertTrue(resultObject.getAssetClassifications() == null);

        assertTrue(resultObject.getQualifiedName().equals("TestQualifiedName"));
        assertTrue(resultObject.getAdditionalProperties() == null);

        assertTrue(resultObject.getVersionNumber().equals("TestVersionNumber"));
        assertTrue(resultObject.getUsage().equals("TestUsage"));
        assertTrue(resultObject.getEncodingStandard().equals("TestEncodingStandard"));
        assertTrue(resultObject.getAuthor().equals("TestAuthor"));
        assertTrue(resultObject.getAssetMeanings() != null);

        assertTrue(resultObject.getSchemaAttributes() != null);
        assertTrue(resultObject.getSchemaLinks() != null);
        assertTrue(resultObject.getMaximumElements() == 9);
        assertTrue(resultObject.getSchemaType().equals(SchemaType.SET));

    }


    /**
     * Validate that the object that comes out of the test has the same content as the original test object.
     *
     * @param nullObject object to test
     */
    private void validateNullObject(AssetComplexSchemaType nullObject)
    {
        assertTrue(nullObject.getType() == null);
        assertTrue(nullObject.getGUID() == null);
        assertTrue(nullObject.getURL() == null);
        assertTrue(nullObject.getAssetClassifications() == null);

        assertTrue(nullObject.getQualifiedName() == null);
        assertTrue(nullObject.getAdditionalProperties() == null);

        assertTrue(nullObject.getAssetMeanings() == null);
        assertTrue(nullObject.getAuthor() == null);
        assertTrue(nullObject.getEncodingStandard() == null);
        assertTrue(nullObject.getUsage() == null);
        assertTrue(nullObject.getVersionNumber() == null);

        assertTrue(nullObject.getSchemaLinks() == null);
        assertTrue(nullObject.getSchemaAttributes() == null);
        assertTrue(nullObject.getMaximumElements() == 0);
        assertTrue(nullObject.getSchemaType() == null);

    }


    /**
     * Validate that the object is initialized properly
     */
    @Test public void testNullObject()
    {
        Schema                 nullBean;
        AssetComplexSchemaType nullObject;
        AssetComplexSchemaType nullTemplate;
        AssetDescriptor        parentAsset;

        nullBean = null;
        nullObject = new AssetComplexSchemaType(nullBean, null, null, null);
        validateNullObject(nullObject);

        nullBean = new Schema();
        nullObject = new AssetComplexSchemaType(nullBean, null, null, null);
        validateNullObject(nullObject);

        nullBean = new Schema(null);
        nullObject = new AssetComplexSchemaType(nullBean, null, null, null);
        validateNullObject(nullObject);

        parentAsset = null;
        nullBean = null;
        nullObject = new AssetComplexSchemaType(parentAsset, nullBean, null, null, null);
        validateNullObject(nullObject);

        nullBean = new Schema();
        nullObject = new AssetComplexSchemaType(parentAsset, nullBean, null, null, null);
        validateNullObject(nullObject);

        nullBean = new Schema(null);
        nullObject = new AssetComplexSchemaType(parentAsset, nullBean, null, null, null);
        validateNullObject(nullObject);

        nullTemplate = null;
        nullObject = new AssetComplexSchemaType(parentAsset, nullTemplate);
        validateNullObject(nullObject);

        nullTemplate = new AssetComplexSchemaType(parentAsset, nullBean, null, null, null);;
        nullObject = new AssetComplexSchemaType(parentAsset, nullTemplate);
        validateNullObject(nullObject);
    }


    /**
     * Test that the maximum elements are properly set depending on the type of schema.
     */
    @Test public void testMaximumElements()
    {
        Schema  arrayBean = new Schema();
        arrayBean.setSchemaType(SchemaType.ARRAY);
        arrayBean.setMaximumElements(25);

        AssetComplexSchemaType testObject = new AssetComplexSchemaType(arrayBean, null, null, null);

        assertTrue(testObject.getMaximumElements() == 25);


        Schema  setBean = new Schema();
        setBean.setSchemaType(SchemaType.SET);
        setBean.setMaximumElements(27);

        testObject = new AssetComplexSchemaType(setBean, null, null, null);

        assertTrue(testObject.getMaximumElements() == 27);


        Schema  structBean = new Schema();
        structBean.setSchemaType(SchemaType.STRUCT);
        structBean.setMaximumElements(22);

        testObject = new AssetComplexSchemaType(structBean, null, null, null);

        assertTrue(testObject.getMaximumElements() == 0);

        testObject = new AssetComplexSchemaType(structBean,
                                                null,
                                                new MockAssetSchemaAttributes(null,
                                                                   25,
                                                                   10),
                                                null);

        assertTrue(testObject.getMaximumElements() == 0);
    }

    /**
     * Test that the link is properly managed
     */
    @Test public void testMeaningsAttributesLinks()
    {
        AssetMeanings          meanings         = new MockAssetMeanings(null, 23, 60);
        AssetSchemaAttributes  schemaAttributes = new MockAssetSchemaAttributes(null, 12, 56);
        AssetSchemaLinks       schemaLinks      = new MockAssetSchemaLinks(null, 4, 28);
        AssetComplexSchemaType testObject       = new AssetComplexSchemaType(null, null, null, null);

        assertTrue(testObject.getAssetMeanings() == null);
        assertTrue(testObject.getSchemaAttributes() == null);
        assertTrue(testObject.getSchemaLinks() == null);

        testObject = new AssetComplexSchemaType(null, meanings, schemaAttributes, schemaLinks);

        assertTrue(testObject.getAssetMeanings() != null);
        assertTrue(testObject.getSchemaAttributes() != null);
        assertTrue(testObject.getSchemaLinks() != null);
    }


    /**
     * Validate that 2 different objects with the same content are evaluated as equal.
     * Also that different objects are considered not equal.
     */
    @Test public void testEquals()
    {
        assertFalse(getTestObject().equals(null));
        assertFalse(getTestObject().equals("DummyString"));
        assertTrue(getTestObject().equals(getTestObject()));

        AssetComplexSchemaType sameObject = getTestObject();
        assertTrue(sameObject.equals(sameObject));

        assertFalse(getTestObject().equals(getDifferentObject()));
        assertFalse(getTestObject().equals(getAnotherDifferentObject()));
        assertFalse(getTestObject().equals(getYetAnotherDifferentObject()));
        assertFalse(getTestObject().equals(getAndYetAnotherDifferentObject()));
    }


    /**
     *  Validate that 2 different objects with the same content have the same hash code.
     */
    @Test public void testHashCode()
    {
        assertTrue(getTestObject().hashCode() == getTestObject().hashCode());
    }


    /**
     *  Validate that an object cloned from another object has the same content as the original
     */
    @Test public void testClone()
    {
        validateResultObject(new AssetComplexSchemaType(null, getTestObject()));

        validateResultObject((AssetComplexSchemaType)getTestObject().cloneAssetSchemaElement(null));


    }

    /**
     * Test that toString is overridden.
     */
    @Test public void testToString()
    {
        assertTrue(getTestObject().toString().contains("Schema"));
    }
}
