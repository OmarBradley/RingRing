package olab.ringring.join.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.pinball83.maskededittext.MaskedEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.main.home.HomeActivity;
import olab.ringring.util.NullCommand;

public class LoginFragment extends Fragment {

    // TODO: 2016-05-18 view들의 id 선언해주기..
    @Bind(R.id.edit_login_password) MaskedEditText editLoginPassword;
    @Bind(R.id.edit_login_email) EditText editLoginEmail;



    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        setOnClickListenerOnEditLoginPassword();
      /*  addTextChangedListenerOnEditLoginEmail();*/
        return view;
    }

    private void setOnClickListenerOnEditLoginPassword() {
        editLoginPassword.setMaskIconCallback(() -> {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }


    /*private void setOnClickListenerOnEditLoginPassword() {
        editLoginPassword.setMaskIconCallback(()->{
            editLoginPassword.setVisibility(View.INVISIBLE);

        });

    }*/

    /*private void addTextChangedListenerOnEditLoginEmail(){
        editLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 8){
                    editLoginPassword.setVisibility(View.VISIBLE);
                    editLoginPassword.setMaskIconCallback(()->{
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/


}
