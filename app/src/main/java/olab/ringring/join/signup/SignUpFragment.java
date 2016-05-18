package olab.ringring.join.signup;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pinball83.maskededittext.MaskedEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import olab.ringring.R;
import olab.ringring.join.signup.partnerauthorization.PartnerConnectingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @Bind(R.id.edit_sign_up_password_confirm) MaskedEditText test;



    public SignUpFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        test.setMaskIconCallback(()-> {
            Intent intent = new Intent(getContext(), PartnerConnectingActivity.class);
            startActivity(intent);
        });
        return view;
    }

}
