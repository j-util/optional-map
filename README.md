# optional-map

Small Java 8+ utility for reading `Map` values as `Optional`.

`Map.get(key)` returns `null` when a key is absent. It can also return `null`
when the key is present and explicitly mapped to `null`, which makes simple
lookup code easy to misread.

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
key is present with a `null` value. Use `containsKey` when you need to
distinguish those cases.

## Build

```sh
./mvnw test
```
