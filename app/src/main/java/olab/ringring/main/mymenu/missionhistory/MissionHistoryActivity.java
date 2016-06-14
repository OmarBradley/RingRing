package olab.ringring.main.mymenu.missionhistory;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.gcm.KeywordSuccessReceiver;
import olab.ringring.gcm.RingRingGcmListenerService;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.mymenu.missionhistory.customview.KeywordView;
import olab.ringring.network.NetworkManager;
import olab.ringring.network.request.home.HomeProtocol;
import olab.ringring.network.request.mymenu.MyMenuProtocol;
import olab.ringring.network.response.mymenu.history.SuccessKeywordData;
import olab.ringring.util.normalvisitor.element.NormalActivityElement;
import olab.ringring.util.normalvisitor.visitor.NormalActivityVisitor;
import olab.ringring.util.normalvisitor.visitor.concretevisitor.SetActionBarIconVisitor;

public class MissionHistoryActivity extends AppCompatActivity implements NormalActivityElement {

    private static final int INIT_KEYWORD_COUNT = 0;


    @Bind(R.id.view_keyword_pretty) KeywordView prettyKeyword;
    @Bind(R.id.view_keyword_call) KeywordView callKeyword;
    @Bind(R.id.view_keyword_good) KeywordView goodKeyword;
    @Bind(R.id.view_keyword_handsome) KeywordView handsomeKeyword;
    @Bind(R.id.view_keyword_love) KeywordView loveKeyword;
    @Bind(R.id.view_keyword_cute) KeywordView cuteKeyword;
    @Bind(R.id.view_keyword_good_night) KeywordView goodNightKeyword;
    @Bind(R.id.view_keyword_beating) KeywordView beatingKeyword;
    private KeywordSuccessReceiver keywordSuccessReceiver;
    private IntentFilter keywordSuccessDialogFilter = new IntentFilter(RingRingGcmListenerService.KEYWORD_SUCCESS_DIALOG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_history);
        ButterKnife.bind(this);
        initKeywordSuccessReceiver();
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this,R.drawable.actionbar_home_as_up_image)));
        initKeywordView();
    }

    private void initKeywordSuccessReceiver(){
        keywordSuccessReceiver = new KeywordSuccessReceiver(this);
    }

    @Override
    public void accept(NormalActivityVisitor visitor) {
        visitor.visit(this);
    }

    private void initKeywordView(){
        NetworkManager.getInstance().sendRequest(MyMenuProtocol.makeKeywordHistoryRequest(this), SuccessKeywordData.class , (request, result) -> {
            setKeywordView(prettyKeyword, result.getPrettyKeyword(), R.drawable.keyword_pretty_image);
            setKeywordView(callKeyword, result.getCallKeyword(), R.drawable.keyword_call_image);
            setKeywordView(goodKeyword, result.getGoodKeyword(), R.drawable.keyword_good_image);
            setKeywordView(handsomeKeyword, result.getHandsomeKeyword(), R.drawable.keyword_handsome_image);
            setKeywordView(loveKeyword, result.getLoveKeyword(), R.drawable.keyword_love_image);
            setKeywordView(cuteKeyword, result.getCuteKeyword(), R.drawable.keyword_cute_image);
            setKeywordView(goodNightKeyword, result.getGoodNightKeyword(), R.drawable.keyword_good_night_image);
            setKeywordView(beatingKeyword, result.getBeatingKeyword(), R.drawable.keyword_beating_image);
        }, (request, networkResponseCode, throwable) -> {
            Toast.makeText(this, networkResponseCode.getMessage(), Toast.LENGTH_SHORT).show();
            setKeywordView(prettyKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_pretty_image);
            setKeywordView(callKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_call_image);
            setKeywordView(goodKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_good_image);
            setKeywordView(handsomeKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_handsome_image);
            setKeywordView(loveKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_love_image);
            setKeywordView(cuteKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_cute_image);
            setKeywordView(goodNightKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_good_night_image);
            setKeywordView(beatingKeyword, INIT_KEYWORD_COUNT, R.drawable.keyword_beating_image);
        });
    }

    private void setKeywordView(KeywordView view, int successCount, @DrawableRes int keywordBackgroundImageRes){
        view.setKeywordImage(keywordBackgroundImageRes);
        view.setKeywordSuccessCountText(successCount+"");
    }

    @Override
    public void onBackPressed() {
        Intent pageMover = new Intent(this, MyMenuActivity.class);
        pageMover.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pageMover);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
