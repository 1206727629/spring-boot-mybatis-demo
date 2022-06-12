package xyz.ibenben.zhongdian.system.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.ibenben.zhongdian.system.dao.TaskDao;
import xyz.ibenben.zhongdian.system.entity.Task;
import xyz.ibenben.zhongdian.system.entity.User;
import xyz.ibenben.zhongdian.system.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private TaskDao taskDao;
	
	@Transactional
	public void saveUser(User user){

	}
}
