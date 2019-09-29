package com.yb.medical_online.fun.service;

import com.yb.medical_online.fun.bean.test.TestBean;
import com.yb.medical_online.fun.bean.test.TestForm;
import com.yb.medical_online.fun.dao.ITestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl {

    @Autowired
    private ITestDao idao;

    public List<TestBean> getTestList(TestForm form) throws Exception{
        return idao.getTestList(form);
    }
}
