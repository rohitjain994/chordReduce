package de.uniba.wiai.lspi.chord.console.command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import de.uniba.wiai.lspi.chord.console.command.entry.Key;
import de.uniba.wiai.lspi.chord.console.command.entry.Value;
import de.uniba.wiai.lspi.chord.service.Chord;
import de.uniba.wiai.lspi.util.console.Command;

import de.uniba.wiai.lspi.util.console.ConsoleException;

import java.io.File;

public class InsertData extends Command {
	public static final String COMMAND_NAME = "insertData";
	protected static final String INPUT_PARAM = "input";
	public InsertData(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() throws ConsoleException{
		String input = this.parameters.get(INPUT_PARAM);
		long startSystemTimeNano = System.currentTimeMillis();
		File file = new File(input.toString());
		
		//this.out.println(input);
		
		if(file.length()<= 134217728){
			try{
			FileInputStream inp = new FileInputStream(file);
	        byte[] bf = new byte[65536];
	        while ((inp.read(bf))!=-1){
		        String fileContent = new String(bf, "UTF-8");
		        chordInsert(file.getName(),fileContent);
		    }
	    	}catch (FileNotFoundException e) {
		          e.printStackTrace();
		    } catch (IOException e) {
		          e.printStackTrace();
		    }
		}else{
			this.out.println("file size must be less than 128MB...");
		}	
		/*System.out.println("Name: " + file.getName());
		System.out.println("Absolute path: " + file.getAbsolutePath());
		System.out.println("Size: " + file.length());
		System.out.println("Last modified: " + new Date(file.lastModified()));*/
		long stopSystemTimeNano = System.currentTimeMillis();
		this.out.println("TIME : ");
		this.out.println(stopSystemTimeNano - startSystemTimeNano);
		
	}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void printOutHelp() {
		this.out.println("This is simple insertData command");
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


}
