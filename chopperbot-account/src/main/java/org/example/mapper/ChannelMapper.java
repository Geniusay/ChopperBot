package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Account;
import org.example.pojo.AccountChannel;
import org.example.pojo.Channel;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2023/11/17 8:19
 */

public interface ChannelMapper extends BaseMapper<Channel> {

    @Select("select a.* from account a join account_channel ac on a.Id = ac.account_id where ac.channel_id = #{id}")
    List<Account> getChannelAccounts(Long channelId);

    @Insert("insert into account_channel (channel_id,account_id) values (#{channelId},#{accountId})")
    int bindChannel(AccountChannel accountChannel);

    @Delete("delete from account_channel where account_id = #{accountId} and channel_id = #{channelId}")
    int unBindChannel(String accountId,String channelId);

    @Select("select * from account_channel")
    List<AccountChannel> queryAccountChannel();

}
