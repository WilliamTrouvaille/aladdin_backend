/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.service.impl
 * @className: StoreServiceImpl
 * @author: trouvaille_william
 * @description: 门店ServiceImpl
 * @date: 2022/7/27 20:20
 * @version: 1.0
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Store;
import com.trouvaille.aladdin.mapper.StoreMapper;
import com.trouvaille.aladdin.service.StoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
}
