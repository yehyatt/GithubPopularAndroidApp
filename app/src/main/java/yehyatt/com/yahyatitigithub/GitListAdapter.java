package yehyatt.com.yahyatitigithub;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import yehyatt.com.yahyatitigithub.models.ItemsModel;

/**
 * Created by yehyatt on 2/24/18.
 */
public class GitListAdapter extends RecyclerView.Adapter<GitListAdapter.MyViewHolder>
{
    private Activity mActivity;
    private List<ItemsModel> listOfItems;

    GitListAdapter(Activity activity, List<ItemsModel> cloudList)
    {
        listOfItems = cloudList;
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView GitFullNameTextView;
        public TextView GitDescriptionTextView;
        public RelativeLayout CellBody;

        public MyViewHolder(View v)
        {
            super(v);
            GitFullNameTextView = (TextView) v.findViewById(R.id.GitFullNameTextView);
            GitDescriptionTextView = (TextView) v.findViewById(R.id.GitDescriptionTextView);
            CellBody = (RelativeLayout) v.findViewById(R.id.CellBody);

        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        holder.GitFullNameTextView.setText(listOfItems.get(position).getFull_name());
        holder.GitDescriptionTextView.setText(listOfItems.get(position).getDescription());

        holder.CellBody.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((GitListActivity) mActivity).ItemSelected(listOfItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listOfItems.size();
    }


}