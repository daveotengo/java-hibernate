package com.dave.hibernateproj.utils;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.dave.hibernateproj.model.Comment;

//import com.etzgh.justpay.ws.entity.ListItems;
//import com.etzgh.justpay.ws.entity.MenuItems;
//import com.etzgh.justpay.ws.entity.Otp;
//import com.etzgh.justpay.ws.entity.Transactions;
//import com.etzgh.justpay.ws.entity.UserDevices;
//import com.etzgh.justpay.ws.entity.UserFavourites;
//import com.etzgh.justpay.ws.entity.UserProfile;
//import com.etzgh.justpay.ws.entity.Wallets;

/**
 *
 * @author david oteng
 */
public class DbAccessUtils {
	private static SessionFactory sessionFactory;
	private static String DB_USERNAME = PropsCache.getInstance().getProperty("DB_USERNAME");
	private static String DB_PASSWORD = PropsCache.getInstance().getProperty("DB_PASSWORD");
	private static String DB_NAME = PropsCache.getInstance().getProperty("DB_NAME");
	private static String DB_HOST = PropsCache.getInstance().getProperty("DB_HOST");
	private static String DB_PORT = PropsCache.getInstance().getProperty("DB_PORT");
	private static String DB_DRIVER = PropsCache.getInstance().getProperty("DB_DRIVER");
	private static String DB_POOL_SIZE = PropsCache.getInstance().getProperty("DB_POOL_SIZE");
	private static String DB_POOL_MIN_SIZE = PropsCache.getInstance().getProperty("DB_POOL_MIN_SIZE");
	private static String DB_POOL_MAX_SIZE = PropsCache.getInstance().getProperty("DB_POOL_MAX_SIZE");
	private static String DB_POOL_TIMEOUT = PropsCache.getInstance().getProperty("DB_POOL_TIMEOUT");
	private static String DB_POOL_ACQUIRE_INCREMENT = PropsCache.getInstance().getProperty("DB_POOL_ACQUIRE_INCREMENT");
	private static String DB_POOL_MAX_STATEMENTS = PropsCache.getInstance().getProperty("DB_POOL_MAX_STATEMENTS");
	private static String DB_POOL_IDLE_TIME = PropsCache.getInstance().getProperty("DB_POOL_IDLE_TIME");
	private static String DB_SHOW_SQL = PropsCache.getInstance().getProperty("DB_SHOW_SQL");

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {

				// Create registry builder
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, DB_DRIVER);
				settings.put(Environment.URL,String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true", DB_HOST, DB_PORT, DB_NAME));
				settings.put(Environment.USER, DB_USERNAME);
				settings.put(Environment.PASS, DB_PASSWORD);
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				settings.put(Environment.POOL_SIZE, DB_POOL_SIZE);
				settings.put(Environment.CACHE_PROVIDER_CONFIG, "org.hibernate.cache.internal.NoCacheProvider");
				settings.put(Environment.CONNECTION_PROVIDER, "org.hibernate.connection.C3P0ConnectionProvider");
				settings.put(Environment.C3P0_MIN_SIZE, DB_POOL_MIN_SIZE);
				settings.put(Environment.C3P0_MAX_SIZE, DB_POOL_MAX_SIZE);
				settings.put(Environment.C3P0_TIMEOUT, DB_POOL_TIMEOUT);
				settings.put(Environment.C3P0_ACQUIRE_INCREMENT, DB_POOL_ACQUIRE_INCREMENT);
				settings.put(Environment.C3P0_MAX_STATEMENTS, DB_POOL_MAX_STATEMENTS);
				settings.put(Environment.C3P0_IDLE_TEST_PERIOD, DB_POOL_IDLE_TIME);
				settings.put(Environment.SHOW_SQL, DB_SHOW_SQL);

				configuration.setProperties(settings);

				// mapped classes here
//				configuration.addAnnotatedClass(UserProfile.class);
//				configuration.addAnnotatedClass(UserDevices.class);
//				configuration.addAnnotatedClass(Otp.class);
//				configuration.addAnnotatedClass(Wallets.class);
//				configuration.addAnnotatedClass(Transactions.class);
//				configuration.addAnnotatedClass(MenuItems.class);
//				configuration.addAnnotatedClass(ListItems.class);
//				configuration.addAnnotatedClass(UserFavourites.class);
				configuration.addAnnotatedClass(Comment.class);
				

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
