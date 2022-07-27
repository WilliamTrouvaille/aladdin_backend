/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.service.impl
 * @className: SupplierServiceImpl
 * @author: Eric
 * @description: 供应商ServiceImpl
 * @date: 2022/7/27 21:23
 * @version: 1.0
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Supplier;
import com.trouvaille.aladdin.mapper.SupplierMapper;
import com.trouvaille.aladdin.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {
}
