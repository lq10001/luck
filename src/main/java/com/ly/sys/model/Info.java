package com.ly.sys.model;

import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

@TableBind(tableName = "info")
public class Info extends Model<Info> {

    public static final Info infoDao = new Info();

    public Info fetch(final Long id)
    {
        String key = "info_" + id;
        return CacheKit.get("info", key, new IDataLoader() {
            public Object load() {
                return Info.infoDao.findById(id);
            }
        });
    }

}