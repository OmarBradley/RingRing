package olab.ringring.main.mymenu;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.gcm.KeywordSuccessReceiver;
import olab.ringring.gcm.RingRingGcmListenerService;
import olab.ringring.main.nav.MainNavigationFragment;
import olab.ringring.main.nav.visitor.MainNavigationVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.CloseDrawerVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetNavigationFragmentVisitor;
import olab.ringring.main.nav.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.nav.visitor.element.MainNavigationElement;
import olab.ringring.util.normalvisitor.element.NormalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.OnBackPressedVisitor;

public class MyMenuActivity extends AppCompatActivity
        implements MainNavigationElement, NormalActivityElement {

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter MainNavigationFragment navigationView;

    private NormalActivityVisitor onBackPressedVisitor;
    private KeywordSuccessReceiver keywordSuccessReceiver;
    private IntentFilter keywordSuccessDialogFilter = new IntentFilter(RingRingGcmListenerService.KEYWORD_SUCCESS_DIALOG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);
        ButterKnife.bind(this);
        initKeywordSuccessReceiver();
        setSupportActionBar(toolbar);
        this.accept(new SetNavigationFragmentVisitor());
        this.accept(new SetToggleVisitor());
        initOnBackPressedVisitor();
        attachMyMenuFragment();
    }

    private void initKeywordSuccessReceiver(){
        keywordSuccessReceiver = new KeywordSuccessReceiver(this);
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(MainNavigationVisitor visitor) {
        visitor.visit(this, this);
    }

    private void initOnBackPressedVisitor(){
        onBackPressedVisitor = new OnBackPressedVisitor();
    }

    @Override
    public void onBackPressed() {
        this.accept(onBackPressedVisitor);
        this.accept(new CloseDrawerVisitor());
    }

    private void attachMyMenuFragment(){
        MyMenuFragment myMenuFragment = new MyMenuFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_my_menu_fragment, myMenuFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(keywordSuccessReceiver, keywordSuccessDialogFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(keywordSuccessReceiver);
    }
}
