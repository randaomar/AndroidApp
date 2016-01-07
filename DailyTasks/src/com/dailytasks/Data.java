package com.dailytasks;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

public class Data {
	private ArrayList<Task> tasks;
	private ArrayList<Category> categories;
	private Context c;
	public Data(Context c) throws IOException
	{
		tasks=new ArrayList<Task>();
		categories=new ArrayList<Category>();
		 this.c=c;
		  File newFolder = new File(Environment.getExternalStorageDirectory(), "DailyTasks");
		  File file; 
		  FileOutputStream fOut;
		  OutputStreamWriter myOutWriter;
		  FileInputStream fIn;
		  BufferedReader myReader;
		  if (!newFolder.exists()) {
		        newFolder.mkdir();
		       file = new File(newFolder, "Tasks" + ".txt");
		       
		      fOut = new FileOutputStream(file);
		      myOutWriter = new OutputStreamWriter(
		               fOut);
		     
		       myOutWriter.close();
		       fOut.close();
		       //create category file
		       file = new File(newFolder, "Categories" + ".txt");
		       
		        fOut = new FileOutputStream(file);
		        myOutWriter = new OutputStreamWriter(
		               fOut);
		   
		       myOutWriter.close();
		       fOut.close();
		    }
		  else
		  {
		
			  //get all tasks
			file= new File(newFolder, "Tasks" + ".txt");
		    fIn = new FileInputStream(file);
			myReader = new BufferedReader(new InputStreamReader(fIn));
			String line = "";
			 while ((line = myReader.readLine()) != null) {
		
			if(!line.equals("")){
			Task task=new Task();
			       StringTokenizer st = new StringTokenizer(line, "|");
			       while(st.hasMoreElements())
			       {
			    	   task.setTaskName(st.nextElement().toString());
			    	   task.setPriority(st.nextElement().toString());
			    	
			    	   task.setCategory(st.nextElement().toString());
			    	   
			       }
			       tasks.add(task);	  
			}
			   }
			    fIn.close();
			    myReader.close();
			    //get all categories
			    file= new File(newFolder, "Categories" + ".txt");
			    fIn = new FileInputStream(file);
				myReader = new BufferedReader(new InputStreamReader(fIn));
			    line = "";
		    	   while((line = myReader.readLine()) != null)
		    	   {
		    		   Category category=new Category();
				       category.setCategory(line);
				       categories.add(category);
		    	   }
		    	   fIn.close();
		    	   myReader.close();
		    	   if(categories.size()==0)
		    	   {
		    		   Category cat=new Category();
		    		   cat.setCategory("");
		    		   categories.add(cat);
		    	   }
		      
		  }
		
	}

	public void addTask(Task t) throws IOException
	{ 
		File newFolder = new File(Environment.getExternalStorageDirectory(), "DailyTasks");
		  
		  //get all tasks
		File file= new File(newFolder, "Tasks" + ".txt");
		FileInputStream fIn = new FileInputStream(file);
		BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
		String line = "";
		String fileTxt="";
		 while ((line = myReader.readLine()) != null) {
	
		if(!line.equals("")){
		fileTxt=fileTxt+line+'\r';
		}
		   }
		    fIn.close();
		    myReader.close();
	  FileOutputStream fOut;
	  OutputStreamWriter myOutWriter;
	  file = new File(newFolder, "Tasks" + ".txt");
      fOut = new FileOutputStream(file);
      myOutWriter = new OutputStreamWriter(
               fOut);

       myOutWriter.write(fileTxt+t.getTaskName()+" |"+t.getPriority()+"|"+t.getCategory()+" "+'\r');
       myOutWriter.close();
       fOut.close();

				
	}
	public void addCategory(Category c) throws IOException
	{ 
		File newFolder = new File(Environment.getExternalStorageDirectory(), "DailyTasks");
		  
		  //get all tasks
		File file= new File(newFolder, "Categories" + ".txt");
		FileInputStream fIn = new FileInputStream(file);
		BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
		String line = "";
		String fileTxt="";
		 while ((line = myReader.readLine()) != null) {
	
		if(!line.equals("")){
		fileTxt=fileTxt+line+'\r';
		}
		   }
		    fIn.close();
		    myReader.close();
	  FileOutputStream fOut;
	  OutputStreamWriter myOutWriter;
	  file = new File(newFolder, "Categories" + ".txt");
      fOut = new FileOutputStream(file);
      myOutWriter = new OutputStreamWriter(
               fOut);

       myOutWriter.write(fileTxt+c.getCategory()+'\r');
       myOutWriter.close();
       fOut.close();

				
	}
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	
	public ArrayList<Category> getCategories()
	{
		return categories;
	}
}
