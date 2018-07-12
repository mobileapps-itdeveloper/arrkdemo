package com.arrktest.arrkdemo.mvp;

import com.arrktest.arrkdemo.server.APIInterface;
import com.arrktest.arrkdemo.server.CharacterInfoResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class StarWarIntractorImpl implements MvpInterfaces.StarWarInteractor {
    Retrofit retrofitClient;
    OnFinishedListener listener;

    public StarWarIntractorImpl(Retrofit retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    @Override
    public void getStarWarCharacters(OnFinishedListener listener) {
        this.listener = listener;
        APIInterface apiInterface = retrofitClient.create(APIInterface.class);
        apiInterface.getCharacterInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setupRecyclerView, this::handleError);
    }

    private void setupRecyclerView(CharacterInfoResponse characterInfoResponse) {
        if (characterInfoResponse != null && characterInfoResponse.getResults() != null && characterInfoResponse.getResults().size() > 0) {
            listener.onSuccess(characterInfoResponse.getResults());
        } else {
            listener.onFailure(new Throwable("Data is Null or Empty list of StartWar characters."));
        }
    }

    public void handleError(Throwable t) {
        listener.onFailure(t);
    }


}
