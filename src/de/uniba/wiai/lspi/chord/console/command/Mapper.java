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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Mapper extends Command {
	public static final String COMMAND_NAME = "map";
	protected static final String INPUT_PARAM = "input";
	public Mapper(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() throws ConsoleException{
		String input = this.parameters.get(INPUT_PARAM);
		try{
			long startSystemTimeNano = System.currentTimeMillis();
		    File file = new File(input.toString());
		    FileReader fr = new FileReader(file);
		    BufferedReader br = new BufferedReader(fr);
		    String line;
		    while( (line=br.readLine()) != null ){
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
				chordInsert(lang,pageDetail+" "+visits);				
			  }	    	
		    }
		    br.close();
		    long stopSystemTimeNano = System.currentTimeMillis();
		    this.out.println("TIME : ");
		    this.out.println(stopSystemTimeNano - startSystemTimeNano);

		}catch(IOException e){
		e.printStackTrace();
		}
	}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void printOutHelp() {
		this.out.println("This is simple hello command");
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
