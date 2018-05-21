package org.yq.mybook.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.yq.mybook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {

    private static List<DiscoveryItem> items = new ArrayList<>();

    static {
        items.add(new DiscoveryItem(R.drawable.ic_paihangbang,"排行榜"));
        items.add(new DiscoveryItem(R.drawable.ic_zhutishudan,"主题书单"));
        items.add(new DiscoveryItem(R.drawable.ic_fenlei,"分类"));
    }
    private RecyclerView mDiscoveryRecyclerView;

    private DiscoveryItemAdapter mItemAdapter;





    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        mDiscoveryRecyclerView = (RecyclerView) view.findViewById(R.id.discovery_recycler_view);
        mDiscoveryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        updateUI();
        return view;
    }

    private void updateUI() {
        mItemAdapter = new DiscoveryItemAdapter(DiscoveryFragment.items);
        mDiscoveryRecyclerView.setAdapter(mItemAdapter);
    }

    private class DiscoveryItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView itemIcon;

        private TextView itemDesc;


        public DiscoveryItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.discovery_item, parent, false));
            itemIcon = itemView.findViewById(R.id.discovery_item_icon);
            itemDesc = itemView.findViewById(R.id.discovery_item_desc);
            itemView.setOnClickListener(this);
        }


        public void bind(DiscoveryItem item) {

            itemIcon.setImageResource(item.iconResId);
            itemDesc.setText(item.desc);
        }

        @Override
        public void onClick(View v) {
            System.out.println("ddddddddddddddddddddddd");
            Toast.makeText(getActivity(),itemDesc.getText()+" on click!",Toast.LENGTH_LONG).show();
        }
    }


    private class DiscoveryItemAdapter extends RecyclerView.Adapter<DiscoveryItemHolder> {

        private List<DiscoveryItem> mDiscoveryItems;

        public DiscoveryItemAdapter(List<DiscoveryItem> mDiscoveryItems) {
            this.mDiscoveryItems = mDiscoveryItems;
        }

        @Override
        public DiscoveryItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());


            return new DiscoveryItemHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(DiscoveryItemHolder holder, int position) {

            DiscoveryItem item = mDiscoveryItems.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return mDiscoveryItems.size();
        }
    }

    public static class DiscoveryItem {

        private int iconResId;

        private String desc;

        public DiscoveryItem(int iconResId, String desc) {
            this.iconResId = iconResId;
            this.desc = desc;
        }

        public int getIconResId() {
            return iconResId;
        }

        public void setIconResId(int iconResId) {
            this.iconResId = iconResId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
