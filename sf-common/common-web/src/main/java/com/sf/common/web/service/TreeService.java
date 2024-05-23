package com.sf.common.web.service;

import com.sf.common.web.model.tree.TreeNode;

import java.util.List;

/**
 * @author ky
 * @description
 * @date 2024-05-17 15:18
 */
public interface TreeService {

    /**
     * 转换为树型结构
     * @param parentId
     * @param allTreeNode
     * @return
     */
    List<TreeNode> translateTree(String parentId, List<TreeNode> allTreeNode);
}
