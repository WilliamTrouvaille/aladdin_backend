/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.entity.dto
 * @className: CommodityDto
 * @author: trouvaille_william
 * @description: 商品数据传输对象
 * @date: 2023/5/1 19:58
 * @version: 1.0
 */
package com.trouvaille.aladdin.entity.dto;

import com.trouvaille.aladdin.entity.Commodity;
import lombok.Data;

@Data
public class CommodityDto extends Commodity {

    // 商品类别名称
    private String categoryName;
}
