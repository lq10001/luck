package com.ly.score.service;

import com.ly.score.vo.Programe;
import com.ly.score.vo.Vip;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;


@IocBean(fields = { "dao" })
public class VipService extends IdEntityService<Vip> {
}


