package olab.ringring.main.home.chat.moudle.localdb;

/**
 * Created by 재화 on 2016-06-01.
 */
public interface CoupleChatTableSql {
    public static final String DB_CREATE = "CREATE TABLE " + CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME + " ("
            + CoupleChatDBConstant.CoupleChatTableColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CoupleChatDBConstant.CoupleChatTableColumn._CHAT_DATE + " INTEGER,"
            + CoupleChatDBConstant.CoupleChatTableColumn._CHAT_MESSAGE + " TEXT,"
            + CoupleChatDBConstant.CoupleChatTableColumn._SENDER_ID + " TEXT,"
            + CoupleChatDBConstant.CoupleChatTableColumn._RECEIVER_ID + " TEXT,"
            + CoupleChatDBConstant.CoupleChatTableColumn._IS_READ + " INTEGER DEFAULT 0);";
}
