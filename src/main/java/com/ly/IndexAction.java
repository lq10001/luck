package com.ly;

import com.ly.score.service.ProgrameService;
import com.ly.score.service.VipService;
import com.ly.score.service.VipprogrameService;
import com.ly.score.vo.Programe;
import com.ly.score.vo.Vip;
import com.ly.score.vo.Vipprograme;
import com.ly.sys.service.InfoService;
import com.ly.sys.vo.User;
import com.ly.util.EnDeCode;
import org.beetl.core.statement.Program;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@IocBean
@At("/")
@Fail("json")
public class IndexAction {
	
	private static final Log log = Logs.getLog(IndexAction.class);

    @Inject
    private InfoService infoService;

    @Inject
    private VipService vipService;

    @Inject
    private ProgrameService programeService;

    @Inject
    private VipprogrameService vipprogrameService;


    @At("")
    @Ok("beetl:/WEB-INF/login.html")
	public void index(HttpServletRequest request)
	{
        request.setAttribute("info", infoService.fetch(1L));
    }

    @At
    @Ok("beetl:/WEB-INF/lottery2.html")
    public void jian(HttpServletRequest request)
    {
//        request.setAttribute("info", infoService.fetch(1L));
    }

    @At
    @Ok("beetl:/WEB-INF/${req_attr.page}")
    public void login(HttpServletRequest request,
                      HttpSession session,
                      @Param("name")String name,
                      @Param("pwd")String pwd)
    {
        Vip vip = vipService.fetch(Cnd.where("name", "=", name).and("pwd","=",pwd));

        if (vip == null) {
            request.setAttribute("page", "login.html");
            return;
        }
        session.setAttribute("userid",vip.getId());

        request.setAttribute("programes",programeService.query(null,null));

        request.setAttribute("page","index2.html");
    }

    @At
    @Ok("beetl:/WEB-INF/programe.html")
    public void programe(HttpServletRequest request,
                         HttpSession session,
                      @Param("pid")Long pid)
    {
        Long vipid = (Long)session.getAttribute("userid");
        request.setAttribute("programe", programeService.fetch(pid));
        Vipprograme vp = vipprogrameService.fetch(Cnd.where("vipid", "=", vipid).and("programeid", "=", pid));
        if (vp == null)
        {
            request.setAttribute("vp",0);
        }else{
            request.setAttribute("vp",1);
        }

    }

    @At
    @Ok("beetl:/WEB-INF/index2.html")
    public void score(HttpServletRequest request,
                      HttpSession session,
                      @Param("..")Vipprograme vipprograme)
    {

        Long vipid = (Long)session.getAttribute("userid");
        vipprograme.setVipid(vipid);
        vipprogrameService.dao().insert(vipprograme);

        request.setAttribute("programes",programeService.query(null,null));
    }

    @At
    @Ok("beetl:/WEB-INF/ps.html")
    public void ps(HttpServletRequest request)
    {
        List<Programe> programeList = programeService.query(null, null);
        for (Programe p : programeList)
        {
//            programeService.dao().
            Sql sql = Sqls.fetchInt("select sum(num) as num from vipprograme where programeid = "+p.getId());


        }
        request.setAttribute("programes",programeService.query(null,null));
    }





}
