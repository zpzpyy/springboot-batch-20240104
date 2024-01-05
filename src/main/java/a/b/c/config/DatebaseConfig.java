package a.b.c.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan( value="a.b.c.mapper", sqlSessionFactoryRef = "defaultSqlSessionFactory" )
@EnableTransactionManagement
public class DatebaseConfig {

    @Bean( name="defaultDataSource" )
    @ConfigurationProperties( prefix = "spring.datasource" )
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean( name="defaultSqlSessionFactory" )
    @Primary
    public SqlSessionFactory sqlSessionFactory( @Qualifier("defaultDataSource") DataSource defaultDataSource
                                              , ApplicationContext applicationContent ) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( defaultDataSource );

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase( true );
        sqlSessionFactoryBean.setConfiguration( configuration );
        sqlSessionFactoryBean.setMapperLocations(applicationContent.getResources("classpath:sql/mysql/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "dreamsearchSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate defaultSqlSessionTemplate( SqlSessionFactory defaultSqlSessionFactory ) throws Exception{
        return new SqlSessionTemplate( defaultSqlSessionFactory );
    }

}
