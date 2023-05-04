package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.SalesDetail;
import com.trouvaille.aladdin.mapper.SalesDetailMapper;
import com.trouvaille.aladdin.service.SalesDetailService;
import org.springframework.stereotype.Service;

/**
 * (SalesDetail)表服务实现类
 *
 * @author trouvaille
 * @since 2023-04-22 17:47:04
 */
@Service ("salesDetailService")
public class SalesDetailServiceImpl extends ServiceImpl<SalesDetailMapper, SalesDetail> implements SalesDetailService {

}

