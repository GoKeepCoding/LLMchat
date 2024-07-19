package com.jnu.myllm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jnu.myllm.domain.LUser;
import com.jnu.myllm.service.LUserService;
import com.jnu.myllm.mapper.LUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @author Nowonder
* @description 针对表【l_user】的数据库操作Service实现
* @createDate 2024-07-16 11:31:05
*/
@Service
@EnableTransactionManagement
public class LUserServiceImpl extends ServiceImpl<LUserMapper, LUser>
    implements LUserService{

}




