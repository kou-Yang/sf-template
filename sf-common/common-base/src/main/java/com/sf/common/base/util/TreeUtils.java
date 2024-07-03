package com.sf.common.base.util;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ky
 * @description
 * @date 2024-05-04 23:48
 */
public class TreeUtils {

    private static final String DEFAULT_ID_KEY = "id";
    private static final String DEFAULT_PARENT_ID_KEY = "parentId";
    private static final String DEFAULT_WEIGHT_KEY = "orderNum";
    private static final String DEFAULT_NAME_KEY = "name";
    private static final String DEFAULT_CHILDREN_KEY = "children";
    private static final Integer DEFAULT_DEEP = null;
    private static final String DEFAULT_ROOT_ID = "0";

    /**
     * 默认配置
     */
    private static final TreeNodeConfig DEFAULT_CONFIG = createConfig(DEFAULT_ID_KEY, DEFAULT_PARENT_ID_KEY, DEFAULT_WEIGHT_KEY, DEFAULT_NAME_KEY, DEFAULT_CHILDREN_KEY, DEFAULT_DEEP);

    public static <T, E> List<Tree<E>> translate(List<T> list, E rootId, TreeNodeConfig config) {
        if (CollectionUtils.isNotEmpty(list)) {
            return TreeUtil.build(list, rootId, config, (object, tree) -> {
                Map<String, T> map = BeanMap.create(object);
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    tree.putExtra(entry.getKey(), entry.getValue());
                }
            });
        }
        return Collections.emptyList();
    }

    public static <T, E> List<Tree<E>> translate(List<T> list, E rootId) {
        return translate(list, rootId, DEFAULT_CONFIG);
    }

    public static <T> List<Tree<String>> translate(List<T> list) {
        return translate(list, DEFAULT_ROOT_ID);
    }

    public static TreeNodeConfig createConfig(String idKey, String parentIdKey, String weighKey, String nameKey, String childrenKey, Integer deep) {
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey(StringUtils.isBlank(idKey) ? DEFAULT_ID_KEY : idKey)
              .setParentIdKey(StringUtils.isBlank(parentIdKey) ? DEFAULT_PARENT_ID_KEY : parentIdKey)
              .setWeightKey(StringUtils.isBlank(weighKey) ? DEFAULT_WEIGHT_KEY : weighKey)
              .setNameKey(StringUtils.isBlank(nameKey) ? DEFAULT_NAME_KEY : nameKey)
              .setChildrenKey(StringUtils.isBlank(childrenKey) ? DEFAULT_CHILDREN_KEY : childrenKey)
              .setDeep(Objects.isNull(deep) ? DEFAULT_DEEP : deep);
        return config;
    }
}

