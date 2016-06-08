package olab.ringring.main.home.chat.moudle.localdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

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
        while (cursor.moveToNext()) {
            SuccessSendChat data = new SuccessSendChat();
            data.setSendDate(cursor.getLong(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE)));
            data.setMessage(cursor.getString(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE)));
            data.setReceiverId(cursor.getString(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID)));
            data.setSenderId(cursor.getString(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID)));
            data.setReadStatus(cursor.getInt(cursor.getColumnIndex(CoupleChatDBConstant.CoupleChatTableColumn._IS_READ)));
            successSendChats.add(data);
        }
        return successSendChats;
    }

    public long getRecentTime(){
        List<SuccessSendChat> successSendChats = getDataRows();
        return Stream.of(successSendChats).map(SuccessSendChat::getSendDate).max(Long::compare).get();
    }
}
