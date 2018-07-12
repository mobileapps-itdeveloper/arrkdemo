package com.arrktest.arrkdemo.server;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    /**
     * Returns the StarWar characters information
     * @return
     */
    @GET("api/people/")
    Observable<CharacterInfoResponse> getCharacterInformation();
}