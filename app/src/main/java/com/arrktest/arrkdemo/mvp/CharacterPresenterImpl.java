package com.arrktest.arrkdemo.mvp;

import com.arrktest.arrkdemo.server.CharacterInfo;

import java.util.List;

/**
 * Created by khurram 
 */

public class CharacterPresenterImpl implements MvpInterfaces.CharacterPresenter,MvpInterfaces.StarWarInteractor.OnFinishedListener {

    private MvpInterfaces.CharactersView charactersView;
    private MvpInterfaces.StarWarInteractor starWarInteractor;

    public CharacterPresenterImpl(MvpInterfaces.CharactersView charactersView, MvpInterfaces.StarWarInteractor starWarInteractor) {
        this.charactersView = charactersView;
        this.starWarInteractor = starWarInteractor;
    }

    @Override
    public void refreshClicked() {
        if(charactersView !=null){
            charactersView.showProgress();
        }
        starWarInteractor.getStarWarCharacters(this);
    }

    @Override
    public void onDestroy() {
       charactersView =null;
    }


    @Override
    public void onSuccess(List<CharacterInfo> list) {
        if(charactersView !=null){
            charactersView.hideProgress();
            charactersView.loadList(list);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(charactersView !=null){
            charactersView.hideProgress();
            charactersView.handleError(t);
        }
    }
}
