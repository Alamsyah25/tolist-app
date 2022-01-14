package com.mobile.tolist_app.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mobile.tolist_app.R;
import com.mobile.tolist_app.helper.DatabaseHelper;
import com.mobile.tolist_app.model.Todo;

import java.util.ArrayList;

public class itemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Todo> arrayList;

    public itemAdapter(Context context, ArrayList<Todo> arrayList) {
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        convertView = layoutInflater.inflate(R.layout.todo_item, null);
        TextView titleTextView = convertView.findViewById(R.id.title);
        TextView dateTextView = convertView.findViewById(R.id.dateTitle);
        TextView timeTextView = convertView.findViewById(R.id.timeTitle);
        final ImageView delImageView = convertView.findViewById(R.id.delete);
        delImageView.setTag(position);

        delImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = (int) v.getTag();
                deleteItem(pos);
            }
        });

        Todo todo = arrayList.get(position);
        titleTextView.setText(todo.getTitle());
        dateTextView.setText(todo.getDate());
        timeTextView.setText(todo.getTime());
        return convertView;
    }

    private void deleteItem(int position) {
        deleteItemFromDb(arrayList.get(position).getId());
        arrayList.remove(position);
        notifyDataSetChanged();
    }

    private void deleteItemFromDb(int id) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.deleteData(id);
            toastMsg("Successfully Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            toastMsg("Oppss.. something went wrong please try again");
        }
    }

    private void toastMsg(String msg) {
        Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
}
