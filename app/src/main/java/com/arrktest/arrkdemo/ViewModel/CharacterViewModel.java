package com.arrktest.arrkdemo.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.arrktest.arrkdemo.server.CharacterInfo;

import java.util.List;

public class CharacterViewModel extends ViewModel {
    private MutableLiveData<List<CharacterInfo>> characters;
    public LiveData<List<CharacterInfo>> getCharacters() {
        if (characters == null) {
            characters = new MutableLiveData<List<CharacterInfo>>();
        }
        return characters;
    }

    public void loadCharacters(List<CharacterInfo> charactersList) {
        characters.postValue(charactersList);
    }


}
