package com.rago.domain.usecases

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<R> {
    fun execute(): Flow<R>
}