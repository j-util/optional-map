package io.github.jutil.optionalmap;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Utility methods for reading {@link Map} values as {@link Optional}.
 *
 * <p>The methods in this class keep the normal {@link Map} contract intact.
 * They do not provide separate absent-versus-null semantics: a missing key and
 * a key mapped to {@code null} both produce {@link Optional#empty()}. Use
 * {@link Map#containsKey(Object)} when that distinction matters.
 */
public final class OptionalMaps {

    private OptionalMaps() {
    }

    /**
     * Returns the value for the key from the given map as an {@link Optional}.
     *
     * <p>If {@link Map#get(Object)} returns {@code null}, this method returns
     * {@link Optional#empty()}. This includes both absent keys and keys
     * explicitly mapped to {@code null}.
     *
     * @param map the map to read from
     * @param key the key whose associated value is requested
     * @param <K> the key type
     * @param <V> the value type
     * @return an optional containing the non-null value, or an empty optional
     * @throws NullPointerException if {@code map} is {@code null}
     */
    public static <K, V> Optional<V> getOptional(Map<K, V> map, K key) {
        Objects.requireNonNull(map, "map");
        return Optional.ofNullable(map.get(key));
    }

    /**
     * Wraps a map with an {@link OptionalMap} view.
     *
     * <p>The returned view delegates normal {@link Map} operations to the given
     * map. Changes made through the view are made to the wrapped map, and changes
     * made to the wrapped map are visible through the view.
     *
     * @param map the map to wrap
     * @param <K> the key type
     * @param <V> the value type
     * @return an optional-map view of {@code map}
     * @throws NullPointerException if {@code map} is {@code null}
     */
    public static <K, V> OptionalMap<K, V> wrap(Map<K, V> map) {
        return new OptionalMapView<K, V>(Objects.requireNonNull(map, "map"));
    }
}
