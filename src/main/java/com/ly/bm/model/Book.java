package com.ly.bm.model;

import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.ly.comm.MyCnd;


import java.util.List;

@TableBind(tableName = "book")
public class Book extends Model<Book> {

    public static final Book BOOK_DAO = new Book();

    public Page<Book> query(int pageNum,int pageSize,Book book)
    {
        StringBuffer sb = new StringBuffer();
        if(MyCnd.getSql(book, sb))
        {
            return BOOK_DAO.paginate(pageNum, 50, "select *", sb.toString());
        }else{
            return BOOK_DAO.paginateByCache("book", "page_book_" + pageNum, pageNum, pageSize, "select *", "from book order by id desc");
        }
    }
}