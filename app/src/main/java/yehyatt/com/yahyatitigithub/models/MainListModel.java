package yehyatt.com.yahyatitigithub.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yehyatt on 2/24/18.
 */

public class MainListModel
{
    private int totalCount;
    private boolean incompleteResults;
    private List<ItemsModel> items = new ArrayList<ItemsModel>();

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults()
    {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults)
    {
        this.incompleteResults = incompleteResults;
    }

    public List<ItemsModel> getItems()
    {
        return items;
    }

    public void setItems(List<ItemsModel> items)
    {
        this.items = items;
    }
}
