package com.gtbyte.jorgeLanza.home.api

data class DonutDb(
    val batters: Batters,
    val id: String,
    val name: String,
    val ppu: Double,
    val topping: List<Topping>,
    val type: String
)

data class Topping(
    val id: String,
    val type: String
)

data class Batters(
    val batter: List<Batter>
)

data class Batter(
    val id: String,
    val type: String
)