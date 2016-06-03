package olab.ringring.main.mymenu.missionhistory;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.mymenu.MyMenuActivity;
import olab.ringring.main.mymenu.missionhistory.customview.KeywordView;
import olab.ringring.util.actionbar.element.ActionBarElement;
import olab.ringring.util.actionbar.visitor.ActionbarVisitor;
import olab.ringring.util.actionbar.visitor.concretevisitor.SetActionBarIconVisitor;

public class MissionHistoryActivity extends AppCompatActivity implements ActionBarElement{

    @Bind(R.id.view_keyword_pretty) KeywordView prettyKeyword;
    @Bind(R.id.view_keyword_call) KeywordView callKeyword;
    @Bind(R.id.view_keyword_good) KeywordView goodKeyword;
    @Bind(R.id.view_keyword_handsome) KeywordView handsomeKeyword;
    @Bind(R.id.view_keyword_love) KeywordView loveKeyword;
    @Bind(R.id.view_keyword_cute) KeywordView cuteKeyword;
    @Bind(R.id.view_keyword_good_night) KeywordView goodNightKeyword;
    @Bind(R.id.view_keyword_beating) KeywordView beatingKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_history);
        ButterKnife.bind(this);
        this.accept(new SetActionBarIconVisitor(ContextCompat.getDrawable(this,R.drawable.actionbar_home_as_up_image)));
        initKeywordView();
    }

    @Override
    public void accept(ActionbarVisitor visitor) {
        visitor.visit(this);
    }

    private void initKeywordView(){
        seKeywordView(prettyKeyword, 2, R.drawable.keyword_pretty_image);
        seKeywordView(callKeyword, 12, R.drawable.keyword_call_image);
        seKeywordView(goodKeyword, 2, R.drawable.keyword_good_image);
        seKeywordView(handsomeKeyword, 3, R.drawable.keyword_handsome_image);
        seKeywordView(loveKeyword, 4, R.drawable.keyword_love_image);
        seKeywordView(cuteKeyword, 2, R.drawable.keyword_cute_image);
        seKeywordView(goodNightKeyword, 2, R.drawable.keyword_good_night_image);
        seKeywordView(beatingKeyword, 1, R.drawable.keyword_beating_image);

    }

    private void seKeywordView(KeywordView view, int successCount, @DrawableRes int keywordBackgroundImageRes){
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


}
