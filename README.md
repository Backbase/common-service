# Introduction 
This is jar, which contain common code like creating a configs for access-control, arrangment-manager and clients for those API's.
As these configs or client APIs are used in every outbound integration service, created a common-service jar. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1. Software dependencies
    It is depending on service sdk and backbase-bom version. As and when needed, keep update those versions.
2. Latest releases
   when it is build, keep update the pom.xml with the latest version.



# Build and Test
  in local, build using "mvn clean install". It creates the jar in .m2 repository.

In the other services, wherever it is needed to consume access-csontrol or user-manager, 
add this jar in the service as dependency.
    <dependency>
        <groupId>com.backbase.vantage.common</groupId>
        <artifactId>common-service</artifactId>
        <version>${common.service.version}</version> #  actual version when the common-service is build.
    </dependency>

# Contribute
1. Keep update the Service-sdk and backbase-bom versions. Do not add any other ability, because all other capability config not needed for every other services.
2. Pipeline implemtation how to build in Baas or azure environment is needed to add.
