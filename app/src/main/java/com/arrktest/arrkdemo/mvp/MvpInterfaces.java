package com.arrktest.arrkdemo.mvp;


import com.arrktest.arrkdemo.server.CharacterInfo;

import java.util.List;

public interface MvpInterfaces {
    /**
     * Created by khurram on 10/03/18.
     */
    interface CharactersView {
        void showProgress();
        void hideProgress();
        void loadList(List<CharacterInfo> list);
        void handleError(Throwable t);
    }


    interface CharacterPresenter {

        void refreshClicked();
        void onDestroy();
    }



    interface StarWarInteractor {

        interface OnFinishedListener {
            void onSuccess(List<CharacterInfo> list);
            void onFailure(Throwable t);
        }

        void getStarWarCharacters(OnFinishedListener listener);
    }
}
