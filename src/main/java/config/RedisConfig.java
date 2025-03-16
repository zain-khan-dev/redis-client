package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import client.RedisConnectionPool;


// TODO: Need to add default values for the config
public class RedisConfig {


    private String host;
    private int port;
    private String username;
    private String password;
    private boolean auth;
    private int connectionPoolSize;



    private RedisConfig(String host, int port, String username, String password, boolean auth, int connectionPoolSize){
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.connectionPoolSize = connectionPoolSize;
    }   


    public String getHost(){
        return host;
    }

    public int getPort(){
        return port;
    }       

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean isAuth(){
        return auth;
    }

    public int getConnectionPoolSize(){
        return connectionPoolSize;
    }
    
    

    public static class RedisConfigBuilder{
        private String host;
        private int port;
        private String username;
        private String password;
        private boolean auth;
        private int connectionPoolSize;

        public RedisConfigBuilder withHost(String host){
            this.host = host;
            return this;
        }

        public RedisConfigBuilder withPort(int port){
            this.port = port;
            return this;
        }

        public RedisConfigBuilder withUsername(String username){
            this.username = username;   
            return this;
        }

        public RedisConfigBuilder withPassword(String password){
            this.password = password;
            return this;
        }   

        public RedisConfigBuilder withAuth(boolean auth){
            this.auth = auth;
            return this;
        }   

        public RedisConfigBuilder withConnectionPoolSize(int connectionPoolSize){
            this.connectionPoolSize = connectionPoolSize;
            return this;
        }

        public RedisConfig build(){
            return new RedisConfig(host, port, username, password, auth, connectionPoolSize);
        }

    }

    public static RedisConfig getRedisConfigFromFile(){
        Properties properties = new Properties();
        try (InputStream input = RedisConnectionPool.class.getClassLoader().getResourceAsStream("redis-config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load redis-config.properties", e);
        }

        return new RedisConfigBuilder()
        .withHost(properties.getProperty("redis.host"))
        .withPort(Integer.parseInt(properties.getProperty("redis.port")))
        .withUsername(properties.getProperty("redis.auth.username"))
        .withPassword(properties.getProperty("redis.auth.password"))
        .withAuth(Boolean.parseBoolean(properties.getProperty("redis.auth")))
        .withConnectionPoolSize(Integer.parseInt(properties.getProperty("redis.connectionPoolSize")))
        .build();
    }

}
