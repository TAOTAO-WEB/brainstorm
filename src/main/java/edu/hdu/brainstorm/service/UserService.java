package edu.hdu.brainstorm.service;

import edu.hdu.brainstorm.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-03-21 20:20:05
 */
public interface UserService {

    /**
     * 通过用户名查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    User queryByUsername(String username);

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    User queryById(String userid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(String userid);

}