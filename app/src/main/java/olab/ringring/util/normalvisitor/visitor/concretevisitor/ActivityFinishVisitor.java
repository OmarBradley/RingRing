package olab.ringring.util.normalvisitor.visitor.concretevisitor;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;

/**
 * Created by 재화 on 2016-06-08.
 */
public class ActivityFinishVisitor implements NormalActivityVisitor {

    private static final long TIMEOUT_BACK_KEY_DELAY = 2000;
    private boolean isBackPressed;

    Runnable changeIsPressedRunnable = this::setIsBackPressedToFalse;
    Handler handler = new Handler(Looper.getMainLooper());

    public ActivityFinishVisitor(){
        setIsBackPressedToFalse();
    }

    @Override
    public void visit(AppCompatActivity activity) {
        doBackPressed(activity);
    }


    private void doBackPressed(AppCompatActivity activity) {
        if (!isBackPressed) {
            isBackPressed = true;
            Toast.makeText(activity, "한번 더 누루면 종료됩니다", Toast.LENGTH_SHORT).show();
            handler.postDelayed(changeIsPressedRunnable, TIMEOUT_BACK_KEY_DELAY);
        } else {
            handler.removeCallbacks(changeIsPressedRunnable);
            activity.finish();
        }
    }

    private void setIsBackPressedToFalse(){
        isBackPressed = false;
    }
}
