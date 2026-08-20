# Changelog

All notable changes to this project will be documented in this file.

## 1.0.0 - 2026-07-08

### Added

- `OptionalMaps.getOptional(map, key)` for null-safe `Optional` access to map
  values.
- `OptionalMaps.wrap(map)` for a live `OptionalMap` view that delegates ordinary
  `Map` operations to the wrapped map.
- Java 8-compatible source, Javadoc, and binary artifacts published to Maven
  Central.
