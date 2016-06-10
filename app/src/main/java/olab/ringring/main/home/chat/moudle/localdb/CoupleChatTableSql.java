package olab.ringring.main.home.chat.moudle.localdb;

import olab.ringring.main.home.chat.moudle.ReadStatus;

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

    public static final String CHANGE_UNREAD_TO_READ = "UPDATE " + CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME + " SET " +
            CoupleChatDBConstant.CoupleChatTableColumn._IS_READ + " = " + ReadStatus.READ.getReadStatus() +
            " WHERE " + CoupleChatDBConstant.CoupleChatTableColumn._IS_READ + " = " + ReadStatus.UNREAD.getReadStatus();

    public interface UnreadCountSql {
        public static final String ROW_UNREAD_COUNT = "unread_count";
        public static final String COUNT_UNREAD = "SELECT COUNT(*) AS " + ROW_UNREAD_COUNT +
                " FROM " + CoupleChatDBConstant.COUPLE_CHAT_TABLE_NAME +
                " GROUP BY " + CoupleChatDBConstant.CoupleChatTableColumn._IS_READ +
                " HAVING " + CoupleChatDBConstant.CoupleChatTableColumn._IS_READ + " = " + ReadStatus.UNREAD.getReadStatus();
    }

}
