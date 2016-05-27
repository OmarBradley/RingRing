package olab.ringring.util.functioninterface;

/**
 * Created by 재화 on 2016-05-26.
 */
public interface TriConsumer<T, U, V> {
    public void accept(T t, U u, V v);
}