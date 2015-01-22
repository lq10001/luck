package com.ly.bm.action;

import com.ly.comm.Dwz;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ly.bm.vo.Borrower;
import com.ly.bm.service.BorrowerService;
import org.nutz.mvc.upload.UploadAdaptor;


@IocBean
@At("/borrower")
@Fail("json")
//@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class BorrowerAction {

	private static final Log log = Logs.getLog(BorrowerAction.class);
	
	@Inject
	private BorrowerService borrowerService;

    @At("/")
    @Ok("beetl:/WEB-INF/bm/borrower_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Borrower borrower,
                      HttpServletRequest request){
        Cnd c = new ParseObj(borrower).getCnd();
        List<Borrower> list_m = borrowerService.query(c, p);
        p.setRecordCount(borrowerService.count(c));

        request.setAttribute("list_obj", list_m);
        request.setAttribute("page", p);
        request.setAttribute("borrower", borrower);
    }

    @At
    @Ok("beetl:/WEB-INF/bm/borrower.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("borrower", null);
        }else{
            request.setAttribute("borrower", borrowerService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")Borrower borrower){
        Object rtnObject;
        if (borrower.getId() == null || borrower.getId() == 0) {
            rtnObject = borrowerService.dao().insert(borrower);
        }else{
            rtnObject = borrowerService.dao().updateIgnoreNull(borrower);
        }
        return Dwz.rtnMap((rtnObject == null) ? false : true, "tab_borrower", true);
    }



    @At
    @Ok("json")
    @AdaptBy(type = UploadAdaptor.class, args = { "ioc:uploadFile" })
    public Long upload( @Param("qrcode")String qrcode,
                                    @Param("file1") File f1,
                                    HttpServletRequest request
    ) throws IOException {
        Object rtnObject = null;
        Borrower bb = borrowerService.fetch(Cnd.where("qrcode","=",qrcode));
        if (bb == null)
        {
            return 2L;
        }

        if (f1 == null)
        {
            return 1L;
        }

        String webPath =  request.getServletContext().getRealPath("/");
        if (f1 != null)
        {
            String filePath = "upload/"+bb.getQrcode() +".jpg";
            Files.copyFile(f1, new File(webPath + filePath));
            bb.setImgurl("/"+filePath);
            bb.setState(1L);
//            borrowerService.dao.up
            borrowerService.dao().updateIgnoreNull(bb);
        }

        return 10L;
    }


    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  borrowerService.delete(id);
        return Dwz.rtnMap((num > 0) ? true : false , "tab_borrower", false);
    }

}
