# Skyswitch API

Provides an API for Togglz

## Prerequisites

Your application needs to be built on following prerequisites:

* Java 8
* Spring Boot
* Togglz

## Usage

Example configuration

```java
@SpringBootApplication
@Import(SkyswitchApiConfiguration.class)
public class MySpringBootApp {

	public static void main(final String[] args) {
		SpringApplication.run(MySpringBootApp.class, args);
	}

}

```

## API

Your application will publish ReST webservices secured by a token-based secret.
