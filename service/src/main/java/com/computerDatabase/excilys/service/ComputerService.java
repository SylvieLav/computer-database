package com.computerDatabase.excilys.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerDatabase.excilys.dao.ComputerDAO;
import com.computerDatabase.excilys.model.Computer;

@Service
public class ComputerService {
	
	@Autowired
	private ComputerDAO computerDAO;

	private ComputerService() {}

	public Computer create(Computer computer) {
		computerDAO.create(computer);

		return computer;
	}

	public List<Computer> listBySearch(long number, long pageNumber, String sortElement, String orderBy, String search) {
		return computerDAO.listBySearch(number, pageNumber, sortElement, orderBy, search);
	}

	public List<Computer> list(long number, long page, String sortElement, String orderBy) {
		return computerDAO.list(number, page, sortElement, orderBy);
	}

	public Optional<Computer> listDetails(long id) {
		return computerDAO.listDetails(id);
	}

	public Computer update(Computer computer) {
		computer = computerDAO.update(computer);

		return computer;
	}

	public long delete(long id) {
		computerDAO.delete(id);

		return id;
	}
}
