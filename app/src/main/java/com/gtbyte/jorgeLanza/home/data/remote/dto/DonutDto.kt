package com.gtbyte.jorgeLanza.home.data.remote.dto

data class DonutDto(
    val batters: BattersDto,
    val id: String,
    val name: String,
    val ppu: Double,
    val topping: List<ToppingDto>,
    val type: String
)