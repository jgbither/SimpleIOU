package com.jgbither.android.simpleiou;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jgbither.android.simpleiou.IOUData.IOU;
import com.jgbither.android.simpleiou.IOUData.IOUContainer;

import org.w3c.dom.Text;

import java.util.List;

public class SimpleIOU extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private IOUAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_iou);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.IOU_item_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();

    }


    private class IOUHolder extends RecyclerView.ViewHolder {

        private TextView mIOUTitle;
        private TextView mIOUDate;
        private TextView mIOUMoney;
        private IOU mIOU;

        public IOUHolder(View itemView) {
            super(itemView);

            mIOUTitle = (TextView) findViewById(R.id.iou_name_list_item);
            mIOUDate  = (TextView) findViewById(R.id.IOU_date_list_item);
            mIOUMoney = (TextView) findViewById(R.id.iou_money_list_item);
        }

        public void bindIOU(IOU iou){
            mIOU = iou;
            mIOUTitle.setText(mIOU.getmTitle());
            mIOUDate.setText(mIOU.getmDate().toString());
            mIOUMoney.setText(mIOU.getmMoney().toString());
        }
    }


    private class IOUAdapter extends RecyclerView.Adapter<IOUHolder>{

        private List<IOU> mIOUs;

        public IOUAdapter (List<IOU> IOUs){
            mIOUs = IOUs;
        }

        @Override
        public IOUHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getParent());
            View view = layoutInflater.inflate(R.layout.iou_list_item, parent, false);

            return new IOUHolder(view);
        }

        @Override
        public void onBindViewHolder(IOUHolder holder, int position) {
            IOU iou = mIOUs.get(position);
            holder.bindIOU(iou);
        }

        @Override
        public int getItemCount() {
            return mIOUs.size();
        }

        public void setCrimes(List<IOU> IOUs){
            mIOUs = IOUs;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        IOUContainer mIOUContainer = IOUContainer.getIOUContainer(this);
        List<IOU> mIOUs = mIOUContainer.getIOUs();

        if(mAdapter == null){
            mAdapter = new IOUAdapter(mIOUs);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setCrimes(mIOUs);
            mAdapter.notifyDataSetChanged();
        }
    }
}
