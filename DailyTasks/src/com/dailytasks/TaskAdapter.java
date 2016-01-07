package com.dailytasks;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter {

	ArrayList<Task> tasks;
	Context context;
	LayoutInflater inflater;
	TaskAdapter (ArrayList<Task> tasks,Context context)
	{
		this.tasks=tasks;
		this.context=context;
		 this.inflater= ( LayoutInflater )context.
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		
		return tasks.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View rowList, ViewGroup arg2) {
		
	    
		// TODO Auto-generated method stub
		 rowList = inflater.inflate(R.layout.row_list, null);
		TextView taskName=(TextView)rowList.findViewById(R.id.taskName);
		TextView taskCategory=(TextView)rowList.findViewById(R.id.taskCategory);
		ImageView priority= (ImageView)rowList.findViewById(R.id.priorityImage);
		taskName.setText(tasks.get(arg0).getTaskName());
		taskCategory.setText(tasks.get(arg0).getCategory());
		
		if(tasks.get(arg0).getPriority().equals("Low"))
		{
			priority.setImageResource(R.drawable.green);
		}
		else if(tasks.get(arg0).getPriority().equals("High"))
		{
			priority.setImageResource(R.drawable.red);
		}
		else if(tasks.get(arg0).getPriority().equals("Medium"))
		{
			priority.setImageResource(R.drawable.orange);
		}
		return rowList;
	}

}
