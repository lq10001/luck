package com.ly.score.service;

import com.ly.score.vo.Programe;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;


@IocBean(fields = { "dao" })
public class ProgrameService extends IdEntityService<Programe> {
}


