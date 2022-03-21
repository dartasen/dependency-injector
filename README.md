<div align="center">
	<h1>Simple DI Container</h1>
	<strong><i>Java Pro Project - Clément </i></strong>
    <br></br>
</div>

# Introduction

Dependency injector is a simple lightweight `Dependency injector` framework you can use for small projects. The goal is to provide a simple framework to implement Inversion of Control pattern.


## Feature Highlights

+ Support for `field`, `setter` injections (*constructors coming soon*)
+ `@Autowired` annotation to inject specific dependency
+ Support for interface (besides marker java interfaces like Serializable, Cloneable, ...), base class in the dependency map
+ Package scanning for annotated class (with `@Component` annotation)
+ Support for different lifecycle :
    * Singleton (*DEFAULT*): Only one instance of the component will be lazy created in the framework
    * Instance : The framework will instanciate another component each time
  

## Usage

As advanced as the library may sound, in reality using the library is super simple.

### Manually register type as itself

```java
Container container = new Container();
container.registerType(Sample.class);
```

> Will register a type as itself in the dependency map

### Manually register both abstract type and implementation

```java
Container container = new Container();
container.registerType(AbstractSample.class, SampleImpl.class);
```

> Will register a type as it's abstract representation in the dependency map

### Manually register with specific options

```java
Container container = new Container();
container.registerType(Sample.class, EnumSet.of(RegisterOptions.AS_SELF, RegisterOptions.AS_IMPLEMENTED_INTERFACES));
```

Available `RegisterOptions` :
* `AS_SELF` : Register class as itself
* `AS_EXTENDED_TYPE` : Register class as its extended type (mother class)
* `AS_FIRST_INTERFACE` : Register class as his first valid interface declared
* `AS_IMPLEMENTED_INTERFACES` : Register class as its valid interfaces
* `DEFAULT` : Combine *AS_SELF, AS_EXTENDED_TYPE, AS_IMPLEMENTED_INTERFACES*

> Invalid interfaces that will not be registered by the framework : *Serializable*, *Cloneable*, *Remote*, *EventListener*

### Recursive dependency scan in a package

```java
Container container = new Container();
container.scan(PACKAGE_NAME);
//or
container.scan(Main.class);
```

### Manually instanciate a type with injections

```java
Container container = new Container();
Injector injector = new Injector(container);

// Do your registers here
container.scan(Main.class);

Sample sampleInstance = injector.get(Sample.class);
```

> Get will instanciate a type and inject annotated dependency to it

In addition, feel free to look through `test folder` for some ideas 😉.

## Known bugs

- Register an existing interface will override the implementation link in the dependency container
- ...

## Contributions

Feel free to implement new features, make bug fixes or suggestions so long as they are accompanied by an issue with a clear description of the pull request.