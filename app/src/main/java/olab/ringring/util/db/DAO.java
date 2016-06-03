package olab.ringring.util.db;

import java.util.List;

/**
 * Created by 재화 on 2016-06-01.
 */
public interface DAO<T extends RowMapper> {
    public long insertData(T data);
    public List<T> searchDataColumns();
}
