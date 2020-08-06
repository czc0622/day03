package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.DeviceMapper;
import com.xiaoshu.dao.DtypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Device;
import com.xiaoshu.entity.Dtype;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;
import com.xiaoshu.entity.bing;

@Service
public class DeviceService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	private DeviceMapper dm;
	
	@Autowired
	private DtypeMapper tm;
	// 查询所有
	public List<User> findUser(User t) throws Exception {
		return userMapper.select(t);
	};

	// 数量
	public int countUser(User t) throws Exception {
		return userMapper.selectCount(t);
	};

	// 通过ID查询
	public User findOneUser(Integer id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	};

	// 新增
	public void addDevice(Device device) throws Exception {
		dm.insert(device);
	};

	// 修改
	public void updateDevice(Device device) throws Exception {
		dm.updateByPrimaryKey(device);
	};

	// 删除
	public void deleteDevice(Integer id) throws Exception {
		dm.deleteByPrimaryKey(id);
	};

	// 登录
	public User loginUser(User user) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPasswordEqualTo(user.getPassword()).andUsernameEqualTo(user.getUsername());
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public Device existDeviceByName(String dname) throws Exception {
		List<Device> dList = dm.findByName(dname);
		return dList.isEmpty()?null:dList.get(0);
	};

	// 通过角色判断是否存在
	public User existUserWithRoleId(Integer roleId) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	}

	public PageInfo<Device> findDevicePage(Device device, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Device> dList = dm.findAll(device);
		PageInfo<Device> pageInfo = new PageInfo<Device>(dList);
		return pageInfo;
	}

	public List<Dtype> findAllDtype() {
		// TODO Auto-generated method stub
		return tm.selectAll();
	}

	public List<bing> echartsDeviceAll() {
		// TODO Auto-generated method stub
		return dm.echartsDeviceAll();
	}


}
