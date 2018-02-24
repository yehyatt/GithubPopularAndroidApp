package yehyatt.com.yahyatitigithub.models;

import java.io.Serializable;

/**
 * Created by yehyatt on 2/24/18.
 */

public class ItemsModel implements Serializable
{
    private String full_name;
    private String description;
    private int forks;
    private float score;

    public String getFull_name()
    {
        return full_name;
    }

    public void setFull_name(String full_name)
    {
        this.full_name = full_name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getForks()
    {
        return forks;
    }

    public void setForks(int forks)
    {
        this.forks = forks;
    }

    public float getScore()
    {
        return score;
    }

    public void setScore(float score)
    {
        this.score = score;
    }
}
