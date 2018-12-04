package com.computerDatabase.excilys.ui;

import java.util.Scanner;

import org.slf4j.*;

import com.computerDatabase.excilys.cli.*;
import com.computerDatabase.excilys.validator.ComputerValidator;

public class MainClass {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainClass.class);
	private long id;
	
	private static ComputerCli computerCli = ComputerCli.getInstance();
	private static CompanyCli companyCli = CompanyCli.getInstance();
	static int[][] game;
	private static ComputerValidator computerValidator = ComputerValidator.getInstance();
	
	public MainClass(long id) {
		this.id = id;
	}
	
	public static final void main(String[] args) {
		Scanner input = new Scanner(System.in);
		LOGGER.info("Here you can read and modify the list of computers or their manufacturer companies.");
		String line;
		do {
			line = input.nextLine();
			if (line.equalsIgnoreCase("Create a computer")) {
				String computerName;
				do {
					LOGGER.info("Give the name of the computer:");
					computerName = input.nextLine();
				} while (computerName.equals(""));
				LOGGER.info("Give the date when the computer was introduced:");
				String introduced = input.nextLine();
				LOGGER.info("Give the date when the computer was discontinued:");
				String discontinued = input.nextLine();
				LOGGER.info("Give the company ID:");
				String companyID = input.nextLine();;
				computerCli.createCli(computerName, introduced, discontinued, companyID);
			} else if (line.equalsIgnoreCase("List computers")) {
				String number;
				String page = "1";
				do {
					LOGGER.info("Give the number of computers per page:");
					number = input.nextLine();
				} while (number.equals(""));
				computerCli.listCli(number, page);
				String curser;
				do {
					curser = input.nextLine();
					if (curser.equalsIgnoreCase("previous")) {
						page = Long.toString((Long.parseLong(page) - 1));
						computerCli.listCli(number, page);
					} else if (curser.equalsIgnoreCase("next")) {
						page = Long.toString((Long.parseLong(page) + 1));
						computerCli.listCli(number, page);
					}
				} while (curser.equalsIgnoreCase("previous") || curser.equalsIgnoreCase("next"));
			} else if (line.contains("List a computer")) {
				computerCli.listDetailsCli(line.split(" ")[3]);
			} else if (line.contains("Update")) {
				String oldId;
				do {
					LOGGER.info("Give the id of the computer to update:");
					oldId = input.nextLine();
				} while (oldId.equals(""));
				LOGGER.info("Give the new name of the computer:");
				String computerName = input.nextLine();
				LOGGER.info("Give the new date when the computer was introduced:");
				String introduced = input.nextLine();
				LOGGER.info("Give the new date when the computer was discontinued:");
				String discontinued = input.nextLine();
				LOGGER.info("Give the new company ID:");
				String companyID = input.nextLine();
				computerCli.updateCli(oldId, computerName, introduced, discontinued, companyID);
			} else if (line.contains("Delete")) {
				computerCli.deleteCli(line.split(" ")[1]);
			} else if (line.equalsIgnoreCase("List companies")) {
				companyCli.listCli();
			} else {
				LOGGER.info("Your command cannot be executed. Please try again.");
			}
		} while (line.equalsIgnoreCase("Stop") == false);
		input.close();
		LOGGER.info("The programm has been stopped.");
	}

}