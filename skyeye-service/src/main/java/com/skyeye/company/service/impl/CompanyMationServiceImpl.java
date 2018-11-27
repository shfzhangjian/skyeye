package com.skyeye.company.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skyeye.company.dao.CompanyMationDao;
import com.skyeye.company.service.CompanyMationService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;

@Service
public class CompanyMationServiceImpl implements CompanyMationService{
	
	@Autowired
	private CompanyMationDao companyMationDao;

	/**
	 * 
	     * @Title: queryCompanyMationList
	     * @Description: 获取公司信息列表
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryCompanyMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = companyMationDao.queryCompanyMationList(map);
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}

	/**
	 * 
	     * @Title: insertCompanyMation
	     * @Description: 添加公司信息信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void insertCompanyMation(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = companyMationDao.queryCompanyMationByName(map);
		if(bean == null){
			Map<String, Object> user = inputObject.getLogParams();
			map.put("id", ToolUtil.getSurFaceId());
			map.put("createId", user.get("id"));
			map.put("createTime", ToolUtil.getTimeAndToString());
			companyMationDao.insertCompanyMation(map);
		}else{
			outputObject.setreturnMessage("该公司信息已注册，请确认。");
		}
	}

	/**
	 * 
	     * @Title: deleteCompanyMationById
	     * @Description: 删除公司信息信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void deleteCompanyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = companyMationDao.queryCompanyMationById(map);
		if(Integer.parseInt(bean.get("childsNum").toString()) == 0){
			companyMationDao.deleteCompanyMationById(map);
		}else{
			outputObject.setreturnMessage("该公司下存在子公司，无法直接删除。");
		}
	}

	/**
	 * 
	     * @Title: queryCompanyMationToEditById
	     * @Description: 编辑公司信息信息时进行回显
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryCompanyMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = companyMationDao.queryCompanyMationToEditById(map);
		outputObject.setBean(bean);
		outputObject.settotal(1);
	}

	/**
	 * 
	     * @Title: editCompanyMationById
	     * @Description: 编辑公司信息信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void editCompanyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		Map<String, Object> bean = companyMationDao.queryCompanyMationByNameAndId(map);
		if(bean == null){
			companyMationDao.editCompanyMationById(map);
		}else{
			outputObject.setreturnMessage("该公司信息已注册，请确认。");
		}
	}

	/**
	 * 
	     * @Title: queryOverAllCompanyMationList
	     * @Description: 获取总公司信息列表
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@Override
	public void queryOverAllCompanyMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
		Map<String, Object> map = inputObject.getParams();
		List<Map<String, Object>> beans = companyMationDao.queryOverAllCompanyMationList(map);
		if(!beans.isEmpty()){
			outputObject.setBeans(beans);
			outputObject.settotal(beans.size());
		}
	}
	
}
