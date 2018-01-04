package com.careemtest.movies.networking.callbacks;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by wajahat.karim on 28/11/2017.
 */

public abstract class CustomCallback<T> implements retrofit2.Callback<T>
{
    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (!call.isCanceled()) {
            if (response.isSuccessful() && response.code() == HTTP_OK)
            {
                onResult(call, response.body());
            }
            else {
                onError(call, new CustomException(response.code(), response.message(), response));
            }
        }
        else {
            onCancel(call);
        }
    }

    public abstract void onResult(Call<T> call, T body);
    public void onCancel(Call<T> call){}
    public void onNoInternetAvailable(Call<T> call){}
    public abstract void onError(Call<T> call, Throwable throwable);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!call.isCanceled()) { // Cancellation is not an error because we cancel it
            if (t instanceof UnknownHostException)
            {
                // No Internet
                onNoInternetAvailable(call);
            }
            else onError(call, t);
        }
        else {
            onCancel(call);
        }
    }
}
