package com.c4q.fragments44;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Here we talk about fragments. A fragment is a modular UI component with it's own lifecycle. You'll see that in practice by following this application.
 *
 * You can read more about fragments in the class Fragment1.
 *
 * To begin, here in the MainActivity, we attach our layout: activity_main which contains three buttons and a FrameLayout.
 * It is this FrameLayout called fragment_container that we will use as an anchor-point to attach and swap out our fragments.
 * The layout itself will be replaced by whichever fragment we choose based on the button that we press.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener { // Side note: If you have multiple buttons it is generally best practice to implement the OnClick interface.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Since we only need click listeners for the buttons we simply set them right away
        findViewById(R.id.button_fragment1).setOnClickListener(this);
        findViewById(R.id.button_fragment2).setOnClickListener(this);
        findViewById(R.id.button_fragment3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fragment1:
                /**
                 *  On button click We begin the process of swapping a layout for a fragment by getting a fragment manager,
                 *  We have access to this through our activity's context.
                 *
                 *  If you're using a v4 fragment, you'd use getSupportFragmentManager(), you'd use getFragmentManager otherwise.
                 *
                 *  With our fragmentManager in hand, we call beginTransaction. this will give us a FragmentTransaction Object.
                 *  This object takes care of setting up the transaction process.
                 *
                 *  We call the replace method, pass in the layout id of our FrameLayout: fragment_container and an instance of the Fragment we'd like to initialize.
                 *
                 *  Once we call commit on the transaction object, the transaction will take place
                 *  and your fragment layout will inflate beginning its lifecycle.
                 *
                 *  Below is the process broken down: (Make sure that you're always importing the relevant class component (v4 or not) ).
                 */

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, new Fragment1());
                transaction.commit();
                break;
            case R.id.button_fragment2:
                /**
                 * For our second fragment, here is the same process done in one line.
                 */
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment2()).commit();
                break;
            case R.id.button_fragment3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment3()).commit();
                break;
        }
    }

    /**
     *  This method was created as a utility method that is called from our fragment to change the
     * fragment based on parameters that we ourselves delineate.
     *
     * @param s the user input string that we're passing to the fragment we're going to.
     * @param goToFragmentNumber
     */
    public void toNextFragment(String s, int goToFragmentNumber) {
        Bundle bundleToFragment = new Bundle();
        bundleToFragment.putString(Constants.INPUT_KEY, s); // bundle a string value
        Fragment fragment = null;
        switch (goToFragmentNumber) { // assign fragment object accordingly
            case 1:
                fragment = new Fragment1(); // assign to fragment 1 and so on.
                break;
            case 2:
                fragment = new Fragment2();
                break;
            case 3:
                fragment = new Fragment3();
                break;
        }
        if (fragment != null) { // just error checking in case the number passed in is not accounted for in our switch case.

            fragment.setArguments(bundleToFragment); // set our argument bundle to the fragment

            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null) // this allows us to press the back button to return to the previous fragment in the state we left off
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
