package com.cskaoyan.mall.shiro;

import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRealm  extends AuthorizingRealm {


    @Autowired
    AdminMapper adminMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //subject执行login时传入的usernamePasswordToken中的username
        String principal = (String) authenticationToken.getPrincipal();
        //根据principle也就是用户名去数据库查询用户名所对应的密码信息
        String passwordFromDb = adminMapper.queryPasswordByUsername(principal);
        //第一个参数在处理授权信息时可以获得
        //第二个参数 该用户正确的密码（来源于数据库的）
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, passwordFromDb, this.getName());
        return authenticationInfo;
    }

    /**
     * 完成认证之后
     * 获得授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得doGetAuthenticationInfo方法的返回值的第一个参数
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addStringPermission("user:insert");
//        authorizationInfo.addStringPermission("user:delete");
//        authorizationInfo.addStringPermission("user:update");
//        authorizationInfo.addStringPermission("user:query");

        //权限信息时根据认证时的用户信息去数据库中查询获得的
//        List<String> permissions = new ArrayList<>();
//        permissions.add("user:query");
//        permissions.add("user:insert");
//        permissions.add("user:delete");
//        permissions.add("user:update");
//        permissions.add("user:query");
        List<String> permissions = adminMapper.queryPermissionsByUsername(primaryPrincipal);
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addRole("utilMan");
        return authorizationInfo;
    }

}
