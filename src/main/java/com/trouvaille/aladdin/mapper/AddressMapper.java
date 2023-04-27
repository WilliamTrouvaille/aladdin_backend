package com.trouvaille.aladdin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trouvaille.aladdin.entity.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地址信息表(Address)表数据库访问层
 *
 * @author trouvaille
 * @since 2023-04-27 14:07:07
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}

