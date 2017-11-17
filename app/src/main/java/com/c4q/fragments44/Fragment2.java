package com.c4q.fragments44;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Note: You are free to decorate your fragments as you wish.
 */
public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment2, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            String previousInput = args.getString(Constants.INPUT_KEY);
            if (previousInput != null && !previousInput.isEmpty()) {
                ((TextView) view.findViewById(R.id.previous_input)).setText(previousInput);
            }
        }

        view.findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                String userInput = ((EditText) view.findViewById(R.id.fragment_input2)).getText().toString();
                ((MainActivity) getActivity()).toNextFragment(userInput, 3);
            }
        });
    }
}
