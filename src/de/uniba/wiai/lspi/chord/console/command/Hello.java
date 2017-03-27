
package de.uniba.wiai.lspi.chord.console.command;

import java.io.File;
import java.io.PrintStream;
import java.util.*;
//import java.util.*;

import de.uniba.wiai.lspi.util.console.Command;
import de.uniba.wiai.lspi.util.console.ConsoleException;

public class Hello extends Command {
	public static final String COMMAND_NAME = "hello";
	protected static final String NAME_PARAM = "input";
	public Hello(Object[] toCommand1, PrintStream out1) {
		super(toCommand1, out1);
	}

	public void exec() throws ConsoleException {
		String input = this.parameters.get(NAME_PARAM);
		//this.out.println(input);
		File file = new File(input.toString());
		this.out.println("Name: " + file.getName());
		this.out.println("Absolute path: " + file.getAbsolutePath());
		this.out.println("Size: " + file.length());
		this.out.println("Last modified: " + new Date(file.lastModified()));
		
		}

	public String getCommandName() {
		return COMMAND_NAME;
	}

	public void printOutHelp() {
		this.out.println("This is simple hello command");
	}
	
	
}
