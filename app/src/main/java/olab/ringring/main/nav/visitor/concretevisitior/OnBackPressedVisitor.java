package olab.ringring.main.nav.visitor.concretevisitior;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import lombok.Setter;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.users.UsersProtocol;
import olab.ringring.network.response.users.SuccessLogout;

/**
 * Created by 재화 on 2016-06-08.
 */
public class OnBackPressedVisitor implements MainNavigationVisitor {

    private static final long TIMEOUT_BACK_KEY_DELAY = 2000;
    private boolean isBackPressed;

    Runnable changeIsPressedRunnable = this::setIsBackPressedToFalse;
    Handler handler = new Handler(Looper.getMainLooper());

    public OnBackPressedVisitor(){
        setIsBackPressedToFalse();
    }

    @Override
    public void visit(MainNavigationElement element, AppCompatActivity activity) {
        closeDrawer(element);
        doBackPressed(activity);
    }

    private void closeDrawer(MainNavigationElement element) {
        if (element.getDrawer().isDrawerOpen(GravityCompat.START)) {
            element.getDrawer().closeDrawer(GravityCompat.START);
        }
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
