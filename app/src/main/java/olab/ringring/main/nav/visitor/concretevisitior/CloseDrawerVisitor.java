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
public class CloseDrawerVisitor implements MainNavigationVisitor {

    @Override
    public void visit(MainNavigationElement element, AppCompatActivity activity) {
        if (element.getDrawer().isDrawerOpen(GravityCompat.START)) {
            element.getDrawer().closeDrawer(GravityCompat.START);
        }
    }

}
