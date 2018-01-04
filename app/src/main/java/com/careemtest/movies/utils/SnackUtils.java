package com.careemtest.movies.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;

/**
 * Created by wajahat.karim on 04/01/2018.
 */

public class SnackUtils {

    public static Snackbar showLoadingSnackbar(Activity ctx, String message)
    {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) ctx.findViewById(android.R.id.content)).getChildAt(0);
        Snackbar bar = Snackbar.make(viewGroup, message, Snackbar.LENGTH_INDEFINITE);
        //bar.getView().setBackgroundColor(Color.rgb(189,183,107));
        bar.show();
        return bar;
    }
}