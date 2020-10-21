# Spending Pulse API Reference Application
This project showcases the use case of retrieving merchant metrics using the Spending Pulse API [here](https://developer.mastercard.com/drafts/small-business-decision-enhancer/small-business-decision-enhancer-v2/documentation)

## Frameworks Used
- Spring
- OpenAPI Generator

## Requirements
- Java 8
- [Maven](https://maven.apache.org/download.cgi)

## Author
[Enterprise Data Services](mailto:apisupport@mastercard.com)

## Setup
1. Create an account at [Mastercard Developers](https://developer.mastercard.com).
2. Create a new project and add the `Spending Pulse` API to your project. A `.p12` file is downloaded automatically.
3. Take note of the given **consumer key, keyalias, and keystore password** given upon the project creation.
4. Copy the downloaded `.p12` file to `/src/main/resources`.
5. Copy the dw-spendingpulse-api-spec-v1.yaml file to `/src/main/resources`.
6. Update the pom.xml with with open-api-generator dependencies.
7. Run `mvn clean install` from the root of the project directory.


## Mastercard oauth1 Signer
This dependency is required to properly call the API.
```xml
<dependency>
    <groupId>com.mastercard.developer</groupId>
    <artifactId>oauth1-signer</artifactId>
    <version>1.2.3</version>
</dependency>
```
[Link to the oauth1 library's Github](https://github.com/Mastercard/oauth1-signer-java)

[Looking for other languages?](https://github.com/Mastercard?q=oauth&type=&language=)

## Spending Pulse Client Library
The client library used to generate the API Calls and object models for this application can be seen in the pom.xml file
in the project's root directory.
```xml
<plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>3.3.4</version>
                <executions>
                    <execution>
                        <id>Spending Pulse API 1.0 REST Client</id>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/dw-spendingpulse-api-spec-v1.yaml</inputSpec>
                            <generatorName>java</generatorName>
                            <!-- No "library" element here means the plugin will use the default library template ("okhttp-gson") -->
                            <configOptions>
                                <sourceFolder>src/gen/java/main</sourceFolder>
                                <dateLibrary>java8</dateLibrary>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
```
    
For more information on how this client generator works please consult the official [Github repository](https://github.com/OpenAPITools/openapi-generator)

## Reference Application Usage
You make the API call by calling gasweeklyGet, subscriptionGet, 
spendingpulseGet, parametersGet, and then store the response in an object. 
Once you have the response stored, you have a couple of options, but for this reference app you can simply use toString to print the body.

## API Usage
SpendingPulse™ estimates total retail sales within the given countries and regions for all payment forms and correlates extremely well with various government estimates of total retail activity. In addition, SpendingPulse™ reports on various sub-categories (sectors) of retail sales for a more detailed look at certain sectors-worth of total retail sales.

The endpoints `/subscription` , `/parameters` , `/spendingpulse`, and `/gasweekly` 
provide the details for the code you provide.


## License
[Apache 2 License](https://apache.org/licenses/LICENSE-2.0)



**Copyright © 1994-2020, All Right Reserved by Mastercard.**