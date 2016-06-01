package olab.ringring.main.home.chat.moudle.localdb;

import android.database.Cursor;

import java.util.List;

/**
 * Created by 재화 on 2016-06-01.
 */
public interface DAO<T extends RowMapper> {
    public long insertData(T data);
    public List<T> searchDataColumns();
}
