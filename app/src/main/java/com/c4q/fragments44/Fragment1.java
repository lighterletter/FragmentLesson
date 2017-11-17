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
 * This is a Fragment.
 *
 * By extending the Fragment class, we gain access to a specific lifecycle for a layout that you yourself create.
 *
 * In this case, instead of onCreate(), we Override the onCreateView to inflate the layout that we want.
 *
 * Once the layout has been defined, we Override onViewCreated() which is where we have access to our view and can
 * reach any specific layout widgets within our view.
 *
 * From here on out we can treat our fragment as if it was our activity, gaining access to context methods, like:
 *
 * getActivity() to reach the parent activity
 *
 * getContext() for context
 *
 * getView() to reach the view. (You must call this AFTER onCreateView() has been called.
 */
public class Fragment1 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment1, container, false); // here we inflate our view
        // in class we will talk about how this can be done more efficiently.
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments(); // this is very much like getting an intent and getting information from the calling context
        if (args != null) {
            String previousInput = args.getString(Constants.INPUT_KEY); // we defined this key as a final static in the Constants class. It is a String object.
            if (previousInput != null && !previousInput.isEmpty()) {
                ((TextView) view.findViewById(R.id.previous_input)).setText(previousInput);
            }
        }

        view.findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                String userInput = ((EditText) view.findViewById(R.id.fragment_input1)).getText().toString();
                if (getActivity() instanceof MainActivity) { // If we create another activity and create this fragment from within we'd crash when trying to cast which is why we have this gate here.
                    ((MainActivity) getActivity()).toNextFragment(userInput, 2); // get the calling activity cast it to MainActivity and call the toNextFragmentMethod we built.
                }
            }
        });
    }
}
