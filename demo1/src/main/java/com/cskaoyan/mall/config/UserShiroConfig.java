package com.cskaoyan.mall.config;

import com.cskaoyan.mall.shiro.CustomRealmAuthenticator;
import com.cskaoyan.mall.shiro.UserRealm;
import com.cskaoyan.mall.shiro.WxRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;


@Configuration
public class UserShiroConfig {

    /*shiroFilter*/

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //如果访问url没有通过认证，会重定向到loginUrl
        shiroFilterFactoryBean.setLoginUrl("/fail");
        //安全控制器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器配置
        //请求的url
        //anon匿名的
        //authc认证
        //perms权限
        HashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/admin/auth/login","anon");
//        filterChainDefinitionMap.put("/admin/auth/info","anon");
        filterChainDefinitionMap.put("/fail","anon");
        filterChainDefinitionMap.put("/jpg/**","anon");
        filterChainDefinitionMap.put("/admin/**","authc");
        //filterChainDefinitionMap.put("/admin/logout","logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /*securityManager*/
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm,
                                                     @Qualifier("wxRealm") WxRealm wxRealm,
                                                     CustomRealmAuthenticator customRealmAuthenticator,
                                                     DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRealm(wxRealm);
        securityManager.setAuthenticator(customRealmAuthenticator);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    /*自定义realm*/

    /*声明式使用鉴权注解的开关*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*通过异常类型，映射到不同的请求上*/
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("org.apache.shiro.authz.AuthorizationException","/fail");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        return simpleMappingExceptionResolver;
    }
    /*自定义的sessionManager*/
    @Bean
    public DefaultWebSessionManager webSessionManager(){
        MallSessionManager mallSessionManager = new MallSessionManager();
        return mallSessionManager;
    }

    /*自定义的CustomRealmAuthenticator*/
    @Bean
    public CustomRealmAuthenticator customRealmAuthenticator(@Qualifier("userRealm") UserRealm userRealm,
                                                             @Qualifier("wxRealm") WxRealm wxRealm){
        CustomRealmAuthenticator realmAuthenticator = new CustomRealmAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(wxRealm);
        realmAuthenticator.setRealms(realms);

        return realmAuthenticator;
    }
}
