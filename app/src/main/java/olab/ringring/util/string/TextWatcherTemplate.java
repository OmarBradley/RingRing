package olab.ringring.util.string;

import android.text.Editable;
import android.text.TextWatcher;

import com.annimon.stream.function.Consumer;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by 재화 on 2016-06-13.
 */
@Data
@Accessors(chain = true)
public class TextWatcherTemplate {
    private static final Runnable NULL_RUNNABLE = ()->{};
    private static final Consumer<CharSequence> NULL_CONSUMER = (s) ->{};

    private Runnable beforeTextChanged = NULL_RUNNABLE;
    private Consumer<CharSequence> onTextChanged = NULL_CONSUMER;
    private Consumer<CharSequence> afterTextChanged = NULL_CONSUMER;

    public TextWatcher build() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextChanged.run();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextChanged.accept(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                afterTextChanged.accept(s);
            }
        };
    }
}