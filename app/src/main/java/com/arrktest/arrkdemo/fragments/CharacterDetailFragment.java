package com.arrktest.arrkdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arrktest.arrkdemo.R;
import com.arrktest.arrkdemo.classes.Utility;
import com.arrktest.arrkdemo.server.CharacterInfo;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CharacterDetailFragment extends Fragment {

    public static final String PARCELABLE = "Parcelable";
    public static final String PARCELABLE_EXTRA = "ParcelableExtra";
    private CharacterInfo characterInfo;

    public CharacterDetailFragment() {
    }

    @BindView(R.id.txtCharName)
    TextView txtCharName;
    @BindView(R.id.txtCharMass)
    TextView txtCharMass;
    @BindView(R.id.txtDateTime)
    TextView txtDateTime;
    @BindView(R.id.txtCharHeight)
    TextView txtCharHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(PARCELABLE)) {
            characterInfo =getArguments().getParcelable(PARCELABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_detail, container, false);
        ButterKnife.bind(this,rootView);
        if (characterInfo != null) {
            txtCharName.setText(characterInfo.getName());
            txtCharMass.setText(characterInfo.getMass()+" Kg");
            txtDateTime.setText(characterInfo.getCreated());
            txtCharHeight.setText(Utility.getMetersFromCM(Integer.parseInt(characterInfo.getHeight()))+" Metres");
        }
        return rootView;
    }
}
