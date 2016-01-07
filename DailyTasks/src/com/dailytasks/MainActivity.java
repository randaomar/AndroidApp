package com.dailytasks;




import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.dailytasks.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AnalogClock;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements View.OnClickListener{

	TabHost host;
	Data data;
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    try {
			data=new Data(this);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    listView=new ListView(MainActivity.this);
	  
	//set tabs
	    host=(TabHost)findViewById(R.id.tabhost);
        host.setup();
        TabHost.TabSpec spec=host.newTabSpec("all");
        spec.setContent(new TabHost.TabContentFactory(){
            public View createTabContent(String tag)
            {                                   
            	TaskAdapter taskAdapter=new TaskAdapter(data.getTasks(),MainActivity.this);
          	    listView.setAdapter(taskAdapter); 
          	    
          	    return listView;
            }       
        });
        spec.setIndicator("all");
        host.addTab(spec);
       // set dynamic tabs
        ArrayList<Category>categories=data.getCategories();
        for(int i=0;i<categories.size();i++)
        {
        	addTab(categories.get(i));
        }
	    }
void addTab(final Category c)
{
	    TabHost.TabSpec specTabs=host.newTabSpec(c.getCategory());
	    final ListView dynamicListView=new ListView(MainActivity.this);
		 
    	// specTabs.setContent(R.id.listView);
		 specTabs.setContent(new TabHost.TabContentFactory(){
	            public View createTabContent(String tag)
	            {    ArrayList<Task>tabbedTasks=new ArrayList<Task>();
	               ArrayList<Task>allTasks=data.getTasks();
	            	for(int j=0;j<allTasks.size();j++)
	            		{
			    	 if(allTasks.get(j).getCategory().trim().equals(c.getCategory()))
			    		 {
			    			 Log.d("gwa el if", allTasks.get(j).getCategory());
			    			 tabbedTasks.add(allTasks.get(j));
			    		 }
			    	 }                               
	            	TaskAdapter newTaskAdapter=new TaskAdapter(tabbedTasks,MainActivity.this);
	            	dynamicListView.setAdapter(newTaskAdapter);   
	                return dynamicListView;
	            }       
	        });   
    	 specTabs.setIndicator(c.getCategory());
     	 host.addTab(specTabs);
     	 
    
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actionbar, menu);
 
        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_add_category) {
			Intent intent = new Intent(this, Add_Category.class );
			startActivity(intent);
		}
		else if(id==R.id.action_add_task)
		{
			Intent intent = new Intent(this, Add_Task.class );
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		
	}
}
