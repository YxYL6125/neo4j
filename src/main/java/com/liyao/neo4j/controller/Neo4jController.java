package com.liyao.neo4j.controller;

import com.liyao.neo4j.service.CreateAllNodeService;
import com.liyao.neo4j.service.CreateAllRelationShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class Neo4jController {
    @Autowired
    CreateAllNodeService createAllNodeService;
    @Autowired
    CreateAllRelationShipService createAllRelationShipService;

    /**
     * 初始化所有关系并跳转首页
     * @param map
     * @param model
     * @return
     */
    @RequestMapping("/neo4j/AllRelation")
    public String toNeo4jAllRelation(Map<String,String> map, Model model) {
        createAllNodeService.InitNode();
        createAllRelationShipService.InitRelationShip();
        map.put("message", "初始化成功");
        model.addAttribute("msg", "所有数据展示");
        return "index";
    }
}
