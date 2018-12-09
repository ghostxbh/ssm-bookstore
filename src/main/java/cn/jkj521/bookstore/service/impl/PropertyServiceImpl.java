package cn.jkj521.bookstore.service.impl;

import cn.jkj521.bookstore.dao.PropertyMapper;
import cn.jkj521.bookstore.entity.Property;
import cn.jkj521.bookstore.service.PropertyService;
import cn.jkj521.bookstore.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("propertyService")
public class PropertyServiceImpl implements PropertyService{
    private PropertyMapper propertyMapper;
    @Resource(name = "propertyMapper")
    public void setPropertyMapper(PropertyMapper propertyMapper) {
        this.propertyMapper = propertyMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Property property) {
        return propertyMapper.insertOne(property)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean addList(List<Property> propertyList) {
        return propertyMapper.insertList(propertyList) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Property property) {
        return propertyMapper.updateOne(property)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer[] property_id_list) {
        return propertyMapper.delete(property_id_list)>0;
    }

    @Override
    public List<Property> getList(Property property, PageUtil pageUtil) {
        return propertyMapper.select(property,pageUtil);
    }

    @Override
    public Property get(Integer property_id) {
        return propertyMapper.selectOne(property_id);
    }

    @Override
    public Integer getTotal(Property property) {
        return propertyMapper.selectTotal(property);
    }
}
