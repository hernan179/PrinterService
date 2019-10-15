package com.aero.aplication.main;

import java.io.FileInputStream;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class InitialContextUtil {

    public static Context inicializarCTX() {

        String userDir = getCurrentWorkingDirectory();
        String hostFile = userDir + "host.properties";
        Properties configFile = new Properties();

        try {
            configFile.load(new FileInputStream(hostFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final String hostAddress = configFile.getProperty("HOST");
        final String port = configFile.getProperty("PORT");

        Context initialContext = null;
        String name = "";
        try {
            Properties env = new Properties();
            env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
            env.setProperty(Context.PROVIDER_URL, "jnp://" + hostAddress + ":" + port);
            initialContext = new InitialContext(env);

        } catch (Exception nnfe) {
            nnfe.printStackTrace();
            System.out.println("no se encotnro el JNDI");
        }
        return initialContext;
    }

    public static String getCurrentWorkingDirectory() {
        String osname = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osname.toLowerCase().contains("windows")) {
            return userDir + "\\";
        } else {
            return userDir + "/";
        }
    }

    public static void main(String[] args) {
        Context context = inicializarCTX();


        /*Properties env = new Properties();
         env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
         env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming");
         env.setProperty(Context.PROVIDER_URL, "jnp://srvapp-desarrollo.losunos.net:1199");*/

    }
}
