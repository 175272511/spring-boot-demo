//package com.jlings.emall.shiro;
//
//import com.jlings.mob.remote.service.AccountService;
//import com.jlings.mob.remote.vo.RbacPermissionVo;
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
//    private AccountService accountService;
//
//    public void setAccountService(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//
//    // shiro默认的链接定义
//    private String filterChainDefinitions;
//
//    /**
//     * 通过filterChainDefinitions对默认的链接过滤定义
//     *
//     * @param filterChainDefinitions
//     *            默认的接过滤定义
//     */
//    public void setFilterChainDefinitions(String filterChainDefinitions) {
//        this.filterChainDefinitions = filterChainDefinitions;
//    }
//
//    @Override
//    public Section getObject() throws BeansException {
//        Ini ini = new Ini();
//        // 加载默认的url
//        ini.load(filterChainDefinitions);
//        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//        // 获取appCode对应的所有的资源列表
//        List<RbacPermissionVo> permissionVos = accountService.findPermissions();
//        if(permissionVos==null) permissionVos = new ArrayList<RbacPermissionVo>();
//
//        for (RbacPermissionVo resource : permissionVos) {
//            if (StringUtils.isNotEmpty(resource.getPermissionUrl().trim()) &&
//                    StringUtils.isNotEmpty(resource.getPermissionSign().trim())) {
//                section.put(resource.getPermissionUrl(), "perms["+resource.getPermissionSign()+"]");
//            }
//        }
//
//        // 重置 /** 对应的 权限声明
//        section.put("/**",section.remove("/**"));
//
//        return section;
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return Section.class;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return true;
//    }
//
//}
