package com.dailytasks;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_Task extends Activity {

	Data data;
	 Spinner category;
	 Spinner priority ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__task);
		try {
			 data=new Data(this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		priority = (Spinner) findViewById(R.id.taskPrioritySpinner);
		List<String> priorityList = new ArrayList<String>();
		priorityList.add("Low");
		priorityList.add("Medium");
		priorityList.add("High");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, priorityList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	   priority.setAdapter(dataAdapter);
		
		category = (Spinner) findViewById(R.id.taskCategorySpinner);
		List<String> categoryList = new ArrayList<String>();
		for(int i=0;i<data.getCategories().size();i++)
			categoryList.add(data.getCategories().get(i).getCategory());
		ArrayAdapter<String> dataAdapterCategory = new ArrayAdapter<String>(this,
     	android.R.layout.simple_spinner_item, categoryList);
		dataAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(dataAdapterCategory);
		final EditText taskName=(EditText) findViewById(R.id.taskNameEdit);
		Button addBtn=(Button)findViewById(R.id.addTaskBtn);
		addBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Task t=new Task();
		          t.setCategory(category.getSelectedItem().toString());
		          t.setPriority(priority.getSelectedItem().toString());
		          t.setTaskName(taskName.getText().toString());
		   
		        try {
					data.addTask(t);
					Intent intent = new Intent(Add_Task.this, MainActivity.class );
					startActivity(intent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
		          
		        
			}});
	

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.addTaskBtn) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
