package com.jnu.myllm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnu.myllm.domain.LUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Nowonder
* @description 针对表【l_user】的数据库操作Mapper
* @createDate 2024-07-20 00:00:53
* @Entity com.jnu.myllm.domain.LUser
*/
@Mapper
public interface LUserMapper extends BaseMapper<LUser> {

}




