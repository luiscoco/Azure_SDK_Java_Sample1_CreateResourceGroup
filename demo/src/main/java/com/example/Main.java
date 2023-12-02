package com.example;

import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.core.management.Region;

public class Main {
    public static void main(String[] args) {

        //=============================================================
        // Azure SDK for Java: Azure Authentication
        //=============================================================

        AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);
        TokenCredential credential = new DefaultAzureCredentialBuilder()
            .authorityHost(profile.getEnvironment().getActiveDirectoryEndpoint())
            .build();
        AzureResourceManager azureResourceManager = AzureResourceManager
            .authenticate(credential, profile)
            .withDefaultSubscription();
        
        //=============================================================
        // Azure SDK for Java: Create a resource group
        //=============================================================

        final String rgName = "myFirstResourceGroup";
        System.out.println("Creating a resource group with name: " + rgName);

        azureResourceManager.resourceGroups().define(rgName)
             .withRegion(Region.US_WEST)
             .create();
    }
}