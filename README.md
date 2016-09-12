# ![pactsafe-java-sdk](media/logo.png)

[![Build Status](http://jenkins.pactsafe.com/buildStatus/icon?job=Java SDK)](http://jenkins.pactsafe.com/job/Java%20SDK/) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pactsafe/pactsafe-java-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pactsafe/pactsafe-java-sdk)

#pactsafe-java-sdk

A Java client for the [PactSafe](https://www.pactsafe.com) Activity API. Integrate into any application for secure legal record-keeping.

## Initialization

```
<dependency>
  <groupId>com.pactsafe</groupId>
  <artifactId>pactsafe-java-sdk</artifactId>
  <version>{Latest}</version>
</dependency>
```
*Coming soon to maven-central.*

## Initialization

Initialize the Activity client, passing your PactSafe Site's `access_id` String as the first argument.
```java
Activity site = new Activity("ACCESS_KEY");
```

## Configuration

The second argument to the `Activity` constructor is an optional `ParameterStore` object of properties for the Activity client.
```java
ParameterStore parameters = new ParameterStore();
parameters.setTestMode(true);
Activity site = new Activity("ACCESS_KEY", parameters);
```

The third argument is an optional `ActivityOptions` object of client configuration settings.
```java
ActivityOptions options = new ActivityOptions();
options.setHost("http://localhost:3000");
Activity site = new Activity("ACCESS_KEY", parameters, options);
```

## Parameters

Every Action sent to the PactSafe Activity API is built from the parameters stored on the Activity client. These parameter values can be set or retrieved at any point.
```java
ParameterStore parameters = site.getParameters();
parameters.setPageTitle("Registration Page");
site.setParameters(parameters);
```

## Load

The `load` method lets you load the properties and content of a clickwrap Group by key.
```java
Group group = site.load("GROUP_KEY");
```

Optionally, you can pass a custom `Map<String, Object>` as `renderData` for the second argument -- allowing you to alter the content each time the Group is loaded. For this reason, every response from the `load` request provides a unique `render_id` to use for activity tracking.
```java
renderDataMap.put("order_id", 123456);
renderDataMap.put("company_name", "Example LLC");
Group group = site.load("GROUP_KEY", renderDataMap);
```

## Retrieve

The `retrieve` method lets you load a Signer's acceptance history for a given set of Contract IDs.

Providing a `signer_id` and an array of `contracts`, the response will contain a map of each Contract ID and its most recently accepted Version ID.
```java
List<Integer> contractIds = new ArrayList<Integer>();
contractIds.add(1);
contractIds.add(4);
Map<Integer, String> retrieve = activity.retrieve("SIGNER_ID", contractIds);
```

When invoked from a group object, the contracts from the group will be automatically loaded.
```java
Map<Integer, String> retrieve = group.retrieve("SIGNER_ID");
```


## Latest

The `latest` method tells you if a Signer has accepted the latest Version for a given set of Contract IDs.

This method is similar to the `retrieve` method, but instead of providing the actual Version ID that was last accepted, the response contains a boolean `true` or `false` for each contract ID.

```java
Map<Integer, Boolean> latest = activity.latest("SIGNER_ID", contractIds);
```

When invoked from a group object, the contracts from the group will be automatically loaded.
```java
Map<Integer, Boolean> latest = activity.latest("SIGNER_ID");
```

## Send

The `send` method lets you track a Signer Action by sending data to the PactSafe Activity API.

Provide an option from the `EventType` enum, as well as any parameters to save on the Action. The `signerId` parameter and `renderId` are required for most event types.

```java
ParameterStore action = new ParameterStore();
action.setSignerId("john@example.com");
action.setRenderId(group.getParameters().getRenderId());
action.setPageTitle("My Test Page Title");

site.send(EventType.UPDATED, action);
```

## Agreed

The `agreed` method sends a Signer Action to the PactSafe Activity API with the event type `agreed`.

Provide a `signer_id`, as well as any parameters to save on the Action. If the content being accepted was assigned a `render_id`, be sure to include that same `render_id` in the Action parameters.

```java
ParameterStore action = new ParameterStore();
action.setSignerId("john@example.com");
action.setRenderId(group.getParameters().getRenderId());
Map<String, Object> customData = new HashMap<String, Object>();
customData.put("order_id", 123456);
customData.put("sku", "MYTX772");
action.setCustomData(customData);

site.agreed(action);
```

## Exceptions

All methods outside of `Activity` initialization are designed to throw a `PactSafeActivityException` Exception should an issue arrise in the processing of your request. Additionally, an `IllegalArgumentException` will be raised should illegal arguments be passed.

## Development

For development, you can enable the `test_mode` parameter when the client is initialized. Any Signers or Actions created within test mode can be easily cleared from your account.
```java
ParameterStore parameters = new ParameterStore();
parameters.setTestMode(true);
Activity site = new Activity("ACCESS_KEY", parameters);
```

## Documentation

*View the [documentation](https://app.pactsafe.com/docs) to see how our Javascript web snippet works.*


Copyright &copy; 2016 PactSafe, Inc. \<engineering@pactsafe.com\>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the 'Software'), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
