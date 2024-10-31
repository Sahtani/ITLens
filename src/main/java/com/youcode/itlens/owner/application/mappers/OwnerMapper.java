package com.youcode.itlens.owner.application.mappers;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import com.youcode.itlens.owner.domain.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends GenericMapper<Owner, OwnerRequestDTO, OwnerResponseDTO> {

}