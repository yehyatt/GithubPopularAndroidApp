package yehyatt.com.yahyatitigithub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import yehyatt.com.yahyatitigithub.GitConstants.GitConstants;
import yehyatt.com.yahyatitigithub.RestUtils.RetroRestClient;
import yehyatt.com.yahyatitigithub.models.ItemsModel;
import yehyatt.com.yahyatitigithub.models.MainListModel;


/**
 * Created by yehyatt on 2/24/18.
 */
public class GitListActivity extends AppCompatActivity
{
    SharedPreferences preferencesSettings;
    RecyclerView ListView;
    GitListAdapter ListAdapter;
    ArrayList<ItemsModel> allListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_list);
        allListItems = new ArrayList<>();
        preferencesSettings = getSharedPreferences("USER_PREFERENCES", Context.MODE_PRIVATE);
        ListView = (RecyclerView) findViewById(R.id.ListView);
        getSupportActionBar().hide();
        getList();
    }


    public void ItemSelected(ItemsModel selectedItem)
    {
        Intent intent = new Intent(GitListActivity.this, GitDetailsActivity.class);
        intent.putExtra("SelectedGit", selectedItem);
        startActivity(intent);
    }


    public void getList()
    {
        if (IsConnectedToTheInternet())
        {
            final ProgressDialog dialog = ProgressDialog.show(this, "", "PLEASE WAIT");
            RetroRestClient.GitApiInterface service = RetroRestClient.getClient();
            Call<MainListModel> call = service.getAndroidPopular("android", GitConstants.SORT_STARS, GitConstants.ORDER_DESCENDING);
            call.enqueue(new Callback<MainListModel>()
            {
                @Override
                public void onResponse(Response<MainListModel> response)
                {
                    dialog.dismiss();
                    if (response.isSuccess())
                    {
                        MainListModel result = response.body();
                        List<ItemsModel> allGits = result.getItems();
                        populateList(allGits);
                    }
                    else
                    {
                        Toast.makeText(GitListActivity.this, R.string.ResponseFailure, Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Throwable t)
                {
                    dialog.dismiss();
                }
            });
        }
        else
        {
            Toast.makeText(GitListActivity.this, R.string.NoInternet, Toast.LENGTH_SHORT).show();
        }
    }


    public void populateList(final List<ItemsModel> itemsList)
    {


        if (itemsList.size() == 0)
        {
            ListView.setVisibility(View.INVISIBLE);
        }
        else
        {
            ListView.setVisibility(View.VISIBLE);

            // use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            ListView.setLayoutManager(mLayoutManager);
            ListAdapter = new GitListAdapter(this, itemsList);
            ListView.setAdapter(ListAdapter);
        }
    }

    public boolean IsConnectedToTheInternet()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null)
        {
            activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        else
        {
            return false;
        }
    }

}