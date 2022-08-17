package fr.eni.encheres.dal;

import java.util.List;

public interface DAO <T>{
	public T selectById(T element) throws DALException;
	public List<T> selectAll(T element) throws DALException;
	public void insert(T element) throws DALException;
	public void update(T element) throws DALException;
	public void delete(int element) throws DALException;
	}
