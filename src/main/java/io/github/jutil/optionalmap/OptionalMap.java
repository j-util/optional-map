package io.github.jutil.optionalmap;

import java.util.Map;
import java.util.Optional;

/**
 * A {@link Map} that also exposes Optional-based lookup.
 *
 * <p>This interface does not change the contract of {@link #get(Object)}.
 * It only adds {@link #getOptional(Object)} for callers that prefer to handle a
 * possibly missing value with {@link Optional}.
 *
 * <p>{@code getOptional} returns {@link Optional#empty()} both when the key is
 * absent and when the key is present with a {@code null} value. Callers that
 * need to distinguish those cases should use {@link #containsKey(Object)}.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface OptionalMap<K, V> extends Map<K, V> {

    /**
     * Returns the value for the key as an {@link Optional}.
     *
     * <p>If the wrapped map returns {@code null}, this method returns
     * {@link Optional#empty()}. This means an absent key and a present key with a
     * {@code null} value are represented the same way.
     *
     * @param key the key whose associated value is requested
     * @return an optional containing the non-null value, or an empty optional
     */
    Optional<V> getOptional(K key);
}
