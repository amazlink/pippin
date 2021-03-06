package io.github.rothschil.oauth2.domain.mapper;


import io.github.rothschil.base.persistence.mybatis.mapper.BaseMapper;
import io.github.rothschil.oauth2.domain.entity.OauthUser;

import java.util.List;

/**
 * @author WCNGS@QQ.COM
 * @date 20/12/2 16:58
 * @since 1.0.0
 */
public interface OauthUserMapper extends BaseMapper<OauthUser, Long> {

    @Override
    int deleteByPrimaryKey(Long uId);

    @Override
    OauthUser selectByPrimaryKey(Long uId);

    @Override
    int updateByPrimaryKeySelective(OauthUser record);

    @Override
    int updateByPrimaryKey(OauthUser record);

    List<OauthUser> select(OauthUser record);
}