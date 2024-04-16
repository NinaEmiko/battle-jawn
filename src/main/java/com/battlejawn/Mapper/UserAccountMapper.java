package com.battlejawn.Mapper;

import com.battlejawn.DTO.UserAccountDTO;
import com.battlejawn.Entities.UserAccount;
import com.battlejawn.DTO.SignUpDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountDTO toUserAccountDTO(UserAccount userAccount);

    @Mapping(target = "password", ignore = true)
    UserAccount signUpToUserAccount(SignUpDTO signUpDTO);

}