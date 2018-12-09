package cn.jkj521.bookstore.service;

import cn.jkj521.bookstore.entity.Category;
import cn.jkj521.bookstore.util.PageUtil;

import java.util.List;

public interface CategoryService {
    boolean add(Category category);

    boolean update(Category category);

    boolean delete(Integer[] category_id_list);

    List<Category> getList(String category_name, PageUtil pageUtil);

    Category get(Integer category_id);

    Integer getTotal(String category_name);
}
