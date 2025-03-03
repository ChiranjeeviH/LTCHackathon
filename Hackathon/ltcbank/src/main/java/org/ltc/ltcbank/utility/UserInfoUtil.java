package org.ltc.ltcbank.utility;

import org.ltc.ltcbank.dto.UserDTO;
import org.ltc.ltcbank.entity.User;

public class UserInfoUtil {

    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setFullName(user.getFullName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setInitialBalance(user.getInitialBalance());
        return userDTO;
    }
}
