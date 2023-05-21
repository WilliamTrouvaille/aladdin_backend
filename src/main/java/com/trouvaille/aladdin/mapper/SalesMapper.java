package com.trouvaille.aladdin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trouvaille.aladdin.entity.Sales;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName: F:/Study/homework/java/aladdin/aladdin
 * @package: F:/Study/homework/java/aladdin/aladdin
 * @className: SalesMapperController
 * @author: trouvaille
 * @description: SalesMapper数据访问类
 * @date: 2022-08-05 21:14:34
 * @version: 1.0
 */
@Mapper
public interface SalesMapper extends BaseMapper<Sales> {


//    @Select ("SELECT id,name,image,user_id,commodity_id,number,amount,create_time FROM shopping_cart WHERE id = #{id}")
//    Sales selectById (Long id);
}

