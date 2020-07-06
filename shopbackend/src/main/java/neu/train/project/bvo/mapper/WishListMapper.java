package neu.train.project.bvo.mapper;

import java.util.List;
import neu.train.project.bvo.domain.WishList;
import neu.train.project.bvo.domain.WishListExample;
import org.apache.ibatis.annotations.Param;

public interface WishListMapper {
    long countByExample(WishListExample example);

    int deleteByExample(WishListExample example);

    int deleteByPrimaryKey(Integer witId);

    int insert(WishList record);

    int insertSelective(WishList record);

    List<WishList> selectByExample(WishListExample example);

    WishList selectByPrimaryKey(Integer witId);

    int updateByExampleSelective(@Param("record") WishList record, @Param("example") WishListExample example);

    int updateByExample(@Param("record") WishList record, @Param("example") WishListExample example);

    int updateByPrimaryKeySelective(WishList record);

    int updateByPrimaryKey(WishList record);
}