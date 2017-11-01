package com.neerajmohinani.projects.service;




import org.springframework.data.repository.CrudRepository;

import com.neerajmohinani.projects.currencyConverter.models.CurrencyQuoteDao;

import java.util.List;

/**
 * Implement the Service interface for models with Hibernate annotations
 */
public abstract class AbstractService<T> implements Service<T> {
	
	protected CrudRepository<T, Long> tDao;

   

    
    public T save(T model) {
        return tDao.save(model);
    }
    
    
    public List<T> save(List<T> models) {
        return (List<T>)tDao.save(models);
    }

    public void deleteById(Long id) {
        tDao.delete(id);
    }


    public T findById(Long id) {
        return tDao.findOne(id);
    }
  

    public List<T> findAll() {
        return (List<T>)tDao.findAll();
    }


	public void init(CrudRepository<T, Long> daoInterface) {
		this.tDao = daoInterface;
		
	}
}
