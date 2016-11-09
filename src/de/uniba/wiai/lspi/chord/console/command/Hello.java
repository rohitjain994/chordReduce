
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

public class Hello extends Command {
	public static final String COMMAND_NAME = "hello";
	protected static final String NAME_PARAM = "name";
	public Hello(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() {
		/*Object factory = ConsoleThread.getConsole().getCommandFactory();
		// out.println("Factory class " + factory.getClass());
		Field[] fields = factory.getClass().getDeclaredFields();
		// out.println("Number of factory fields " + fields.length);
		Field mapping = null;
		*/
		
		String name = this.parameters.get(NAME_PARAM);
		this.out.println("You have inserted: " + name);

		String key="abc";
		String value=name;
		chordInsert(key,value);
		/*Key keyObject = new Key(key);
		Value valueObject = new Value(value);

		URL url = null; 
		try {
			url = new URL(URL.KNOWN_PROTOCOLS.get(URL.LOCAL_PROTOCOL) + "://" + node + "/");
		} catch (MalformedURLException e1) {
			throw new ConsoleException(e1.getMessage());
		}

		ThreadEndpoint ep = Registry.getRegistryInstance().lookup(url);
		if (ep == null) {
			this.out.println("Node '" + node + "' does not exist!");
			return;
		}
		try {
			ChordImplAccess.fetchChordImplOfNode(ep.getNode()).insert(
					keyObject, valueObject);
		} catch (Throwable t) {
			ConsoleException e = new ConsoleException(
					"Exception during execution of command. " + t.getMessage());
			e.setStackTrace(t.getStackTrace());
			throw e;
		}
		this.out.println("Value '" + value + "' with key '" + key
				+ "' inserted " + "successfully from node '" + node + "'.");
	*/
	}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void printOutHelp() {
		this.out.println("This is simple hello command");
	}
	
	public void chordInsert(String key,String value){
		Chord chord = ((RemoteChordNetworkAccess)this.toCommand[1]).getChordInstance(); 
		
		Key keyObject = new Key(key);
		Value valueObject = new Value(value);
		try {
		    chord.insert(keyObject, valueObject);
		    //this.out.println("Trying to insert.");
	      
		}
		catch (Throwable t){
		    ConsoleException e 
		            = new ConsoleException("Exception during execution of command. " 
		            + t.getMessage(), t);
        }

}


}
