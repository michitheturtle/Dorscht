package ch.turtlestack.dorscht.classPackage;

/**
 * Created by michael on 15.03.15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import java.util.List;


import ch.turtlestack.dorscht.R;
import ch.turtlestack.dorscht.backend.myApi.model.CategoryGroup;
import ch.turtlestack.dorscht.backend.myApi.model.CategoryItem;


public class ExpandableAdapter extends BaseExpandableListAdapter {

    protected List<CategoryGroup> catList;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context ctx;

    public ExpandableAdapter(List<CategoryGroup> catList, Context ctx) {

        this.itemLayoutId = R.layout.list_group_item_andy;
        this.groupLayoutId = R.layout.list_group_categories_andy;
        this.catList = catList;
        this.ctx = ctx;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getItems().get(childPosition).hashCode();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_group_item_andy, parent, false);
        }

        TextView itemName = (TextView) v
                .findViewById(R.id.lblListItem);


        CategoryItem det = catList.get(groupPosition).getItems().get(childPosition);

        itemName.setText(det.getName());


        return v;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = catList.get(groupPosition).getItems().size();
        System.out.println("Child for group [" + groupPosition + "] is [" + size + "]");
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return catList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return catList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return catList.get(groupPosition).hashCode();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_group_categories_andy, parent, false);
        }

        TextView lblListHeader = (TextView) v.findViewById(R.id.lblListHeader);
        // TextView groupDescr = (TextView) v.findViewById(R.id.groupDescr);


        CategoryGroup cat = catList.get(groupPosition);


        lblListHeader.setText(cat.getName());
        // groupDescr.setText(cat.getDescr());

        return v;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}