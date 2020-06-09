package com.pheonix.yujapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    List<String> list = new ArrayList<String>();
    Context context;
    LayoutInflater inflater;
    String [] firstNameList, salaryList, ageList;

    EmployeeAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    EmployeeAdapter(Context context, String[] firstNameList, String[] salaryList, String[] ageList) {
        this.context = context;
        this.firstNameList = firstNameList;
        this.salaryList = salaryList;
        this.ageList = ageList;
    }

    @Override
    public int getCount() {
        return firstNameList.length;
    }

    @Override
    public Object getItem(int position) {
        return firstNameList[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View gridView = view;

        if(view == null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.employee_grid_layout, null);
        }

        TextView employeeTextView =  gridView.findViewById(R.id.employeeTextView);
        employeeTextView.setText(firstNameList[position]);

        TextView salaryTextView =  gridView.findViewById(R.id.salaryTextView);
        salaryTextView.setText(salaryList[position]);

        TextView ageTextView =  gridView.findViewById(R.id.ageTextView);
        ageTextView.setText(ageList[position]);

        return gridView;
    }
}
