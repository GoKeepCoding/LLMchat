package com.jnu.myllm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnu.myllm.domain.LUser;
import com.jnu.myllm.mapper.LUserMapper;
import com.jnu.myllm.service.LUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @author Nowonder
* @description 针对表【l_user】的数据库操作Service实现
* @createDate 2024-07-20 00:00:53
*/
@Service
@EnableTransactionManagement
public class LUserServiceImpl extends ServiceImpl<LUserMapper, LUser>
    implements LUserService{

}




