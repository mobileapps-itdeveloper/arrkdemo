package com.arrktest.arrkdemo.classes;

import com.arrktest.arrkdemo.Activities.CharacterListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(CharacterListActivity listActivity);

}
