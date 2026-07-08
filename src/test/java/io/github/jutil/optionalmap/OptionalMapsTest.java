package io.github.jutil.optionalmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalMapsTest {

    @Test
    void getOptionalReturnsValueForPresentNonNullValue() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Ada");

        Optional<String> result = OptionalMaps.getOptional(map, "name");

        assertEquals(Optional.of("Ada"), result);
    }

    @Test
    void getOptionalReturnsEmptyForAbsentKey() {
        Map<String, String> map = new HashMap<String, String>();

        Optional<String> result = OptionalMaps.getOptional(map, "missing");

        assertFalse(result.isPresent());
    }

    @Test
    void getOptionalReturnsEmptyForPresentNullValue() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", null);

        Optional<String> result = OptionalMaps.getOptional(map, "name");

        assertTrue(map.containsKey("name"));
        assertFalse(result.isPresent());
    }

    @Test
    void getOptionalRejectsNullMap() {
        assertThrows(NullPointerException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                OptionalMaps.getOptional(null, "key");
            }
        });
    }

    @Test
    void wrapRejectsNullMap() {
        assertThrows(NullPointerException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                OptionalMaps.wrap(null);
            }
        });
    }

    @Test
    void wrappedOptionalMapDelegatesNormalMapOperations() {
        Map<String, Integer> delegate = new LinkedHashMap<String, Integer>();
        OptionalMap<String, Integer> map = OptionalMaps.wrap(delegate);

        assertTrue(map.isEmpty());
        assertNull(map.put("one", Integer.valueOf(1)));
        assertEquals(Integer.valueOf(1), delegate.get("one"));
        assertEquals(Integer.valueOf(1), map.get("one"));
        assertTrue(map.containsKey("one"));
        assertEquals(1, map.size());
        assertEquals(delegate.entrySet(), map.entrySet());

        assertEquals(Integer.valueOf(1), map.remove("one"));
        assertFalse(map.containsKey("one"));

        map.put("two", Integer.valueOf(2));
        map.put("three", Integer.valueOf(3));

        Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
        Map.Entry<String, Integer> firstEntry = entries.next();
        firstEntry.setValue(Integer.valueOf(4));
        assertEquals(Integer.valueOf(4), delegate.get(firstEntry.getKey()));

        assertEquals(2, map.size());
        map.clear();
        assertTrue(delegate.isEmpty());
        assertTrue(map.entrySet().isEmpty());
    }

    @Test
    void wrappedOptionalMapReturnsOptionalValue() {
        Map<String, String> delegate = new HashMap<String, String>();
        delegate.put("name", "Grace");
        OptionalMap<String, String> map = OptionalMaps.wrap(delegate);

        assertEquals(Optional.of("Grace"), map.getOptional("name"));
    }
}
