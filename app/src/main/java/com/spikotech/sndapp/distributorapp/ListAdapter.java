/*
 *
 *   Created Awais Aslam on 2/7/21 10:15 AM
 *   Copyright Ⓒ 2021. All rights reserved Ⓒ 2021 http://freefuninfo.com/​
 *   Last modified: 2/6/21 6:32 PM
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ProgrammingViewHolder> {

    //arr
    private ArrayList<String> mShopNames = new ArrayList<>();
    private ArrayList<String> mBrands = new ArrayList<>();
    private ArrayList<String> mPrices = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();

    public ListAdapter(Context context, ArrayList<String> shopNames, ArrayList<String> Brands, ArrayList<String> Prices, ArrayList<String> Time )
    {
        mShopNames = shopNames;
        mBrands = Brands;
        mPrices = Prices;
        mTime = Time;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        holder.txtshopname.setText(mShopNames.get(position));
        holder.txtbrand.setText(mBrands.get(position));
        holder.txtPrice.setText(mPrices.get(position));
        holder.txtTime.setText(mTime.get(position));

    }

    @Override
    public int getItemCount() {
        return mShopNames.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imgcard;
        TextView txtshopname, txtbrand, txtPrice, txtTime;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgcard = (ImageView) itemView.findViewById(R.id.imgcard);
            txtshopname = (TextView) itemView.findViewById(R.id.txtshopname);
            txtbrand = (TextView) itemView.findViewById(R.id.txtbrand);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
        }
    }
}
