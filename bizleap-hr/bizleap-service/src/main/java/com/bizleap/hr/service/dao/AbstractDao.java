package com.bizleap.hr.service.dao;

import java.io.Serializable;
import java.util.List;

import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface AbstractDao<E, I extends Serializable> {
	
    void saveOrUpdate(E e) throws ServiceUnavailableException;
    List<E> findByString(String queryString, String data);
    List<E> findByInteger(String queryString, int data);
    List<E> findByStringInteger(String queryString, String data, int data1);
    List<E> findByString(String queryString);
    List<E> findByString(String queryString, String data, String data1);
    List<E> findByIntegerString(String queryString, Integer data, String data1);
    long getCount(String queryString);
    List<E> getAll(String queryString);
    List<E> getAll(String queryString, int pageNumber);
    List<E> findByLong(String queryString, long data);
}