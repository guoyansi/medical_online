package com.yb.medical_online.fun.dao;

import com.yb.medical_online.fun.bean.test.TestBean;
import com.yb.medical_online.fun.bean.test.TestForm;

import java.util.List;

public interface ITestDao {
    List<TestBean> getTestList(TestForm form) throws Exception;
}
