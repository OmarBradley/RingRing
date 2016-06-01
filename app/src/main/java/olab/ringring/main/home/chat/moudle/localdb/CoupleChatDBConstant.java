package olab.ringring.main.home.chat.moudle.localdb;

import android.provider.BaseColumns;

/**
 * Created by 재화 on 2016-06-01.
 */
public class CoupleChatDBConstant {

    public static final String DB_NAME = "couple_chat";
    public static final int DB_VERSION = 1;
    public static final String COUPLE_CHAT_TABLE_NAME = "couple_chat_table";


    public interface CoupleChatTableColumn extends BaseColumns {
        public static final String _CHAT_DATE = "_chat_data";
        public static final String _CHAT_MESSAGE = "_chat_message";
        public static final String _COUPLE_ID = "_couple_id";
        public static final String _RECEIVER_ID = "_receiver_id";
        public static final String _SENDER_ID = "_sender_id";
        public static final String _IS_READ = "_is_read";
    }

}
