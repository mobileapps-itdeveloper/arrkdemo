package com.arrktest.arrkdemo.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arrktest.arrkdemo.R;
import com.arrktest.arrkdemo.ViewModel.CharacterViewModel;
import com.arrktest.arrkdemo.adapters.CharacterRecyclerViewAdapter;
import com.arrktest.arrkdemo.classes.ArrkApplication;
import com.arrktest.arrkdemo.classes.Utility;
import com.arrktest.arrkdemo.mvp.CharacterPresenterImpl;
import com.arrktest.arrkdemo.mvp.MvpInterfaces;
import com.arrktest.arrkdemo.mvp.StarWarIntractorImpl;
import com.arrktest.arrkdemo.server.CharacterInfo;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class CharacterListActivity extends AppCompatActivity implements MvpInterfaces.CharactersView {

    @BindView(R.id.character_list)
    RecyclerView recyclerView;
    @BindView(R.id.emptyView)
    TextView emptyView;

    @Inject
    Retrofit retrofitClient;

    private KProgressHUD kProgressHUD;
    private boolean mTwoPane;
    private CharacterViewModel model;
    private MvpInterfaces.CharacterPresenter presenter;
    private MvpInterfaces.StarWarInteractor starWarInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        setUpButterKnifeAndDrager();
        checkTwoPaneDevice();
        setUpMvp();
        setViewModelComponent();
    }

    private void setUpButterKnifeAndDrager() {
        ButterKnife.bind(this);
        ((ArrkApplication) getApplication()).getNetComponent().inject(this);
    }

    private void checkTwoPaneDevice() {
        if (findViewById(R.id.character_detail_container) != null) {
            /** large-screen layouts (res/values-w900dp).*/
            mTwoPane = true;
        }
    }

    private void setViewModelComponent() {
        model = ViewModelProviders.of(this).get(CharacterViewModel.class);
        model.getCharacters()
                .observe(this, characterInfos -> {
                            emptyView.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);
                            setupRecyclerView(characterInfos);
        });
    }

    private void setUpMvp() {
        starWarInteractor = new StarWarIntractorImpl(retrofitClient);
        presenter = new CharacterPresenterImpl(this, starWarInteractor);
    }

    private void setupRecyclerView(@NonNull List<CharacterInfo> characterInfoList) {
        if (recyclerView != null)
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CharacterRecyclerViewAdapter(this, characterInfoList, mTwoPane));
    }

    @Override
    public void handleError(Throwable t) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (Utility.isNetworkAvailable(this)) {
                        presenter.refreshClicked();
                    } else {
                        Toast.makeText(this, R.string.internet_error, Toast.LENGTH_SHORT).show();
                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    dialog.dismiss();
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(t.getMessage() + "\n" + getString(R.string.try_again_message)).setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener).show();

    }

    @Override
    public void showProgress() {
        kProgressHUD = KProgressHUD.create(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getString(R.string.progress_message))
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void hideProgress() {
        kProgressHUD.dismiss();
    }

    @Override
    public void loadList(List<CharacterInfo> list) {
        model.loadCharacters(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_menu) {
            if (Utility.isNetworkAvailable(this)) {
                presenter.refreshClicked();
            } else {
                Toast.makeText(this, R.string.internet_error, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
