package test_s2dao.service;

import java.util.ArrayList;

import test_s2dao.entity.BlogEntity;

public interface H2Service {
	public ArrayList<BlogEntity> settei(Integer id);
	public int setteiMId(String a);

}
