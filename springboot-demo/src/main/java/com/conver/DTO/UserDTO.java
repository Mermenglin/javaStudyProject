package com.conver.DTO;

import com.conver.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * 出入参封装对象
 *
 * @Author: meimengling
 * @Date: 2019/10/18 10:58
 */
@Data
public class UserDTO {

    @NotNull
    private String userName;

    @NotNull
    private String age;

    public User convertToUser() {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        User convert = userDTOConvert.convert(this);
        return convert;
    }

    public UserDTO convertFor(User user) {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
//        UserDTO convert = userDTOConvert.reverse().convert(user);
        UserDTO convert = userDTOConvert.convertFor(user);
        return convert;
    }

    private static class UserDTOConvert implements DTOConvert<UserDTO, User> {

        @Override
        public User convert(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            user.setName(userDTO.getUserName());
            return user;
        }

        @Override
        public UserDTO convertFor(User user) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setUserName(user.getName());
            return userDTO;
        }

    }

}
