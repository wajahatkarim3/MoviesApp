package com.careemtest.movies.utils;

import com.careemtest.movies.models.MovieModel;

/**
 * Created by wajahat.karim on 04/01/2018.
 */

public class MyTextUtils {

    public static String getPosterUrl(MovieModel movieModel)
    {
        String url = "";
        url = AppConstants.HTTP.INSTANCE.getIMAGE_URL() + "w185" + movieModel.getPosterPath();
        return url;
    }
}
