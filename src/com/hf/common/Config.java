package com.hf.common;


import com.hf.mongodb.MongoController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.plugin.monogodb.MongodbPlugin;

public class Config extends JFinalConfig {
  public void configConstant(Constants me) {
    me.setDevMode(true);
  }

  public void configRoute(Routes me) {
    me.add("/", MongoController.class, "/mongo");
  }

  public void configPlugin(Plugins me) {
    MongodbPlugin mongodbPlugin = new MongodbPlugin("mydb");
    me.add(mongodbPlugin);


  }

  public void configInterceptor(Interceptors me) {}

  public void configHandler(Handlers me) {}

  public static void main(String[] args) {
    JFinal.start("WebRoot", 80, "/", 5);
  }
}
