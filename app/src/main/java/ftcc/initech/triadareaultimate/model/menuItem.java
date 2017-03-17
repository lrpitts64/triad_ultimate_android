package ftcc.initech.triadareaultimate.model;

/**
 * Created by grturner on 3/16/17.
 */

public class menuItem {
    private String mTitle;
    private int mIcon;

    public menuItem(String title, int Icon) {
        mTitle = title;
        mIcon = Icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
