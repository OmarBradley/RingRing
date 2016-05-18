package olab.ringring.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.annimon.stream.function.Consumer;

/**
 * Created by 재화 on 2016-05-13.
 */
public class NextPageMover {

    // TODO: 2016-05-13  back key를 눌렀을 시 정책을 결정해야 한다.
    public static void goToNextPage(Activity presentPage, Class<?> nextPage) {
        Intent intent = new Intent(presentPage, nextPage);
        presentPage.startActivity(intent);
    }
}
