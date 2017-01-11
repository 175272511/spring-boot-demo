//package com.example.shiro;
//
//import com.mll.sys.rbac.remote.service.AccountService;
//import com.mll.sys.rbac.remote.vo.ResourceVo;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.config.Ini;
//import org.apache.shiro.config.Ini.Section;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.FactoryBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 借助spring {@link FactoryBean} 对apache shiro的premission进行动态创建
// *
// * @author serv
// *
// */
//public class ChainDefinitionSectionMetaSource implements
//        FactoryBean<Section> {
//
//	private AccountService accountService;
//
//	private ShiroSettings shiroSettings;
//
//	private List<ResourceVo> resourceList;
//
//	/**
//	 * 获取应用的所有未删除的资源列表、可能包含禁用的资源
//	 * @return
//	 */
//	public List<ResourceVo> getResourceList() {
//		return resourceList;
//	}
//
//	public void setAccountService(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//	public void setShiroSettings(ShiroSettings shiroSettings) {
//		this.shiroSettings = shiroSettings;
//	}
//
//	// shiro默认的链接定义
//	private String filterChainDefinitions;
//
//	/**
//	 * 通过filterChainDefinitions对默认的链接过滤定义
//	 *
//	 * @param filterChainDefinitions
//	 *            默认的接过滤定义
//	 */
//	public void setFilterChainDefinitions(String filterChainDefinitions) {
//		this.filterChainDefinitions = filterChainDefinitions;
//	}
//
//	@Override
//	public Section getObject() throws BeansException {
//		Ini ini = new Ini();
//		// 加载默认的url
//		ini.load(filterChainDefinitions);
//		Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//		// 获取appCode对应的所有的资源列表
//		resourceList = accountService.searchResourceVoListBySysCode(shiroSettings.getAppCode());
//		if(resourceList==null) resourceList = new ArrayList<ResourceVo>();
//
//		for (ResourceVo resource : resourceList) {
//			if (StringUtils.isNotEmpty(resource.getResourceMatchUrl().trim()) &&
//					StringUtils.isNotEmpty(resource.getResourceMatchPerm().trim())) {
//				section.put(resource.getResourceMatchUrl(), "perms["+resource.getResourceMatchPerm()+"]");
//			}
//		}
//
//		// 重置 /** 对应的 权限声明
//		section.put("/**",section.remove("/**"));
//
//		return section;
//	}
//
//	@Override
//	public Class<?> getObjectType() {
//		return Section.class;
//	}
//
//	@Override
//	public boolean isSingleton() {
//		return true;
//	}
//
//}
