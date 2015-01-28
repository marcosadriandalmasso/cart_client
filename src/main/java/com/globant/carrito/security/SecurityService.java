package com.globant.carrito.security;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;

@RestController
public class SecurityService {

	@RequestMapping(value = "/service/login", method=RequestMethod.POST)
	@ResponseBody
	public StatusDto login(@RequestBody LoginDto dto) {
		return new StatusDto(dto.getUsername().equals("bart")
				&& dto.getPassword().equals("simpson"));
	}
}
