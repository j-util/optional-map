[![MvnRepository](https://badges.mvnrepository.com/badge/io.github.j-util/optional-map/badge.svg?label=MvnRepository&color=green)](https://mvnrepository.com/artifact/io.github.j-util/optional-map)

# optional-map

Small Java 8+ utility for null-safe `Optional` access to `Map` values.

Replace:

```java
Optional<String> name = Optional.ofNullable(map.get("user-1"));
```

with:

```java
Optional<String> name = OptionalMaps.getOptional(map, "user-1");
```

## Installation

Maven:

```xml
<dependency>
  <groupId>io.github.j-util</groupId>
  <artifactId>optional-map</artifactId>
  <version>1.0.0</version>
</dependency>
```

[Maven Central](https://central.sonatype.com/artifact/io.github.j-util/optional-map)

## Usage

Use `OptionalMaps.getOptional(map, key)` when you only need one lookup:

```java
Map<String, String> names = new HashMap<String, String>();
names.put("user-1", "Ada");

Optional<String> name = OptionalMaps.getOptional(names, "user-1");
```

Use `OptionalMaps.wrap(map)` when you want a `Map` view with an extra
`getOptional` method:

```java
Map<String, String> names = new HashMap<String, String>();
OptionalMap<String, String> optionalNames = OptionalMaps.wrap(names);

optionalNames.put("user-1", "Ada");
Optional<String> name = optionalNames.getOptional("user-1");
```

## Null Values

`getOptional` returns `Optional.empty()` both when the key is absent and when the
key is present with a `null` value. You still need to use `containsKey` to
distinguish those cases.

## Build

```sh
./mvnw test
```
