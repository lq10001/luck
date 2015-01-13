package com.ly.comm;

import com.jfinal.render.IMainRenderFactory;
import com.jfinal.render.Render;
import org.beetl.ext.jfinal.BeetlRender;
import org.beetl.ext.jfinal.BeetlRenderFactory;


public class MyBeetlRenderFactory extends BeetlRenderFactory {
    @Override
    public Render getRender(String view) {
        BeetlRender render=new BeetlRender(groupTemplate, view);
        return render;
    }
    @Override
    public String getViewExtension() {
        return ".html";
    }
}