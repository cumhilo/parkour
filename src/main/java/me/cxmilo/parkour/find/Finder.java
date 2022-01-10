package me.cxmilo.parkour.find;

/**
 * Searches for an instance of the object specified in generic T with the key specified in generic K
 *
 * @param <T> The object which will return the {@link #find(Object) find}
 * @param <K> The key which is used to search for the object
 */
public interface Finder<T extends Findable<K>, K> {

    T find(K key);
}
