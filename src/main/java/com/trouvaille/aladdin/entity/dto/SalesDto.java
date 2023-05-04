/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.entity.dto
 * @className: SalesDto
 * @author: trouvaille_william
 * @description: 顾客订单拓展类
 * @date: 2022/8/6 14:41
 * @version: 1.0
 */
package com.trouvaille.aladdin.entity.dto;

import com.trouvaille.aladdin.entity.Commodity;
import com.trouvaille.aladdin.entity.Sales;
import lombok.Data;

import java.util.List;

@Data
public class SalesDto extends Sales {
    
    
    private String userName;
    
    
    private List<Commodity> commodityList;
    
    
    private String addressDetail;
    
    private Integer sumNum;
    
    
}
