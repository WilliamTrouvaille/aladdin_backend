package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Address;
import com.trouvaille.aladdin.mapper.AddressMapper;
import com.trouvaille.aladdin.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * 地址信息表(Address)表服务实现类
 *
 * @author trouvaille
 * @since 2023-04-27 14:07:07
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}

