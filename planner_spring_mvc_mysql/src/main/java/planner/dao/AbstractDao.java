package planner.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao  <PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public T getByKey(PK key){
		return (T)  getSession().get(persistentClass, key);
	}

	public void persistEntity(T entity){
		getSession().persist(entity);
		
	}
	
	public void mergeEntity(T entity){
		getSession().merge(entity);
		
		
	}
	
	public void updateEntity(T entity){
//		Session session = getSession();
//		org.hibernate.Transaction transaction = session.getTransaction();
//		transaction.begin();
//				
		getSession().saveOrUpdate(entity);
//		transaction.commit();
	}
	
	
	public void deleteEntity(T entity){
		getSession().delete(entity);
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	protected Query createEntityQuery(String query){
		return getSession().createQuery(query);
	}
}
