package org.leetrepository.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionHandler {

    //want a SINGLE connection to db, hence static
    private static Connection connection;

    // use static piece of code to initialize the connection, as opposed to constructor, will execute when app starts
    static {
        if (connection == null){
            /**
             * https://www.baeldung.com/java-properties
             * java.util.Properties holds properties in the form of propertyname=value
             * key-value pairs
             * Properties.load(stream) will read properties from a given stream
             */
            Properties properties = new Properties();

            /**
             * class loaders are objects responsible for loading a class, part of the JVM.
             * class loaders can also locate resources on the classpath.
             *
             * https://stackoverflow.com/questions/2396493/what-is-a-classpath-and-how-do-i-set-it
             * in java, when you want to make a class available to other files, you might import them.
             * for the JVM to locate the compiled class, it looks at the classpath. in this case,
             * maven builds projects into projectname/target/classes (in this case, dao_demo/target/classes)
             * internally, when "java" is run, it will look at the class path "java -cp ..."
             * The reason that database.properties appears inside dao_demo/target/classes is because maven will
             * copy it there from resources whenever maven compile, package, or install is run
             *
             * bottom line, place resources inside the resource folder. this.class.getClassLoader().getResourceAsStream("resource name")
             * will be able to locate the resource because maven will copy it to the classpath in target/classes
             */
            try (InputStream input = ConnectionHandler.class.getClassLoader().getResourceAsStream("database.properties")){

                if(input == null){
                    throw new Exception("Unable to find database.properties");
                }else{
                    properties.load(input);
                }

                /** Load JDBC Driver
                 * determines the db.driver property from database.properties=org.postgresql.Driver
                 * gets the driver class located in the postgresql maven package
                 * (see pom.xml - dependencies - org.postgresql)
                 *
                 * the line below runs the **static** initializer of the class, much like the current
                 * static code of this class, to set up and initialize the driver class.
                 */
                Class.forName(properties.getProperty("db.driver"));

                /**
                 * get jdbc url, username, password from database.properties file, meaning that
                 * sensitive info no longer is hard-coded. also means that the database.properties file
                 * should be kept locally and not commited/pushed with git
                 */
                connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.username"),
                        properties.getProperty("db.password")
                );

            }catch(IOException | ClassNotFoundException e){
                throw new RuntimeException("Failed to load database configuration");
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection() throws RuntimeException {
        if(connection == null){
            throw new RuntimeException("Connection failed to setup correctly");
        }

        return connection;
    }

}
