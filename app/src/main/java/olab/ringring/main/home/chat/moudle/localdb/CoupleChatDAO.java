package olab.ringring.main.home.chat.moudle.localdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.annimon.stream.Stream;
import static com.annimon.stream.Collectors.toList;
import java.util.List;

import lombok.Cleanup;
import olab.ringring.init.application.RingRingApplication;
import olab.ringring.network.response.chat.ChatContent;

/**
 * Created by 재화 on 2016-06-01.
 */
public class CoupleChatDAO extends SQLiteOpenHelper implements DAO<ChatContent> {

    private static final class SingletonHolder{
        private static final CoupleChatDAO INSTANCE = new CoupleChatDAO();
    }

    public static CoupleChatDAO getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private CoupleChatDAO() {
        super(RingRingApplication.getContext(), CoupleChatDBConstant.DB_NAME, null, CoupleChatDBConstant.DB_VERSION);
    }

    public static final long INVALID_ID = -1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = CoupleChatTableSql.DB_CREATE;
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public long insertData(ChatContent data) {
        @Cleanup SQLiteDatabase db = getWritableDatabase();
        long resultValue = db.insert(CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME, null, data.mapRow());
        return resultValue;
    }

    @Override
    public List<ChatContent> searchDataColumns() {
        String[] columns = {CoupleChatDBConstant.CoupleChatTableColumn._ID, CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE,
                CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE, CoupleChatDBConstant.CoupleChatTableColumn._COUPLE_ID,
                CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID, CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID,
                CoupleChatDBConstant.CoupleChatTableColumn._IS_READ};
        SQLiteDatabase db = getReadableDatabase();
        String orderBySql = CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE + "ASC";
        Cursor cursor = db.query(CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME, columns, null, null, null, null, orderBySql);
        return Stream.of(cursor).map(c -> {
            ChatContent data = new ChatContent();
            data.setMessageIndex(c.getLong(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._ID)));
            data.setSendDate(c.getString(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE)));
            data.setMessage(c.getString(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE)));
            data.setCoupleId(c.getString(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._COUPLE_ID)));
            data.setReceiverId(c.getString(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID)));
            data.setSenderId(c.getString(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID)));
            data.setReadStatus(c.getInt(c.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._IS_READ)));
            return data;
        }).collect(toList());
    }

    public long getChatContentId(Long senderId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {CoupleChatDBConstant.CoupleChatTableColumn._ID};
        String selection = CoupleChatDBConstant.CoupleChatTableColumn._ID + "= ?";
        String[] selectionArgs = {"" + senderId};
        @Cleanup Cursor cursor = db.query(CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToNext()) {
            return INVALID_ID;
        }
        long id = cursor.getLong(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._ID));
        return id;
    }

}
