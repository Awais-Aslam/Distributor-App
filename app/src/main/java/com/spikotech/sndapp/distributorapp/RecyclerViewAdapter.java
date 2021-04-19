/*
 *
 *   Created Awais Aslam on 2/7/21 10:15 AM
 *   Copyright Ⓒ 2021. All rights reserved Ⓒ 2021 http://freefuninfo.com/​
 *   Last modified: 2/6/21 2:12 PM
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS...​ Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.spikotech.sndapp.distributorapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //Vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> titles){
        mNames = names;
        mTitles = titles;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onCreateViewHolder: called.");

        holder.txtcardname.setText(mNames.get(position));
        holder.txtCardTitle.setText(mTitles.get(position));

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtcardname, txtCardTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            txtCardTitle = itemView.findViewById(R.id.txtCardTitle);
            txtcardname = itemView.findViewById(R.id.txtcardname);
        }

    }
}
