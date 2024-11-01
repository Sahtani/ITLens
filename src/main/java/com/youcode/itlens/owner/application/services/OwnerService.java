package com.youcode.itlens.owner.application.services;

import com.youcode.itlens.common.services.GenericService;
import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import com.youcode.itlens.owner.domain.Owner;

public interface OwnerService extends GenericService<Owner, Long , OwnerRequestDTO, OwnerResponseDTO> {
}
