package com.example.bottom_navigation;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends Fragment {
    @Test

    public void onClick() {
        ((MainActivity)getActivity()).replaceFragment(Gyoyang.newinstance());
    }
}