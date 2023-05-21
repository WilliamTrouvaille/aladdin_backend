/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.service.impl
 * @className: CommodityServiceImpl
 * @author: trouvaille_william
 * @description: 商品ServiceImpl
 * @date: 2022/7/27 15:38
 * @version: 1.0
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Commodity;
import com.trouvaille.aladdin.mapper.CommodityMapper;
import com.trouvaille.aladdin.service.CommodityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
    @Override
    public boolean updateStatus (List<Long> ids , int status) {
        LambdaUpdateWrapper<Commodity> luw = new LambdaUpdateWrapper<>();
        luw.in(Commodity::getId , ids);
        luw.eq(Commodity::getStatus , 1 - status);
        luw.set(Commodity::getStatus , status);
        return this.update(luw);
    }
    
    
}
