package com.example.sqliteclass1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VisitorAdapter extends ArrayAdapter {

    Context context;
    int layoutResourceId;
    Visitor[] data = null;

    public VisitorAdapter(Context context, int layoutResourceId, Visitor[] data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        VisitorHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new VisitorHolder();
            holder.visitorName = (TextView) row.findViewById(R.id.listview_visitor_name);
            holder.apartmentNumber = (TextView) row.findViewById(R.id.listview_apartment_no);
            holder.visitorType = (TextView) row.findViewById(R.id.listview_visitor_type);
            holder.checkingDate = (TextView) row.findViewById(R.id.listview_checking_date);
            row.setTag(holder);
        } else {
            holder = (VisitorHolder) row.getTag();
        }

        Visitor visitor = data[position];
        holder.visitorName.setText(visitor.getName());
        holder.apartmentNumber.setText("Apartment No: " + visitor.getApartmentId());
        holder.visitorType.setText(visitor.getVisitorType());
        holder.checkingDate.setText(visitor.getCheckinDate().toString());

        return row;
    }

    static class VisitorHolder {
        TextView visitorName;
        TextView apartmentNumber;
        TextView visitorType;
        TextView checkingDate;
    }
}
