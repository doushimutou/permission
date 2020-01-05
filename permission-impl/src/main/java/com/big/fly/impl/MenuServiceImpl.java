package com.big.fly.impl;

import com.big.fly.domain.MenuParamDTO;
import com.big.fly.domain.MenuResultDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.mapper.dao.SysMenuMapper;
import com.big.fly.mapper.entity.SysMenu;
import com.big.fly.mapper.entity.SysMenuExample;
import com.big.fly.permission.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 * Author ayt  on
 */
@Component
public class MenuServiceImpl implements MenuService {
	private Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);


	@Resource
	SysMenuMapper sysMenuMapper;

	@Override
	public Pagination<MenuResultDTO> getMenuList(MenuParamDTO menuParamDTO) {
		SysMenuExample example = new SysMenuExample();
		SysMenuExample.Criteria criteria = example.createCriteria();
		if (menuParamDTO.getName() != null) {
			criteria.andNameLike("%" + menuParamDTO.getName() + "%");
		}
		if (menuParamDTO.getStartTime() != null && menuParamDTO.getEndTime() != null) {
			criteria.andCreateTimeBetween(menuParamDTO.getStartTime(), menuParamDTO.getEndTime());
		}
		List<SysMenu> list = sysMenuMapper.selectByExample(example);
		List<MenuResultDTO> endTree = menuToTree(list);
		Pagination<MenuResultDTO> sysMenuPagination = new Pagination<>();
		sysMenuPagination.setList(endTree);
		return sysMenuPagination;
	}

	private List<MenuResultDTO> menuToTree(List<SysMenu> list) {
		List<MenuResultDTO> treeList = new ArrayList<>();
		List<MenuResultDTO> endTree = new ArrayList<>();
		Set<Integer> ids = new HashSet<>();
		//把treeList,sysmenu转成menuResultDTO
		list.forEach(sysMenu -> {
			MenuResultDTO menuResultDTO = new MenuResultDTO();
			BeanUtils.copyProperties(sysMenu, menuResultDTO);
			treeList.add(menuResultDTO);
		});
		treeList.forEach(menuResultDTO -> {
			//把顶层菜单添加到结果树中
			if (menuResultDTO.getParentId() == 0) {
				endTree.add(menuResultDTO);
			}
			//遍历所有的菜单
			treeList.forEach(menuResultDTO1 -> {
				//如果父级菜单等于id,将给这个menuRusultDTO添加子菜单
				if (menuResultDTO1.getParentId().equals(menuResultDTO.getId())) {
					if (menuResultDTO.getChildren() == null) {
						menuResultDTO.setChildren(new ArrayList<>());
					}
					menuResultDTO.getChildren().add(menuResultDTO1);
					ids.add(menuResultDTO1.getId());
				}
			});
			logger.info("menuResultDTO:{}", menuResultDTO);
		});
		if (endTree.size() == 0) {
			treeList.forEach(menuResultDTO -> {
				if (!ids.contains(menuResultDTO.getId())) {
					endTree.add(menuResultDTO);
				}
			});
		}
		return endTree;
	}


	@Override
	public Boolean add(MenuParamDTO menuParamDTO) {
		SysMenu sysMenu = new SysMenu();
		BeanUtils.copyProperties(menuParamDTO, sysMenu);
		if (sysMenu.getCreateTime() == null) {
			sysMenu.setCreateTime(new Date());
		}
		return sysMenuMapper.insertSelective(sysMenu) > 0;
	}

	@Override
	public Boolean update(MenuParamDTO menuParamDTO) {
		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuParamDTO.getId());
		BeanUtils.copyProperties(menuParamDTO, sysMenu);
		return sysMenuMapper.updateByPrimaryKey(sysMenu) > 0;
	}

	@Override
	public Boolean delete(Integer id) {
		return sysMenuMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<Map<String, Object>> getMenuTree(List<MenuResultDTO> list) {
		MenuParamDTO menuParamDTO = new MenuParamDTO();
		Pagination<MenuResultDTO> pagination = getMenuList(menuParamDTO);
		List<MenuResultDTO> listTree = pagination.getList();
		return getMenuTreeTo(listTree);

	}

	private List<Map<String, Object>> getMenuTreeTo(List<MenuResultDTO> list) {
		List<Map<String, Object>> menuTreeList = new ArrayList<>();
		list.forEach(menuResultDTO -> {
			Map<String, Object> map = new HashMap<>();
			map.put("id", menuResultDTO.getId());
			map.put("label", menuResultDTO.getName());
			if (menuResultDTO.getChildren() != null && menuResultDTO.getChildren().size() > 0) {
				map.put("children", getMenuTreeTo(menuResultDTO.getChildren()));
			}

			menuTreeList.add(map);
		});

		return menuTreeList;
	}
}
