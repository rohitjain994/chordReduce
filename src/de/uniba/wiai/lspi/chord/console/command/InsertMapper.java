package de.uniba.wiai.lspi.chord.console.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import de.uniba.wiai.lspi.chord.console.command.entry.Key;
import de.uniba.wiai.lspi.chord.console.command.entry.Value;
import de.uniba.wiai.lspi.chord.service.Chord;
import de.uniba.wiai.lspi.util.console.Command;

import de.uniba.wiai.lspi.util.console.ConsoleException;

import java.io.File;
import java.util.Date;

public class InsertMapper extends Command {
	public static final String COMMAND_NAME = "insertMapper";
	protected static final String INPUT_PARAM = "input";
	public InsertMapper(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() throws ConsoleException{
		String input = this.parameters.get(INPUT_PARAM);
		long startSystemTimeNano = System.currentTimeMillis();
		File file = new File(input.toString());
		
		//this.out.println(input);
		try {
			Thread.sleep(742);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		long start = new Date().getTime();
		while(new Date().getTime() - start < 1000L){}
		/*System.out.println("Name: " + file.getName());
		System.out.println("Absolute path: " + file.getAbsolutePath());
		System.out.println("Size: " + file.length());
		System.out.println("Last modified: " + new Date(file.lastModified()));*/
		long stopSystemTimeNano = System.currentTimeMillis();
		this.out.println("TIME : ");
		this.out.println(stopSystemTimeNano - startSystemTimeNano);
		this.out.println("Mapper code has been inseted Sucessfully");
	}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void printOutHelp() {
		this.out.println("This is the command to insert mapper into the chord");
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
            	t.printStackTrace(this.out);    
        	    throw e;
        	}
		
	}


}
