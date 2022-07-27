/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.service.impl
 * @className: CommodityServiceImpl
 * @author: Eric
 * @description: 商品ServiceImpl
 * @date: 2022/7/27 15:38
 * @version: 1.0
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.mapper.CommodityMapper;
import com.trouvaille.aladdin.service.CommodityService;
import org.springframework.stereotype.Service;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
}
