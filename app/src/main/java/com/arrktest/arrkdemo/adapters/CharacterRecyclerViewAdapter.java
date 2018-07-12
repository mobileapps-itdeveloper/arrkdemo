package com.arrktest.arrkdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arrktest.arrkdemo.Activities.CharacterDetailActivity;
import com.arrktest.arrkdemo.Activities.CharacterListActivity;
import com.arrktest.arrkdemo.R;
import com.arrktest.arrkdemo.fragments.CharacterDetailFragment;
import com.arrktest.arrkdemo.server.CharacterInfo;

import java.util.List;

public class CharacterRecyclerViewAdapter
        extends RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder> {

    private final CharacterListActivity mParentActivity;
    private final List<CharacterInfo> characterInfoList;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CharacterInfo item = (CharacterInfo) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(CharacterDetailFragment.PARCELABLE, item);

                CharacterDetailFragment fragment = new CharacterDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.character_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, CharacterDetailActivity.class);
                Bundle arguments = new Bundle();
                arguments.putParcelable(CharacterDetailFragment.PARCELABLE, item);

                intent.putExtra(CharacterDetailFragment.PARCELABLE_EXTRA, arguments);
                context.startActivity(intent);
            }
        }
    };

    public CharacterRecyclerViewAdapter(CharacterListActivity parent,
                                        @NonNull List<CharacterInfo> characterInfoList,
                                        boolean twoPane) {
        this.characterInfoList = characterInfoList;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.txtCharacterName.setText(characterInfoList.get(position).getName());

        holder.itemView.setTag(characterInfoList.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return characterInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtCharacterName;

        ViewHolder(View view) {
            super(view);
            txtCharacterName = view.findViewById(R.id.txtCharacterName);
        }
    }
}
