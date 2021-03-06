/* SPDX-License-Identifier: Apache-2.0 */
package org.odpi.openmetadata.compliance.tests.repository;

import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.OMRSMetadataCollection;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.*;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.*;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.EntityNotKnownException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.FunctionNotSupportedException;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.StatusNotSupportedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Test that all defined entities can be created, retrieved, updated and deleted.
 */
public class TestSupportedEntityLifecycle extends OpenMetadataRepositoryTestCase
{
    private static final String testUserId = "ComplianceTestUser";

    private static final String testCaseId = "repository-entity-lifecycle";
    private static final String testCaseName = "Repository entity lifecycle test case";

    private static final String assertion1     = testCaseId + "-01";
    private static final String assertionMsg1  = " new entity created.";
    private static final String assertion2     = testCaseId + "-02";
    private static final String assertionMsg2  = " new entity has createdBy user.";
    private static final String assertion3     = testCaseId + "-03";
    private static final String assertionMsg3  = " new entity has creation time.";
    private static final String assertion4     = testCaseId + "-04";
    private static final String assertionMsg4  = " new entity has correct provenance type.";
    private static final String assertion5     = testCaseId + "-05";
    private static final String assertionMsg5  = " new entity has correct initial status.";
    private static final String assertion6     = testCaseId + "-06";
    private static final String assertionMsg6  = " new entity has correct type.";
    private static final String assertion7     = testCaseId + "-07";
    private static final String assertionMsg7  = " new entity has local metadata collection.";
    private static final String assertion8     = testCaseId + "-08";
    private static final String assertionMsg8  = " new entity has version greater than zero.";
    private static final String assertion9     = testCaseId + "-09";
    private static final String assertionMsg9  = " new entity is known.";
    private static final String assertion10    = testCaseId + "-10";
    private static final String assertionMsg10 = " new entity summarized.";
    private static final String assertion11    = testCaseId + "-11";
    private static final String assertionMsg11 = " new entity retrieved.";
    private static final String assertion12    = testCaseId + "-12";
    private static final String assertionMsg12 = " new entity is unattached.";
    private static final String assertion13    = testCaseId + "-13";
    private static final String assertionMsg13 = " entity status updated.";
    private static final String assertion14    = testCaseId + "-14";
    private static final String assertionMsg14 = " entity new status is ";
    private static final String assertion15    = testCaseId + "-15";
    private static final String assertionMsg15 = " entity with new status version number is ";
    private static final String assertion16    = testCaseId + "-16";
    private static final String assertionMsg16 = " entity can not be set to DELETED status.";
    private static final String assertion17    = testCaseId + "-17";
    private static final String assertionMsg17 = " entity properties cleared to null.";
    private static final String assertion18    = testCaseId + "-18";
    private static final String assertionMsg18 = " entity with no properties version number is ";
    private static final String assertion19    = testCaseId + "-19";
    private static final String assertionMsg19 = " entity has properties restored.";
    private static final String assertion20    = testCaseId + "-20";
    private static final String assertionMsg20 = " entity after undo version number is ";
    private static final String assertion21    = testCaseId + "-21";
    private static final String assertionMsg21 = " entity deleted version number is ";
    private static final String assertion22    = testCaseId + "-22";
    private static final String assertionMsg22 = " entity no longer retrievable after delete.";
    private static final String assertion23    = testCaseId + "-23";
    private static final String assertionMsg23 = " entity restored version number is ";
    private static final String assertion24    = testCaseId + "-24";
    private static final String assertionMsg24 = " entity purged.";

    private static final String discoveredProperty_undoSupport = " undo support";
    private static final String discoveredProperty_softDeleteSupport = " soft delete support";


    private String                 metadataCollectionId;
    private EntityDef              entityDef;


    /**
     * Typical constructor sets up superclass and discovered information needed for tests
     */
    TestSupportedEntityLifecycle(String                  workbenchId,
                                 String                  metadataCollectionId,
                                 EntityDef               entityDef)
    {
        super(workbenchId, testCaseId, testCaseName);
        this.metadataCollectionId = metadataCollectionId;
        this.entityDef = entityDef;
    }


