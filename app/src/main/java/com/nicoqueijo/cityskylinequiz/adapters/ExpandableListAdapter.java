package com.nicoqueijo.cityskylinequiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activities.QuizMenuActivity;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * The ExpandableListAdapter to display the hierarchy of menus for the quiz game mode.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    /**
     * Constructor for the ExpandableListAdapter
     *
     * @param context         activity hosting the RecyclerView
     * @param parentGameModes the menu items for the game mode
     * @param childGameModes  the submenu items for the game mode
     */
    public ExpandableListAdapter(Context context, List<Integer> parentGameModes, Map<Integer,
            List<Integer>> childGameModes) {
        this.mContext = context;
        this.mParentGameModes = parentGameModes;
        this.mChildGameModes = childGameModes;
    }

    private Context mContext;
    private List<Integer> mParentGameModes;
    private Map<Integer, List<Integer>> mChildGameModes;

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return mParentGameModes.size();
    }

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupPosition the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildGameModes.get(mParentGameModes.get(groupPosition)).size();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mParentGameModes.get(groupPosition);
    }

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildGameModes.get(mParentGameModes.get(groupPosition)).get(childPosition);
    }

    /**
     * Gets the ID for the group at the given position.
     *
     * @param groupPosition the position of the group for which the ID is wanted
     * @return the ID associated with the group
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Gets the ID for the given child within the given group.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group for which
     *                      the ID is wanted
     * @return the ID associated with the child
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the
     * underlying data.
     *
     * @return whether or not the same ID always refers to the same object
     * @see Adapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Gets a View that displays the given group.
     *
     * @param groupPosition the position of the group for which the View is
     *                      returned
     * @param isExpanded    whether the group is expanded or collapsed
     * @param convertView   the old view to reuse, if possible.
     * @param parent        the parent that this view will eventually be attached to
     * @return the View corresponding to the group at the specified position
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        int parentMode = (Integer) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                    (mContext.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_parent, null);
        }
        ImageView iconListParent = (ImageView) convertView.findViewById(R.id.icon_list_parent);
        TextView labelListParent = (TextView) convertView.findViewById(R.id.label_list_parent);
        labelListParent.setText(mContext.getString(parentMode));
        switch (getThemeId()) {
            case (R.style.AppThemeLight):
                switch (groupPosition) {
                    case (QuizMenuActivity.PARENT_MODE_TIMED):
                        iconListParent.setImageResource(R.drawable.ic_dark_timer_on);
                        break;
                    case (QuizMenuActivity.PARENT_MODE_UNTIMED):
                        iconListParent.setImageResource(R.drawable.ic_dark_timer_off);
                        break;
                    case (QuizMenuActivity.PARENT_MODE_EVERY_CITY):
                        iconListParent.setImageResource(R.drawable.ic_dark_all_inclusive);
                        break;
                }
                break;
            case (R.style.AppThemeDark):
                switch (groupPosition) {
                    case (QuizMenuActivity.PARENT_MODE_TIMED):
                        iconListParent.setImageResource(R.drawable.ic_light_timer_on);
                        break;
                    case (QuizMenuActivity.PARENT_MODE_UNTIMED):
                        iconListParent.setImageResource(R.drawable.ic_light_timer_off);
                        break;
                    case (QuizMenuActivity.PARENT_MODE_EVERY_CITY):
                        iconListParent.setImageResource(R.drawable.ic_light_all_inclusive);
                        break;
                }
        }
        return convertView;
    }

    /**
     * Gets a View that displays the data for the given child within the given
     * group.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child (for which the View is
     *                      returned) within the group
     * @param isLastChild   Whether the child is the last child within the group
     * @param convertView   the old view to reuse, if possible.
     * @param parent        the parent that this view will eventually be attached to
     * @return the View corresponding to the child at the specified position
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        int childMode = (Integer) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                    (mContext.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_child, null);
        }
        TextView labelListChild = (TextView) convertView.findViewById(R.id.label_list_child);
        labelListChild.setText(mContext.getString(childMode));
        return convertView;
    }

    /**
     * Whether the child at the specified position is selectable.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group
     * @return whether the child is selectable.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * Gets the resource id of the current theme.
     *
     * @return the resource id as an int.
     */
    private int getThemeId() {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
