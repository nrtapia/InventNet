dataSource {
    
    //pooled = true
    //driverClassName = "org.hsqldb.jdbcDriver"
    //url = "jdbc:hsqldb:mem:devDB"
    //username = "sa"
    //password = ""
    //logSql = true
    pooled = true
    //driverClassName = "com.mysql.jdbc.Driver"    
    logSql = false
    //dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"     
    
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {


            dbCreate = "update" // one of 'create', 'create-drop','update'            
            url = "jdbc:mysql://localhost/inventuser?useUnicode=yes&characterEncoding=UTF-8"
            driverClassName = "com.mysql.jdbc.Driver"
            username = "root"
            password = "admin"
            s

             /*
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            url = "jdbc:postgresql://localhost:5432/invent_ladecol"
            username = "postgres"
            password = "842671395"
               */
            
            maxActive = 30
            minidle = 1
            numTestsPerEvictionRun = 3
            testOnBorrow = true
            testWhileIdle = true
            testOnReturn = true
            validationQuery = "SELECT 1"
            minEvictableIdleTimeMillis = (1000 * 60 * 5)
            timeBetweenEvictionRunsMillis = (1000 * 60 * 5)    
            logSql = true
            
        }
    }
    test {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            //url = "jdbc:hsqldb:mem:devDB"
            url = "jdbc:postgresql://localhost:5432/inventnet"
        }
    }
    production { 
        dataSource {
            dbCreate = "update"


            url = "jdbc:mysql://localhost/inventuser?useUnicode=yes&characterEncoding=UTF-8"
            username = "inventuser"
            password = "pWQXZupbmZSBHXG8"    
            driverClassName = "com.mysql.jdbc.Driver" 

                         /*
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            url = "jdbc:postgresql://localhost:5432/inventnet"
            username = "postgres"
            password = "842671395"
                       */
            pooled = true
            
            properties {
               maxActive = 30
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
