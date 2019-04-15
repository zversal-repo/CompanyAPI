package com.zversal.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
/**
 * This class is used for database configuration. It implements {@link EnvironmentAware} . EnvironmentAware ia an  Interface to be implemented by any bean that wishes to be notified of the Environment that it runs in.
 * @author bhupinder 
 */
@Configuration
@PropertySource("classpath:mongo.properties")
public class ConfigProperties implements EnvironmentAware {
	@Autowired
	private static Environment env;
   
	/**
	 *Set the Environment that is Component runs in.
	 */
	@Override
	public void setEnvironment(final Environment environment) {
		ConfigProperties.env = environment;
	}
	/**
	 *reads the database name from property file
	 *@return database Name as a String
	 */
	public String getDatabaseName() {
		String databasename = null;
		try {
			databasename = env.getProperty("mongo.database");
			if (databasename.isEmpty()) {
				System.out.println("DatabaseName is Empty ");
				System.exit(1);
			}
		} catch (NullPointerException n) {
			System.out.println("mongo.database property is not present in property file");
			n.printStackTrace();
			System.exit(1);
		}
		return databasename;
	}
	/**
	 *reads the Uri from property file
	 *@return uri as a String
	 */
	public String getUri() {
		String uri = null;
		try {
			uri = env.getProperty("mongo.uri");
			if (uri.isEmpty()) {
				System.out.println("Uri is Empty");
				System.exit(1);
			}
		} catch (NullPointerException n) {
			System.out.println("mongo.uri property is not present in property file");
			n.printStackTrace();
			System.exit(1);
		}
		return uri;
	}
	/**
	 *reads the Collection Name from property file
	 *@return Collections Name as a String
	 */
	public String getCollection() {
		String collectionName = null;
		try {
			collectionName = env.getProperty("mongo.collection");
			if (collectionName.isEmpty()) {
				System.out.println("collectionName is Empty");
				System.exit(1);
			}
		} catch (NullPointerException n) {
			System.out.println("mongo.collection property is not present in property file");
			n.printStackTrace();
			System.exit(1);
		}
		return collectionName;
	}
}