    /**
     * Method implemented by the actual test case.
     *
     * @throws Exception something went wrong with the test.
     */
    protected void run() throws Exception
    {
        Map<String, Object>    discoveredProperties = new HashMap<>();
        OMRSMetadataCollection metadataCollection = super.getMetadataCollection();


        String   testTypeName = entityDef.getName();

        EntityDetail newEntity = metadataCollection.addEntity(testUserId,
                                                              entityDef.getGUID(),
                                                              super.getPropertiesForInstance(entityDef.getPropertiesDefinition()),
                                                              null,
                                                              null);

        assertCondition((newEntity != null), assertion1, testTypeName + assertionMsg1);
        assertCondition(testUserId.equals(newEntity.getCreatedBy()), assertion2, testTypeName + assertionMsg2);
        assertCondition((newEntity.getCreateTime() != null), assertion3, testTypeName + assertionMsg3);
        assertCondition((newEntity.getInstanceProvenanceType() == InstanceProvenanceType.LOCAL_COHORT), assertion4, testTypeName + assertionMsg4);
        assertCondition((newEntity.getStatus() == entityDef.getInitialStatus()), assertion5, testTypeName + assertionMsg5);

        InstanceType instanceType = newEntity.getType();

        if (instanceType != null)
        {
            assertCondition(((instanceType.getTypeDefGUID().equals(entityDef.getGUID())) &&
                    (instanceType.getTypeDefName().equals(testTypeName))), assertion6, testTypeName + assertionMsg6);

        }
        else
        {
            assertCondition(false, assertion6, testTypeName + assertionMsg6);
        }

        /*
         * The metadata collection should be set up and consistently
         */
        assertCondition(((newEntity.getMetadataCollectionId() != null) && newEntity.getMetadataCollectionId().equals(this.metadataCollectionId)),
                        assertion7, testTypeName + assertionMsg7);

        assertCondition((newEntity.getVersion() > 0), assertion8, testTypeName + assertionMsg8);

        /*
         * Validate that the entity can be consistently retrieved.
         */
        assertCondition((newEntity.equals(metadataCollection.isEntityKnown(testUserId, newEntity.getGUID()))), assertion9, testTypeName + assertionMsg9);
        assertCondition((metadataCollection.getEntitySummary(testUserId, newEntity.getGUID()) != null), assertion10, testTypeName + assertionMsg10);
        assertCondition((newEntity.equals(metadataCollection.getEntityDetail(testUserId, newEntity.getGUID()))), assertion11, testTypeName + assertionMsg11);

        /*
         * No relationships have been created so none should be returned.
         */
        assertCondition((metadataCollection.getRelationshipsForEntity(testUserId,
                                                                      newEntity.getGUID(),
                                                                      null,
                                                                      0,
                                                                      null,
                                                                      null,
                                                                      null,
                                                                      null,
                                                                      0) == null), assertion12, testTypeName + assertionMsg12);


        /*
         * Update entity status
         */
        long  nextVersion = newEntity.getVersion() + 1;
        for (InstanceStatus validInstanceStatus : entityDef.getValidInstanceStatusList())
        {
            if (validInstanceStatus != InstanceStatus.DELETED)
            {
                EntityDetail updatedEntity = metadataCollection.updateEntityStatus(testUserId, newEntity.getGUID(), validInstanceStatus);
                assertCondition((updatedEntity != null), assertion13, testTypeName + assertionMsg13);
                assertCondition((updatedEntity.getStatus() == validInstanceStatus), assertion14, testTypeName + assertionMsg14 + validInstanceStatus.getName());
                assertCondition((updatedEntity.getVersion() == nextVersion), assertion15, testTypeName + assertionMsg15 + nextVersion);
                nextVersion ++;
            }
        }

        try
        {
            metadataCollection.updateEntityStatus(testUserId, newEntity.getGUID(), InstanceStatus.DELETED);
            assertCondition((false), assertion16, testTypeName + assertionMsg16);
        }
        catch (StatusNotSupportedException exception)
        {
            assertCondition((true), assertion16, testTypeName + assertionMsg16);
        }

        if ((newEntity.getProperties() != null) && (newEntity.getProperties().getInstanceProperties() != null) && (! newEntity.getProperties().getInstanceProperties().isEmpty()))
        {
            EntityDetail noPropertiesEntity = metadataCollection.updateEntityProperties(testUserId,
                                                                                        newEntity.getGUID(),
                                                                                        new InstanceProperties());
            assertCondition(((noPropertiesEntity != null) &&
                                    ((noPropertiesEntity.getProperties() == null) || (noPropertiesEntity.getProperties().getInstanceProperties() == null) || (noPropertiesEntity.getProperties().getInstanceProperties().isEmpty()))),
                            assertion17,
                            testTypeName + assertionMsg17);
            assertCondition(((noPropertiesEntity != null) && (noPropertiesEntity.getVersion() == nextVersion)),
                            assertion18,
                            testTypeName + assertionMsg18 + nextVersion);
            nextVersion++;


            try
            {
                EntityDetail undoneEntity = metadataCollection.undoEntityUpdate(testUserId, newEntity.getGUID());

                discoveredProperties.put(testTypeName + discoveredProperty_undoSupport, "Enabled");
                super.result.setDiscoveredProperties(discoveredProperties);

                assertCondition(((undoneEntity != null) && (undoneEntity.getProperties() != null)),
                                assertion19,
                                testTypeName + assertionMsg19);
                assertCondition(((undoneEntity != null) && (undoneEntity.getVersion() == nextVersion)),
                                assertion20,
                                testTypeName + assertionMsg20 + nextVersion);
                nextVersion++;
            }
            catch (FunctionNotSupportedException exception)
            {
                discoveredProperties.put(testTypeName + discoveredProperty_undoSupport, "Disabled");
                super.result.setDiscoveredProperties(discoveredProperties);

            }
        }

        try
        {
            EntityDetail deletedEntity = metadataCollection.deleteEntity(testUserId,
                                                                         newEntity.getType().getTypeDefGUID(),
                                                                         newEntity.getType().getTypeDefName(),
                                                                         newEntity.getGUID());
            discoveredProperties.put(testTypeName + discoveredProperty_softDeleteSupport, "Enabled");
            super.result.setDiscoveredProperties(discoveredProperties);

            assertCondition(((deletedEntity != null) && (deletedEntity.getVersion() == nextVersion)), assertion21, testTypeName + assertionMsg21 + nextVersion);
            nextVersion ++;

            try
            {
                metadataCollection.getEntityDetail(testUserId, newEntity.getGUID());

                assertCondition((false), assertion22, testTypeName + assertionMsg22);
            }
            catch (EntityNotKnownException exception)
            {
                assertCondition((true), assertion22, testTypeName + assertionMsg22);
            }

            EntityDetail restoredEntity = metadataCollection.restoreEntity(testUserId, newEntity.getGUID());

            assertCondition(((restoredEntity != null) && (restoredEntity.getVersion() == nextVersion)), assertion23, testTypeName + assertionMsg23 + nextVersion);

            metadataCollection.deleteEntity(testUserId,
                                            newEntity.getType().getTypeDefGUID(),
                                            newEntity.getType().getTypeDefName(),
                                            newEntity.getGUID());
        }
        catch (FunctionNotSupportedException exception)
        {
            discoveredProperties.put(testTypeName + discoveredProperty_softDeleteSupport, "Disabled");
            super.result.setDiscoveredProperties(discoveredProperties);
        }

        metadataCollection.purgeEntity(testUserId,
                                       newEntity.getType().getTypeDefGUID(),
                                       newEntity.getType().getTypeDefName(),
                                       newEntity.getGUID());

        try
        {
            metadataCollection.getEntityDetail(testUserId, newEntity.getGUID());

            assertCondition((false), assertion24, testTypeName + assertionMsg24);
        }
        catch (EntityNotKnownException exception)
        {
            assertCondition((true), assertion24, testTypeName + assertionMsg24);
        }

        super.result.setSuccessMessage("Entities can be managed through their lifecycle");

        super.result.setDiscoveredProperties(discoveredProperties);
    }
}
