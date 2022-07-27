/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: CommodityController
 * @author: Eric
 * @description: Commodity控制类
 * @date: 2022/7/27 15:45
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/page")
    public R<Page<Commodity>> page(int page, int pageSize, String name) {
        log.info("商品分页查询==>当前页数:{},页面大小:{},查询条件:{}", page, pageSize, name);

        Page<Commodity> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Commodity> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Commodity::getName, name);
        lqw.orderByDesc(Commodity::getUpdateTime);
        commodityService.page(pageInfo, lqw);
        return R.success(pageInfo);

    }

}
