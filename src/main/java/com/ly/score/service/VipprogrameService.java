package com.ly.score.service;

import com.ly.score.vo.Vip;
import com.ly.score.vo.Vipprograme;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;


@IocBean(fields = { "dao" })
public class VipprogrameService extends IdEntityService<Vipprograme> {
}


