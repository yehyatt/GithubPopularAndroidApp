package yehyatt.com.yahyatitigithub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import yehyatt.com.yahyatitigithub.models.ItemsModel;

public class GitDetailsActivity extends AppCompatActivity
{
    ItemsModel SelectedGit;
    TextView NameTextView;
    TextView DescriptionTextView;
    TextView NumberOfForksTextView;
    TextView ScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_details);
        NameTextView = (TextView) findViewById(R.id.NameTextView);
        DescriptionTextView = (TextView) findViewById(R.id.DescriptionTextView);
        NumberOfForksTextView = (TextView) findViewById(R.id.NumberOfForksTextView);
        ScoreTextView = (TextView) findViewById(R.id.ScoreTextView);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        SelectedGit = (ItemsModel) intent.getSerializableExtra("SelectedGit");
        PopulateFields(SelectedGit);
    }

    void PopulateFields(ItemsModel SelectedGit)
    {
        String NameString = "Name : " + SelectedGit.getFull_name();
        String DescriptionString = "Description : " + SelectedGit.getDescription();
        String ForksString = "Number of forks : " + SelectedGit.getForks();
        String ScoreString = "Score : " + SelectedGit.getScore();
        NameTextView.setText(NameString);
        DescriptionTextView.setText(DescriptionString);
        NumberOfForksTextView.setText(ForksString);
        ScoreTextView.setText(ScoreString);
    }
}
