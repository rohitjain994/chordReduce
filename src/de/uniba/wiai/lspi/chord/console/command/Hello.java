
package de.uniba.wiai.lspi.chord.console.command;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import de.uniba.wiai.lspi.util.console.Command;
import de.uniba.wiai.lspi.util.console.ConsoleThread;

import java.net.MalformedURLException;

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

public class Hello extends Command {
	public static final String COMMAND_NAME = "hello";
	protected static final String NAME_PARAM = "name";
	public Hello(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() {
		String name = this.parameters.get(NAME_PARAM);
		try{
		    String verify;
		    File file = new File(name.toString());
		   /* file.createNewFile();
		    FileWriter fw = new FileWriter(file);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write("Some text here for a reason");
		    bw.flush();
		    bw.close();*/
		    FileReader fr = new FileReader(file);
		    BufferedReader br = new BufferedReader(fr);

		    while( (verify=br.readLine()) != null ){
		    	this.out.println(verify);
		    }
		    br.close();


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
	
	/*public void chordInsert (String key,String value){
		
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
		
	}*/

}
