/* SPDX-License-Identifier: Apache-2.0 */
package org.odpi.openmetadata.accessservices.assetconsumer.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.odpi.openmetadata.accessservices.assetconsumer.properties.Asset;
import org.odpi.openmetadata.accessservices.assetconsumer.properties.Classification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Validate that the AssetConsumerEvent bean can be cloned, compared, serialized, deserialized and printed as a String.
 */
public class AssetConsumerEventTest
{
    private AssetConsumerEventType eventType                = AssetConsumerEventType.NEW_ASSET_EVENT;
    private Asset                  originalAsset            = new Asset();
    private Asset                  asset                    = new Asset();
    private String                 url                      = "TestURL";
    private String                 guid                     = "TestGUID";
    private String                 typeId                   = "TestTypeId";
    private String                 typeName                 = "TestTypeName";
    private long                   typeVersion              = 7;
    private String                 typeDescription          = "TestTypeDescription";
    private String                 qualifiedName            = "TestQualifiedName";
    private String                 displayName              = "TestDisplayName";
    private String                 description              = "TestDescription";
    private String                 owner                    = "TestOwner";
    private Map<String, Object>    additionalProperties     = new HashMap<>();
    private List<Classification>   classifications          = new ArrayList<>();
    private Classification         classification           = new Classification();
    private Map<String, Object>    classificationProperties = new HashMap<>();


    /**
     * Default constructor
     */
    public AssetConsumerEventTest()
    {
        asset.setGUID(guid);
        asset.setTypeName(typeName);
        asset.setTypeDescription(typeDescription);
        asset.setQualifiedName(qualifiedName);
        asset.setDisplayName(displayName);
        asset.setDescription(description);
        asset.setOwner(owner);
        asset.setAdditionalProperties(additionalProperties);
        asset.setClassifications(classifications);

        additionalProperties.put("TestAdditionalPropertyName", "TestAdditionalPropertyValue");

        classification.setClassificationName("TestClassificationName");
        classificationProperties.put("TestClassificationPropertyName", "TestClassificationPropertyValue");
        classification.setClassificationProperties(classificationProperties);
        classifications.add(classification);
    }


    /**
     * Set up an example object to test.
     *
     * @return filled in object
     */
    private AssetConsumerEvent getTestObject()
    {
        AssetConsumerEvent testObject = new AssetConsumerEvent();

        testObject.setEventType(eventType);
        testObject.setOriginalAsset(originalAsset);
        testObject.setAsset(asset);

        return testObject;
    }


    /**
     * Validate that the object that comes out of the test has the same content as the original test object.
     *
     * @param resultObject object returned by the test
     */
    private void validateResultObject(AssetConsumerEvent  resultObject)
    {
        assertTrue(resultObject.getEventType().equals(eventType));
        assertTrue(resultObject.getOriginalAsset().equals(originalAsset));
        assertTrue(resultObject.getAsset().equals(asset));
    }


    /**
     * Validate that the object is initialized properly
     */
    @Test public void testNullObject()
    {
        AssetConsumerEvent    nullObject = new AssetConsumerEvent();

        assertTrue(nullObject.getEventType() == null);
        assertTrue(nullObject.getOriginalAsset() == null);
        assertTrue(nullObject.getAsset() == null);

        nullObject = new AssetConsumerEvent(null);

        assertTrue(nullObject.getEventType() == null);
        assertTrue(nullObject.getOriginalAsset() == null);
        assertTrue(nullObject.getAsset() == null);
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

        AssetConsumerEvent  sameObject = getTestObject();
        assertTrue(sameObject.equals(sameObject));

        AssetConsumerEvent  differentObject = getTestObject();
        differentObject.setAsset(new Asset());
        assertFalse(getTestObject().equals(differentObject));

        AssetConsumerEvent  anotherObject = getTestObject();
        anotherObject.setEventVersionId(3773L);
        assertFalse(getTestObject().equals(anotherObject));
    }


    /**
     *  Validate that 2 different objects with the same content have the same hash code.
     */
    @Test public void testHashCode()
    {
        assertTrue(getTestObject().hashCode() == getTestObject().hashCode());

        AssetConsumerEvent  testObject = getTestObject();

        assertTrue(testObject.hashCode() != 0);

        AssetConsumerEvent  differentObject = getTestObject();

        differentObject.setAsset(null);

        assertFalse(testObject.hashCode() == differentObject.hashCode());
    }


    /**
     *  Validate that an object cloned from another object has the same content as the original
     */
    @Test public void testClone()
    {
        validateResultObject(new AssetConsumerEvent(getTestObject()));
    }


    /**
     * Validate that an object generated from a JSON String has the same content as the object used to
     * create the JSON String.
     */
    @Test public void testJSON()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String       jsonString   = null;

        /*
         * This class
         */
        try
        {
            jsonString = objectMapper.writeValueAsString(getTestObject());
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        try
        {
            validateResultObject(objectMapper.readValue(jsonString, AssetConsumerEvent.class));
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        /*
         * Through superclass
         */
        AssetConsumerEventHeader superObject = getTestObject();

        try
        {
            jsonString = objectMapper.writeValueAsString(superObject);
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        try
        {
            validateResultObject((AssetConsumerEvent) objectMapper.readValue(jsonString, AssetConsumerEventHeader.class));
        }
        catch (Throwable  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }
    }


    /**
     * Test that toString is overridden.
     */
    @Test public void testToString()
    {
        assertTrue(getTestObject().toString().contains("AssetConsumerEvent"));
    }
}
