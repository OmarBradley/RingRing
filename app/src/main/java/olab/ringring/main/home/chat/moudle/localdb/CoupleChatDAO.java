package olab.ringring.main.home.chat.moudle.localdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.BinaryOperator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static com.annimon.stream.Stream.*;

import lombok.Cleanup;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.network.response.home.SuccessSendChat;
import olab.ringring.util.db.DAO;


/**
 * Created by 재화 on 2016-06-01.
 */
public class CoupleChatDAO extends SQLiteOpenHelper implements DAO<SuccessSendChat> {

    private static final class SingletonHolder{
        private static final CoupleChatDAO INSTANCE = new CoupleChatDAO();
    }

    public static CoupleChatDAO getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private CoupleChatDAO() {
        super(RingRingApplication.getContext(), CoupleChatDBConstant.DB_NAME, null, CoupleChatDBConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = CoupleChatTableSql.DB_CREATE;
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public long insertData(SuccessSendChat data) {
        @Cleanup SQLiteDatabase db = getWritableDatabase();
        long resultValue = db.insert(CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME, null, data.mapRow());
        return resultValue;
    }

    @Override
    public List<SuccessSendChat> getDataRows() {
        List<SuccessSendChat> successSendChats = new ArrayList<>();
        String[] columns = {CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE, CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE,
                CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID, CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID,
                CoupleChatDBConstant.CoupleChatTableColumn._IS_READ};
        @Cleanup SQLiteDatabase db = getReadableDatabase();
        @Cleanup Cursor cursor = db.query(CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME, columns, null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                successSendChats.add(new SuccessSendChat().mapCursor(cursor));
            }
        } catch (Exception e) {
            Log.e("cursor exception", e.toString());
        }
        return successSendChats;
    }

    public long getRecentTime() {
        return Stream.of(getDataRows()).map(SuccessSendChat::getSendDate).max(Long::compare).orElse(0L);
    }

    public void changeUnReadToRead(){
        @Cleanup SQLiteDatabase db = getWritableDatabase();
        db.execSQL(CoupleChatTableSql.CHANGE_UNREAD_TO_READ);
    }

    public int getUnreadRowCount() {
        int readCount = 0;
        @Cleanup SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CoupleChatTableSql.UnreadCountSql.COUNT_UNREAD,null);
        while (cursor.moveToNext()) {
            readCount = cursor.getInt(cursor.getColumnIndex(CoupleChatTableSql.UnreadCountSql.ROW_UNREAD_COUNT));
        }
        return readCount;
    }



}
