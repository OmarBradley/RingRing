package olab.ringring.main.home.chat.moudle.localdb;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.annimon.stream.function.Supplier;
import com.annimon.stream.function.UnaryOperator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by 재화 on 2016-06-01.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SQLiteTransactionTemplate {

    private static final class SingletonHolder {
        private static final SQLiteTransactionTemplate INSTANCE = new SQLiteTransactionTemplate();
    }

    public static SQLiteTransactionTemplate getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void executeTransaction(Supplier<SQLiteDatabase> beforeUseDatabase, UnaryOperator<SQLiteDatabase> afterUseDatabase) {
        SQLiteDatabase db = beforeUseDatabase.get();
        try {
            db.beginTransaction();
            afterUseDatabase.apply(db).setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("sqlite exception", e.toString());
        } finally {
            db.endTransaction();
        }
    }
}
