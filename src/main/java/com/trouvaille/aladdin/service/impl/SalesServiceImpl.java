package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Sales;
import com.trouvaille.aladdin.mapper.SalesMapper;
import com.trouvaille.aladdin.service.SalesService;
import org.springframework.stereotype.Service;

/**
 * (Sales)表服务实现类
 *
 * @author trouvaille
 * @since 2022-07-30 11:47:10
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {

}

