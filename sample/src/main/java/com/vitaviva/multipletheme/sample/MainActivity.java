package com.vitaviva.multipletheme.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitaviva.multipletheme.util.ThemeManager;

public class MainActivity extends MultiThemedActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.findViewById(R.id.tv_menu_item_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopDialog.Builder.show(MainActivity.this);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter;
        recyclerView.setAdapter(adapter = new RecyclerView.Adapter<InnerViewHolder>() {
            @Override
            public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new InnerViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item,
                        parent, false));
            }

            @Override
            public void onBindViewHolder(InnerViewHolder holder, int position) {
                holder.tv.setText("MultiTheme Sample " + position);
            }

            @Override
            public int getItemCount() {
                return Integer.MAX_VALUE;
            }
        });
        adapter.notifyDataSetChanged();

    }

    class InnerViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public InnerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
