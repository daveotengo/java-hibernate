package com.dave.hibernateproj;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import com.dave.hibernateproj.utils.PropsCache;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

/**
 * Hello world!
 *
 */


public class HibernateApiApplicationServer 
{
	private static Logger _log = Logger.getLogger(HibernateApiApplicationServer.class);

    public static void main( String[] args )
    {
       // System.out.println( "Hello World!" );
    	
    	// Get Port
    			String host = PropsCache.getInstance().getProperty("HOST");
    			
    			int port = Integer.parseInt(PropsCache.getInstance().getProperty("PORT"));
    			
    			// Create the JAXRS Server
    			UndertowJaxrsServer server = new UndertowJaxrsServer();
    			Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(port, host);
    			server.start(serverBuilder);

    			// Create the deployment
    			ResteasyDeployment deployment = new ResteasyDeployment();
    			deployment.setApplicationClass(HibernateApiApplication.class.getName());
    			deployment.setInjectorFactoryClass(org.jboss.resteasy.cdi.CdiInjectorFactory.class.getName());

    			// Configure the deployment info
    			String genReqPath = "/api";
    			String contextPath= PropsCache.getInstance().getProperty("CONTEXT_PATH");
    			DeploymentInfo di = server.undertowDeployment(deployment, genReqPath);
    			//System.out.println("props: "+PropsCache.getInstance().getProperty("CONTEXT_PATH"));
    			di.setClassLoader(HibernateApiApplicationServer.class.getClassLoader()).setContextPath(contextPath).setDeploymentName("HibernateApiApp");

    			// Add CDI listener
    			di.addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));

    			// Deploy the API
    			server.deploy(di);
    			
    			_log.info("HibWS started on " + host + ":" + port+contextPath+genReqPath);
    			System.out.println("HibWS started on " + host + ":" + port+contextPath+genReqPath);

			
			    
    }
    
    
}
