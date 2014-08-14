/*package org.jainbooks.ebook.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jainbooks.ebook.dao.LogoDao;
import org.jainbooks.ebook.domain.Logo;
import org.jainbooks.ebook.domain.LogoType;
import org.jainbooks.ebook.exception.YourKeyException;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.stereotype.Repository;

@Repository("logoDao")
public class LogoDaoImpl extends GenericDaoImpl<Logo, Long> implements LogoDao {

	private static final Logger logger = Logger.getLogger(LogoDaoImpl.class);

	@Override
	public List<Logo> getAllActiveLogos() {
		List<Logo> logos = new ArrayList<Logo>();
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Logo l join fetch l.logoType where l.active = true";
			Query query = session.createQuery(queryString);
			logos = query.list();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		} finally {
			session.close();
		}
		return logos;
	}

	@Override
	public void saveLogoForLogoType(Logo logo, long logoTypeId) {
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			LogoType logoType = (LogoType) session.load(LogoType.class,
					logoTypeId);
			logo.setLogoType(logoType);
			save(logo);
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		} finally {
			session.close();
		}
	}

	@Override
	public Logo getLogo(long id) {
		Session session = null;
		Logo logo = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Logo l join fetch l.logoType where l.id = :id";
			Query query = session.createQuery(queryString);
			query.setLong("id", id);
			logo = (Logo) query.uniqueResult();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		} finally {
			session.close();
		}
		return logo;
	}

	@Override
	public List<Logo> getAllLogos() {
		List<Logo> logos = null;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Logo l join fetch l.logoType lt order by lt.type";
			Query query = session.createQuery(queryString);
			logos = query.list();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		} finally {
			session.close();
		}
		return logos;
	}

	@Override
	public List<Logo> getLogosBySearchString(StringBuilder searchString) {
		List<Logo> logos = null;
		Session session = null;
		String param = "%" + searchString.toString() + "%";
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Logo l join fetch l.logoType lt where l.name like :pram1 or lt.type like :pram2";
			Query query = session.createQuery(queryString);
			query.setString("pram1", param);
			query.setString("pram2", param);
			logos = query.list();
			System.out.println();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		} finally {
			session.close();
		}
		return logos;
	}
	
	@Override
	public void updateLogoStatus(boolean statusToSet, List<Long> logoIdList) {
		
		Session session = null;
		Transaction tx = null;
		Logo logo = null;
		int count=0;
		
		try {
			session = getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			for(Long logoId : logoIdList) {
				logo = getLogo(logoId);
				logo.setActive(statusToSet);
				
				logger.info("Updating logo status: logo id = "+logoId);
				session.update(logo);
				
				if(++count % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();	
		} catch (Exception e) {
			logger.info("Transaction to update logo status is rolled back...");
			tx.rollback();
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new YourKeyException();
		} finally {
			session.flush();
			session.close();
		}
	}
}
*/