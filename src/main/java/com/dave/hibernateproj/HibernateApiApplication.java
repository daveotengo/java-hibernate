package com.dave.hibernateproj;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.dave.hibernateproj.endpoints.CommentEndPoint;
import com.dave.hibernateproj.exceptions.GeneralExceptionMapper;
import com.dave.hibernateproj.exceptions.MissingRequiredParamsExceptionMapper;




public class HibernateApiApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new LinkedHashSet<Class<?>>();

        resources.add(CommentEndPoint.class);

        return resources;
    }

    /*
     * Exception mapper handler here
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> singleton = new LinkedHashSet<Object>();
        singleton.add(new GeneralExceptionMapper());
        singleton.add(new MissingRequiredParamsExceptionMapper());

        return singleton;
    }

}
