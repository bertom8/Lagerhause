package org.lagerhause.View.Util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.lagerhause.Model.Services.CreateService;
import org.lagerhause.View.Constants.Constants;

/**
 * Entityk keresésére használt osztály
 * @author Pilán Ádám György
 *
 */
public class EntityFinderUtility {
	/**
	 * Egy entity megkeresése adott mező alapján
	 * @param paramClass Entity osztálya
	 * @param name Mező értéke
	 * @param fieldName Mező neve
	 * @return A megtalált entity
	 */
	public static <T> T findEntityByName(final Class<T> paramClass, final String name, final String fieldName) {
        final EntityManager entityManager = CreateService.createEntityManager();
        T result;
        try {
            final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> query = builder.createQuery(paramClass);
            final Root<T> from = query.from(paramClass);
            final List<Predicate> conditions = new ArrayList<Predicate>();
            conditions.add(builder.equal(from.<String>get(fieldName), name));
            conditions.add(builder.equal(from.<String>get(Constants.DELETED), false));
            query.where(conditions.toArray(new Predicate[Constants.CONST_0]));
            final TypedQuery<T> typedQuery = entityManager.createQuery(query);
            result = null;
            for (final T t : typedQuery.getResultList()) {
                result = t;
                break;
            }
        } finally {
            entityManager.close();
        }
        return result;
    }
	
	/**
	 * Entity lista készítése adott mező alapján
	 * @param paramClass Entity osztálya
	 * @param name Mező értéke
	 * @param fieldName Mező neve
	 * @return Az összes entity, melynek adott mezőjén adott érték szerepel
	 */
	public static <T> List<T> findMatchingEntities(final Class<T> paramClass, final String name, final String fieldName) {
        final EntityManager entityManager = CreateService.createEntityManager();
        List<T> result = new ArrayList<T>();
        try {
            final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> query = builder.createQuery(paramClass);
            final Root<T> from = query.from(paramClass);
            final List<Predicate> conditions = new ArrayList<Predicate>();
            conditions.add(builder.equal(from.<String>get(fieldName), name));
            conditions.add(builder.equal(from.<String>get(Constants.DELETED), false));
            query.where(conditions.toArray(new Predicate[Constants.CONST_0]));
            final TypedQuery<T> typedQuery = entityManager.createQuery(query);
            for (final T t : typedQuery.getResultList()){
            	result.add(t); 
            }          
        } finally {
            entityManager.close();
        }
        return result;
    }
	
	/**
	 * Entity lista készítése
	 * @param paramClass Entity osztálya
	 * @return Az összes tárolt osztálybeli entity
	 */
	public static <T> List<T> findAllEntities(final Class<T> paramClass) {
        final EntityManager entityManager = CreateService.createEntityManager();
        List<T> result = new ArrayList<T>();
        try {
            final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> query = builder.createQuery(paramClass);
            final Root<T> from = query.from(paramClass);
            final List<Predicate> conditions = new ArrayList<Predicate>();
            conditions.add(builder.equal(from.<String>get(Constants.DELETED), false));
            query.where(conditions.toArray(new Predicate[Constants.CONST_0]));
            final TypedQuery<T> typedQuery = entityManager.createQuery(query);
            for (final T t : typedQuery.getResultList()){
            	result.add(t); 
            }          
        } finally {
            entityManager.close();
        }
        return result;
    }
}
