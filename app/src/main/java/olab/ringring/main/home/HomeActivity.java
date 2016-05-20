package olab.ringring.main.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;
import olab.ringring.R;
import olab.ringring.main.home.data.ChatContent;
import olab.ringring.main.home.view.chatview.adapter.ChatViewAdapter;
import olab.ringring.main.home.view.customview.ChatProfileView;
import olab.ringring.main.visitor.Visitor;
import olab.ringring.main.visitor.concretevisitior.NavigationVisitor;
import olab.ringring.main.visitor.concretevisitior.SetToggleVisitor;
import olab.ringring.main.visitor.element.MainActivityElement;
import olab.ringring.util.date.NowDateGetter;

public class HomeActivity extends AppCompatActivity
        implements MainActivityElement {

    public static final int USER_ID = 1;
    public static final int LOVER_ID = 2;
    public static final int CHAT_DAY_ID = 3;

    @Getter @Bind(R.id.toolbar) Toolbar toolbar;
    @Getter @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Getter @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.profile_view_user) ChatProfileView userProfileView;
    @Bind(R.id.profile_view_lover) ChatProfileView loverProfileView;
    @Bind(R.id.list_view_chat_message) RecyclerView chatMessageListView;
    @Bind(R.id.btn_user) Button userBtn;
    @Bind(R.id.btn_lover) Button loverBtn;
    @Bind(R.id.btn_chat_date) Button chatDateBtn;
    @Bind(R.id.edit_chat_message) EditText chatMessageEdit;

    ChatViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        deleteActionBarTitle();
        this.accept(new NavigationVisitor());
        this.accept(new SetToggleVisitor());
        setElevationInChatProfileView();
        setChatMessageListView();
        setOnClickListenerOnButtons();
        setButtonClickable();
    }

    private void deleteActionBarTitle(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void setElevationInChatProfileView(){
        userProfileView.setElevation(2.0f);
        loverProfileView.setElevation(2.0f);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this, this);
    }

    private void setChatMessageListView(){
        adapter = new ChatViewAdapter();
        chatMessageListView.setAdapter(adapter);
        chatMessageListView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setOnClickListenerOnButtons() {
        userBtn.setOnClickListener(view -> {
            ChatContent chatContent = new ChatContent();
            chatContent.setMessage(chatMessageEdit.getText().toString());
            chatContent.setSenderId("" + USER_ID);
            chatContent.setRegDate(new NowDateGetter().getChatTimeString());
            adapter.addMessage(chatContent);
            scrollLastItem(chatMessageListView);
        });
        loverBtn.setOnClickListener(view -> {
            ChatContent chatContent = new ChatContent();
            chatContent.setMessage(chatMessageEdit.getText().toString());
            chatContent.setSenderId("" + LOVER_ID);
            chatContent.setRegDate(new NowDateGetter().getChatTimeString());
            adapter.addMessage(chatContent);
            scrollLastItem(chatMessageListView);
        });
        chatDateBtn.setOnClickListener(view -> {
            ChatContent chatContent = new ChatContent();
            chatContent.setSenderId("" + CHAT_DAY_ID);
            chatContent.setRegDate(new NowDateGetter().getNowDayString());
            adapter.addMessage(chatContent);
            scrollLastItem(chatMessageListView);
        });
    }

    private void scrollLastItem(RecyclerView recyclerView) {
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    private void setButtonClickable() {
        userBtn.setEnabled(false);
        loverBtn.setEnabled(false);
        chatDateBtn.setEnabled(false);
        chatMessageEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() == 0) {
                    userBtn.setEnabled(false);
                    loverBtn.setEnabled(false);
                    chatDateBtn.setEnabled(false);
                } else {
                    userBtn.setEnabled(true);
                    loverBtn.setEnabled(true);
                    chatDateBtn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

}
