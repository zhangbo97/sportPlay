package com.deyunjiaoyu.sportplay.dao;

import com.deyunjiaoyu.sportplay.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    public List<MainMenu> getMenus();
}
