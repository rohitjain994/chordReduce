package de.uniba.wiai.lspi.chord.console.command;

import java.io.PrintStream;

import de.uniba.wiai.lspi.util.console.Command;

import de.uniba.wiai.lspi.chord.console.command.entry.Key;
import de.uniba.wiai.lspi.chord.console.command.entry.Value;
import de.uniba.wiai.lspi.chord.service.Chord;
import de.uniba.wiai.lspi.util.console.Command;
import de.uniba.wiai.lspi.util.console.ConsoleException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.Scanner;
import java.util.Set;

public class Reducer extends Command {
	public static final String COMMAND_NAME = "reduce";
	protected static final String INPUT_PARAM = "jobId";
	public Reducer(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() throws ConsoleException{
		String fileName = this.parameters.get(INPUT_PARAM);
		long startSystemTimeNano = System.currentTimeMillis();
		if(fileName.equals("reducer")){
		String fileValue = retrieve (fileName);
		Scanner sc = new Scanner(fileValue);
		StringBuilder sb = new StringBuilder();
		while( sc.hasNextLine() ){
			String line = sc.nextLine() ;
			int count=0;
			for (char c : line.toCharArray()) {
				if (c == ' ') {
					count++;
				}
			}
		  if(count == 3){
			String[] str_input =line.split(" ");
			String lang = str_input[0];
			String pageDetail = str_input[1];
			String visits =str_input[2];
			sb.append(lang+"\t"+pageDetail+"\t"+visits+"\n");			
		  }	    	
		}
		String file = "map"+fileName;
		try {
		    //Whatever the file path is.
		    File statText = new File("/home/raman/Desktop/files/"+file);
		    FileOutputStream is = new FileOutputStream(statText);
		    OutputStreamWriter osw = new OutputStreamWriter(is);    
		    Writer w = new BufferedWriter(osw);
		    w.write(sb.toString());
		    w.close();
		} catch (IOException e) {
		    this.out.println("Problem writing to the file");
		}
		chordInsert(file,sb.toString());
		}
		try {
		      for (double progressPercentage = 0.0; progressPercentage < 1.0; progressPercentage += 0.01) {
		        updateProgress(progressPercentage);
		        Thread.sleep(500);
		      }
		    } catch (InterruptedException e) {}
		this.out.println("\nReducing is done !!!!!!!!!!!");
		long stopSystemTimeNano = System.currentTimeMillis();
		this.out.println("TIME : ");
		this.out.println(stopSystemTimeNano - startSystemTimeNano);
	}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void printOutHelp() {
		this.out.println("This is the command to reduce file content in <key,value> format");
	}
	
	public void chordInsert (String key,String value) throws ConsoleException{
		
		Chord chord = ((RemoteChordNetworkAccess)this.toCommand[1]).getChordInstance(); 
		Key keyObject = new Key(key);
        	Value valueObject = new Value(value);
	        try {
        	    chord.insert(keyObject, valueObject);
        	}
        	catch (Throwable t){
        	    ConsoleException e = new ConsoleException("Exception during execution of command. " + t.getMessage(), t);
            	    throw e;
        	}
		
	}

	public String retrieve(String key) {
	    if ( (key == null) || (key.length() == 0) ){
	       // throw new ConsoleException("Not enough parameters! " + KEY_PARAM + " is missing.");
	    }
	    
	    Key keyObject = new Key(key);
	    String ret="";
	    Chord chord = ((RemoteChordNetworkAccess)this.toCommand[1]).getChordInstance(); 
	    try {
	        Set<Serializable> vs = chord.retrieve(keyObject);
	        Object[] values = vs.toArray(new Object[vs.size()]); 
	        //this.out.println("Values associated with key '" + key + "': ");
	        for (int i = 0; i < values.length; i++) {
	          //  this.out.println(values[i]);
	            ret=String.valueOf(values[i]);
	            return ret;
	        
	        }
	    }
	    catch (Throwable t){
	       
	    }
	  return "";   
	}
	void updateProgress(double progressPercentage) {
	    final int width = 50; // progress bar width in chars

	    this.out.print("\r[");
	    int i = 0;
	    for (; i <= (int)(progressPercentage*width); i++) {
	      this.out.print("#");
	    }
	    for (; i < width; i++) {
	      this.out.print(" ");
	    }
	    this.out.print("]");
	  }

}


