package com.ly.sys.model;

import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

@TableBind(tableName = "user")
public class User extends Model<User> {

    public static final User USER_DAO = new User();


}