# Swagger Quick Reference

## Table of Contents

   1. [Overview](#overview)
       - [Key Components](#key-components)
       - [Benefits of Using Swagger](#benefits-of-using-swagger)
   2. [Getting Started - Documenting a Spring REST API](#getting-started---documenting-a-spring-rest-api)
       - [Setting up `springdoc-openapi`](#setting-up-springdoc-openapi)
       - [OpenAPI Description Path](#openapi-description-path)
       - [Important Checks Before Integration with Swagger UI](#important-checks-before-integration-with-swagger-ui)
       - [Swagger UI](#swagger-ui)
   3. [References](#references)

## Overview

Swagger is an open-source toolset for designing, building, and documenting RESTful APIs. It provides a standardized way to describe API endpoints, making it easier for developers to understand how to interact with an API. 

### Key Components

1. **Swagger Specification (OpenAPI Specification)**:
   - At the core of Swagger is the **OpenAPI Specification (OAS)**, a format for describing REST APIs.
   - The specification is usually written in a JSON file, detailing endpoints, request/response formats, parameters, authentication, and other metadata.

2. **Swagger UI**:
   - **Swagger UI** is a web-based interface that generates interactive documentation from the OpenAPI spec.
   - It visualizes the API’s endpoints and allows users to test them directly from the browser, which is great for exploring and validating API behavior without needing external tools.

3. **Swagger Editor**:
   - The **Swagger Editor** is an in-browser editor for creating and editing OpenAPI specifications.
   - It provides syntax highlighting, autocomplete, and validation to help structure the API specifications correctly.

### Benefits of Using Swagger

- **Standardized Documentation**: Makes your API easier to understand and use;
- **Interactive Testing**: Lets users test endpoints directly from the documentation;
- **Automated Code Generation**: Saves time by creating boilerplate code for various languages and platforms;
- **Error Reduction**: Standardized specification ensures fewer discrepancies between documentation and actual implementation.


## Getting Started - Documenting a Spring REST API 

### Setting up `springdoc-openapi`

**pom.xml**
```xml
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.6.0</version>
		</dependency>
```

### OpenAPI Description Path

After setting up the dependency correctly, we can run our application and find the OpenAPI descriptions at `/v3/api-docs`, which is the `default path`:

```bash
http://localhost:8080/v3/api-docs
```

We can customize the path in application.properties using the `springdoc.api-docs` property:

```bash
springdoc.api-docs.path=/api-docs
```

Then, we’ll be able to access the docs at: 

```bash
http://localhost:8080/api-docs
```

### Important Checks Before Integration with Swagger UI

1. **Check the Controller Annotations**:
   - Make sure the controller classes are annotated with `@RestController` and `@RequestMapping("api//v1/<endpoints>")`.

2. **Check the Method Annotations**:
    - Make sure the methods are annotated with `@GetMapping`, `@PostMapping`, `@PutMapping`, or `@DeleteMapping`.
    - Double-check that every method in the controller has a path defined, even if it’s just / in the mapping annotation (e.g.: `@GetMapping("/")` or `@GetMapping`).

### Swagger UI

The springdoc-openapi dependency **already includes Swagger UI**, so we’re all set to access the API documentation at:

```bash
http://localhost:8080/swagger-ui/index.html
```

The springdoc-openapi library also supports [**swagger-ui properties**](https://springdoc.org/#swagger-ui-properties). 
These can be used as Spring Boot properties with the prefix springdoc.swagger-ui, and they allow us to customize the Swagger UI interface.

Example:

**application.properties**
```bash
# Set the path for the API documentation
springdoc.api-docs.path=/v3/api-docs
# Set the path for the Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
# Sort the operations by method
springdoc.swagger-ui.operationsSorter=method 
# Sort the operations by tags (alphabetically) 
springdoc.swagger-ui.tagsSorter=alpha
# Enable the filter bar
springdoc.swagger-ui.filter=true

# Example of tags: movie-controller, quote-controller, etc.
```

Also, we can add the `@OpenAPIDefinition `annotation to the main class to provide additional information about the API:

```java
@OpenAPIDefinition(
    info = @Info(
        title = "Movie Quotes API", 
        version = "1.0.0",
        description = "Famous quotes from movies.",
        contact = @Contact(name = "Tomás Santos", email = "tomas@tomas.com")
    )
)
@SpringBootApplication
public class Ex3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex3Application.class, args);
	}

}
```

Finnaly, we can access the Swagger UI at:

```bash 
http://localhost:8080/swagger-ui.html
```

## References

- [Swagger Official Website](https://swagger.io/)
- [Baeldung Tutorial](https://www.baeldung.com/spring-rest-openapi-documentation)
- [Spring Doc - Swagger Properties](https://springdoc.org/#swagger-ui-properties)
