# Spending Pulse API Reference Application
[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Mastercard/loyalty-user-management-reference/blob/master/LICENSE)

## Table of Contents

- [Overview](#overview)
    * [Compatibility](#compatibility)
    * [References](#references)
    * [Frameworks](#frameworks)
- [Setup](#setup)
    * [Prerequisites](#prerequisites)
    * [Application Configuration](#configuration)
    * [Reference Application Usage](#reference-application-usage)
    * [Integrating with OpenAPI Generator](#integrating-with-openapi-generator)
- [Use Cases](#use-cases)
    * [Parameters](#parameters)
    * [Optional Body](#spulse-params)
- [API Reference](#api-reference)
- [Authentication](#authentication)
    * [Mastercard's OAuth Library](#oauth-library)
- [Support](#support)
- [License](#license)

## Overview <a name="overview"></a>
This project showcases the use case of retrieving merchant metrics using the Spending Pulse API [here](https://developer.mastercard.com/spending-pulse/documentation/)

### Compatibility <a name="compatibility"></a>
 * [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) or later
 
### References <a name="references"></a>
* [Mastercard’s OAuth Signer library](https://github.com/Mastercard/oauth1-signer-java)
* [Using OAuth 1.0a to Access Mastercard APIs](https://developer.mastercard.com/platform/documentation/using-oauth-1a-to-access-mastercard-apis/)

### Frameworks <a name="frameworks"></a>
- Spring
- OpenAPI Generator
- [Java 8+](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Setup <a name="setup"></a>

### Prerequisites <a name="prerequisites"></a>

* [Mastercard Developers Account](https://developer.mastercard.com/dashboard)
* A text editor or IDE
* [Java 8+](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi)
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.

### Application Configuration <a name="configuration"> </a>

1. Create an account at [Mastercard Developers](https://developer.mastercard.com).
2. Create a new project and add the `Spending Pulse` API to your project. A `.p12` file is downloaded automatically.
3. Take note of the given **consumer key, keyalias, and keystore password** given upon the project creation.                    
4. Copy the downloaded `.p12` file to `/src/main/resources`.
5. Copy the dw-spendingpulse-api-spec-v1.yaml file to `/src/main/resources`.
6. Update the pom.xml with with open-api-generator dependencies.
7. Run `mvn clean install` from the root of the project directory.

## Reference Application Usage <a name="reference-application-usage"> </a>
You make the API call by calling gasweeklyGet, subscriptionGet, 
spendingpulseGet, parametersGet, and then store the response in an object. 
Once you have the response stored, you have a couple of options, but for this reference app you can simply use toString to print the body.

### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

See also:
* [OpenAPI Generator (maven Plugin)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [OpenAPI Generator (executable)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli)
* [CONFIG OPTIONS for java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

#### OpenAPI Generator Plugin Configuration
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
    
## Use Cases <a name="use-cases"></a>

#### Parameters <a name="parameters"></a>
These are the parameters used for all Spending Pulse API. All parameters are optional.

| Name      | Type      | Default Value      | Purpose       |
|-----------|-----------|--------------------|---------------|
| currentRow    | string       | 1                  | For Pagination; currentRow is used to offset the start of the list.        |
| offset     | string       | 25         | For Pagination; offset is used to limit the number of entities returned |

#### Parameters for /spendingpulse endpoint <a name="spulse-params"></a>
These are optional parameters used for the Spending Pulse API endpoint, "/spendingpulse".

| Name      | Type      | Default Value      | Purpose       |
|-----------|-----------|--------------------|---------------|
| country   | string    | US         | Country code. |
| ecomm     | string    | Y          | Ecommerce indicator. |
| period    | string    | Weekly     | Indicates the period covered by the data with possible values of - day, week, month, quarter, annual |
| productLine | string      | Weekly Sales  | Product Line. Either “US Executive Report” or “Weekly Sales” |
| publicationCoveragePeriod | string     | 25         | Publication Coverage Period indicates what period is to be covered, often the current report will include the month prior. |
| reportType  | string      | 25         | Report type name, today the only report supported is "monitor". |
| sector      | string      | 25         | Sector name. |


## API Reference <a name="api-reference"></a>

See the [API Reference](https://developer.mastercard.com/spending-pulse/documentation/api-reference/) page in the documentation. 

| API Endpoint                  | Description                                                       |
| ----------------------------- | ----------------------------------------------------------------- |
| [/subscriptions](https://developer.mastercard.com/spending-pulse/documentation/api-reference/#apis)                 | Returns a list of all subscribed reports and indicates the reports that may be retrieved with data present |
| [/parameters](https://developer.mastercard.com/spending-pulse/documentation/api-reference/#apis)                 | Returns a list of all reports available as well as subscribed reports |
| [/spendingpulse](https://developer.mastercard.com/spending-pulse/documentation/api-reference/#apis)            | Returns a given subscribed Spending Pulse report for the user            |
| [/gasweekly](https://developer.mastercard.com/spending-pulse/documentation/api-reference/#apis)   | 	Returns weekly gasoline report details                      |

## Authentication <a name="authentication"></a>

### Mastercard oauth1 Signer Library <a name="oauth-library"></a>
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

See the code used in this application to utilize the library.

``` Java
ApiClient client = new ApiClient();
client.setBasePath("https://sandbox.api.mastercard.com/spendingpulse/v1/spulse.svc");
client.setDebugging(true);

List<Interceptor> interceptors = client.getHttpClient().networkInterceptors();
interceptors.add(new ForceJsonResponseInterceptor());
interceptors.add(new OkHttp2OAuth1Interceptor(consumerKey, signingKey));
```

## Support <a name="support"></a>
If you would like further information, please send an email to apisupport@mastercard.com

## License <a name="license"></a>
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

## API Usage
SpendingPulse™ estimates total retail sales within the given countries and regions for all payment forms and correlates extremely well with various government estimates of total retail activity. In addition, SpendingPulse™ reports on various sub-categories (sectors) of retail sales for a more detailed look at certain sectors-worth of total retail sales.

The endpoints `/subscription` , `/parameters` , `/spendingpulse`, and `/gasweekly` 
provide the details for the code you provide.


## License
[Apache 2 License](https://apache.org/licenses/LICENSE-2.0)

**Copyright © 1994-2021, All Right Reserved by Mastercard.**