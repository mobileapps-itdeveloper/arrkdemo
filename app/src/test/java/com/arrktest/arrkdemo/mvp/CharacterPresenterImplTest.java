package com.arrktest.arrkdemo.mvp;

import com.arrktest.arrkdemo.server.CharacterInfo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CharacterPresenterImplTest {
    private  static List<CharacterInfo> NOTES = new ArrayList<CharacterInfo>();
    private  static List<CharacterInfo> EMPTY_NOTES = new ArrayList<>(0);

    @BeforeClass
    public static void beforeClass() {
        NOTES.add(new CharacterInfo("Test", "100","100","2014-12-20T21:17:56.891000Z"));
        NOTES.add(new CharacterInfo("Test2", "100","100","2014-12-20T21:17:56.891000Z"));
    }




    @Mock
    private StarWarIntractorImpl starWarIntractor;

    @Mock
    private MvpInterfaces.CharactersView mNotesView;


    private CharacterPresenterImpl characterPresenter;

    @Before
    public void setupNotesPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
        characterPresenter = new CharacterPresenterImpl(mNotesView,starWarIntractor);
    }

    @Test
    public void loadNotesFromRepositoryAndLoadIntoView() {
     //TODO
    }

    @Test
    public void clickOnFab_ShowsAddsNoteUi() {
       //TODO
    }

    @Test
    public void clickOnNote_ShowsDetailUi() {
      //TODO
    }

}
