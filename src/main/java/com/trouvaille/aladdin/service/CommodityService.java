package com.trouvaille.aladdin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trouvaille.aladdin.entity.Commodity;

import java.util.List;

public interface CommodityService extends IService<Commodity> {
    boolean updateStatus (List<Long> ids , int status);
    
    
}
