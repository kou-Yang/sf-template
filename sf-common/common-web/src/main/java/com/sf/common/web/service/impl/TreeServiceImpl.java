package com.sf.common.web.service.impl;

import com.sf.common.web.constant.TreeConstant;
import com.sf.common.web.model.tree.TreeNode;
import com.sf.common.web.service.TreeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ky
 * @description
 * @date 2024-05-17 15:19
 */
@Service
public class TreeServiceImpl implements TreeService {

    @Override
    public List<TreeNode> translateTree(String parentId, List<TreeNode> allTreeNode) {
        return allTreeNode.stream()
                .filter(item -> Objects.equals(item.getParentId(), parentId))
                .peek(item -> item.setChild(translateTree(item.getId(), allTreeNode)))
                .sorted(Comparator.comparingInt(node -> (Objects.isNull(node.getOrderNum()) ? TreeConstant.DEFAULT_ORDER_NUM : node.getOrderNum())))
                .collect(Collectors.toList());
    }
}
