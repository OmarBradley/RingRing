package olab.ringring.util.db;

import android.database.Cursor;

/**
 * Created by 재화 on 2016-06-11.
 */
public interface CursorMapper<T> {
    public T mapCursor(Cursor cursor);
}
