package com.gzzy.foodManProgram.service.common;

import com.gzzy.foodManProgram.dao.common.AppDao;
import com.gzzy.foodManProgram.dto.AbstractInputDto;
import com.gzzy.foodManProgram.dto.AbstractOutputDto;
import com.gzzy.foodManProgram.dto.ValueLableDto;
import com.gzzy.foodManProgram.entity.common.App;
import com.gzzy.foodManProgram.entity.common.bean.ValueLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AppService
 * @Description TODO
 * @Author wangbin
 * @Date 2018/8/14/014 19:25
 * @Version 1.0
 */
@Service
public class AppService {

    @Autowired
    private AppDao appDao;


    public AbstractOutputDto queryAppAll(AbstractInputDto<App> parmeter) {
        AbstractOutputDto abstractOutputDto=new AbstractOutputDto();

        int startnum=(parmeter.getPage()-1)*parmeter.getRows();
        int endnum=parmeter.getPage()*parmeter.getRows();

        int totalrecords=appDao.queryAppAllCount(parmeter.getData());

        int totalpages=(totalrecords + parmeter.getRows()-1)/parmeter.getRows();
        abstractOutputDto.setCurrpage(parmeter.getPage());
        abstractOutputDto.setStatus(0);
        abstractOutputDto.setMsg("请求成功");
        abstractOutputDto.setTotalpages(totalpages);
        abstractOutputDto.setTotalrecords(totalrecords);
        List<App> apps=appDao.queryAppAll(parmeter.getSidx(),parmeter.getSord(),startnum,endnum,parmeter.getData());
        abstractOutputDto.setDatalist(apps);
        return abstractOutputDto;
    }



    public void insertApp(App app) {
        appDao.insertApp(app);
    }

    public void updateApp(App app) {
        appDao.updateApp(app);
    }

    /**
     *
     * 功能描述:查询
     * @auther: wangbin
     * @date: 2018/8/22/022 17:14
     */
    public List<ValueLabel> queryAppValueLabel(ValueLableDto valueLableDto) {
        List<ValueLabel> valueLabels = appDao.queryAppValueLabel(valueLableDto);
        return valueLabels;
    }
}
