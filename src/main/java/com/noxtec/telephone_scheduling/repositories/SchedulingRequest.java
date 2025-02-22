package com.noxtec.telephone_scheduling.repositories;

public record SchedulingRequest(
        Long id,
        String name,
        String email,
        String cellphone,
        String telephone,
        Boolean favorite,
        Boolean active
) {
}