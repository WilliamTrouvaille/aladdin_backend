package com.trouvaille.aladdin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trouvaille.aladdin.entity.Sales;

public interface SalesService extends IService<Sales> {
    
    boolean submit (Sales sales);
    
    boolean again (Long salesId);
    
}

