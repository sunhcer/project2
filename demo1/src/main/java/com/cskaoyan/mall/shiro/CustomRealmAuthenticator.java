package com.cskaoyan.mall.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/6
 * @Time 16:05
 */
public class CustomRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomToken customToken = (CustomToken) authenticationToken;
        String type = customToken.getType();

        this.assertRealmsConfigured();
        Collection<Realm> allRealms = this.getRealms();
        Collection<Realm> realms = new ArrayList<>();
        for (Realm allRealm : allRealms) {
            if (allRealm.getName().toLowerCase().contains(type)){
                realms.add(allRealm);
            }
        }

        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) : this.doMultiRealmAuthentication(realms, authenticationToken);

    }
}
