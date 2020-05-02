package com.example.sqliteclass1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VisitorAdapter extends CursorAdapter {

    Context context;
    int layoutResourceId;
    Cursor data = null;

    public VisitorAdapter(Context context, Cursor data,int layoutResourceId) {
        super(context, data,layoutResourceId);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        VisitorHolder holder = new VisitorHolder();
        holder.visitorName = (TextView) view.findViewById(R.id.listview_visitor_name);
        holder.apartmentNumber = (TextView) view.findViewById(R.id.listview_apartment_no);
        holder.visitorType = (TextView) view.findViewById(R.id.listview_visitor_type);
        holder.checkingDate = (TextView) view.findViewById(R.id.listview_checking_date);

        holder.visitorName.setText(cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_nombre)));
        holder.apartmentNumber.setText("Apartment No: " + cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_apartmentId)));
        holder.visitorType.setText(cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_visitorType)));
        holder.checkingDate.setText(cursor.getString(cursor.getColumnIndexOrThrow(DefDB.col_checkinDate)));
    }

    /*
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
    } */

    static class VisitorHolder {
        TextView visitorName;
        TextView apartmentNumber;
        TextView visitorType;
        TextView checkingDate;
    }
}
