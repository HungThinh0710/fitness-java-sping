package com.minhchieu.mapstruct.mapper;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.model.Account;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(
   componentModel = "spring"
)
@Component
public interface MapStructMapper {

    AccountGetDTO accountToAccountGetDTO(Account account);
    Account accountToAccountPostDTO(AccountPostDTO accountPostDTO);
}
